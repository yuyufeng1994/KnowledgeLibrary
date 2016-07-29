package com.lib.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.lib.entity.UserInfo;
import com.lib.enums.Const;
import com.lib.service.user.UserService;
/**
 * 主要拦截器
 * @author Yu Yufeng
 *
 */
public class UserInterceptor implements HandlerInterceptor {
	@Autowired
	private UserService userService;
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	// 执行Handler完成执行此方法
	// 应用场景：统一异常处理，统一日志处理
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object,
			Exception exception) throws Exception {
	}

	// 进入Handler方法之后，返回modelAndView之前执行
	// 应用场景从模型出发 公用model数据（菜单导航）在这里传到视图，也可以在这里统一指定视图
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object,
			ModelAndView modelAndView) throws Exception {
	}

	// 进入Handler方法之前
	// 用于身份认真、身份授权
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		HttpSession session = request.getSession();
		UserInfo user = (UserInfo) session.getAttribute(Const.SESSION_USER);
		String uri = request.getRequestURI();
		Cookie[]  cookies= request.getCookies();
		
		if(user == null){
			String userEmail = null;
			String userPassword = null;
			if(cookies == null){
				response.sendRedirect(request.getContextPath()+"/illegal-view");
				return false;
			}
			for(Cookie c:cookies){
				if(c.getName().equals("userEmail")){
					userEmail= c.getValue();
				}else if(c.getName().equals("userPassword")){
					userPassword = c.getValue();
				}
			}
			
			if(userEmail != null && userPassword !=null){
				user = new UserInfo();
				user.setUserEmail(userEmail);
				user.setUserPassword(userPassword);
				try {
					userService.checkUserByEmail(user);
					//在session中保存用户基本信息
					UserInfo userBasicInfo = userService.getBasicUserInfoByEmail(user.getUserEmail());
					session.setAttribute(Const.SESSION_USER, userBasicInfo);
					return true;
				} catch (Exception e) {
					response.sendRedirect(request.getContextPath()+"/illegal-view");
					return false;
				}
			}
			
			response.sendRedirect(request.getContextPath()+"/illegal-view");
			return false;
		}
		return true;
	}

}
