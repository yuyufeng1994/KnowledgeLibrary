package com.lib.web.admin.main;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
	@ResponseBody
	@RequestMapping(value="/count/hot-file", method = RequestMethod.GET)
	public JsonResult< List<ClickInfo>> getHotFiles(){
		JsonResult< List<ClickInfo>> jr = null;
		try{
			 List<ClickInfo> fileList = countService.getHotFiles();
			 System.out.println(fileList);
			jr = new JsonResult< List<ClickInfo>>(true, fileList);
		}catch (Exception e) {
			jr = new JsonResult< List<ClickInfo>>(false, "获取失败");
		}
		return jr;
	}
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
	/*
	@ResponseBody
	@RequestMapping(value="/count/hot-classes", method = RequestMethod.GET)
	public JsonResult<Map< List<ClassesClickInfo>,List<ClickInfo>>> getHotClasses(){
		JsonResult< Map< List<ClassesClickInfo>,List<ClickInfo>>> jr = null;
		try{
			Map< List<ClassesClickInfo>,List<ClickInfo>> map = new LinkedHashMap< List<ClassesClickInfo>,List<ClickInfo>>();
			 List<ClassesClickInfo> classList = countService.getHotClass(1);
			 List<ClickInfo> fileList = countService.getHotFiles();
			 map.put(classList, fileList);
			 System.out.println(fileList);
			jr = new JsonResult<Map< List<ClassesClickInfo>,List<ClickInfo>>>(true, map);
		}catch (Exception e) {
			jr = new JsonResult< Map< List<ClassesClickInfo>,List<ClickInfo>>>(false, "获取失败");
		}
		return jr;
	}
	*/
}
