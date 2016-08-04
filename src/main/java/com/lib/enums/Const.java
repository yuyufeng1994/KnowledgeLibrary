package com.lib.enums;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Const {
	public final static String SESSION_USER = "session_user"; // session中的user对象
	public final static String SESSION_IS_COMPRESSING = "session_is_compress";
	public final static int COMMON_PAGE_SIZE = 10; // session中的pageSize对象
	public static final String SESSION_NEW_FILE = "session_new_file"; // session中的新建文件对象
	public static final String MYFILE_SEARCH_VALUE = "my_file_search_value";//我的文件搜索信息
	public static String ROOT_PATH = null; // 磁盘根目录路径
	public static String CONTAINER_PATH = null;// 项目根路径
	public static String HEAD_URL = null;// 项目根路径
	public static String STREAM_PATH=null;
	
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
			STREAM_PATH = ROOT_PATH+"red5-server/webapps/lib/streams/";
			File streamDir = new File(STREAM_PATH);
			if(!streamDir.exists()){
				streamDir.mkdirs();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
