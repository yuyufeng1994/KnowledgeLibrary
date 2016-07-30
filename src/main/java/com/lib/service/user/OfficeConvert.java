package com.lib.service.user;

import java.io.File;

public interface OfficeConvert {
	/**
	 * 是否是office文件
	 * 
	 * @param files
	 * @return
	 */
	public boolean isOfficeFile(String fileName);

	/**
	 * 是否是pdf文件
	 * 
	 * @param files
	 * @return
	 */
	public boolean isPDF(String fileName);

	/**
	 * 是否启动转换程序
	 * 
	 * @return
	 */
	public boolean isConvert();

	/**
	 * office转PDF
	 * 
	 * @param officeFile
	 * @param pdfFile
	 */
	public void convertToPDF(File officeFile, File pdfFile);

	/**
	 * pdf转swf
	 * 
	 * @param pdfFile
	 * @param swfFile
	 */
	public void convertToSWF(File pdfFile, File swfFile);

	/**
	 * office转html
	 * 
	 * @param officeFile
	 * @param htmlFile
	 */
	public void convertToHTML(File officeFile, File htmlFile);

	void convertTest(File officeFile, File File);
}
