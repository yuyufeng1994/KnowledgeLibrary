package com.lib.utils;

import java.util.LinkedList;
import java.util.List;

public class ParagraphUtil {
	
	/**
     * 将文本切割为段落
     * @param content
     * @return
     */
    public static List<String> toParagraphList(String content)
    {
        return toParagraphList(content.toCharArray());
    }
    
    public static List<String> toParagraphList(char[] chars)
    {
    	
    	StringBuilder sb = new StringBuilder();

        List<String> paragraps = new LinkedList<String>();
        
        String  paragrap = "";
        
        for (int i = 0; i < chars.length; ++i)
        {
            if (sb.length() == 0 && (Character.isWhitespace(chars[i])))
            {
                continue;
                
            }else if(chars[i]=='。'||chars[i]=='!'||chars[i]=='?'||chars[i]=='…')
            {	
            	
          
            	if(chars[i+1]==' '&&chars[i+2]==' '||chars[i+1]=='\n'||chars[i+1]=='\r'||chars[i+1]=='…')
            	{   
            		sb.append(chars[i]);
            		sb.append(chars[i+1]);
            		String content = sb.toString();
                    if (content.length() > 0)
                    {
                    	paragrap+=content;
                    }
                    sb = new StringBuilder();
            		paragraps.add(paragrap);
            		paragrap="";
            	}
            }
            else{
            	 sb.append(chars[i]);
            	 String content = sb.toString();
                 if (content.length() > 0)
                 {
                 	paragrap+=content;
                 }
                 sb = new StringBuilder();
            }
            
        }
        return paragraps;
    }
  
    public static void main(String[] args) {
		String content="";
		
		System.out.println(toParagraphList(content));
	}
}
