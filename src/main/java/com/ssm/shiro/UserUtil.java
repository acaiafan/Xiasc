package com.ssm.shiro;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

public class UserUtil {

	/**
	 * 获取授权主要对象
	 */
	public static Subject getSubject(){
		return SecurityUtils.getSubject();
	}

	/**
	 * 获取当前登录者对象
	 */
	public static Principal getPrincipal(){
		try{
			Subject subject = SecurityUtils.getSubject();
			Principal principal = (Principal)subject.getPrincipal();
			if (principal != null){
				return principal;
			}
		}catch (UnavailableSecurityManagerException e) {

		}catch (InvalidSessionException e){

		}
		return null;
	}
	
	/*public static Principal getPrincipal(HttpServletRequest request){
		JedisSessionDAO dao = SpringContextHolder.getBean(JedisSessionDAO.class);
		SimpleCookie cookie = SpringContextHolder.getBean(SimpleCookie.class);
		String sessionId = CookieUtils.getCookie(request, cookie.getName());
		byte[] sessionByte = JedisClusterUtils.get(StringUtils.getBytes(dao.getSessionKeyPrefix() + sessionId));
		if(null != sessionByte){
			Session session = (Session)ObjectUtils.unserialize(sessionByte);
			PrincipalCollection pc = (PrincipalCollection)session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
			Principal principal = (Principal)pc.getPrimaryPrincipal();
			return principal;
		}
		return null;
	}*/

	public static Session getSession(){
		try{
			Subject subject = SecurityUtils.getSubject();
			Session session = subject.getSession(false);
			if (session == null){
				session = subject.getSession();
			}
			if (session != null){
				return session;
			}
		}catch (InvalidSessionException e){

		}
		return null;
	}
	
}
