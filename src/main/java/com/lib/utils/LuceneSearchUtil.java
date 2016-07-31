package com.lib.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.BytesRef;
import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.dictionary.CoreSynonymDictionary;
import com.hankcs.lucene.HanLPAnalyzer;
import com.hankcs.lucene.HanLPTokenizer;
import com.lib.entity.FileInfo;
/**
 * 搜索索引 Lucene 5.5+
 * 
 * @author Administrator
 * 
 */
public class LuceneSearchUtil {
	/**
	 * 根据索引搜索doc
	 * @param text
	 * @param pageNo
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	static Object oldBooleanQuery=null;
	public static List<Map<String, String>> indexDocSearch(FileInfo doc, Integer pageNo, List<Long> classIds,int flag){
		// 保存索引文件的地方
		Directory directory=null;
		// IndexReader reader=DirectoryReader
		DirectoryReader ireader=null;
		// 创建 IndexSearcher对象，相比IndexWriter对象，这个参数就要提供一个索引的目录就行了
		IndexSearcher indexSearch = null;
		//查询到doc
		List<Map<String, String>> listStr = new ArrayList<Map<String, String>>();
		try {
			directory = FSDirectory.open(new File("filePath").toPath());
			ireader = DirectoryReader.open(directory);
			indexSearch = new IndexSearcher(ireader);
		// 分词器
		Analyzer analyzer =  new HanLPAnalyzer() {
			@Override
			protected TokenStreamComponents createComponents(String arg0) {
				Tokenizer tokenizer = new HanLPTokenizer(HanLP.newSegment().enableIndexMode(true).enableJapaneseNameRecognize(true)
			               .enableIndexMode(true).enableNameRecognize(true).enablePlaceRecognize(true), null, false);
				return new TokenStreamComponents(tokenizer);
			}
		};
		// 多个条件组合查询
		BooleanQuery booleanQuery = new BooleanQuery();
		if(flag==1)
		booleanQuery.add((BooleanQuery)oldBooleanQuery,BooleanClause.Occur.MUST);
		else{
			oldBooleanQuery=null;
		}
		// 查询条件一 字段查询
		Query queryText = null;
		/*if (doc.getDocName() != null && !"".equals(doc.getDocName())) {
			
			String[] fields = { "docName", "docText", "docBrief" };
			Map<String, Float> boost = new HashMap<String, Float>();
			boost.put("docName", 3.0f);
			boost.put("docBrief", 2.0f);
			boost.put("docText", 1.0f);
			// 创建QueryParser对象,第一个表示搜索Field的字段,第二个表示搜索使用分词器
			QueryParser queryParser = new MultiFieldQueryParser(fields, analyzer, boost);
			// 生成Query对象
			queryText = queryParser.parse(doc.getDocName());
			booleanQuery.add(queryText, BooleanClause.Occur.MUST);
		}
		// 查询条件二日期
		Date sDate = doc.getDocUpTime();
		Date eDate = doc.getDocModTime();
		if ((sDate == null || "".equals(sDate)) && (eDate != null && !"".equals(eDate))) {
			Calendar calendar = Calendar.getInstance();
			calendar.set(1900, 0, 1);
			sDate = calendar.getTime();
		}
		// 若只有起始值结束值默认为当天
		if ((sDate != null && !"".equals(sDate)) && (eDate == null || "".equals(eDate))) {
			eDate = new Date();
		}
		if ((sDate != null && !"".equals(sDate)) && (eDate != null || !"".equals(eDate))) {

			// Lucene日期转换格式不准，改用format格式
			// sDateStr=DateTools.dateToString(sDate,
			// DateTools.Resolution.MINUTE);
			// eDateStr=DateTools.dateToString(eDate,
			// DateTools.Resolution.MINUTE);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			BytesRef sDateStr = new BytesRef(sdf.format(sDate));
			BytesRef eDateStr = new BytesRef(sdf.format(eDate));

			// 时间范围查询
			Query queryUpTime = new TermRangeQuery("docUpTime", sDateStr, eDateStr, true, true);
			Query queryDocTime = new TermRangeQuery("docModTime", sDateStr, eDateStr, true, true);
			booleanQuery.add(queryUpTime, BooleanClause.Occur.MUST);
			booleanQuery.add(queryDocTime, BooleanClause.Occur.MUST);
		}
		// 查询条件三分类查询
		for (Long id : classIds) {
			TermQuery termQuery = new TermQuery(new Term("docClass", id + ""));
			booleanQuery.add(termQuery, BooleanClause.Occur.SHOULD);
		}
		// 查询条件四类型查询
		if (doc.getDocExt() != null && !"".equals(doc.getDocExt())) {
			List<String> typeList = null;
			if (doc.getDocExt().equals("office")) {
				typeList = Const.officeList;
			}
			if (doc.getDocExt().equals("video")) {
				typeList = Const.videoList;
			}
			if (doc.getDocExt().equals("img")) {
				typeList = Const.imgList;
			}
			for (String type : typeList) {
				TermQuery termQuery = new TermQuery(new Term("docExt", type));
				booleanQuery.add(termQuery, BooleanClause.Occur.SHOULD);
			}
		}*/
		oldBooleanQuery=booleanQuery;
		// 搜索结果 TopDocs里面有scoreDocs[]数组，里面保存着索引值
		TopDocs hits = indexSearch.search(booleanQuery, 100000);
		// hits.totalHits表示一共搜到多少个
		System.out.println("找到了"+hits.totalHits+"个");
		// 循环hits.scoreDocs数据，并使用indexSearch.doc方法把Document还原，再拿出对应的字段的值
		ScoreDoc[] scoreDoc = hits.scoreDocs;
		//listStr=null;
		for (int i = pageNo * 12, j = 0; i < scoreDoc.length && j < 12; i++, j++) {
			Map<String, String> map = new HashMap<String, String>();
			int docId = scoreDoc[i].doc;
			Document nDoc = indexSearch.doc(docId);
			map.put("docId", nDoc.get("docId"));
			
			String str = null;
			if (nDoc.get("docName") != null && !"".equals(nDoc.get("docName")))
				str = displayHtmlHighlight(queryText, analyzer, "docName", nDoc.get("docName"), 30);
			map.put("docName", str);
			
			str = null;
			if (nDoc.get("docBrief")!= null && !"".equals(nDoc.get("docBrief")))
				str = displayHtmlHighlight(queryText, analyzer, "docBrief", nDoc.get("docBrief"), 30);
			map.put("docBrief", str);
			
			str = null;
			if (nDoc.get("docText") != null&& !"".equals(nDoc.get("docText"))) {
				str = displayHtmlHighlight(queryText, analyzer, "docText", nDoc.get("docText"), 30);
			}
			map.put("docText", str);
			map.put("len", scoreDoc.length + "");
			listStr.add(map);
		}
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ireader != null) {
					ireader.close();
				}
				if (directory != null) {
					directory.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return listStr;
	
	}

	/**
	 * 根据索引搜索docs
	 * 
	 * @param text
	 * @param pageNo
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 * @throws InvalidTokenOffsetsException 
	 */
	public static List<Map<String, String>> indexDocsSearch(FileInfo docs, Integer pageNo) {
		// 保存索引文件的地方
		Directory directory = null;
		// IndexReader reader=DirectoryReader
		DirectoryReader ireader = null;
		// 创建 IndexSearcher对象，相比IndexWriter对象，这个参数就要提供一个索引的目录就行了
		IndexSearcher indexSearch = null;
		// 查询到doc
		List<Map<String, String>> listStr = new ArrayList<Map<String, String>>();
		try {
			directory = FSDirectory.open(new File("filePath").toPath());
			// IndexReader reader=DirectoryReader
			ireader = DirectoryReader.open(directory);
			// 创建 IndexSearcher对象，相比IndexWriter对象，这个参数就要提供一个索引的目录就行了
			indexSearch = new IndexSearcher(ireader);
			// 分词器
			Analyzer analyzer = new HanLPAnalyzer() {
				@Override
				protected TokenStreamComponents createComponents(String arg0) {
					Tokenizer tokenizer = new HanLPTokenizer(
							HanLP.newSegment().enableIndexMode(true).enableJapaneseNameRecognize(true)
									.enableIndexMode(true).enableNameRecognize(true).enablePlaceRecognize(true),
							null, false);
					return new TokenStreamComponents(tokenizer);
				}
			};
			// 查询条件一 字段查询
			Query queryText = null;
			/*if (docs.getDocsName() != null && !"".equals(docs.getDocsName())) {
				String[] fields = { "docsName", "docsBrief" };
				Map<String, Float> boost = new HashMap<String, Float>();
				boost.put("docsName", 2.0f);
				boost.put("docsBrief", 1.0f);
				// 创建QueryParser对象,第一个表示搜索Field的字段,第二个表示搜索使用分词器
				QueryParser queryParser = new MultiFieldQueryParser(fields, analyzer, boost);
				// 生成Query对象
				queryText = queryParser.parse(docs.getDocsName());

			}*/
			// 搜索结果 TopDocs里面有scoreDocs[]数组，里面保存着索引值
			TopDocs hits = indexSearch.search(queryText, 100000);
			// hits.totalHits表示一共搜到多少个
			
			ScoreDoc[] scoreDoc = hits.scoreDocs;
			//System.out.println("找到了"+scoreDoc.length+"个");
			for (int i = pageNo * 12, j = 0; i < scoreDoc.length && j < 12; i++, j++) {
				Map<String, String> map = new HashMap<String, String>();
				int docId = scoreDoc[i].doc;
				
				Document nDoc = indexSearch.doc(docId);
				map.put("docsId", nDoc.get("docsId"));
				
				String str = null;
				if (nDoc.get("docsName")!=null)
					str = displayHtmlHighlight(queryText, analyzer, "docsName", nDoc.get("docsName"), 100);
				map.put("docsName", str);
				
				
				str = null;
				if (nDoc.get("docsBrief")!=null) {
					str = displayHtmlHighlight(queryText, analyzer, "docsBrief", nDoc.get("docsBrief"), 100);
				}
				map.put("docsBrief", str);
				
				map.put("len", scoreDoc.length + "");
				listStr.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ireader != null) {
					ireader.close();
				}
				if (directory != null) {
					directory.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return listStr;
	}
	/**
	 * 获取主题句
	 * @param docId
	 * @param docName
	 * @return
	 * @throws IOException 
	 * @throws InvalidTokenOffsetsException 
	 * @throws ParseException 
	 */
	public static String extractSummary(Long docId, String docName) throws IOException, InvalidTokenOffsetsException, ParseException {
		Document document = new Document();
		// 保存索引文件的地方
		Directory directory = FSDirectory.open(new File("filePath").toPath());
		// IndexReader reader=DirectoryReader
		DirectoryReader ireader = DirectoryReader.open(directory);
		// 创建 IndexSearcher对象，相比IndexWriter对象，这个参数就要提供一个索引的目录就行了
		IndexSearcher indexSearch = new IndexSearcher(ireader);
		Term term = new Term("docId", docId.toString());
		TermQuery termQuery = new TermQuery(term);
		// System.out.println(termQuery);
		TopDocs topdocs = indexSearch.search(termQuery, 1);
		// System.out.println("共找到" + topdocs.scoreDocs.length + ":条记录");
		for (ScoreDoc scoreDocs : topdocs.scoreDocs) {
			int documentId = scoreDocs.doc;
			document = indexSearch.doc(documentId);
		}
		if (document.get("docText") != null && !"".equals(document.get("docText"))) {
			Analyzer analyzer = new HanLPAnalyzer() {
				@Override
				protected TokenStreamComponents createComponents(String arg0) {
					Tokenizer tokenizer = new HanLPTokenizer(
							HanLP.newSegment().enableIndexMode(true).enableJapaneseNameRecognize(true)
									.enableIndexMode(true).enableNameRecognize(true).enablePlaceRecognize(true),
							null, false);
					return new TokenStreamComponents(tokenizer);
				}
			};
			QueryParser queryParser = new QueryParser("docText", analyzer);
			// 生成Query对象
			Query query = null;
			String str = null;
			String reStr="";
			if (docName != null && !"".equals(docName)) {
				query = queryParser.parse(docName);
				str = displayHtmlHighlight(query, analyzer, "docText", document.get("docText"), 2000);
				System.out.println(str);
				if(str!=null)
				reStr=HanLP.getSummary(str,3);
			} else {
				str = document.get("docText");
				for(String restr:HanLP.extractSummary(str, 3))
				{
					reStr=reStr+restr+"。";
				}
				//strs.add(HanLP.getSummary(str, 100).toString());
			}
			if (ireader != null) {
				ireader.close();
			}
			if (directory != null) {
				directory.close();
			}
			return reStr;
		} else {
			return null;
		}
	}
	/**
	/**
	 * 获取主题词
	 * @param docId
	 * @return
	 * @throws IOException 
	 */
	public static List<String> extractKeyword(Long docId) throws IOException {
		Document document = new Document();
		// 保存索引文件的地方
		Directory directory = FSDirectory.open(new File("filePath").toPath());
		// IndexReader reader=DirectoryReader
		DirectoryReader ireader = DirectoryReader.open(directory);
		// 创建 IndexSearcher对象，相比IndexWriter对象，这个参数就要提供一个索引的目录就行了
		IndexSearcher indexSearch = new IndexSearcher(ireader);
		Term term = new Term("docId", docId.toString());
		TermQuery termQuery = new TermQuery(term);
		// System.out.println(termQuery);
		TopDocs topdocs = indexSearch.search(termQuery, 1);
		// System.out.println("共找到" + topdocs.scoreDocs.length + ":条记录");
		for (ScoreDoc scoreDocs : topdocs.scoreDocs) {
			int documentId = scoreDocs.doc;
			document = indexSearch.doc(documentId);
		}
		if(ireader!=null){
			ireader.close();
		}
		if(directory!=null){
			directory.close();
		}
		if (document.get("docText")!= null && !"".equals(document.get("docText")))
		{
			return HanLP.extractKeyword(document.get("docText"), 3);
		}
		else {
			return null;
		}
	}
	/**
	 * 查找关联文档ids
	 * @param strs 关联词
	 * @return
	 * @throws IOException 
	 */
	public static List<Long> extractText(List<String> strs) throws IOException {
		Document document = new Document();
		List<Long> ids = new ArrayList<Long>();
		// 保存索引文件的地方
		Directory directory = FSDirectory.open(new File("filePath").toPath());
		// IndexReader reader=DirectoryReader
		DirectoryReader ireader = DirectoryReader.open(directory);
		// 创建 IndexSearcher对象，相比IndexWriter对象，这个参数就要提供一个索引的目录就行了
		IndexSearcher indexSearch = new IndexSearcher(ireader);
		BooleanQuery booleanQuery = new BooleanQuery();

		for (String str : strs) {
			TermQuery termQuery = new TermQuery(new Term("docKeyWord", str));
			booleanQuery.add(termQuery, BooleanClause.Occur.SHOULD);
		}
		// System.out.println(termQuery);
		TopDocs topdocs = indexSearch.search(booleanQuery, 5);
		// System.out.println("共找到" + topdocs.scoreDocs.length + ":条记录");
		for (ScoreDoc scoreDocs : topdocs.scoreDocs) {
			int documentId = scoreDocs.doc;
			document = indexSearch.doc(documentId);
			ids.add(Long.valueOf(document.get("docId")));
		}
		if(ireader!=null){
			ireader.close();
		}
		if(directory!=null){
			directory.close();
		}
		return ids;
	}
	
	static String displayHtmlHighlight(Query query, Analyzer analyzer, String fieldName, String fieldContent,
			int fragmentSize) throws IOException, InvalidTokenOffsetsException {
		// 创建一个高亮器
		Highlighter highlighter = new Highlighter(new SimpleHTMLFormatter("<font color='#c00'>", "</font>"),
				new QueryScorer(query));
		Fragmenter fragmenter = new SimpleFragmenter(fragmentSize);
		highlighter.setTextFragmenter(fragmenter);
		return highlighter.getBestFragment(analyzer, fieldName, fieldContent);
	}
	static String[] displayHtmlHighlights(Query query, Analyzer analyzer, String fieldName, String fieldContent,
			int fragmentSize,int len) throws IOException, InvalidTokenOffsetsException {
		// 创建一个高亮器
		Highlighter highlighter = new Highlighter(new SimpleHTMLFormatter("<font color='#c00'>", "</font>"),
				new QueryScorer(query));
		Fragmenter fragmenter = new SimpleFragmenter(fragmentSize);
		highlighter.setTextFragmenter(fragmenter);
		return highlighter.getBestFragments(analyzer, fieldName, fieldContent,len);
	}
}