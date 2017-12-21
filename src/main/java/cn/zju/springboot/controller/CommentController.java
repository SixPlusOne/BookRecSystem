/**  
 * Project Name:BookRecSystem  
 * File Name:CommentController.java  
 * Package Name:cn.zju.springboot.controller  
 * Date:2017年12月18日上午7:02:51  
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved.  
 *  
*/  
  
package cn.zju.springboot.controller;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.zju.springboot.pojo.Comment;
import cn.zju.springboot.service.CommentService;

/**  
 * ClassName:CommentController <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     2017年12月18日 上午7:02:51 <br/>  
 * @author   john-lin  
 * @version    
 * @since    JDK 1.6  
 * @see        
 */
@Controller
@RequestMapping("comment/")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@RequestMapping("insert")
	@ResponseBody
	public String insert(HttpServletRequest request) {
		
		Comment comment =new Comment();
		int userId = Integer.parseInt(request.getParameter("userId"));
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		int score = Integer.parseInt(request.getParameter("score"));
		String content = request.getParameter("content");
		
		comment.setBookId(bookId);
		comment.setUserId(userId);
		comment.setScore(score);
		comment.setContent(content);
		comment.setCreateDate(new Date(System.currentTimeMillis()));
		
		if(commentService.insert(comment)) {
			return "插入成功";
		}
		
		return "插入成功";
		
	}
	
	@RequestMapping("getCommentByUserId")
	@ResponseBody
	public Object getCommentByUserId(HttpServletRequest request) {
		
		int userId = Integer.parseInt(request.getParameter("userId"));
		
		return commentService.getCommentByUserId(userId);
		
	}
	
	@RequestMapping("getCommentByBookId")
	@ResponseBody
	public Object getCommentByBookId(HttpServletRequest request) {
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		
		return commentService.getCommentByBookId(bookId);
		
	}
	
	

}
  
