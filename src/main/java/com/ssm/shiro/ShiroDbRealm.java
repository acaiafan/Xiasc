package com.ssm.shiro;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;

import com.ssm.bean.User;
import com.ssm.dao.TestMapper;
import com.ssm.service.IUserService;
import com.ssm.util.MD5Util;

public class ShiroDbRealm extends AuthorizingRealm {
	
	Logger logger = Logger.getLogger(ShiroDbRealm.class);

	@Resource
	private IUserService userService;

	/**
	 * 验证登陆
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
			throws AuthenticationException {
		logger.debug("shiro验证登录");
		System.out.println("shiro验证登录");
		// TODO Auto-generated method stub
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		logger.debug(token.getUsername()+"      "+token.getPassword());
		User user = userService.getUser(token.getUsername(), String.valueOf(token.getPassword()));
		System.out.println(token.getUsername()+"       "+String.valueOf(token.getPassword()));
		if (user != null) {
			logger.debug(user.getUserName()+"登录成功");
			System.out.println(user.getUserName()+"登录成功");
			return new SimpleAuthenticationInfo(new com.ssm.shiro.Principal(user.getId(), user.getUserName(), "1", "0"),
					user.getPassword(), getName());
		} else {
			throw new AuthenticationException();
		}

	}

	/**
	 * 登陆成功之后，进行角色和权限验证
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		Principal principal = (Principal) getAvailablePrincipal(principals);

		SimpleAuthorizationInfo info ;
		if ("1".equals(principal.getIsAdmin())) {
			Set<String> set = new HashSet<String>();
			set.add("admin.do");
			info = new SimpleAuthorizationInfo(set);
			info.setStringPermissions(set);
			
		}else{
			info =  new SimpleAuthorizationInfo();
		}
		return info;
	}
	
	 /** 
     * 清除所有用户授权信息缓存. 
     */  
    public void clearCachedAuthorizationInfo(String principal) {  
        SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());  
        clearCachedAuthorizationInfo(principals);  
    }  
  
  
    /** 
     * 清除所有用户授权信息缓存. 
     */  
    public void clearAllCachedAuthorizationInfo() {  
        Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();  
        if (cache != null) {  
            for (Object key : cache.keys()) {  
                cache.remove(key);  
            }  
        }  
    }

}
