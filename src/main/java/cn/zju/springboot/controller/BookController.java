package cn.zju.springboot.controller;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.zju.springboot.mapper.BookFormMapper;
import cn.zju.springboot.pojo.Book;
import cn.zju.springboot.service.BookService;

@Controller
@RequestMapping("book")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private BookFormMapper bookFormMapper;
	
	@RequestMapping("getBookById")
	@ResponseBody
	public Object getBookById(HttpServletRequest request) {
		//返回的是Json格式，不需要通过jsp
		int id = Integer.parseInt(request.getParameter("id"));
		return bookService.getBookById(id);
    }

	@RequestMapping("getBookByIsbn")
	@ResponseBody
    public Object getBookByIsbn(HttpServletRequest request){
		String isbn = request.getParameter("isbn");
		return bookService.getBookByIsbn(isbn);
	}

	@RequestMapping("getBookByName")
	@ResponseBody
	public Object getBookByName(HttpServletRequest request){
		String name = request.getParameter("name");
		return bookService.getBookByName(name);
	}

	@RequestMapping("getBookByAuthorId")
	@ResponseBody
	public Object getBookByAuthorId(HttpServletRequest request){
		int authorId = Integer.parseInt(request.getParameter("authorId"));
		return bookService.getBookByAuthorId(authorId);
	}
	
	@RequestMapping("insertBook")
	@ResponseBody
	public void insertBook(HttpServletRequest request){
		System.out.println("insert a book");
	}
	
	@RequestMapping("deleteBook")
	@ResponseBody
	public void deleteBook(HttpServletRequest request){
		System.out.println("delete a book");
	}
	
	@GetMapping("/{id}")
	public String getDetailedBookForm(@PathVariable int id,Model model){
		model.addAttribute("book",bookFormMapper.findone(id));

		model.addAttribute("simbooks",bookFormMapper.getSimilarBooks(id));
		return "bookDetails";
		
	
	}
}
