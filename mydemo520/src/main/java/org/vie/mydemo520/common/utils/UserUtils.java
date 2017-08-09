package org.vie.mydemo520.common.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.vie.mydemo520.common.Global;
import org.vie.mydemo520.entity.sys.User;
import org.vie.mydemo520.mapper.sys.UserMapper;
import org.vie.mydemo520.security.SystemAuthorizingRealm.Principal;

public class UserUtils {

	private static UserMapper userMapper = SpringContextHolder.getBean(UserMapper.class);

	public static final String USER_CACHE = "userCache";

	/**
	 * 
	 * @Description 获取当前登录用户 @return @return User @throws
	 */
	public static User getUser() {
		 Object obj =
		 SecurityUtils.getSubject().getSession().getAttribute(Global.CURRENT_USER);
		 if (obj != null) {
		 	return (User) obj;
		 }else{
			 Principal principal = getPrincipal();
			 User user = userMapper.getByUsername(principal.getUsername());
			 SecurityUtils.getSubject().getSession().setAttribute(Global.CURRENT_USER,user);
			 return user;
		 }
	}

	/**
	 * 获取授权主要对象
	 */
	public static Subject getSubject() {
		return SecurityUtils.getSubject();
	}

	/**
	 * 获取授权身份
	*/
	public static Principal getPrincipal() {
		try {
			Subject subject = getSubject();
			Principal principal = (Principal) subject.getPrincipal();
			if (principal != null) {
				return principal;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
