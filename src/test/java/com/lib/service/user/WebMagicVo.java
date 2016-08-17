package com.lib.service.user;


import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;
import us.codecraft.webmagic.pipeline.PageModelPipeline;



public class WebMagicVo {
	
	@ExtractBy(value = "//dd[@class='lemmaWgt-lemmaTitle-title']/h1/text()", notNull = true)
	private String title;

	@ExtractBy("//div[@class='lemma-summary']/allText()")
	private String brief;

	@Override
	public String toString() {

		return "WebMagicTest [title=" + title + ", brief=" + brief + "]";
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}
	
	
}

