package com.lib.web.user.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

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

import com.github.pagehelper.PageInfo;
import com.lib.dto.FileInfoVO;
import com.lib.dto.ForkFileInfoVo;
import com.lib.dto.JsonResult;
import com.lib.entity.DocInfo;
import com.lib.entity.ForkInfo;
import com.lib.entity.UserInfo;
import com.lib.enums.Const;
import com.lib.service.user.DocInfoService;
import com.lib.service.user.FileInfoService;
import com.lib.service.user.ForkInfoService;


/**
 * 专门处理文件的Controller
 * 
 * @author Yu Yufeng
 *
 */
@Controller
@RequestMapping("/user")
public class FileContentController {
	@Autowired
	private FileInfoService fileInfoService;
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    
	//文件夹操作service
	@Autowired
	private DocInfoService docInfoService;
	
	//收藏操作service
	@Autowired
	private ForkInfoService forkInfoService;
	
	/**
	 * 跳转到文件预览主体
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/file/{uuid}", method = RequestMethod.GET)
	public String upload(Model model, @PathVariable("uuid") String uuid, HttpSession session) {
		UserInfo user = (UserInfo) session.getAttribute(Const.SESSION_USER);
		FileInfoVO fileInfo = fileInfoService.getFileInfoByUuid(uuid);
		model.addAttribute("fileInfo", fileInfo);
		return "file/content";
	}
	
	/**
	 * 查找用户全部收藏夹
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/findAllByUserId", method = RequestMethod.GET)
	public @ResponseBody JsonResult findAllByUserId(HttpSession session) {
		
		UserInfo user = (UserInfo) session.getAttribute(Const.SESSION_USER);
		JsonResult jr = new JsonResult(true, "暂存成功");
		List<DocInfo> docInfos = docInfoService.findAllByUserId(Long.valueOf(user.getUserId()));
		if(docInfos.size()==0)
		{
		   docInfoService.insert("常用收藏", user.getUserId());
		   docInfos = docInfoService.findAllByUserId(Long.valueOf(user.getUserId()));
		}
		jr.setData(docInfos);
		return jr;
	}
	
	/**
	 * 添加收藏夹
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/insertDoc", method = RequestMethod.POST)
	public @ResponseBody JsonResult insertDoc(String docName,HttpSession session) {
		UserInfo user = (UserInfo) session.getAttribute(Const.SESSION_USER);
		JsonResult jr = new JsonResult(true, "添加成功");
		List<DocInfo> docInfos=docInfoService.findAllByUserId(user.getUserId());
		for(DocInfo d:docInfos)
		{
			if(d.getDocName().equals(docName)){
				
				jr.setError("添加失败,收藏夹名重复");
				jr.setSuccess(false);
				return jr;
			}
			
		}
		docInfoService.insert(docName, user.getUserId());
		return jr;
	}
	/**
	 * 删除收藏夹
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/deleteDoc", method = RequestMethod.POST)
	public @ResponseBody JsonResult deleteDoc(Long docId,HttpSession session) {
		UserInfo user = (UserInfo) session.getAttribute(Const.SESSION_USER);
		JsonResult jr = new JsonResult(true, "删除成功");
		docInfoService.delete(docId);
		return jr;
	}
	
	/**
	 * 添加一个收藏
	 * @param forkInfo
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/insertFork", method = RequestMethod.POST)
	public @ResponseBody JsonResult insertFork(ForkInfo forkInfo,HttpSession session) {
		JsonResult jr = new JsonResult(true, "收藏成功");
		List<ForkInfo> forkInfos =forkInfoService.findByDocId(forkInfo.getDocId());
		for(ForkInfo f:forkInfos){
			
			
			if(f.getFileId().equals(forkInfo.getFileId())){
				jr.setError("收藏失败,你已经收藏过该文件!");
				jr.setSuccess(false);
				return jr;
			}
			
		}
		forkInfoService.insert(forkInfo);
		return jr;
	}
	/**
	 * 修改一个收藏
	 * @param forkInfo
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/modifyFork", method = RequestMethod.POST)
	public @ResponseBody JsonResult modifyFork(ForkInfo forkInfo,HttpSession session) {
		JsonResult jr = new JsonResult(true, "修改成功");
		forkInfoService.modify(forkInfo);
		return jr;
	}
	/**
	 * 删除一个收藏
	 * @param forkInfo
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/deleteFork", method = RequestMethod.POST)
	public @ResponseBody JsonResult deleteFork(Long forkId,HttpSession session) {
		JsonResult jr = new JsonResult(true, "删除成功");
		forkInfoService.delete(forkId);
		return jr;
	}
	
	/**
	 * 根据文件名查找收藏
	 * @param forkInfo
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/findByFileName", method = RequestMethod.POST)
	public String findByFileName(Model model,Integer pageNo,String fileName,HttpSession session) {
		System.out.println(pageNo);
		System.out.println(fileName);
		UserInfo user = (UserInfo) session.getAttribute(Const.SESSION_USER);
		PageInfo<ForkFileInfoVo> page = forkInfoService.findByFileName(pageNo, fileName, user.getUserId());
		model.addAttribute("page", page);
		return "file/myforks";
	}
}
