package com.lib.service.user;

import java.util.Map;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.model.ConsolePageModelPipeline;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;
import us.codecraft.webmagic.pipeline.PageModelPipeline;


@TargetUrl("http://wenku.baidu.com/view/\\w+")
@HelpUrl("http://wenku.baidu.com/portal/subject/\\w+")

class test implements PageModelPipeline<WebMagicTest>{

	@Override
	public void process(WebMagicTest t, Task task) {
		// TODO Auto-generated method stub
		System.out.println(t.getAuthor());
		
	}
	
	
}
public class WebMagicTest {

	@ExtractBy(value = "//h1[@class='reader_ab_test with-top-banner']/span/text()", notNull = true)
	private String title;

	@ExtractBy(value = "//p[@class='doc-value']/span/a/text()")
	private String author;

	@ExtractBy("//span[@class='doc-desc-label']/text()")
	private String brief;
	
	@Override
	public String toString() {
		return "WebMagicTest [title=" + title + ", author=" + author + ", brief=" + brief + "]";
	}
	
	

	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getAuthor() {
		return author;
	}



	public void setAuthor(String author) {
		this.author = author;
	}



	public String getBrief() {
		return brief;
	}



	public void setBrief(String brief) {
		this.brief = brief;
	}



	public static void main(String[] args) {
		OOSpider.create(Site.me().setCharset("GBK").setSleepTime(1000), new test()
			,WebMagicTest.class).addUrl("http://wenku.baidu.com/portal/subject/31_s1_g0_v0").thread(5).run();
		
		
	}

}

