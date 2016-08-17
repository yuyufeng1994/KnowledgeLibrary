package com.lib.utils;

import java.io.File;
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

	// 索引存放路径
	private static String indexPath = Const.ROOT_PATH + "lucene";
	// 词法分析器
	// private static Analyzer analyzer = new HanLPAnalyzer();
	private static Analyzer analyzer = new HanLPAnalyzer() {
		@Override
		protected TokenStreamComponents createComponents(String arg0) {
			Tokenizer tokenizer = new HanLPTokenizer(
					HanLP.newSegment().enableMultithreading(true).enableAllNamedEntityRecognize(true).enableOffset(true).enableIndexMode(true),
					null, true);
			return new TokenStreamComponents(tokenizer);
		}
	};

	/**
	 * 添加文件索引
	 * 
	 * @param file
	 */
	public synchronized static void addFileIndex(FileInfo file, String fileUserName, String fileText) {

		Document document = new Document();
		// 创建Directory对象
		IndexWriter indexWriter = null;
		// 创建Directory对象
		Directory directory = null;
		// 创建IndexWriter对象,
		IndexWriterConfig config = null;
		// pdf文件存放路径
		String filePath = Const.ROOT_PATH + file.getFilePath() + "." + file.getFileExt();
		try {

			directory = FSDirectory.open(new File(indexPath).toPath());
			config = new IndexWriterConfig(analyzer);
			config.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
			indexWriter = new IndexWriter(directory, config);
			String result = null;
			// 判断是否需要建文档内容索引

			if (fileText != null && !fileText.equals("")) {
				result = fileText;

			} else if (new File(filePath).exists() && !JudgeUtils.isVideoFile(file.getFileExt())
					&& !JudgeUtils.isAudioFile(file.getFileExt()) && !JudgeUtils.isImageFile(file.getFileExt())) {
				
				// 创建输入流读取pdf文件
				result = ExtractUtil.Parser(filePath, file.getFileExt());
			//	System.out.println(result);
			}

			if (result != null && !"".equals(result)) {
				document.add(new TextField("fileText", result, Field.Store.YES));

				List<String> fileKeyWords = HanLP.extractKeyword(result, 10);
				String _fileKeyWords = "";

				for (String str : fileKeyWords) {
					_fileKeyWords = _fileKeyWords + "," + str;
				}
				List<String> fileSummarys = HanLP.extractSummary(result, 3);
				
				document.add(new TextField("fileKeyWords", _fileKeyWords, Field.Store.YES));
				
				//System.out.println(_fileSummarys.toString());
				document.add(new StringField("fileSummarys", fileSummarys.toString(), Field.Store.YES));
				
			} else {
				List<String> fileKeyWords = HanLP.extractKeyword(file.getFileBrief() + "." + file.getFileName(), 10);
				String _fileKeyWords = "";
				for (String str : fileKeyWords) {
					_fileKeyWords = _fileKeyWords + "," + str;
				}
				document.add(new TextField("fileKeyWords", _fileKeyWords.toString(), Field.Store.YES));
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			// System.out.println(doc.getDocModTime()+" "+doc.getDocUpTime());

			document.add(new StringField("fileId", file.getFileId() + "", Field.Store.YES));

			document.add(new StringField("fileUserName", fileUserName, Field.Store.YES));

			document.add(new StringField("fileUuid", file.getFileUuid() + "", Field.Store.YES));

			document.add(new TextField("fileName", file.getFileName() + "", Field.Store.YES));

			document.add(new StringField("fileExt", file.getFileExt() + "", Field.Store.YES));

			document.add(new TextField("fileBrief", file.getFileBrief() + "", Field.Store.YES));

			document.add(new StringField("fileUserId", file.getFileUserId() + "", Field.Store.YES));

			document.add(new StringField("fileCreateTime", sdf.format(file.getFileCreateTime()), Field.Store.YES));

			document.add(new StringField("filePath", file.getFilePath() + "", Field.Store.YES));

			document.add(new StringField("fileState", file.getFileState() + "", Field.Store.YES));

			document.add(new StringField("fileClassId", file.getFileClassId() + "", Field.Store.YES));

			indexWriter.addDocument(document);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(indexWriter, directory);
		}
	}

	/**
	 * 
	 * 根据id删除file索引
	 * 
	 * @param doc
	 * @throws IOException
	 */
	public static void deteleFileIndex(FileInfo file) {

		// 创建Directory对象
		IndexWriter indexWriter = null;
		// 创建Directory对象
		Directory directory = null;
		// 创建IndexWriter对象,
		IndexWriterConfig config = null;
		try {
			directory = FSDirectory.open(new File(indexPath).toPath());
			config = new IndexWriterConfig(analyzer);
			config.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
			indexWriter = new IndexWriter(directory, config);
			// System.out.println("删除成功");
			indexWriter.deleteDocuments(new Term("fileId", file.getFileId() + ""));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(indexWriter, directory);
		}
	}

	public static void close(IndexWriter indexWriter, Directory directory) {
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
