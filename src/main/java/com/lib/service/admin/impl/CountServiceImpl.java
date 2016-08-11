package com.lib.service.admin.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lib.dao.CountDao;
import com.lib.dao.FileInfoDao;
import com.lib.dto.ClassesClickInfo;
import com.lib.dto.ClickInfo;
import com.lib.entity.FileInfo;
import com.lib.service.admin.CountService;

@Service
public class CountServiceImpl implements CountService {
	@Autowired
	private CountDao countDao;
	
	@Autowired
	private FileInfoDao fileInfoDao;

	@Override
	public List<ClickInfo> getHotFiles() {
		return countDao.getHotFiles();
	}

	@Override
	public List<ClassesClickInfo> getHotClass(int before) {
		Date dNow = new Date(); //当前时间
		Date dBefore = new Date();
		Calendar calendar = Calendar.getInstance(); //得到日历
		calendar.setTime(dNow);//把当前时间赋给日历
		calendar.add(calendar.MONTH, -before); //设置为前1月
		dBefore = calendar.getTime(); //得到前1月的时间
		List<ClassesClickInfo> classesClickInfos = countDao.getHotClasses(dBefore);
		for(int i = 0;i<classesClickInfos.size();i++){
			ClassesClickInfo classesClickInfo = classesClickInfos.get(i);
			Long forkFileTimes = countDao.getForkFileTimes(dBefore, classesClickInfo.getClassId());
			Long clickTimes = countDao.getClickTimes(dBefore, classesClickInfo.getClassId());
			classesClickInfo.setForkFileTimes(forkFileTimes);
			classesClickInfo.setClassClickTimes(clickTimes);
			Long score = classesClickInfo.getUploadFileTimes()*10+classesClickInfo.getForkFileTimes()*5+classesClickInfo.getClassClickTimes();
			classesClickInfo.setScore(score);
		}
		return classesClickInfos;
	}
}
