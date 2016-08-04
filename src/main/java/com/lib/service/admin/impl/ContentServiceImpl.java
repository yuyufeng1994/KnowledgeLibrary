package com.lib.service.admin.impl;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.web.context.ContextLoader;

import com.lib.enums.Const;
import com.lib.service.admin.ContentService;

public class ContentServiceImpl implements ContentService {

	@Override
	@PostConstruct
	public void init() {
		
	}

	@Override
	@PreDestroy
	public void dostory() {

	}

}
