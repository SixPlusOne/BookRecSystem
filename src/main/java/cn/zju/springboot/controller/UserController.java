/**  
 * Project Name:BookRecSystem  
 * File Name:UserController.java  
 * Package Name:cn.zju.springboot.controller  
 * Date:2017年12月12日下午7:04:20  
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved.  
 *  
*/  
  
package cn.zju.springboot.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.zju.springboot.pojo.Book;
import cn.zju.springboot.pojo.Favor;
import cn.zju.springboot.pojo.User;
import cn.zju.springboot.service.UserService;

/**  
 * ClassName:UserController <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     2017年12月12日 下午7:04:20 <br/>  
 * @author   john-lin  
 * @version    
 * @since    JDK 1.6  
 * @see        
 */
@Controller
@RequestMapping("user/")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("register")
	@ResponseBody
	public String register(HttpServletRequest request, HttpServletResponse response) {
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		if(StringUtils.isEmpty(userName)||StringUtils.isEmpty(password))
			return "账号用户名密码错误";
		User user = new User();
		user.setName(userName);
		user.setPassword(password);
		Integer res = userService.register(user);
		Integer falseRes = new Integer(-1);
		if(!res.equals(falseRes)) {
			session.setAttribute("userId", res);
//			try {
//				response.sendRedirect("/login.html");
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
			return "登录成功";
		} else {
			return "账户名密码不匹配";
		}
		
	}
	
	@RequestMapping(value = "login",method = RequestMethod.POST)
	@ResponseBody
	public Object login(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		Map<String, Object> result = new HashMap<>();
		HttpSession session = request.getSession();
		request.setCharacterEncoding("utf-8");
		String userName = request.getParameter("username");
		String passWord = request.getParameter("password");
		if(StringUtils.isEmpty(userName)||StringUtils.isEmpty(passWord)) {
			result.put("success", false);
			result.put("Msg", "账号用户名密码错误");
			return result;
		}
		User user = new User();
		user.setName(userName);
		user.setPassword(passWord);
		int resultCode = userService.login(userName, passWord);
		if(resultCode == -1) {
			result.put("success", false);
			result.put("Msg", "用户尚未注册");
			return result;
		}else if (resultCode == -2) {
			session.invalidate();
			result.put("success", false);
			result.put("Msg", "登录失败，用户密码错误");
			return result;
		}else {
			session.setAttribute("userId", resultCode);
			result.put("success",true);
			result.put("Msg", "登录成功");
			return result;
		}
		
		
	}
	
	@RequestMapping("getUser")
	@ResponseBody
	public Object getUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Map<String, Object> result = new HashMap<>();
		if(session==null||session.getAttribute("userId")==null)
		{
			result.put("success", false);
			result.put("Msg", "Time out,please login again!");
			return result;
		}
		int userId = (int) session.getAttribute("userId");
		
		return userService.getUserBySession(userId);
		
	}
	
	
	@RequestMapping("update")
	@ResponseBody
	public Object update(HttpServletRequest request,HttpServletResponse response) {
		String UserName = request.getParameter("UserName");
		String OldStaffPwd = request.getParameter("OldStaffPwd");
		String NewStaffPwd = request.getParameter("NewStaffPwd");
		if(StringUtils.isEmpty(UserName)||StringUtils.isEmpty(OldStaffPwd)||StringUtils.isEmpty(NewStaffPwd)) {
			return "密码不能为空";
		}
		if(userService.update(UserName,OldStaffPwd,NewStaffPwd)) {
			return "修改成功";
		}
		return "修改失败";
		
	}
	
	@RequestMapping("/updateName")
	@ResponseBody
	public Object updateName(HttpServletRequest request,HttpServletResponse response) {
		int userId = Integer.parseInt(request.getParameter("userId"));
		String userName = request.getParameter("userName");
		if(StringUtils.isEmpty(userName)) {
			return "昵称不能为空";
		}
		if(userService.updateName(userId,userName)) {
			return "success";
		}
		return "fail";
	}
	
	/**
	 * 查询一个用户的收藏记录
	 */
	@GetMapping("/settings")
	public Object queryUserFavor(Model model,HttpSession session) throws IOException{
		User user = userService.getUserById(1);
		model.addAttribute("user", user);
		return "settings";
	}
}
  
