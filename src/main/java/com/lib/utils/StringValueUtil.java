package com.lib.utils;

import java.text.DecimalFormat;
import java.util.UUID;

import org.springframework.util.DigestUtils;

public class StringValueUtil {
	// md5盐值字符串,用于混淆md5
	private final static String slat = "qihangdui2016";

	/**
	 * md5码
	 * 
	 * @param str
	 * @return
	 */
	public static String getMD5(String str) {
		String base = str + "/" + slat;
		String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
		return md5;
	}

	/**
	 * 产生uuid
	 * 
	 * @return
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString();
	}

	/**
	 * 文件大小格式
	 * 
	 * @param size
	 * @return
	 */
	public static String getDataSize(long size) {
		DecimalFormat formater = new DecimalFormat("####.00");
		if (size < 1024) {
			return size + " bytes";
		} else if (size < 1024 * 1024l) {
			float kbsize = size / 1024f;
			return formater.format(kbsize) + " KB";
		} else if (size < 1024 * 1024 * 1024l) {
			float mbsize = size / 1024f / 1024f;
			return formater.format(mbsize) + " MB";
		} else if (size < 1024 * 1024 * 1024 * 1024l) {
			float gbsize = size / 1024f / 1024f / 1024f;
			return formater.format(gbsize) + " GB";
		} else {
			return "size: error";
		}
	}
	public static void main(String[] args) {
		System.out.println(1024 * 1024 * 1024 * 1024l);
	}
}
