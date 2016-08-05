package com.lib.web.user.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lib.dto.FileInfoVO;
import com.lib.dto.JsonResult;
import com.lib.entity.Classification;
import com.lib.entity.UserInfo;
import com.lib.enums.Const;
import com.lib.service.user.FileManageService;

/**
 * Test的Controller
 * 
 * @author Yu Yufeng
 *
 */
@Controller
public class TestController {
	@Autowired
	private FileManageService fileManageService;

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String login(Model model) {
		model.addAttribute("date", new Date());
		return "test";
	}

	@RequestMapping(value = "/node", method = RequestMethod.GET)
	public @ResponseBody JsonResult<List<Classification>> getChildFileClass() {
		Long fileClassId = 1l;
		JsonResult<List<Classification>> jr = null;
		List<Classification> list = fileManageService.getAllChildClassesById(fileClassId);
		jr = new JsonResult<List<Classification>>(true, list);
		return jr;
	}

}
