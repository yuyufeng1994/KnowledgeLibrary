package com.lib.web.user.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.druid.support.logging.Log;
import com.lib.dto.FileInfoVO;
import com.lib.entity.FileInfo;
import com.lib.entity.UserInfo;
import com.lib.enums.Const;
import com.lib.service.user.FileInfoService;
import com.lib.utils.JudgeUtils;
import com.lib.utils.StringValueUtil;

/**
 * 专门处理文件的Controller
 * 
 * @author Yu Yufeng
 *
 */
@Controller
@RequestMapping("/user")
public class FileContentrController {
	@Autowired
	private FileInfoService fileInfoService;
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

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

}
