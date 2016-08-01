package com.lib.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;

import com.github.junrar.Archive;
import com.github.junrar.rarfile.FileHeader;
import com.lib.entity.FileInfo;
import com.lib.enums.Const;

/**
 * 处理压缩文件的工具类
 * 
 * @author yyf
 *
 */

public class CompressUtil {

	public static void main(String[] args) throws Exception {

	}

	public static List<FileInfo> startCompress(String name, Long userId) throws Exception {
		String userFilePath = "users/"+userId + "/files/";
		String filePath = userFilePath;
		if (name.trim().endsWith("rar")) {
			return unrar(name, filePath);
		} else if (name.trim().endsWith("zip")) {
			return unzip(name, filePath);
		}
		return null;
	}

	private static final int BUFFEREDSIZE = 1024;

	/**
	 * 解 压zip格式的压缩文件到指定位置
	 * 
	 * @param zipFileName
	 *            压 缩文件
	 * @param extPlace
	 *            解 压目录
	 * @throws Exception
	 */
	public static List<FileInfo> unzip(String filepath, String filePath) throws Exception {
		List<FileInfo> expfs = new ArrayList<FileInfo>();
		File f = new File(filepath);
		ZipFile zipFile = new ZipFile(filepath, "gbk");
		if ((!f.exists()) && (f.length() <= 0)) {
			throw new Exception("要解压的文件不存在!");
		}
		String strPath, gbkPath, strtemp;
		strPath = Const.ROOT_PATH + filePath;
		@SuppressWarnings("rawtypes")
		java.util.Enumeration e = zipFile.getEntries();
		while (e.hasMoreElements()) {
			String uuid = StringValueUtil.getUUID();
			org.apache.tools.zip.ZipEntry zipEnt = (ZipEntry) e.nextElement();
			gbkPath = zipEnt.getName();

			// 读写文件
			InputStream is = zipFile.getInputStream(zipEnt);
			BufferedInputStream bis = new BufferedInputStream(is);
			gbkPath = zipEnt.getName();
			strtemp = strPath + File.separator + gbkPath;
			String ext = strtemp.substring(strtemp.lastIndexOf(".") + 1, strtemp.length());
			strtemp = strPath + File.separator + uuid + "." + ext;
			FileInfo d = new FileInfo();
			d.setFileName(gbkPath.substring(0, gbkPath.indexOf(".")));
			d.setFilePath(filePath + uuid);
			d.setFileUuid(uuid);
			d.setFileExt(ext);
			d.setFileState(2);
			FileOutputStream fos = new FileOutputStream(strtemp);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			int c;
			while ((c = bis.read()) != -1) {
				bos.write((byte) c);
			}

			if (bos != null) {
				try {
					bos.close();
					bos = null;
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
					fos = null;
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if (bis != null) {
				try {
					bis.close();
					bis = null;
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if (is != null) {
				try {
					is.close();
					is = null;
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			strtemp = Const.ROOT_PATH + filePath + uuid + "." + ext;
			d.setFileSize(new File(strtemp).length());
			expfs.add(d);
		}
		zipFile.close();
		return expfs;
	}

	/**
	 * 压 缩zip格式的压缩文件
	 * 
	 * @param inputFilename
	 *            压 缩的文件或文件夹及详细路径
	 * @param zipFilename
	 *            输 出文件名称及详细路径
	 * @throws IOException
	 */
	public static void zip(String inputFilename, String zipFilename) throws IOException {
		zip(new File(inputFilename), zipFilename);
	}

	/**
	 * 压 缩zip格式的压缩文件
	 * 
	 * @param inputFile
	 *            需 压缩文件
	 * @param zipFilename
	 *            输 出文件及详细路径
	 * @throws IOException
	 */
	public static void zip(File inputFile, String zipFilename) throws IOException {
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFilename));
		try {
			zip(inputFile, out, "");
		} catch (IOException e) {
			throw e;
		} finally {
			out.close();
		}
	}

	/**
	 * 压 缩zip格式的压缩文件
	 * 
	 * @param inputFile
	 *            需 压缩文件
	 * @param out
	 *            输 出压缩文件
	 * @param base
	 *            结 束标识
	 * @throws IOException
	 */
	private static void zip(File inputFile, ZipOutputStream out, String base) throws IOException {
		if (inputFile.isDirectory()) {
			File[] inputFiles = inputFile.listFiles();
			out.putNextEntry(new ZipEntry(base + "/"));
			base = base.length() == 0 ? "" : base + "/";
			for (int i = 0; i < inputFiles.length; i++) {
				zip(inputFiles[i], out, base + inputFiles[i].getName());
			}
		} else {
			if (base.length() > 0) {
				out.putNextEntry(new ZipEntry(base));
			} else {
				out.putNextEntry(new ZipEntry(inputFile.getName()));
			}
			FileInputStream in = new FileInputStream(inputFile);
			try {
				int c;
				byte[] by = new byte[BUFFEREDSIZE];
				while ((c = in.read(by)) != -1) {
					out.write(by, 0, c);
				}
			} catch (IOException e) {
				throw e;
			} finally {
				in.close();
			}
		}
	}

	/**
	 * 解 压rar格式的压缩文件到指定目录下
	 * 
	 * @param rarFileName
	 *            压 缩文件
	 * @param extPlace
	 *            解 压目录
	 * @throws Exception
	 */
	public static List<FileInfo> unrar(String sourceRar, String filePath) throws Exception {

		List<FileInfo> expfs = new ArrayList<FileInfo>();
		Archive archive = null;
		FileOutputStream fos = null;
		try {
			File file = new File(sourceRar);
			archive = new Archive(file);

			FileHeader fh = archive.nextFileHeader();
			File destFileName = null;
			while (fh != null) {
				String uuid = StringValueUtil.getUUID();
				FileInfo d = new FileInfo();
				String compressFileName = null;
				if (!existZH(fh.getFileNameW())) {
					compressFileName = fh.getFileNameString().trim();
				} else {
					compressFileName = fh.getFileNameW().trim();
				}
				String ext = compressFileName.substring(compressFileName.lastIndexOf(".") + 1,
						compressFileName.length());
				d.setFileName(compressFileName.substring(0, compressFileName.indexOf(".")));
				d.setFilePath(filePath + uuid);
				d.setFileExt(ext);
				d.setFileState(2);
				d.setFileUuid(uuid);
				destFileName = new File(Const.ROOT_PATH + filePath + uuid + "." + ext);

				if (fh.isDirectory()) {
					if (!destFileName.exists()) {
						destFileName.mkdirs();
					}
					fh = archive.nextFileHeader();
					continue;
				}
				if (!destFileName.getParentFile().exists()) {
					destFileName.getParentFile().mkdirs();
				}
				fos = new FileOutputStream(destFileName);
				archive.extractFile(fh, fos);
				fos.close();
				fos = null;
				fh = archive.nextFileHeader();
				d.setFileSize(destFileName.length());
				expfs.add(d);
			}

			archive.close();
			archive = null;
		} catch (Exception e) {
			throw e;
		} finally {
			if (fos != null) {
				try {
					fos.close();
					fos = null;
				} catch (Exception e) {
					// ignore
				}
			}
			if (archive != null) {
				try {
					archive.close();
					archive = null;
				} catch (Exception e) {
					// ignore
				}
			}
		}
		return expfs;
	}

	public static boolean existZH(String str) {
		String regEx = "[\\u4e00-\\u9fa5]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		while (m.find()) {
			return true;
		}
		return false;
	}

}
