package com.lib.service.user;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.lib.entity.MessageInfo;

public interface MessageService {
	/**
	 * 插入一条消息记录
	 * @param msg
	 */
	public void insertMsg(MessageInfo msg);
	
	/**
	 * 根据用户id获取消息集合
	 * @param userId
	 * @return
	 */
	public PageInfo<MessageInfo> queryByPage(Integer pageNo, Long userId);
	
	public Long countMsgByUserId(Long userId);
	
	public void isReadMsg(Long msgId);
	
	public void deleteMsgByMsgId(Long msgId);
}
