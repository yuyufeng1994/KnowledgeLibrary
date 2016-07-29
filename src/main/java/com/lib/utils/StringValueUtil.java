package com.lib.utils;

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
}
