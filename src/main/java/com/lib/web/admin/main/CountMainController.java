package com.lib.web.admin.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lib.dto.ActiveUserInfo;
import com.lib.dto.ClassesClickInfo;
import com.lib.dto.ClickInfo;
import com.lib.dto.JsonResult;
import com.lib.entity.FileInfo;
import com.lib.entity.UserInfo;
import com.lib.service.admin.CountService;

/**
 * 后台数据统计
 * 
 * @author Yu Yufeng
 *
 */
@Controller
@RequestMapping("/admin")
public class CountMainController {
	
	@Autowired
	private CountService countService;
	
	@RequestMapping(value = "/count-ui", method = RequestMethod.GET)
	public String indexui() {

		return "admin/index";
	}
	
	@RequestMapping(value = "/count/index", method = RequestMethod.GET)
	public String index() {
		
		return "admin/count-index";
	}
	/**
	 * 热门文档
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/count/hot-file", method = RequestMethod.GET)
	public JsonResult< List<ClickInfo>> getHotFiles(){
		JsonResult< List<ClickInfo>> jr = null;
		try{
			 List<ClickInfo> fileList = countService.getHotFiles();
			jr = new JsonResult< List<ClickInfo>>(true, fileList);
		}catch (Exception e) {
			jr = new JsonResult< List<ClickInfo>>(false, "获取失败");
		}
		return jr;
	}
	/**
	 * 热门分类
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/count/hot-classes", method = RequestMethod.GET)
	public JsonResult< List<ClassesClickInfo>> getHotClasses(){
		JsonResult< List<ClassesClickInfo>> jr = null;
		try{
			 List<ClassesClickInfo> classesClickInfos = countService.getHotClass(1);
			jr = new JsonResult< List<ClassesClickInfo>>(true, classesClickInfos);
		}catch (Exception e) {
			jr = new JsonResult< List<ClassesClickInfo>>(false, "获取失败");
		}
		return jr;
	}
	/**
	 * 活跃用户
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/count/active-user", method = RequestMethod.GET)
	public JsonResult< List<ActiveUserInfo>> getActiveUsers(){
		JsonResult< List<ActiveUserInfo>> jr = null;
		try{
			 List<ActiveUserInfo> classesClickInfos = countService.getActiveUsers(1);
			 System.out.println(classesClickInfos);
			jr = new JsonResult< List<ActiveUserInfo>>(true, classesClickInfos);
		}catch (Exception e) {
			jr = new JsonResult< List<ActiveUserInfo>>(false, "获取失败");
		}
		return jr;
	}
	/**
	 * 最近上传
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/count/recent-files", method = RequestMethod.GET)
	public JsonResult< List<Long>> getRecentUploadFiles(){
		JsonResult< List<Long>> jr = null;
		try{
			 List<Long> list = countService.getUploadTimesByTime();
			 Collections.reverse(list);//倒序排列
			 System.out.println(list);
			jr = new JsonResult< List<Long>>(true, list);
		}catch (Exception e) {
			jr = new JsonResult< List<Long>>(false, "获取失败");
		}
		return jr;
	}
	/**
	 * 网站点击
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/count/click-times", method = RequestMethod.GET)
	public JsonResult< List<Long>> getClickTims(){
		JsonResult< List<Long>> jr = null;
		try{
			 List<Long> list = countService.getClickTimesByTime();
			 Collections.reverse(list);//倒序排列
			 System.out.println(list);
			jr = new JsonResult< List<Long>>(true, list);
		}catch (Exception e) {
			jr = new JsonResult< List<Long>>(false, "获取失败");
		}
		return jr;
	}
}
