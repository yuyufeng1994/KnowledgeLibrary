package com.lib.enums;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Const {
	public final static String SESSION_USER = "session_user"; // session中的user对象
	public final static String SESSION_IS_COMPRESSING = "session_is_compress";
	public static String ROOT_PATH = null; // 根目录路径

	public static void loadRootPath() {
		Properties prop = new Properties();
		InputStream in = Const.class.getResourceAsStream("/jdbc.properties");
		try {
			prop.load(in);
			ROOT_PATH = prop.getProperty("root_path").trim();
			File RootDir = new File(ROOT_PATH);
			if (!RootDir.exists()) {
				RootDir.mkdirs();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
