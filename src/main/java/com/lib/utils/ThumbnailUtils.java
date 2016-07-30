package com.lib.utils;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.icepdf.core.pobjects.Document;
import org.icepdf.core.util.GraphicsRenderingHints;
import com.lib.enums.Const;

/**
 * 获取缩略图工具
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
	
	public static void videoGetThumb(String path, String filePath) {
		File file = new File(path);
		
		if (!file.exists()) {

			System.err.println("路径[" + path + "]对应的视频文件不存在!");

		}

		List<String> commands = new ArrayList<String>();

		// System.out.println(Const.FFMPEG_PATH);
		commands.add(Const.CONTAINER_PATH+"resource/ffmpeg/ffmpeg.exe");

		commands.add("-i");

		commands.add(path);

		commands.add("-y");

		commands.add("-f");

		commands.add("image2");

		commands.add("-ss");

		commands.add("38");

		commands.add("-t");

		commands.add("0.001");

		commands.add("-s");

		commands.add("320x240");

		commands.add(filePath);

		try {

			ProcessBuilder builder = new ProcessBuilder();

			builder.command(commands);

			builder.start();

		} catch (Exception e) {

			e.printStackTrace();

		}
	}
}
