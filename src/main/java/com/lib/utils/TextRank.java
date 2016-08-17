package com.lib.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import com.hankcs.hanlp.dictionary.CoreSynonymDictionary;
public class TextRank {
	 /**
     * 提取多少个关键字
     */
    int nKeyword = 10;
    /**
     * 阻尼系数一般取值为0.85
     */
    final static float d = 0.85f;
    /**
     * 最大迭代次数
     */
    final static int max_iter = 200;
    final static float min_diff = 0.001f;
    
    /**
     * 使用已经分好的词来计算rank
     * @param termList
     * @return
     */
    public Map<String,Float> getRank(List<String> termList)
    {
        List<String> wordList = new ArrayList<String>(termList.size());
     
//        System.out.println(wordList);
        Map<String, Set<String>> words = new TreeMap<String, Set<String>>();
        Map<Map<String,String>,Long> weights= new HashMap<Map<String,String>,Long>();
        Queue<String> que = new LinkedList<String>();
        for (String w1 : wordList)
        {
           for(String w2 : wordList)
           {
        	   
        	   if (!words.containsKey(w1))
               {
                   words.put(w1, new TreeSet<String>());
               }
        	   long weight=CoreSynonymDictionary.distance(w1, w2);
        	   if(weight!=0&&weight<10000000)
        	   {
        		 
        		   words.get(w1).add(w2);
                   words.get(w2).add(w1);
                   Map<String,String> map=new HashMap<String, String>();
                   map.put(w1, w2);
                   map.put(w2, w1);
                   weights.put(map, weight);
        	   }
           }
        }
//        System.out.println(words);
        Map<String, Float> score = new HashMap<String, Float>();
        for (int i = 0; i < max_iter; ++i)
        {
            Map<String, Float> m = new HashMap<String, Float>();
            float max_diff = 0;
            for (Map.Entry<String, Set<String>> entry : words.entrySet())
            {
                String key = entry.getKey();
                Set<String> value = entry.getValue();
                
                for (String element : value)
                {
                	  Map<String,String> map=new HashMap<String, String>();
                      map.put(key, element);
                      map.put(element, key);
                      long weight=weights.get(map);
                    if (key.equals(element) || weight == 0) continue;
                    m.put(key, (1-d) + d / weight * (score.get(element) == null ? 0 : score.get(element)));
                }
                max_diff = Math.max(max_diff, Math.abs(m.get(key) - (score.get(key) == null ? 0 : score.get(key))));
            }
            score = m;
            if (max_diff <= min_diff) break;
        }
        
        return score;
    }
    public static void main(String[] args) {
    	
    	
    }
}
