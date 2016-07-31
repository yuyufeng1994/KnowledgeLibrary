package com.lib.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lib.enums.Const;
import com.lib.service.user.OfficeConvert;
import com.lib.service.user.impl.OfficeConvertImpl;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class TranslateUtils {
     
	
	public static OfficeConvert getOfficeConvert() {
		org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration config = new org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration();
		config.setOfficeHome("C:\\Program Files (x86)\\OpenOffice 4\\");
		// OpenOffice安装路径
		config.setPortNumber(8100);
		// 启动服务端口号
		config.setTaskExecutionTimeout(180000);
		// 单任务执行超时 1000*60 1分钟
		config.setTaskQueueTimeout(60000000);
		// 任务队列超时 1000*60 * 1000 1000分钟
		OfficeConvert imp = new OfficeConvertImpl();
		imp.setConvert(true);
		imp.setConfiguration(config);
		// imp.setPdf2swf("D:\\DevInstall\\SWFTools\\pdf2swf.exe");
		List<String> list = new ArrayList<String>();
		Collections.addAll(list, new String[] { "doc", "docx", "xls", "xlsx", "ppt", "pptx" });
		imp.setConvertExtList(list);
		return imp;
	}
	
    /** 
     * Mencoder: 
     * 对ffmpeg无法解析的文件格式(wmv9，rm，rmvb等),可以先用别的工具（mencoder）转换为avi(ffmpeg能解析的)格式. 
     * @param type 
     * @param inputFile 
     * @return 
     */  
	public static boolean processAVI(String filePath, String outPath) {  
        File file =new File(outPath);  
       
        if(file.exists()){
        	System.out.println("avi文件已经存在！无需转换");
        	return false;
        }  
        List<String> commend = new java.util.ArrayList<String>();  
        commend.add(Const.CONTAINER_PATH+"resource/mencoder/mencoder.exe");  
        commend.add(filePath);  
        commend.add("-oac");  
        commend.add("mp3lame");  
        commend.add("-lameopts");  
        commend.add("preset=64");  
        commend.add("-ovc");  
        commend.add("xvid");  
        commend.add("-xvidencopts");  
        commend.add("bitrate=600");  
        commend.add("-of");  
        commend.add("avi");  
        commend.add("-o");  
        commend.add(outPath);  
        StringBuffer test=new StringBuffer();  
        for(int i=0;i<commend.size();i++)  
            test.append(commend.get(i)+" ");  
       // System.out.println(test);  
        try   
        {  
            ProcessBuilder builder = new ProcessBuilder();  
            builder.command(commend);  
            Process p=builder.start();  
            
            /** 
             * 清空Mencoder进程 的输出流和错误流 
             * 因为有些本机平台仅针对标准输入和输出流提供有限的缓冲区大小， 
             * 如果读写子进程的输出流或输入流迅速出现失败，则可能导致子进程阻塞，甚至产生死锁。  
             */  
            final InputStream is1 = p.getInputStream();  
            final InputStream is2 = p.getErrorStream();  
            new Thread() {  
                public void run() {  
                    BufferedReader br = new BufferedReader(new InputStreamReader(is1));  
                    try {  
                        String lineB = null;  
                        while ((lineB = br.readLine()) != null ){  
                            if(lineB != null)System.out.println(lineB);  
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
                        while ( (lineC = br2.readLine()) != null){  
                            if(lineC != null)System.out.println(lineC);  
                        }  
                    } catch (IOException e) {  
                        e.printStackTrace();  
                    }  
                }  
            }.start();   
              
            //等Mencoder进程转换结束，再调用ffmpeg进程  
            p.waitFor();  
            p.destroy();
           // System.out.println("who cares");  
            return true;  
        }catch (Exception e){   
            System.err.println(e);   
            return false;  
        }   
    }  
	
	 /** 
     *  ffmepg: 能解析的格式：（asx，asf，mpg，wmv，3gp，mp4，mov，avi，flv等） 
     * @param inputFile 
     * @param outputFile 
     * @return 
     */  
    public static boolean processFLV(String filePath, String outPath) {  
    	
    	System.out.println("abcd");
    	File file = new File(filePath);
		if (!file.exists()) {

			System.out.println("路径[" + filePath + "]对应的视频文件不存在!");

		}
		//System.out.println("1234");
        File outfile = new File(outPath);
        if(outfile.exists()){
        	System.out.println("flv文件已经存在！无需转换");
        	return true;
        } else {
        	//System.out.println("正在转换成flv文件……");
        	
        	List<String> commend = new java.util.ArrayList<String>();  
 	        //低精度  
 	        commend.add(Const.CONTAINER_PATH+"resource/ffmpeg/ffmpeg.exe");
 	        System.out.println(Const.CONTAINER_PATH+"resource/ffmpeg/ffmpeg.exe");
 	        commend.add("-i");  
 	        commend.add(filePath);  
 	        commend.add("-ab");  
 	        commend.add("128");  
 	        commend.add("-acodec");  
 	        commend.add("libmp3lame");  
 	        commend.add("-ac");  
 	        commend.add("1");  
 	        commend.add("-ar");  
 	        commend.add("22050");  
 	        commend.add("-r");  
 	        commend.add("29.97"); 
 	        // 清晰度 -qscale 4 为最好但文件大, -qscale 6就可以了
 	        commend.add("-qscale");  
 	        commend.add("6");  
 	        commend.add("-y");  
 	        commend.add(outPath);  
 	        StringBuffer test=new StringBuffer();  
 	        for(int i=0;i<commend.size();i++)  
 	            test.append(commend.get(i)+" ");  
 	        System.out.println(test);  
 	       try   
 	        {  
 	            ProcessBuilder builder = new ProcessBuilder();  
 	            builder.command(commend);  
 	            Process p=builder.start();  
 	            
 	            /** 
 	             * 清空Mencoder进程 的输出流和错误流 
 	             * 因为有些本机平台仅针对标准输入和输出流提供有限的缓冲区大小， 
 	             * 如果读写子进程的输出流或输入流迅速出现失败，则可能导致子进程阻塞，甚至产生死锁。  
 	             */  
 	            final InputStream is1 = p.getInputStream();  
 	            final InputStream is2 = p.getErrorStream();  
 	            new Thread() {  
 	                public void run() {  
 	                    BufferedReader br = new BufferedReader(new InputStreamReader(is1));  
 	                    try {  
 	                        String lineB = null;  
 	                        while ((lineB = br.readLine()) != null ){  
 	                            if(lineB != null);
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
 	                        while ( (lineC = br2.readLine()) != null){  
 	                            if(lineC != null);
 	                            	//System.out.println(lineC);  
 	                        }  
 	                    } catch (IOException e) {  
 	                        e.printStackTrace();  
 	                    }  
 	                }  
 	            }.start();   
 	              
 	            //等Mencoder进程转换结束，再调用ffmpeg进程  
 	            p.waitFor();  
 	            p.destroy();
 	           // System.out.println("who cares");  
 	            return true;  
 	        }catch (Exception e){   
 	            System.err.println(e);   
 	            return false;  
 	        } 
 	       
        }
       
    }  
    
    public static boolean toPNG(String filePath, String outPath) 
    {
        File fi = new File(filePath);
     
        File fo = new File(outPath);
        BufferedImage im;
		try {
			im = ImageIO.read(fi);
		    if(ImageIO.write(im, "png", fo))
		    {
		    	return true;
		    }
		    return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
       
    }

	

}
