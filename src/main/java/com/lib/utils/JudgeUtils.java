package com.lib.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 专门用于判断的工具类
 * 
 * @author Yu Yufeng
 *
 */
public class JudgeUtils {

	public final static List<String> exts = new ArrayList<String>();
	static {
		exts.add("rar");
		exts.add("zip");
	}

	public static boolean isCompressFile(String ext) {
		if (exts.contains(ext)) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		System.out.println(isCompressFile("zip"));
	}

}
