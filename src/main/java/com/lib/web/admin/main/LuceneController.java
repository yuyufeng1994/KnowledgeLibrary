package com.lib.web.admin.main;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lib.service.user.LuceneService;

@Controller
@RequestMapping("/admin")
public class LuceneController {

	
	@Resource
	private LuceneService lucene;
	
	/**
	 * 分类管理主页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/lucene", method = RequestMethod.GET)
	public void addAllIndex() {
		lucene.addAllIndex();
	}
}
