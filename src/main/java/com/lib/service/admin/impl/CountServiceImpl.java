package com.lib.service.admin.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.regexp.recompile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lib.dao.CountDao;
import com.lib.dao.FileInfoDao;
import com.lib.dto.ActiveUserInfo;
import com.lib.dto.ClassesClickInfo;
import com.lib.dto.ClickInfo;
import com.lib.dto.FileScoreInfo;
import com.lib.entity.FileInfo;
import com.lib.service.admin.CountService;
import com.lib.utils.MahoutRecommender;

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
		Date dNow = new Date(); // 当前时间
		Date dBefore = new Date();
		Calendar calendar = Calendar.getInstance(); // 得到日历
		calendar.setTime(dNow);// 把当前时间赋给日历
		calendar.add(calendar.MONTH, -before); // 设置为前1月
		dBefore = calendar.getTime(); // 得到前1月的时间
		List<ClassesClickInfo> classesClickInfos = countDao.getHotClasses(dBefore);
		for (int i = 0; i < classesClickInfos.size(); i++) {
			ClassesClickInfo classesClickInfo = classesClickInfos.get(i);
			Long forkFileTimes = countDao.getForkFileTimes(dBefore, classesClickInfo.getClassId());
			Long clickTimes = countDao.getClickTimes(dBefore, classesClickInfo.getClassId());
			classesClickInfo.setForkFileTimes(forkFileTimes);
			classesClickInfo.setClassClickTimes(clickTimes);
			Long score = classesClickInfo.getUploadFileTimes() * 10 + classesClickInfo.getForkFileTimes() * 5
					+ classesClickInfo.getClassClickTimes();
			classesClickInfo.setScore(score);
		}
		return classesClickInfos;
	}

	@Override
	public List<ActiveUserInfo> getActiveUsers(int before) {
		Date dNow = new Date(); // 当前时间
		Date dBefore = new Date();
		Calendar calendar = Calendar.getInstance(); // 得到日历
		calendar.setTime(dNow);// 把当前时间赋给日历
		calendar.add(calendar.MONTH, -before); // 设置为前1月
		dBefore = calendar.getTime(); // 得到前1月的时间
		List<ActiveUserInfo> activeUserInfos = countDao.getActiveUsers(dBefore);
		for (int i = 0; i < activeUserInfos.size(); i++) {
			ActiveUserInfo activeUserInfo = activeUserInfos.get(i);
			Long userForkFileTimes = countDao.getUserForkFileTimes(dBefore, activeUserInfo.getUserId());
			Long userClickTimes = countDao.getUserClickTimes(dBefore, activeUserInfo.getUserId());
			activeUserInfo.setForkFileTimes(userForkFileTimes);
			activeUserInfo.setClickFileTimes(userClickTimes);
			Long active = activeUserInfo.getUploadFileTimes() * 10 + activeUserInfo.getForkFileTimes() * 5
					+ activeUserInfo.getClickFileTimes();
			activeUserInfo.setActive(active);
		}
		return activeUserInfos;
	}

	@Override
	public List<Long> getUploadTimesByTime() {
		Date now = new Date(); // 当前时间
		Date before = null;
		List<Long> list = new ArrayList<Long>();
		for (int i = 1; i <= 32; i++) {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -i);
			before = cal.getTime();
			now.setHours(0);
			before.setHours(0);
			Long l = countDao.getUploadTimesByTime(before, now);
			list.add(l);
			now = before;
		}
		return list;
	}

	@Override
	public List<Long> getClickTimesByTime() throws ParseException {
		Date now = new Date(); // 当前时间
		Date before = null;
		List<Long> list = new ArrayList<Long>();
		for (int i = 1; i <= 32; i++) {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -i);
			before = cal.getTime();
			now.setHours(0);
			before.setHours(0);
			Long l = countDao.getClickTimesByTime(before, now);
			list.add(l);
			now = before;
		}
		return list;
	}

	@Override
	public List<FileInfo> getFileScoreList(Long userId,int recomNum) {
		List<FileInfo> files = new ArrayList<FileInfo>();
		try {
			List<RecommendedItem> recomList = MahoutRecommender.recommender(userId,recomNum);
			for(int i = 0;i<recomList.size();i++){
				files.add(fileInfoDao.getFileInfoByFileId(recomList.get(i).getItemID()));
			}
		} catch (Exception e) {

		}
		return files;
	}
}
