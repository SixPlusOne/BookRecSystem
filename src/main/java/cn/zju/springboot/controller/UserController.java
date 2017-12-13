/**  
 * Project Name:BookRecSystem  
 * File Name:UserController.java  
 * Package Name:cn.zju.springboot.controller  
 * Date:2017年12月12日下午7:04:20  
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved.  
 *  
*/  
  
package cn.zju.springboot.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("register")
	@ResponseBody
	public String register(HttpServletRequest request) {
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		if(StringUtils.isEmpty(userName)||StringUtils.isEmpty(password))
			return "账号用户名密码错误";
		User user = new User();
		user.setName(userName);
		user.setPassword(password);
		return userService.register(user);
		
	}
	
	@RequestMapping("login")
	@ResponseBody
	public String login(HttpServletRequest request) {
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		if(StringUtils.isEmpty(userName)||StringUtils.isEmpty(password))
			return "账号用户名密码错误";
		User user = new User();
		user.setName(userName);
		user.setPassword(password);
		return userService.login(userName, password);
		
	}
	
	@RequestMapping("getUserByName")
	@ResponseBody
	public Object getUserByName(HttpServletRequest request) {
		String userName = request.getParameter("userName");
		if(StringUtils.isEmpty(userName))
			return "用户名不能为空";
		
		return userService.getUserByName(userName);
		
	}

}
  
