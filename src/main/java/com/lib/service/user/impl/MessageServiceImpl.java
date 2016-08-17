package com.lib.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lib.dao.MessageInfoDao;
import com.lib.entity.MessageInfo;
import com.lib.entity.UserInfo;
import com.lib.enums.Const;
import com.lib.service.user.MessageService;
@Service
public class MessageServiceImpl implements MessageService{
	
	@Autowired
	private MessageInfoDao msgDao;
	
	@Override
	public void insertMsg(MessageInfo msg) {
		msgDao.insertMsg(msg);
	}

	@Override
	public PageInfo<MessageInfo> queryByPage(Integer pageNo, Long userId) {
		PageHelper.startPage(pageNo,  Const.COMMON_PAGE_SIZE);
		List<MessageInfo> list = msgDao.queryMsgByUserId(userId);
		PageInfo<MessageInfo> page = new PageInfo<MessageInfo>(list);
		return page;
	}

	@Override
	public Long countMsgByUserId(Long userId) {
		return msgDao.countMsgByUserId(userId);
	}

	@Override
	public void isReadMsg(Long msgId) {
		msgDao.isReadMsg(msgId);
	}

	@Override
	public void deleteMsgByMsgId(Long msgId) {
		msgDao.deleteMsgByMsgId(msgId);
	}
}
