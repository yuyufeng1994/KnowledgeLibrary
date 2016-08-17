package com.lib.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lib.entity.MessageInfo;

public interface MessageInfoDao {
	/**
	 * 插入一条消息记录
	 * @param msg
	 * @return
	 */
	MessageInfo insertMsg(MessageInfo msg);
	
	/**
	 * 根据用户查找消息集合
	 * @param UserId
	 * @return
	 */
	List<MessageInfo> queryMsgByUserId(@Param("userId")Long userId);
	
	/**
	 * 统计用户未读消息
	 * @param userId
	 * @return
	 */
	Long countMsgByUserId(@Param("userId")Long userId);
	/**
	 * 设置消息已读
	 */
	void isReadMsg(@Param("msgId")Long msgId);
	/**
	 * 根据消息id删除消息
	 * @param msgId
	 */
	void deleteMsgByMsgId(@Param("msgId")Long msgId);
}
