package com.lib.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import com.hankcs.hanlp.HanLP;
import com.hankcs.lucene.HanLPAnalyzer;
import com.hankcs.lucene.HanLPTokenizer;
import com.lib.entity.FileInfo;
import com.lib.enums.Const;


/**
 * 创建索引 Lucene 5.0+
 * 
 * @author Administrator
 * 
 */
public class LuceneIndexUtil { 
	
	
	/**
	 * 增加doc索引
	 * 
	 * @param pdf
	 * @param doc
	 * @throws IOException
	 */
	public static void addDocIndexer(FileInfo file) {
		Document document = new Document();
		IndexWriter indexWriter = null;
		Directory directory =null;
		try {
			
			// 创建Directory对象
			directory = FSDirectory.open(new File(Const.ROOT_PATH+"lucene").toPath());
			// 词法分析器
			Analyzer analyzer =  new HanLPAnalyzer() {
				@Override
				protected TokenStreamComponents createComponents(String arg0) {
					Tokenizer tokenizer = new HanLPTokenizer(HanLP.newSegment().enableIndexMode(true).enableJapaneseNameRecognize(true)
				               .enableIndexMode(true).enableNameRecognize(true).enablePlaceRecognize(true), null, false);
					return new TokenStreamComponents(tokenizer);
				}
			};
			// 创建IndexWriter对象,
			IndexWriterConfig config = new IndexWriterConfig(analyzer);
			config.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
			indexWriter = new IndexWriter(directory, config);
			// 判断是否需要建文档内容索引
			/*System.out.println(doc.getIsOffice());
			if (doc.getIsOffice()!=null&&doc.getIsOffice() == true) {
				// 创建输入流读取pdf文件
				String result = "";
				System.out.println("pdf");
				FileInputStream is = null;
				PDDocument PDdoc = null;
				try {
					is = new FileInputStream(new File(Const.ROOT_PATH + "upload/doc/" + doc.getDocPath() + ".pdf"));
					PDFParser parser = new PDFParser(is);
					parser.parse();
					PDdoc = parser.getPDDocument();
					PDFTextStripper stripper = new PDFTextStripper();
					result = stripper.getText(PDdoc);
					if(result!=null){
					document.add(new TextField("docText", result, Field.Store.YES));
					List<String> strList = HanLP.extractKeyword(result, 3);
					String strs = "";
					for (String str : strList) {
						strs = strs + str;
					}
					if(strs!=null)
					document.add(new TextField("docKeyWord", strs, Field.Store.YES));
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (is != null) {
						try {
							is.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if (PDdoc != null) {
						try {
							PDdoc.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			// System.out.println(doc.getDocModTime()+" "+doc.getDocUpTime());
			if(doc.getDocExt()!=null)
			document.add(new TextField("docExt", doc.getDocExt() + "", Field.Store.YES));
			if(doc.getDocsClass()!=null)
			document.add(new StringField("docClass", doc.getDocsClass().getDocsClassId() + "", Field.Store.YES));
			if(doc.getDocUpTime()!=null)
			document.add(new StringField("docUpTime", sdf.format(doc.getDocUpTime()), Field.Store.YES));
			if(doc.getDocModTime()!=null)
			document.add(new StringField("docModTime", sdf.format(doc.getDocModTime()), Field.Store.YES));
			if(doc.getDocName()!=null)
			document.add(new TextField("docName", doc.getDocName() + "", Field.Store.YES));
			if(doc.getDocBrief()!=null)
			document.add(new TextField("docBrief", doc.getDocBrief() + "", Field.Store.YES));
			if(doc.getDocId()!=null)
			document.add(new StringField("docId", doc.getDocId() + "", Field.Store.YES));
			indexWriter.addDocument(document);*/
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (indexWriter != null) {
					indexWriter.commit();
					indexWriter.close();
				}
				if (directory != null) {
					directory.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 增加docs索引
	 * @param pdf
	 * @param docs
	 * @throws IOException
	 */
	public static void addDocsIndexer(FileInfo docs){
		Document document = new Document();
		IndexWriter indexWriter = null;
		Directory directory=null;
		try {
			System.out.println("pdf");
			// 创建Directory对象
			directory = FSDirectory.open(new File("filePath").toPath());
			// 词法分析器
			Analyzer analyzer =  new HanLPAnalyzer() {
				@Override
				protected TokenStreamComponents createComponents(String arg0) {
					Tokenizer tokenizer = new HanLPTokenizer(HanLP.newSegment().enableIndexMode(true).enableJapaneseNameRecognize(true)
				               .enableIndexMode(true).enableNameRecognize(true).enablePlaceRecognize(true), null, false);
					return new TokenStreamComponents(tokenizer);
				}
			};
			IndexWriterConfig config = new IndexWriterConfig(analyzer);
			config.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
			// 创建IndexWriter对象
			indexWriter = new IndexWriter(directory, config);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		/*	if (docs.getDocExt() != null)
				document.add(new TextField("docsExt", docs.getDocExt() + "", Field.Store.YES));
			if (docs.getDocsClass() != null)
				document.add(new StringField("docsClass", docs.getDocsClass().getDocsClassId() + "", Field.Store.YES));
			if (docs.getDocUpTime() != null)
				document.add(new StringField("docsUpTime", sdf.format(docs.getDocUpTime()), Field.Store.YES));
			if (docs.getDocsModTime() != null)
				document.add(new StringField("docsModTime", sdf.format(docs.getDocsModTime()), Field.Store.YES));
			if (docs.getDocsName() != null)
				document.add(new TextField("docsName", docs.getDocsName() + "", Field.Store.YES));
			if (docs.getDocsBrief() != null)
				document.add(new TextField("docsBrief", docs.getDocsBrief() + "", Field.Store.YES));
			if (docs.getDocsId() != null)
				document.add(new StringField("docsId", docs.getDocsId() + "", Field.Store.YES));
			indexWriter.addDocument(document);*/
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (indexWriter != null) {
					indexWriter.commit();
					indexWriter.close();	
				}
				if (directory != null) {
					directory.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 根据id删除doc索引
	 * 
	 * @param doc
	 * @throws IOException
	 */
	public static void deteleDocIndexer(FileInfo doc){
		
		IndexWriter indexWriter=null;
		Directory directory=null;
		try{
		// 创建Directory对象
		directory = FSDirectory.open(new File("filePath").toPath());
		// 词法分析器
		Analyzer analyzer =  new HanLPAnalyzer() {
			@Override
			protected TokenStreamComponents createComponents(String arg0) {
				Tokenizer tokenizer = new HanLPTokenizer(HanLP.newSegment().enableIndexMode(true).enableJapaneseNameRecognize(true)
			               .enableIndexMode(true).enableNameRecognize(true).enablePlaceRecognize(true), null, false);
				return new TokenStreamComponents(tokenizer);
			}
		};
		IndexWriterConfig config = new IndexWriterConfig(analyzer);
		config.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
	    indexWriter = new IndexWriter(directory, config);
		/*indexWriter.deleteDocuments(new Term("docId", doc.getDocId() + ""));*/
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (indexWriter != null) {
					indexWriter.commit();
					indexWriter.close();
				}
				if (directory != null) {
					directory.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 根据id删除docs索引
	 * @param doc
	 * @throws IOException
	 */
	public static void deteleDocsIndexer(FileInfo docs){
		IndexWriter indexWriter=null;
		Directory directory=null;
		try{
		// 创建Directory对象
		directory = FSDirectory.open(new File("filePath").toPath());
		// 词法分析器
		Analyzer analyzer =  new HanLPAnalyzer() {
			@Override
			protected TokenStreamComponents createComponents(String arg0) {
				Tokenizer tokenizer = new HanLPTokenizer(HanLP.newSegment().enableIndexMode(true).enableJapaneseNameRecognize(true)
			               .enableIndexMode(true).enableNameRecognize(true).enablePlaceRecognize(true), null, false);
				return new TokenStreamComponents(tokenizer);
			}
		};
		IndexWriterConfig config = new IndexWriterConfig(analyzer);
		config.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
	    indexWriter = new IndexWriter(directory, config);
	   /* indexWriter.deleteDocuments(new Term("docsId", docs.getDocsId() + ""));*/
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (indexWriter != null) {
					indexWriter.commit();
					indexWriter.close();
				}
				if (directory != null) {
					directory.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
