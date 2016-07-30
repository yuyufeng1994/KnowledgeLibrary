package com.lib.service.user.impl;

import java.io.File;
import java.util.List;

import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.OfficeManager;
import org.springframework.stereotype.Service;

import com.lib.service.user.OfficeConvert;
import com.lib.utils.FileUtil;

@Service("officeConvert")
public class OfficeConvertImpl implements OfficeConvert {

	private DefaultOfficeManagerConfiguration configuration;

	private OfficeManager manager;

	/**
	 * 需要转换成PDF的文件后缀,小写
	 */
	private static List<String> convertExtList;// = new ArrayList<String>();
	
	private boolean convert = false;

	/**
	 * pdf转swf的执行文件路径
	 */
	private String pdf2swf;

	@Override
	public boolean isOfficeFile(String fileName) {
		return convertExtList.contains(FileUtil.getFileExt(fileName));
	}

	@Override
	public boolean isPDF(String fileName) {
		return "pdf".equals(FileUtil.getFileName(fileName));
	}

	@Override
	public synchronized void convertToPDF(File officeFile, File pdfFile) {
		if (!convert) {
			return;
		}
		if (isOfficeFile(officeFile.getName())) {
			init();
			OfficeDocumentConverter converter = new OfficeDocumentConverter(manager);
			converter.convert(officeFile, pdfFile);
			close();
		}
	}

	@Override
	public synchronized void convertToHTML(File officeFile, File htmlFile) {
		if (!convert) {
			return;
		}
		if (isOfficeFile(officeFile.getName())) {
			init();
			OfficeDocumentConverter converter = new OfficeDocumentConverter(manager);
			converter.convert(officeFile, htmlFile);
			close();
		}
	}

	@Override
	public synchronized void convertTest(File officeFile, File File) {
		if (!convert) {
			return;
		}
		if (isOfficeFile(officeFile.getName())) {
			init();
			OfficeDocumentConverter converter = new OfficeDocumentConverter(manager);
			converter.convert(officeFile, File);
			close();
		}
	}

	@Override
	public synchronized void convertToSWF(File pdfFile, File swfFile) {
		if (!convert) {
			return;
		}
		try {
			Runtime r = Runtime.getRuntime();
			@SuppressWarnings("unused")
			Process p = r.exec(pdf2swf + " " + pdfFile.getPath() + " -o " + swfFile.getPath() + " -T 9");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	@Override
	public void setConfiguration(DefaultOfficeManagerConfiguration configuration) {
		this.configuration = configuration;
	}

	@Override
	public void setConvertExtList(List<String> convertExtList) {
		this.convertExtList = convertExtList;
	}

	/**
	 * 尝试启动OpenOffice服务
	 */
	public void init() {
		if (!convert) {
			return;
		}
		try {
			if (manager == null) {
				manager = configuration.buildOfficeManager();
			}
			if (!manager.isRunning()) {
				manager.start();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 尝试关闭OpenOffice服务
	 */
	public void close() {
		if (!convert) {
			return;
		}
		try {
			if (manager != null) {
				if (manager.isRunning()) {
					manager.stop();
				}
				manager = null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void setManager(OfficeManager manager) {
		this.manager = manager;
	}

	/**
	 * pdf转swf的执行文件路径
	 * 
	 * @param pdf2swf
	 */
	public void setPdf2swf(String pdf2swf) {
		this.pdf2swf = pdf2swf;
	}

	public void setConvert(boolean convert) {
		this.convert = convert;
	}

	public boolean isConvert() {
		return convert;
	}

}
