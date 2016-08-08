package com.lib.service.admin.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lib.dao.ClassificationDao;
import com.lib.entity.Classification;
import com.lib.enums.Const;
import com.lib.service.admin.ClassificationService;

@Service
public class ClassificationServiceImpl implements ClassificationService {

	@Autowired
	private ClassificationDao ClassificationDao;
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Override
	public void insert(String classificationName, Long parentId) {
		// TODO Auto-generated method stub
		String parentPath = ClassificationDao.findFatherPathById(parentId);
		if (parentPath == null) {
			parentPath = "";
		}
		parentPath = parentPath + "." + parentId;
		ClassificationDao.insert(classificationName, parentId, parentPath);
	}
	

	/**
	 * 删除节点
	 */
	@Override
	public int delete(Long classificationId) {
		Classification c = ClassificationDao.findById(classificationId);
		int res = ClassificationDao.delete(classificationId);
		if (res == 1) {
			try {
				FileUtils.forceDelete(new File(Const.ROOT_PATH + "thumbnail/" + c.getClassificationPicture() + ".png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return res;
	}

	@Override
	public void modify(String classificationName, Long classificationId) {
		// TODO Auto-generated method stub
		ClassificationDao.modify(classificationName, classificationId);
	}

	@Override
	public Classification findById(Long classificationId) {
		// TODO Auto-generated method stub
		return ClassificationDao.findById(classificationId);
	}

	@Override
	public String findFatherPathById(Long classificationId) {
		// TODO Auto-generated method stub
		return ClassificationDao.findFatherPathById(classificationId);
	}

	@Override
	public List<Classification> findOneChildById(Long classificationId) {
		// TODO Auto-generated method stub
		return ClassificationDao.findOneChildById(classificationId);
	}

	@Override
	public List<Classification> findAllBotherById(Long classificationId) {
		// TODO Auto-generated method stub
		return ClassificationDao.findAllBotherById(classificationId);
	}

	@Override
	public List<Classification> findAllfatherById(Long classificationId) {
		// TODO Auto-generated method stub
		String parentPath = ClassificationDao.findFatherPathById(classificationId);
		String[] parentIds = parentPath.split("\\.");
		List<Classification> classifications = new ArrayList<Classification>();
		for (int i = 0; i < parentIds.length; i++) {
			// System.out.println(parentIds[i]);
			classifications.add(ClassificationDao.findById(Long.parseLong(parentIds[i])));
		}
		return classifications;
	}

	@Override
	public Map<String, List<Classification>> findAllChildById(String classificationId) {

		List<Classification> classifications = ClassificationDao.findAllChildById("%" + classificationId + "%");
		Map<String, List<Classification>> mapList = new HashMap<String, List<Classification>>();
		for (Classification c : classifications) {
			String str[] = c.getParentPath().split("\\.");
			String key = str[str.length];
			if (mapList.get(key) == null) {
				List<Classification> list = new ArrayList<Classification>();
				list.add(c);
				mapList.put(key, list);
			} else {
				mapList.get(key).add(c);
			}
		}
		return mapList;
	}

	@Override
	public List<Classification> findAllChildById1(String classificationId) {
		// TODO Auto-generated method stub
		return ClassificationDao.findAllChildById("%" + classificationId + "%");
	}

	@Override
	public int updatePicPath(Classification c) {
		return ClassificationDao.update(c);
	}


	@Override
	public int insert(Classification c) {
		// TODO Auto-generated method stub
		return ClassificationDao.insertByc(c);
	}

}
