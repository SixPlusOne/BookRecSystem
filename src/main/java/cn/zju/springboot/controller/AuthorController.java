/**  
 * Project Name:BookRecSystem  
 * File Name:AuthorController.java  
 * Package Name:cn.zju.springboot.controller  
 * Date:2017年12月12日下午7:11:16  
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

import cn.zju.springboot.service.AuthorService;

/**  
 * ClassName:AuthorController <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     2017年12月12日 下午7:11:16 <br/>  
 * @author   john-lin  
 * @version    
 * @since    JDK 1.6  
 * @see        
 */
@Controller
@RequestMapping("author")
public class AuthorController {
	
	@Autowired
	private AuthorService authorService;
	
	@RequestMapping("/getAuthorByName")
	@ResponseBody
	public Object getAuthorByName(HttpServletRequest request) {
		String authorName = request.getParameter("name");
		if(StringUtils.isEmpty(authorName))
			return "作者名不能为空";
		
		return authorService.getAuthorByName(authorName);
		
	}
	
	@RequestMapping("/test")
	@ResponseBody
	public Object test(HttpServletRequest request) {
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		
		return authorService.getAuthorListByPage(pageSize, pageNum);
		
	}

}
  
