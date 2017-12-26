package cn.zju.springboot.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.abel533.entity.Example;

import cn.zju.springboot.mapper.BookMapper;
import cn.zju.springboot.pojo.Author;
import cn.zju.springboot.pojo.Book;
import cn.zju.springboot.pojo.Tag;
import cn.zju.springboot.pojo.User;
import cn.zju.springboot.service.AuthorRecommendService;
import cn.zju.springboot.service.BookRecommendServiceImpl;
import cn.zju.springboot.service.BookService;
import cn.zju.springboot.service.TagServiceImpl;

@Controller
public class RecommendController {
	
	@Autowired
	BookMapper bookMapper;
	
	@Autowired
	BookService bookService;
	
	@Autowired
	BookRecommendServiceImpl bookRecommendService;
	
	@Autowired
	TagServiceImpl tagService;
	
	@Autowired
	AuthorRecommendService authorRecommendService;
	
	@GetMapping("/recommend/{userId}")  //根据用户给出item-cf推荐书籍
	@ResponseBody
	public List<Book> getRecommendBooks(@PathVariable int userId) throws IOException{
		return bookRecommendService.getRecommendBooks(userId);

	}
	
	@GetMapping("/recommend_user_cf/{userId}")  //根据用户给出user-cf推荐书籍
	@ResponseBody
	public List<Book> getRecommendBooksByUserCF(@PathVariable int userId) throws IOException{
		return bookRecommendService.getRecommendBooksByUserCF(userId);
	}
	
	@GetMapping("/tag/{tag}")   //获得指定某一标签的书籍
	public String getBooksByTag(@PathVariable String tag,Model model){
		List<Book> books= tagService.getBooksByTag(tag);
		int count=books.size();
		model.addAttribute("books",books);
		model.addAttribute("count",count);
		model.addAttribute("tag",tag);
		return "rank";
	}
	
	@GetMapping("/test")   //测试标签存入user_tag表
	@ResponseBody
	public void addTagsToUser(){
		List<String> tags=new LinkedList<String>();
		tags.add("三国");
		tags.add("水浒");
		tagService.addTagsToUser(tags, 123);
		
	}
	

	@GetMapping("/tag/test")//测试根据多个标签推荐书籍
	public String getBooksByMultiplyTags(Model model){
		List<String> tags=new LinkedList<String>();
		tags.add("三国");
		tags.add("火凤燎原");
		List<Book> books=tagService.getBooksByMultiplyTags(tags);
		model.addAttribute("books",books);
		return "first_page";
	}
	
	@GetMapping("/first_page")
	public String getFirst_Page(Model model,HttpSession session) throws IOException{
		User user=(User)session.getAttribute("CURRENT_USER");
		List<Book> books=bookRecommendService.getRecommendBooks(123);
		List<Author> authors=authorRecommendService.getAuthorRecommend(123);
		model.addAttribute("books",books);
		model.addAttribute("authors",authors);
		List<Book> hotBooks=bookService.getHotBookList();
		model.addAttribute("hotBooks",hotBooks);
		return "first_page";
		
		
	}

}
