package com.lib.service.admin.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lib.dao.CountDao;
import com.lib.service.admin.CountService;

@Service
public class CountServiceImpl implements CountService {
	@Autowired
	private CountDao countDao;
}
