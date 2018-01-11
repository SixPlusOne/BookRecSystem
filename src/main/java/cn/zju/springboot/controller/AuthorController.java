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

import cn.zju.springboot.pojo.Author;
import cn.zju.springboot.pojo.Book;
import cn.zju.springboot.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.zju.springboot.service.AuthorService;

import java.util.List;

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
	
	@Autowired
	private BookService bookService;
	
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
	
	@RequestMapping("/getAuthorDetail")
	public String getAuthorDetail(HttpServletRequest request, Model model){
		//获取作者信息并添加到Model中
		String name = request.getParameter("name");
		System.out.println(name);

		Author author = authorService.getAuthorByName(name);
		System.out.println("成功获取作者" + name);

		model.addAttribute("author", author);

		//获取该作者的六本书籍
		System.out.println("author.id = " + author.getId());

		List<Book> bookList = bookService.getBookByAuthorId(author.getId());
		System.out.println("成功获取该作者的书籍");
		if(bookList != null && bookList.size() != 0){
			for(int i = 0; i < 6 && i < bookList.size(); i++){
				model.addAttribute("book" + i, bookList.get(i));
			}
		}
		return "authorDetails";
	}

}
  
