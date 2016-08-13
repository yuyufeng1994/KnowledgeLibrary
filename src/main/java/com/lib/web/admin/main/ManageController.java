package com.lib.web.admin.main;

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
import com.lib.dto.JsonResult;
import com.lib.entity.FileInfo;
import com.lib.entity.UserInfo;
import com.lib.enums.Const;
import com.lib.service.user.FileInfoService;
import com.lib.service.user.FileManageService;
import com.lib.service.user.UserService;
import com.lib.utils.PagedResult;

/**
 * 后台处理文件的Controller
 * 
 * @author Yu Yufeng
 *
 */
@Controller
@RequestMapping("/admin")
public class ManageController {
	@Autowired
	private FileInfoService fileInfoService;

	@Autowired
	private UserService userService;

	@Autowired
	private FileManageService fileManageService;

	/**
	 * 用户管理主页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/user-manage-ui", method = RequestMethod.GET)
	public String userManageUI(Model model) {
		try {
			PagedResult<UserInfo> pagedResult = userService.queryByPage(null, null, null);
			model.addAttribute("pages", pagedResult);
		} catch (Exception e) {

		}
		return "admin/user-manage";
	}

	/**
	 * 文件管理主页
	 * 
	 * @return
	 */
	// @RequestMapping(value = "/file-manage-ui", method = RequestMethod.GET)
	// public String ManageUI() {
	//
	// return "admin/file-manage";
	// }

	@RequestMapping(value = "/user-manage", method = RequestMethod.GET)
	public @ResponseBody JsonResult<List<FileInfo>> test() {

		return null;
	}

	@RequestMapping(value = "/file-freeze/{uuid}", method = RequestMethod.POST)
	public @ResponseBody Integer changeState(@PathVariable("uuid") String uuid) {
		FileInfoVO f = fileInfoService.getFileInfoByUuid(uuid);
		FileInfo fi = new FileInfo();
		fi.setFileId(f.getFileId());
		fi.setFileUuid(uuid);
		int state = f.getFileState();
		if (state == 7) {
			state = 6;
		} else {
			state = 7;
		}
		fi.setFileState(state);
		fileInfoService.saveBaseFileInfoByUuid(fi);
		return state;
	}

	/**
	 * 跳转到文件管理
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/file-manage-ui/{pageNo}", method = RequestMethod.GET)
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
		PageInfo<FileInfoVO> page = fileManageService.getFileInfoPage(pageNo, "file_id desc", searchValue);
		model.addAttribute("page", page);
		return "admin/file-manage";
	}

}
