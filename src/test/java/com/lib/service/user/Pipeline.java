package com.lib.service.user;
import java.io.File;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.web.util.HtmlUtils;

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

@TargetUrl("http://www.yiibai.com/\\w+/\\w+")
@HelpUrl("http://www.yiibai.com/\\w+")
public class Pipeline  implements PageModelPipeline<WebMagicVo>  {

	
	@Resource
	private FileInfoService fileInfoService;

	public Pipeline(FileInfoService fileInfoService) {
		super();
		this.fileInfoService = fileInfoService;
	}

	@Override
	public void process(WebMagicVo t, Task task) {
		// TODO Auto-generated method stub
		Long id=2016140L+((int)(Math.random()*9)+5);
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
			fi.setFileBrief(HanLP.extractSummary(t.getBrief(), 3).toString());
			fi.setFileUserId(Long.valueOf(id));
			fi.setFileUuid(uuid);
			fi.setFilePath("users/"+id+"/files/" + uuid);
			fi.setFileState(5);
			fi.setFileClassId(1L);
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
