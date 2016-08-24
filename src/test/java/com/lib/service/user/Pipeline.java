package com.lib.service.user;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.Analyzer.TokenStreamComponents;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;
import org.springframework.web.util.HtmlUtils;

import com.hankcs.hanlp.HanLP;
import com.hankcs.lucene.HanLPAnalyzer;
import com.hankcs.lucene.HanLPTokenizer;
import com.lib.entity.FileInfo;
import com.lib.enums.Const;
import com.lib.utils.ExtractUtil;
import com.lib.utils.HtmlToWord;
import com.lib.utils.JudgeUtils;
import com.lib.utils.StringValueUtil;
import com.lib.utils.ThumbnailUtils;

import base.BaseTest;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

@TargetUrl("http://www.runoob.com/\\w+/\\w+")
@HelpUrl("http://www.runoob.com/")
public class Pipeline  implements PageModelPipeline<WebMagicVo>  {

	
	// 索引存放路径
		private static String indexPath = "D:/soklib/lucene";
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
		
	@Resource
	private FileInfoService fileInfoService;

	public Pipeline(FileInfoService fileInfoService) {
		super();
		this.fileInfoService = fileInfoService;
	}

	@Override
	public void process(WebMagicVo t, Task task) {
		// TODO Auto-generated method stub
		int x=((int)(Math.random()*9));
		Long id=2016145L+x;
		String userName="test"+x;
		String path1="D:/soklib/" + "users/" +id+"/files/";
		String uuid = StringValueUtil.getUUID();
		String path = path1 + uuid + ".pdf";
		System.out.println(t.getBrief());
		
		try {
			HtmlToWord.Pdf(t.getBrief(), path);
			File file = new File(path);
			FileInfo fi = new FileInfo();
			fi.setFileName(t.getTitle());
			fi.setFileSize(file.length());
			fi.setFileExt("pdf");
			fi.setFileBrief(null);
			fi.setFileUserId(Long.valueOf(id));
			fi.setFileUuid(uuid);
			fi.setFileCreateTime(new Date());
			fi.setFilePath("users/"+id+"/files/" + uuid);
			fi.setFileState(5);
			fi.setFileClassId(1L);
			int res = fileInfoService.insertFile(fi);
			fi.setFileId(fileInfoService.getFileInfoByUuid(uuid).getFileId());
			if (new File(path).exists()) {
				ThumbnailUtils.pdfGetThumb(path1+uuid+ ".pdf",
						path1+uuid + ".png");
			}
			addFileIndex(fi,userName,t.getBrief().trim());
		} catch (Exception e) {
			e.printStackTrace();

		}
	}
	
	public synchronized static void addFileIndex(FileInfo file, String fileUserName, String fileText) {

		Document document = new Document();
		// 创建Directory对象
		IndexWriter indexWriter = null;
		// 创建Directory对象
		Directory directory = null;
		// 创建IndexWriter对象,
		IndexWriterConfig config = null;
		// pdf文件存放路径
		String filePath = "D:/soklib/"+ file.getFilePath() + ".pdf" ;
		try {

			directory = FSDirectory.open(new File(indexPath).toPath());
			config = new IndexWriterConfig(analyzer);
			config.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
			indexWriter = new IndexWriter(directory, config);
			String result = null;
			// 判断是否需要建文档内容索引

			if (fileText != null && !fileText.equals("")) {
				result = fileText;

			} else if (new File(filePath).exists()) {
				
				// 创建输入流读取pdf文件
				result = ExtractUtil.Parser(filePath, file.getFileExt());
				//System.out.println(result);
			}else if(!JudgeUtils.isVideoFile(file.getFileExt())&& !JudgeUtils.isAudioFile(file.getFileExt()) && !JudgeUtils.isImageFile(file.getFileExt()))
			{
				
				result = ExtractUtil.Parser("D:/soklib/" + file.getFilePath() + "."+file.getFileExt(), file.getFileExt());
				
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
