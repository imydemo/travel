package org.vie.mydemo520.common.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.vie.mydemo520.common.Global;
import org.vie.mydemo520.entity.sys.Resource;
import org.vie.mydemo520.security.SystemAuthorizingRealm.Principal;
import org.vie.mydemo520.service.sys.ResourceService;


/**
 * 权限菜单拦截器
 * 
 * @author Edward
 * 
 */
public class AuthInterceptor implements HandlerInterceptor {

	@Autowired
	private ResourceService resourceService;

	/**
	 * 将用户权限保存在menu菜单里
	 * */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		Subject subject = SecurityUtils.getSubject();
		Principal principal = (Principal) subject.getPrincipal();
		if (subject.isAuthenticated() || subject.isRemembered()) {
			Object obj = subject.getSession().getAttribute(Global.MENUS);
			if (obj == null) {
				System.out.println("将用户权限保存在menu菜单里");
				List<Resource> menus = resourceService.queryMenus(principal.getUsername());
				System.out.println(menus.get(0).getName());
				subject.getSession().setAttribute(Global.MENUS, menus);
			}
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

	}

}
