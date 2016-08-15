package com.lib.web.user.main;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lib.dto.JsonResult;
import com.lib.dto.SerResult;
import com.lib.entity.Classification;
import com.lib.service.user.FileManageService;
import com.lib.service.user.LuceneService;

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
	@Autowired
	private LuceneService lservice;
	
	@RequestMapping(value = "/test-sub")
	public String sub(Model model,String str) {
		model.addAttribute("str", str);
		List<SerResult> list = lservice.getParagraph(str,10L);
		model.addAttribute("list", list);
		
		
		return "test";
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String login(Model model,HttpServletRequest req) throws MalformedURLException {
		String url = req.getRequestURL().toString();
		URL u = new URL(url);
		System.out.println(u.getHost()+":"+u.getPort());
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
