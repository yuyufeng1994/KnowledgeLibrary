package com.lib.utils;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.icepdf.core.pobjects.Document;
import org.icepdf.core.util.GraphicsRenderingHints;
import com.lib.enums.Const;

/**
 * 获取缩略图工具
 * 
 * @author zcq
 *
 */
public class ThumbnailUtils {

	public static void pdfGetThumb(String path, String filePath) {
		// TODO Auto-generated method stub
		Document document = new Document();

		try {
			document.setFile(path);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		BufferedImage image = null;

		RenderedImage rendImage = null;

		for (int i = 0; i < 1; i++) {
			image = (BufferedImage) document.getPageImage(i, GraphicsRenderingHints.SCREEN, i, 0f, 1.5f);
			rendImage = image;
			File file = new File(filePath);
			if (!file.exists()) {
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					ImageIO.write(rendImage, "png", file);
				} catch (IOException e) {
					e.printStackTrace();
				}
				image.flush();
			}
		}

		document.dispose();

	}

	public static boolean videoGetThumb(String filePath, String outPath) {
		File file = new File(filePath);

		if (!file.exists()) {

			System.out.println("路径[" + filePath + "]对应的视频文件不存在!");

		}

		List<String> commands = new ArrayList<String>();

		// System.out.println(Const.FFMPEG_PATH);
		commands.add(Const.CONTAINER_PATH + "resource/ffmpeg/ffmpeg.exe");

		commands.add("-i");

		commands.add(filePath);

		commands.add("-y");

		commands.add("-f");

		commands.add("image2");

		commands.add("-ss");

		commands.add("38");

		commands.add("-t");

		commands.add("0.001");

		commands.add("-s");

		commands.add("320x240");

		commands.add(outPath);
		try {
			ProcessBuilder builder = new ProcessBuilder();
			builder.command(commands);
			Process p = builder.start();

			/**
			 * 清空Mencoder进程 的输出流和错误流 因为有些本机平台仅针对标准输入和输出流提供有限的缓冲区大小，
			 * 如果读写子进程的输出流或输入流迅速出现失败，则可能导致子进程阻塞，甚至产生死锁。
			 */
			final InputStream is1 = p.getInputStream();
			final InputStream is2 = p.getErrorStream();
			new Thread() {
				public void run() {
					BufferedReader br = new BufferedReader(new InputStreamReader(is1));
					try {
						String lineB = null;
						while ((lineB = br.readLine()) != null) {
							if (lineB != null);
								//System.out.println(lineB);
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}.start();
			new Thread() {
				public void run() {
					BufferedReader br2 = new BufferedReader(new InputStreamReader(is2));
					try {
						String lineC = null;
						while ((lineC = br2.readLine()) != null) {
							if (lineC != null);
							//System.out.println(lineB);
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}.start();

			// 等Mencoder进程转换结束，再调用ffmpeg进程
			p.waitFor();
			p.destroy();
			// System.out.println("who cares");
			return true;
		} catch (Exception e) {
			System.err.println(e);
			return false;
		}
	}
}
