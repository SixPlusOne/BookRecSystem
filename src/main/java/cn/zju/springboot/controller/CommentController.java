/**  
 * Project Name:BookRecSystem  
 * File Name:CommentController.java  
 * Package Name:cn.zju.springboot.controller  
 * Date:2017年12月18日上午7:02:51  
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved.  
 *  
*/  
  
package cn.zju.springboot.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.zju.springboot.mapper.BookMapper;
import cn.zju.springboot.pojo.Book;
import cn.zju.springboot.pojo.Comment;
import cn.zju.springboot.pojo.History;
import cn.zju.springboot.pojo.User;
import cn.zju.springboot.service.CommentService;
import cn.zju.springboot.service.CommentServiceImpl;
import cn.zju.springboot.service.HistoryServiceImpl;
import cn.zju.springboot.service.UserLikedBookServiceImpl;
import cn.zju.springboot.service.UserServiceImpl;

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
	private HistoryServiceImpl historyService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private UserServiceImpl userService;
	@Autowired
	private UserLikedBookServiceImpl userLikedBookService;
	
	@Autowired
	BookMapper bookMapper;
	
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
	
	/**
	 * 查询一个用户所有的已读书籍记录
	 */
	@GetMapping("/read_books")
	public Object queryReadBooks(Model model,HttpSession session) throws IOException{
		User user = userService.getUserById(1);
		int userFavor = userLikedBookService.countUserLikedBooks(1);
		int userRead = commentService.countReadBooks(1);
		List<Comment> commentRecords = commentService.getCommentByUserId(1);
		List<Book> readBooks = new LinkedList<Book>();
		for(Comment commentRecord:commentRecords) {
			readBooks.add(bookMapper.selectByPrimaryKey(commentRecord.getBookId()));
		}
		model.addAttribute("readBooks", readBooks);
		model.addAttribute("user", user);
		model.addAttribute("userFavor", userFavor);
		model.addAttribute("userRead", userRead);
		return "read_books";
	}
	
	

}
  
