package com.lib.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lib.entity.RelationInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-dao.xml" })
public class RelationInfoDaoTest {
	@Autowired
	private RelationInfoDao dao;

	@Test
	public void testInsert() {
		RelationInfo r = new RelationInfo();
		r.setMainFileId(310l);
		r.setRelationFileId(314l);
		int res = dao.insert(r);
		System.out.println(res);
	}
	
	@Test
	public void testInsertList() {
		RelationInfo r;
		
		List<RelationInfo> rs = new ArrayList<RelationInfo>();
		r = new RelationInfo();
		r.setMainFileId(310l);
		r.setRelationFileId(315l);
		rs.add(r);
		
		r = new RelationInfo();
		r.setMainFileId(310l);
		r.setRelationFileId(316l);
		rs.add(r);
		
		r = new RelationInfo();
		r.setMainFileId(310l);
		r.setRelationFileId(317l);
		rs.add(r);
		
		int res = dao.insertList(rs);
		System.out.println(res);
	}

	@Test
	public void testSelectList() {
		List<RelationInfo> list = dao.selectList(310l);
		for(RelationInfo r: list){
			System.out.println(r);
		}
	}

	@Test
	public void testDel() {
		RelationInfo r = new RelationInfo();
		r.setMainFileId(310l);
		r.setRelationFileId(314l);
		int res = dao.delete(r);
	}

}
