package com.lib.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lib.entity.Classification;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-dao.xml" })
public class ClassificationDaoTest {

	@Resource
	private ClassificationDao ClassificationDao;

	@Test
	public void insert() {

		Classification classification = new Classification();
		classification.setClassificationName("数学");
		classification.setParentId(1L);
		String parentPath = ClassificationDao.findFatherPathById(classification.getParentId());
		if (parentPath == null) {
			parentPath = "";
		}
		classification.setParentPath(parentPath + "." + classification.getParentId());
		ClassificationDao.insert(classification.getClassificationName(), classification.getParentId(),
				classification.getParentPath());
	}

	@Test
	public void delete() {

		Classification classification = new Classification();
		classification.setClassificationId(19L);
		ClassificationDao.delete(classification.getClassificationId());
	}

	@Test
	public void modify() {

		Classification classification = new Classification();
		classification.setClassificationId(20L);
		classification.setClassificationName("物理");
		ClassificationDao.modify(classification.getClassificationName(), classification.getClassificationId());
	}

	@Test
	public void findById() {

		Classification classification = new Classification();
		classification.setClassificationId(15L);
		System.out.println(ClassificationDao.findById(classification.getClassificationId()));

	}

	@Test
	public void findAllChildById() {

		Classification classification = new Classification();
		classification.setClassificationId(1L);
		List<Classification> lists = ClassificationDao.findAllChildById(classification.getClassificationId());
		for (Classification list : lists) {
			System.out.println(list);
		}
	}


	@Test
	public void findAllBotherById() {

		Classification classification = new Classification();
		classification.setClassificationId(15L);
		Long parentId = ClassificationDao.findById(classification.getClassificationId()).getParentId();
		List<Classification> lists = ClassificationDao.findAllChildById(parentId);
		for (Classification list : lists) {
			System.out.println(list);
		}
	}

	@Test
	public void findAllfatherById() {

		Classification classification = new Classification();
		classification.setClassificationId(15L);
		String parentPath = ClassificationDao.findFatherPathById(classification.getClassificationId());
		// System.out.println(parentPath);
		String[] parentIds = parentPath.split("\\.");
		List<Classification> classifications = new ArrayList<Classification>();
		for (int i = 0; i < parentIds.length; i++) {
			// System.out.println(parentIds[i]);
			classifications.add(ClassificationDao.findById(Long.parseLong(parentIds[i])));
		}
		System.out.println(classifications);
	}
}
