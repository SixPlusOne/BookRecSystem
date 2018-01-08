package cn.zju.springboot.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.zju.springboot.mapper.BookFormMapper;
import cn.zju.springboot.pojo.Book;
import cn.zju.springboot.pojo.BookForm;
import cn.zju.springboot.pojo.Comment;
import cn.zju.springboot.service.BookService;
import cn.zju.springboot.service.CommentService;

@Controller
@RequestMapping("book/")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private BookFormMapper bookFormMapper;
	
	@Autowired
	private CommentService commentService;
	
	@RequestMapping("getBookById")
	@ResponseBody
	public Object getBookById(HttpServletRequest request) {
		//返回的是Json格式，不需要通过jsp
		int id = Integer.parseInt(request.getParameter("id"));
		return bookService.getBookById(id);
    }
	
	@RequestMapping("getBookNameByIdTest")
	@ResponseBody
	public Object getBookNameByIdTest(HttpServletRequest request) {
		//返回的是Json格式，不需要通过jsp
		int id = Integer.parseInt(request.getParameter("id"));
		return bookService.getBookNameByIdTest(id);
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
	
	@RequestMapping("getBookByPage")
	@ResponseBody
	public List<Book> getBookByPage(HttpServletRequest request){
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		
		return bookService.getBookByPage(pageSize, pageNum);
		
	}
	
	@GetMapping("/{userId}/{bookId}")
	public String getDetailedBookForm(@PathVariable int userId,@PathVariable int bookId,Model model){
		System.out.println("userId: " + userId);
		System.out.println("bookId: " + bookId);
		model.addAttribute("book",bookFormMapper.findone(bookId));
		List<Comment> commentList = commentService.getCommentByUserIdAndBookId(userId, bookId);
		Comment comment = new Comment();
		if (!commentList.isEmpty()) {
			comment = commentList.get(0);
		}
		model.addAttribute("comment",comment);
		// 此处暂时不使用分页, 通过if判断只显示前5本书籍
		List<BookForm> tem = bookFormMapper.getSimilarBooks(bookId);
		List<BookForm> simbooks = tem;
		if (tem.size() > 6) {
			simbooks = tem.subList(0, 6);
		}
		model.addAttribute("simbooks",simbooks);
		return "bookDetails";
	}
	
}
