package com.lib.dao;

import java.util.List;

import com.lib.dto.ForkFileInfoVo;
import com.lib.entity.ForkInfo;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * 我的收藏dao
 * @author drmam
 *
 */
public interface ForkInfoDao {
	
	
	/**
	 * 插入收藏
	 * @param forkInfo
	 * @return
	 */
	int insert(ForkInfo forkInfo);
	
	/**
	 * 删除收藏
	 * @param forkId
	 * @return
	 */
	int delete(Long forkId);
	/**
	 * 修改收藏
	 * @param forkId
	 * @return
	 */
	int modify(ForkInfo forkInfo);
	/**
	 * 查找收藏
	 * @param forkId
	 * @return
	 */
	List<ForkInfo> findByDocId(Long docId);
	/**
	 * 查找一个文件夹
	 * @param docId
	 * @return
	 */
	ForkInfo findByForkId(Long forkId);
	/**
	 * 查找用户全部收藏
	 * @return
	 */
	List<ForkFileInfoVo> findAll(Long docUserId);
	
	/**
	 * 查询用户一个文件夹下的收藏
	 * @param docId
	 * @return
	 */
	List<ForkFileInfoVo> findAllByDocId(@Param("docId")Long docId,@Param("docUserId")Long docUserId);



	
}
