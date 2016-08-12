package com.lib.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.parser.txt.TXTParser;
/*import org.apache.tika.parser.asm.ClassParser;
import org.apache.tika.parser.html.HtmlParser;
import org.apache.tika.parser.microsoft.ooxml.OOXMLParser;
import org.apache.tika.parser.odf.OpenDocumentParser;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.parser.txt.TXTParser;
import org.apache.tika.parser.xml.XMLParser;*/
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.ContentHandler;

public class ExtractUtil {

	
	public static String Parser(String filePath,String fileExt){
		
		
		Parser parser = new AutoDetectParser();
		if(fileExt.equals("txt"))
		{
			parser=new TXTParser();
		}
        InputStream input = null;  
        try{  
            Metadata metadata = new Metadata();  
            metadata.set(Metadata.CONTENT_ENCODING, "utf-8");  
             //metadata.set(Metadata.RESOURCE_NAME_KEY, file.getName());  
            input = new FileInputStream(new File(filePath));  
            ContentHandler handler = new BodyContentHandler(1024*1024*10);//当文件大于100000时，new BodyContentHandler(1024*1024*10);   
            ParseContext context = new ParseContext();  
            //context.set(Parser.class,parser);  
            parser.parse(input,handler, metadata,context);  
            /*for(String name:metadata.names()) {  
                System.out.println(name+":"+metadata.get(name));  
            } */
            //System.out.println(handler.toString());
            return handler.toString().trim();  
        }catch (Exception e){  
            e.printStackTrace();  
        }finally {  
            try {  
                if(input!=null)input.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
        return null;  
		
	}
	public static String Parser(FileInputStream input){
		
		
		Parser parser = new AutoDetectParser();  
		
        try{  
            Metadata metadata = new Metadata();  
            metadata.set(Metadata.CONTENT_ENCODING, "utf-8");   
            ContentHandler handler = new BodyContentHandler(1024*1024*10);//当文件大于100000时，new BodyContentHandler(1024*1024*10);   
            ParseContext context = new ParseContext();  
            context.set(Parser.class,parser);  
            parser.parse(input,handler, metadata,context);  
            /*for(String name:metadata.names()) {  
                System.out.println(name+":"+metadata.get(name));  
            }  */
            return handler.toString();  
        }catch (Exception e){  
            e.printStackTrace();  
        }finally {  
            try {  
                if(input!=null)input.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
        return null;  
		
	}
	
	/*private static  void JudgeParser(String fileExt)
	{
		if(fileExt.equals("xml"))
		{
			parser=new XMLParser();
		}else if(fileExt.equals("pdf")){
			
			parser=new PDFParser(); 
		}else if(fileExt.equals("odf")){
			
			parser=new OpenDocumentParser();
		}else if(JudgeUtils.isOfficeFile(fileExt)){
			
			parser=new OOXMLParser();
		}else if(fileExt.equals("txt")){
			
			parser=new TXTParser();
		}else if(fileExt.equals("html")){
			
			parser=new HtmlParser();
		}else if(fileExt.equals(".class")){
			parser = new ClassParser();
		}

	}*/
}
