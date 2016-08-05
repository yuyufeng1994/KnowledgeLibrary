package com.lib.web.user.main;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.pagehelper.PageInfo;
import com.lib.dto.FileInfoVO;
import com.lib.entity.Classification;
import com.lib.service.user.FileManageService;
import com.lib.service.user.UserService;

/**
 * 主要页面跳转
 * 
 * @author Yu Yufeng
 *
 */
@Controller
@RequestMapping("/user")
public class MainController {
	@Autowired
	private FileManageService fileManageService;
	@Autowired
	private UserService userService;

	/**
	 * 跳转到主页
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String login(Model model) {
		model.addAttribute("date", new Date());
		return "main/index";
	}

	@RequestMapping(value = "/public/{fileClassId}/{pageNo}", method = RequestMethod.GET)
	public String publicResource(Model model, @PathVariable("fileClassId") Long fileClassId,
			@PathVariable("pageNo") Integer pageNo) {
		List<Classification> list = fileManageService.getClassificationByParentId(fileClassId);
		// 得到所有父节点链表
		List<Classification> plist = fileManageService.getFatherClassesById(fileClassId);
		Classification c = fileManageService.getClassificationById(fileClassId);
		// list.add(0, c);
		PageInfo<FileInfoVO> page= fileManageService.getAllChildFiles(pageNo, fileClassId);
		model.addAttribute("classi", c);
		model.addAttribute("list", list);
		model.addAttribute("plist", plist);
		
		model.addAttribute("page", page);
		
		
		
		return "main/public";
	}

}
