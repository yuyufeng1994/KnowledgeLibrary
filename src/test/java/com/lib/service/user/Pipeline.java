package com.lib.service.user;
import java.io.File;

import javax.annotation.Resource;

import org.junit.Test;

import com.hankcs.hanlp.HanLP;
import com.lib.entity.FileInfo;
import com.lib.enums.Const;
import com.lib.utils.HtmlToWord;
import com.lib.utils.StringValueUtil;
import com.lib.utils.ThumbnailUtils;

import base.BaseTest;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

@TargetUrl("http://wenku.baidu.com/view/\\w+")
@HelpUrl("http://baike.baidu.com/wikitag/taglist?tagId=68031")
public class Pipeline  implements PageModelPipeline<WebMagicVo>  {

	
	@Resource
	private FileInfoService fileInfoService;
	
	private String path1="D:/soklib/" + "users/" + "2016001" + "/files/";

	public Pipeline(FileInfoService fileInfoService) {
		super();
		this.fileInfoService = fileInfoService;
	}



	@Override
	public void process(WebMagicVo t, Task task) {
		// TODO Auto-generated method stub
		System.out.println(t.getTitle());
		System.out.println(t.getBrief());
		String uuid = StringValueUtil.getUUID();
		String path = path1 + uuid + ".pdf";
		try {
			HtmlToWord.HtmlToPdf("<P>" + t.getBrief() + "</P>", path);
			File file = new File(path);
			
			FileInfo fi = new FileInfo();
			fi.setFileName(t.getTitle());
			fi.setFileSize(file.length());
			fi.setFileExt("pdf");
			fi.setFileBrief(HanLP.extractSummary(t.getBrief(), 3).toString());
			fi.setFileUserId(2016001L);
			fi.setFileUuid(uuid);
			fi.setFilePath("users/2016001/files/" + uuid);
			fi.setFileState(2);
			fi.setFileClassId(1l);
			int res = fileInfoService.insertFile(fi);
			
			if (new File(path).exists()) {
				ThumbnailUtils.pdfGetThumb(path1+uuid+ ".pdf",
						path1+uuid + ".png");
			}
			

		} catch (Exception e) {
			e.printStackTrace();

		}
	}
	
	
}
