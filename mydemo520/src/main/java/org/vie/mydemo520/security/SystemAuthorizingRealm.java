package org.vie.mydemo520.security;

import java.io.Serializable;
import java.util.List;


import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.vie.mydemo520.entity.sys.Resource;
import org.vie.mydemo520.entity.sys.User;
import org.vie.mydemo520.service.sys.ResourceService;
import org.vie.mydemo520.service.sys.UserService;

import com.google.common.base.Objects;

/**
 * 扩展AuthorizingRealm
 * 
 * 实现登录数据Realm和password加密
 * 
 * 
 * @author Edward
 * 
 */
public class SystemAuthorizingRealm extends AuthorizingRealm {

	protected static Logger logger = LoggerFactory.getLogger(SystemAuthorizingRealm.class);

	@Autowired
	private ResourceService resourceService;

	@Autowired
	private UserService userService;

	/**
	 * 鉴权的时候调用
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Principal principal = (Principal) principals.getPrimaryPrincipal();
		List<Resource> permissions = resourceService.queryPermissions(principal.getUsername());
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		for (Resource r : permissions) {
			if (StringUtils.isNotBlank(r.getPermission())) {
				authorizationInfo.addStringPermission(r.getPermission());
			}
		}
		return authorizationInfo;

	}

	/**
	 * 登录验证数据正确性
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		// 通过表单接收的用户名
		String username = token.getUsername();
		char password[] = token.getPassword();
		// 查询用户
		User user = userService.getByUsername(username);
		/*User user = new User();
		user.setUsername(username);
		user.setNickname("管理员");
		user.setPassword("2a75cbf2861918a4d800c1361a2d7734202224c2");
		user.setSalt("1fcd6496522a69e0");
		user.setLocked(0);*/
		
		System.out.println(user.getNickname());
		if (null == user) {
			logger.debug("没有找到帐号");
			throw new UnknownAccountException(); // 没有找到帐号
		}
		if (1 == user.getLocked()) {
			logger.debug("帐号锁定");
			throw new LockedAccountException(); // 帐号锁定
		}
		// 解密盐
		byte[] salt=null;
		try {
		 salt = Hex.decodeHex(user.getSalt().toCharArray());
				} catch (DecoderException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		//System.out.println(ByteSource.Util.bytes(salt));
		return new SimpleAuthenticationInfo(new Principal(user.getId(),user.getUsername(), user.getNickname()),
				user.getPassword(), ByteSource.Util.bytes(salt), getName() );
		}

	@Override
	public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
		super.clearCachedAuthorizationInfo(principals);
	}

	@Override
	public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
		super.clearCachedAuthenticationInfo(principals);
	}

	@Override
	public void clearCache(PrincipalCollection principals) {
		super.clearCache(principals);
	}

	public void clearAllCachedAuthorizationInfo() {
		getAuthorizationCache().clear();
	}

	public void clearAllCachedAuthenticationInfo() {
		getAuthenticationCache().clear();
	}

	public void clearAllCache() {
		clearAllCachedAuthenticationInfo();
		clearAllCachedAuthorizationInfo();
	}

	/**
	 * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息.
	 */
	public static class Principal implements Serializable {
		private static final long serialVersionUID = -1373760761780840081L;
		
		private int id; //用户Id
		private String username; // 用户名 用于登录
		private String name; // 姓名 用于显示

		public Principal(int id,String username, String name) {
			this.id = id;
			this.username = username;
			this.name = name;
		}
		
		public int getId(){
			return id;
		}

		public String getName() {
			return name;
		}

		public String getUsername() {
			return username;
		}
		

		/**
		 * 本函数输出将作为默认的<shiro:principal/>输出.
		 */
		@Override
		public String toString() {
			return name;
		}

		/**
		 * 重载hashCode,只计算loginName;
		 */
		@Override
		public int hashCode() {
			return Objects.hashCode(username);
		}

		/**
		 * 重载equals,只计算loginName;
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			Principal other = (Principal) obj;
			if (username == null) {
				if (other.username != null) {
					return false;
				}
			} else if (!username.equals(other.username)) {
				return false;
			}
			return true;
		}
	}

}
