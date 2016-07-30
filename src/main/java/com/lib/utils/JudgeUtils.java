package com.lib.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 专门用于判断的工具类
 * 
 * @author Yu Yufeng
 *
 */
public class JudgeUtils {

	public final static List<String> compressFile = new ArrayList<String>();
	public final static List<String> officeFile = new ArrayList<String>();
	static {
		Collections.addAll(compressFile, new String[] { "rar", "zip" });
		Collections.addAll(officeFile, new String[] { "doc", "docx", "xls", "xlsx", "ppt", "pptx" });
	}

	public static boolean isOfficeFile(String ext) {
		if (officeFile.contains(ext)) {
			return true;
		}
		return false;
	}

	public static boolean isCompressFile(String ext) {
		if (compressFile.contains(ext)) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		System.out.println(isCompressFile("zip"));
	}

}
