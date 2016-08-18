package com.lib.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.mahout.cf.taste.common.TasteException;  
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;  
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.slopeone.SlopeOneRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.TanimotoCoefficientSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;  
import org.apache.mahout.cf.taste.model.JDBCDataModel;  
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;  
import org.apache.mahout.cf.taste.recommender.RecommendedItem;  
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import com.lib.enums.Const;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
public class MahoutRecommender {  
	  
    /** 
     * use the MYSQL database as the input for MAHOUT   
     * 
     */  
	private static String SERVER_NAME = null;
	private static String USER = null;
	private static String PASSWORD = null;
	private static String DATABASE_NAME = null;
	private static String TABLE = null;
	private static String UID = null;
	private static String IID = null;
	private static String VAL = null;
	private static String TIME = null;
	private static MysqlDataSource dataSource;
	private static JDBCDataModel dataModel;
	static {
		Properties prop = new Properties();
		InputStream in = Const.class.getResourceAsStream("/jdbc.properties");
		try {
			prop.load(in);
			SERVER_NAME = prop.getProperty("server_name").trim();
			USER = prop.getProperty("jdbc.username").trim();
			PASSWORD = prop.getProperty("jdbc.password").trim();
			DATABASE_NAME = prop.getProperty("database_name").trim();
			TABLE = prop.getProperty("mahout_table").trim();
			UID = prop.getProperty("mahout_uid").trim();
			IID = prop.getProperty("mahout_iid").trim();
			VAL = prop.getProperty("mahout_val").trim();
			TIME = prop.getProperty("mahout_time").trim();
			dataSource =new MysqlDataSource();  
	        dataSource.setServerName(SERVER_NAME);  
	        dataSource.setUser(USER);  
	        dataSource.setPassword(PASSWORD);  
	        dataSource.setDatabaseName(DATABASE_NAME);  
	        dataModel =new MySQLJDBCDataModel(dataSource,TABLE,UID,IID,VAL, TIME);  
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 基于用户的协同过滤算法
	 * @param userId
	 * @param recomNum
	 * @return
	 * @throws TasteException
	 */
    public static  List<RecommendedItem> UserCFRcommender(Long userId,int recomNum) throws TasteException {  
        // TODO Auto-generated method stub  
        long t1=System.currentTimeMillis();  

        DataModel model=dataModel;  
     // 相似度 度量方式，采用皮尔逊相关系数度量，也可以采用其他度量方式  
        UserSimilarity similarity=new PearsonCorrelationSimilarity(model);  
        // 用户邻居，与给定用户最相似的一组用户  
        UserNeighborhood neighborhood=new NearestNUserNeighborhood(10,similarity,model);  
        // the Recommender.recommend() method's arguments: first one is the user id;  
        //     the second one is the number recommended  
        // 推荐引擎，合并这些组件，实现推荐  
        Recommender recommender=new GenericUserBasedRecommender(model,neighborhood,similarity);  
        // 为userID为2016001的用户推荐2个item  
        List<RecommendedItem> recommendations=recommender.recommend(userId, recomNum);  
        for(RecommendedItem recommendation:recommendations){  
            System.out.println(recommendation);  
        }  
        System.out.println("done and time spend:"+(System.currentTimeMillis()-t1));  
        return recommendations;
    }  
    /**
     * 基于物品的推荐
     * @param userId
     * @param recomNum
     * @return
     * @throws TasteException
     */
    public static  List<RecommendedItem> ItemCFRecommender(Long userId,int recomNum) throws TasteException {
    	  // TODO Auto-generated method stub  
        DataModel model=dataModel;  
     // 相似度 度量方式，采用皮尔逊相关系数度量，也可以采用其他度量方式  
        ItemSimilarity itemSimilarity = new PearsonCorrelationSimilarity(model);
        // 推荐引擎，合并这些组件，实现推荐  
        Recommender recommender=new GenericItemBasedRecommender(model, itemSimilarity);
        // 为userID为2016001的用户推荐2个item  
        List<RecommendedItem> recommendations=recommender.recommend(userId, recomNum);  
        return recommendations;
    }
    /**
     * slopone推荐
     * @param userId
     * @param recomNum
     * @return
     * @throws TasteException
     */
    public static  List<RecommendedItem> SlopOneRecommender(Long userId,int recomNum) throws TasteException {
  	  // TODO Auto-generated method stub  
      long t1=System.currentTimeMillis();  

      DataModel model=dataModel;  
      Recommender recommender=new SlopeOneRecommender(model);
      // 为userID为2016001的用户推荐2个item  
      List<RecommendedItem> recommendations=recommender.recommend(userId, recomNum);  
      for(RecommendedItem recommendation:recommendations){  
          System.out.println(recommendation);  
      }  
      System.out.println("done and time spend:"+(System.currentTimeMillis()-t1));  
      return recommendations;
  }
}  