package com.lib.utils;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * 文件和流的基本操作
 * 
 * @author yyf
 * 
 */
public final class FileUtil {

	private FileUtil() {
	}

	/**
	 * 将输入流in保存到文件fileName
	 * 
	 * @param in
	 * @param fileName
	 *            完全文件名
	 * @return
	 */
	public static void saveFile(InputStream in, java.io.File file) throws Exception {
		OutputStream out = new FileOutputStream(file);
		try {
			byte[] buffer = new byte[8192];
			int bytesRead = 0;
			while ((bytesRead = in.read(buffer, 0, 8192)) != -1) {
				out.write(buffer, 0, bytesRead);
			}
		} finally {
			out.close();
			in.close();
		}
	}

	/**
	 * 获取文件名称，去路径和扩展名
	 * 
	 * @param a
	 * @return
	 */
	public static Object getFileName(String fileName) {
		try {
			if (fileName.lastIndexOf("/") > 0) {
				return fileName.substring(fileName.lastIndexOf("/") + 1, fileName.lastIndexOf("."));
			} else {
				return fileName.substring(0, fileName.lastIndexOf("."));
			}
		} catch (Exception ex) {
			return fileName;
		}
	}

	/**
	 * 获取文件大小描叙
	 * 
	 * @param size
	 * @return
	 */
	public static String getSizeDescribe(long size) {
		try {
			if (size < 1024l) {
				return size + " bytes";
			} else if (size < 1048576l) {
				return (Math.round(((size * 10) / 1024)) / 10) + " KB";
			} else if (size < 1073741824l) {
				return (Math.round(((size * 10) / 1048576)) / 10) + " MB";
			} else if (size < 1099511627776l) {
				return (Math.round(((size * 10) / 1073741824)) / 10) + " GB";
			} else {
				return (Math.round(((size * 10) / 1099511627776l)) / 10) + " TB";
			}
		} catch (Exception ex) {
			return Long.toString(size);
		}
	}

	/**
	 * 获取文件扩展名
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getFileExt(String fileName) {
		try {
			return fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
		} catch (Exception ex) {
			return "unknow";
		}
	}

	/**
	 * 文件拷贝
	 * 
	 * @param fileFrom
	 *            原文件
	 * @param fileTo
	 *            拷贝后的文件
	 */
	public static void copy(File fileFrom, File fileTo) throws Exception {
		FileInputStream in = new java.io.FileInputStream(fileFrom);
		FileOutputStream out = new FileOutputStream(fileTo);
		try {
			byte[] bt = new byte[1024];
			int count;
			while ((count = in.read(bt)) > 0) {
				out.write(bt, 0, count);
			}
		} finally {
			in.close();
			out.close();
		}
	}

	public static void delete(File file) throws Exception {
		file.delete();
	}

	/**
	 * 保存对象到fileName的文件
	 * 
	 * @param fileName
	 * @param obj
	 * @return
	 */
	public static void saveObject(File file, Object obj) throws Exception {
		FileOutputStream fo = new FileOutputStream(file);
		ZipOutputStream out = new ZipOutputStream(fo);
		ObjectOutputStream so = new ObjectOutputStream(out);
		try {
			ZipEntry entry = new ZipEntry("data");
			out.putNextEntry(entry);
			so.writeObject(obj);
		} finally {
			so.close();
			out.close();
			fo.close();
		}
	}

	/**
	 * 从fileName对应的文件中读取对象
	 * 
	 * @param fileName
	 * @return
	 */
	public static Object readObject(File file) throws Exception {
		Object obj = null;
		ZipFile zipFile = null;
		ObjectInputStream si = null;
		try {
			zipFile = new ZipFile(file);
			ZipEntry entry = zipFile.getEntry("data");
			si = new ObjectInputStream(zipFile.getInputStream(entry));
			obj = si.readObject();
		} finally {
			si.close();
			zipFile.close();
		}
		return obj;
	}

	/**
	 * 读取文本文件，编码UTF-8
	 * 
	 * @param file
	 * @return
	 */
	public static String readText(File file) throws Exception {
		return readText(file, "UTF-8");
	}

	/**
	 * 读取文本文件
	 * 
	 * @param file
	 * @param charset
	 * @return
	 */
	public static String readText(File file, String charset) throws Exception {
		FileInputStream fis = null;
		DataInputStream in = null;
		InputStreamReader inr = null;
		BufferedReader reader = null;
		try {
			fis = new FileInputStream(file);
			in = new DataInputStream(fis);
			inr = new InputStreamReader(in, charset);
			reader = new BufferedReader(inr);
			StringBuffer text = new StringBuffer();
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				text.append(tempString);
				text.append("\r\n");
			}
			return text.toString();
		} finally {
			reader.close();
			inr.close();
			in.close();
			fis.close();
		}
	}

	/**
	 * 保存文本文件，编码UTF-8
	 * 
	 * @param content
	 * @param file
	 * @return
	 */
	public static void saveText(String content, File file) throws Exception {
		saveText(content, file, "UTF-8");
	}

	/**
	 * 保存文本文件
	 * 
	 * @param content
	 * @param file
	 * @param charset
	 * @return
	 */
	public static void saveText(String content, File file, String charset) throws Exception {
		FileOutputStream fos = null;
		Writer out = null;
		try {
			fos = new FileOutputStream(file);
			out = new OutputStreamWriter(fos, charset);
			out.write(content);
		} finally {
			out.close();
			fos.close();
		}
	}
}