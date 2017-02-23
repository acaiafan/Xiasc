package com.ssm.controller;



import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.jms.MapMessage;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
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
import com.ssm.util.BeanUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

@Controller
public class UserController {
	
	@Autowired
	private Jedis jedis;
	
	public static final Logger logger =  Logger.getLogger(UserController.class);
	
	@Resource
	private IUserService userService;
	
	@Resource(name="messageSender")
	private MessageSender msgSender;

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
	
	
	@RequestMapping("sendTopic")
	@ResponseBody
	public String sendTopic(HttpServletRequest request){
		
		String destination="queue.test";
		
		
		//发送一个 Map topic 
		Map<String,String> map = new HashMap<String,String>();
		map.put("1", "send");
		msgSender.queueSender(map, destination);
		
		//发送一个byte  topic 
		byte[] bt ={1,2};
		msgSender.queueSender(bt, destination);
		
		//发送一个user类型对象
		User user = new User();
		user.setAge("11");
		user.setName("name");
		msgSender.queueSender(user, destination);
	
		msgSender.queueSender("221", destination);
		
		return "发送完毕";
	}
	
	
}
