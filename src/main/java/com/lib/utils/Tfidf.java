package com.lib.utils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Tfidf {
		
	
	
	private Map<String, Integer> map1=new LinkedHashMap<String, Integer>();
	
	private Map<String, Integer> map2=new LinkedHashMap<String, Integer>();
	
	public static Double Tfidf(List<String> list1,List<String> list2)
	{
		Tfidf tfidf=new Tfidf();
		return tfidf.Count(list1,list2);
		
	}
	private Double Count(List<String> list1,List<String> list2)
	{
	
		for(String str:list1)
		{
			if (!map1.containsKey(str)) {
				map1.put(str, 1);
				
			}else{
				map1.put(str, map1.get(str)+1);
			}
		}
		for(String str:list2)
		{
			if(!map1.containsKey(str))
			{
				map1.put(str, 0);
			}
			if (!map2.containsKey(str)) {
				map2.put(str, 1);
				
			}else{
				map2.put(str, map2.get(str)+1);
			}
		}
		return compute(map1,map2);
		
	}
	private Double compute(Map<String,Integer> map1,Map<String, Integer> map2)
	{
		Double tfidf=null;
		long num=0;
		long num1=0;
		long num2=0;
		for (Entry<String, Integer> entry : map1.entrySet()) {  
			  
		   num1+=entry.getValue()*entry.getValue();
		   if(map2.containsKey(entry.getKey()))
		   {
			   num2+=map2.get(entry.getKey())*map2.get(entry.getKey());
			   num+=entry.getValue()*map2.get(entry.getKey());
		   }
		   
		}
		tfidf=((double)num/(Math.sqrt(num1)*Math.sqrt(num2)));
		return tfidf;
	}
	
	public static void main(String[] args) {
		Tfidf tfidf=new Tfidf();
		List<String> list1=new ArrayList<String>();
		List<String> list2=new ArrayList<String>();
		list1.add("a");
		list1.add("b");
		list1.add("c");
		list1.add("d");
		list2.add("c");
		list2.add("b");
		list2.add("a");
		list2.add("d");
		long before=System.currentTimeMillis();
		System.out.println(Tfidf(list1, list2));
		System.out.println(System.currentTimeMillis()-before);
		
	}
}
