package com.lib.service.user.impl;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lib.dao.CountDao;
import com.lib.service.user.AdminCountService;

@Service
public class AdminCountServiceImpl implements AdminCountService {

	@Autowired
	private CountDao countDao;

	@Override
	public Long getTodaysUpload() {
		Date date = new Date();
		Date res;
		Calendar cNow = Calendar.getInstance();
		cNow.setTime(date);
		res = cNow.getTime();
		cNow.set(Calendar.HOUR_OF_DAY, 0);
		cNow.set(Calendar.SECOND, 0);
		cNow.set(Calendar.MINUTE, 0);
		cNow.set(Calendar.MILLISECOND, 0);
		res = cNow.getTime();
//		cNow.add(Calendar.DATE, -1);
//		res = cNow.getTime();
		Long reso = countDao.getTodaysUpload(res, new Date());
		return reso;
	}

	@Override
	public Long getCountUserFiles(Long userId) {
		Long res = countDao.getCountUserFiles(userId);
		return res;
	}

	@Override
	public Long getCountPublicFiles(Long userId) {
		Long res = countDao.getCountPublicFiles(userId);
		return res;
	}

	@Override
	public Long getCountForkFiles(Long userId) {
		Long res = countDao.getCountForkFiles(userId);
		return res;
	}

}
