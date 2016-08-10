package com.lib.web.user.main;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.lib.dto.FileInfoVO;
import com.lib.dto.ForkFileInfoVo;

import com.lib.dto.LuceneSearchVo;
import com.lib.dto.PageVo;

import com.lib.dto.JsonResult;
import com.lib.entity.DocInfo;

import com.lib.entity.FileInfo;
import com.lib.entity.ForkInfo;
import com.lib.entity.UserInfo;
import com.lib.enums.Const;
import com.lib.service.user.FileInfoService;
import com.lib.service.user.FileManageService;
import com.lib.service.user.ForkInfoService;
import com.lib.service.user.LuceneService;
import com.lib.service.user.UserService;

/**
 * 主要页面跳转
 * 
 * @author Yu Yufeng
 *
 */
@Controller
@RequestMapping("/user")
public class MyResourceController {
	@Autowired
	private UserService userService;

	@Autowired
	private FileManageService fileManageService;

	@Autowired
	private FileInfoService fileInfoService;
	// 收藏操作service
	@Autowired
	private ForkInfoService forkInfoService;

	@Autowired
	private LuceneService luceneService;


	@RequestMapping(value = "/file-del/{fileId}", method = RequestMethod.POST)
	public @ResponseBody JsonResult delMyfile(HttpSession session, @PathVariable("fileId") Long fileId) {

		UserInfo user = (UserInfo) session.getAttribute(Const.SESSION_USER);
		JsonResult jr = null;
		try {
			fileInfoService.delFileById(fileId);
			FileInfo fileInfo = new FileInfo();
			fileInfo.setFileId(fileId);
			luceneService.deleteFileIndex(fileInfo);
			jr = new JsonResult(true, "删除成功");
		} catch (Exception e) {
			//e.printStackTrace();
			jr = new JsonResult(false, "删除失败");
		}

		return jr;
	}


	/**
	 * 跳转到我的资源
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/myfiles/{pageNo}", method = RequestMethod.GET)
	public String myFiles(Model model, @PathVariable("pageNo") Integer pageNo, HttpSession session, String searchValue,
			String searchNULL) {
		if (pageNo == null) {
			pageNo = 1;
		}

		if (searchNULL != null) {
			searchValue = "";
		}
		if (searchValue == null) {
			searchValue = (String) session.getAttribute(Const.MYFILE_SEARCH_VALUE);
			if (searchValue == null) {
				searchValue = "";
			}
		}
		session.setAttribute(Const.MYFILE_SEARCH_VALUE, searchValue);
		UserInfo user = (UserInfo) session.getAttribute(Const.SESSION_USER);
		PageInfo<FileInfoVO> page = fileManageService.getFileInfoPageByUserId(pageNo, user.getUserId(), "file_id desc",
				searchValue);
		model.addAttribute("page", page);
		return "file/myfiles";
	}

	/**
	 * 跳转到我的收藏
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/myforks/{docId}/{pageNo}", method = RequestMethod.GET)
	public String myForks(Model model, @PathVariable("pageNo") Integer pageNo, @PathVariable("docId") Long docId,
			String search, HttpSession session) {
		UserInfo user = (UserInfo) session.getAttribute(Const.SESSION_USER);
		PageInfo<ForkFileInfoVo> page = forkInfoService.getFileForkInfoPageByUserId(pageNo, user.getUserId(), docId,
				user.getUserName(), search);
		model.addAttribute("page", page);
		return "file/myforks";
	}
	
	/**
	 * 跳转到我的搜索
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/mySearch/{flag}/{pageNo}", method = RequestMethod.GET)
	public String mySearch(Model model,@PathVariable("pageNo") Integer pageNo,@PathVariable("flag") Integer flag) {
		//PageVo<LuceneSearchVo>  page=luceneService.search(fileInfo, pageNo, flag);
		//model.addAttribute("page", page);
		return "file/search";
	}
	/**
	 * 全文检索
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/search/{flag}/{pageNo}", method = RequestMethod.POST)
	public String search(Model model,FileInfo fileInfo,Date endTime,String keyWord,@PathVariable("pageNo") Integer pageNo,@PathVariable("flag") Integer flag) {
		
		PageVo<LuceneSearchVo>  page=luceneService.search(fileInfo,keyWord,endTime,pageNo, flag);
		model.addAttribute("page", page);
		model.addAttribute("file", fileInfo);
		model.addAttribute("keyWord", keyWord);
		model.addAttribute("endTime", endTime);
		return "file/search";
	}
	
	
}
