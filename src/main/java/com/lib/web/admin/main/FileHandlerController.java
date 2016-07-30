package com.lib.web.admin.main;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lib.dto.JsonResult;
import com.lib.entity.FileInfo;
import com.lib.service.user.FileInfoService;

/**
 * 后台处理文件的Controller
 * 
 * @author Yu Yufeng
 *
 */
@Controller
@RequestMapping("/admin")
public class FileHandlerController {
	@Autowired
	private FileInfoService fileInfoService;

	@RequestMapping(value = "/transfor", method = RequestMethod.GET)
	public @ResponseBody JsonResult<List<FileInfo>> test() {
		
		return null;
	}

}
