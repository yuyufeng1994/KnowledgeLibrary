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
	public final static List<String> videoFile = new ArrayList<String>();
	public final static List<String> imageFile = new ArrayList<String>();
	public final static List<String> audioFile = new ArrayList<String>();
	public final static List<String> elseFile = new ArrayList<String>();
	static {
		Collections.addAll(compressFile, new String[] { "rar", "zip" });
		Collections.addAll(officeFile, new String[] { "doc", "docx", "xls", "xlsx", "ppt", "pptx","pdf"});
		Collections.addAll(videoFile, new String[] {"avi","mpg","3gp","mov","asf","asx","flv","mp4","wmv","mkv"});
		Collections.addAll(imageFile, new String[] {"bmp","png","gif","jpeg","jpg"});
		Collections.addAll(audioFile, new String[] {"mp3","wma","ogg"});
		Collections.addAll(elseFile, new String[] {"html","jsp","txt"});
	}

	public static boolean isOfficeFile(String ext) {
		if (officeFile.contains(ext)) {
			return true;
		}
		return false;
	}

	//是否压缩文件
	public static boolean isCompressFile(String ext) {
		if (compressFile.contains(ext)) {
			return true;
		}
		return false;
	}
	//是否是视频文件
	public static boolean isVideoFile(String ext) {
		if (videoFile.contains(ext)) {
			return true;
		}
		return false;
	}
	//是否是视频文件
	public static boolean isImageFile(String ext) {
			if (imageFile.contains(ext)) {
				return true;
			}
			return false;
	}
	//是否是音频文件
	public static boolean isAudioFile(String ext) {
				if (audioFile.contains(ext)) {
					return true;
				}
				return false;
	}
	//是否是音频文件
	public static boolean isElseFile(String ext) {
		if (elseFile.contains(ext)) {
			return true;
		}
		return false;	
	}
	public static void main(String[] args) {
		System.out.println(isCompressFile("zip"));
	}

}
