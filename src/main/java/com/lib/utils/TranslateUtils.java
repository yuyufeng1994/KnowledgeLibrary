package com.lib.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.lib.service.user.OfficeConvert;
import com.lib.service.user.impl.OfficeConvertImpl;

public class TranslateUtils {

	public static OfficeConvert getOfficeConvert() {
		org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration config = new org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration();
		config.setOfficeHome("C:\\Program Files (x86)\\OpenOffice 4\\");
		// OpenOffice安装路径
		config.setPortNumber(8100);
		// 启动服务端口号
		config.setTaskExecutionTimeout(180000);
		// 单任务执行超时 1000*60 1分钟
		config.setTaskQueueTimeout(60000000);
		// 任务队列超时 1000*60 * 1000 1000分钟
		OfficeConvert imp = new OfficeConvertImpl();
		imp.setConvert(true);
		imp.setConfiguration(config);
		// imp.setPdf2swf("D:\\DevInstall\\SWFTools\\pdf2swf.exe");
		List<String> list = new ArrayList<String>();
		Collections.addAll(list, new String[] { "doc", "docx", "xls", "xlsx", "ppt", "pptx" });
		imp.setConvertExtList(list);
		return imp;
	}

}
