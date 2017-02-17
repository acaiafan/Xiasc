package com.ssm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.ssm.bean.Testt;
import com.ssm.bean.User;
import com.ssm.bean.UserList;
import com.ssm.service.IUserService;

@Controller
public class UserController {
	
	@Resource
	private IUserService userService;

	@RequestMapping("user")
	public String toIndex(HttpServletRequest request,Model model){
        int userId = Integer.parseInt(request.getParameter("id"));
        Testt user = this.userService.getUserById(userId);
        model.addAttribute("user", user);
        System.out.println(JSON.toJSONString(user));
        return "User";
		
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
	
}
