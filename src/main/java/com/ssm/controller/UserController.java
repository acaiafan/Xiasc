package com.ssm.controller;



import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.jms.MapMessage;
import javax.json.JsonString;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ssm.bean.People;
import com.ssm.bean.Testt;
import com.ssm.bean.User;
import com.ssm.mq.MessageSender;
import com.ssm.service.IUserService;
import com.ssm.shiro.Principal;
import com.ssm.shiro.UserUtil;
import com.ssm.util.BeanUtil;
import com.ssm.util.HttpUtil;
import com.ssm.util.MD5Util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

@Controller
public class UserController {
	
	@Autowired
	private Jedis jedis;
	@Resource(name="httpUtil")
	private HttpUtil httpUtil;
	
	public static final Logger logger =  Logger.getLogger(UserController.class);
	
	@Resource
	private IUserService userService;
	
//	@Resource(name="messageSender")
//	private MessageSender msgSender;

	@RequestMapping("user")
	public String toIndex(HttpServletRequest request,Model model){
		//jedisCluster.set("xsc", "xsc");
		jedis.set("xsc", "cc");
        int userId = Integer.parseInt(request.getParameter("id"));
        Testt user = this.userService.getUserById(userId);
        model.addAttribute("user", user);
       System.out.println(jedis.get("xsc"));
        return "user";
		
	}
	
	@RequestMapping("userForm")
	public void getUserForm(User[] userList){
		System.out.println("in");
//		PrintWriter pw= null;
//		try {
//			pw = response.getWriter();
//			pw.print("success");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally{
//			if(pw!=null){
//				pw.flush();
//				pw.close();
//			}
//		}
//	}
	}
	
	@RequestMapping("test")
	public String doSubmit(HttpServletRequest request){
		People people = new People();
		people.setSex("男");
		people.setAge("123");
		System.out.println(BeanUtil.checkEmpty(people));
		System.out.println("finish");
		logger.info("finish");
		return "";
	}
	
//	
//	@RequestMapping("sendTopic")
//	@ResponseBody
//	public String sendTopic(HttpServletRequest request){
//		
//		String destination="queue.test";
//		
		
		//发送一个 Map topic 
//		Map<String,String> map = new HashMap<String,String>();
//		map.put("1", "send");
//		msgSender.queueSender(map, destination);
//		
//		//发送一个byte  topic 
//		byte[] bt ={1,2};
//		msgSender.queueSender(bt, destination);
//		
//		//发送一个user类型对象
//		User user = new User();
//		user.setAge("11");
//		user.setUserName("name");
//		msgSender.queueSender(user, destination);
//	
//		msgSender.queueSender("221", destination);
//		
//		return "发送完毕";
//	}
	
	@RequestMapping("testHttp")
	@ResponseBody
	public String testHttp(){
		Map<String,String> map = new HashMap<String,String>();
		map.put("name", "xsc");
		map.put("age","20");
		map.put("id", "1");
		String result="ss";
		System.out.println("xsc");
		try {
			result = httpUtil.doGet("http://localhost:8080/rest/test/t1", map);
		} catch (IOException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(result);
		
		return JSON.parse(result).toString();
	}
	
	@RequestMapping("regist")
	public String regist(HttpServletRequest request){
		userService.registUser("test", "123123", 22);
		
		return "finish";
	}
	
	
	
	@RequestMapping("checkLogin")
	public String chekcLogin(HttpServletRequest request){
		
		UsernamePasswordToken token = new UsernamePasswordToken("test",MD5Util.MD5("123123"));
		
		Subject nowUser = SecurityUtils.getSubject();
		try{
			
				System.out.println(123);
				token.setRememberMe(true);
				nowUser.login(token);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		Principal pc = UserUtil.getPrincipal();
		logger.debug(pc.getUserName());
		System.out.println(pc.getUserName());
		return "index";
	}
	
	
	
}
