package cn.zju.springboot.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.container.page.Page;
import com.github.abel533.entity.Example;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.zju.springboot.mapper.AuthorMapper;
import cn.zju.springboot.mapper.BookFormMapper;
import cn.zju.springboot.mapper.BookMapper;
import cn.zju.springboot.mapper.BookTagMapper;
import cn.zju.springboot.pojo.Author;
import cn.zju.springboot.pojo.Book;
import cn.zju.springboot.pojo.BookForm;
import cn.zju.springboot.pojo.BookTag;
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
	BookTagMapper bookTagMapper;
	
	@Autowired
	AuthorMapper authorMapper;
	
	@Autowired
	BookRecommendServiceImpl bookRecommendService;
	
	@Autowired
	TagServiceImpl tagService;
	
	@Autowired
	BookFormMapper bookFormMapper;
	
	@Autowired
	AuthorRecommendService authorRecommendService;
	
	@GetMapping("/recommend/{userId}")  //根据用户给出item-cf推荐书籍
	@ResponseBody
	public List<BookForm> getRecommendBooks(@PathVariable int userId) throws IOException{
		return bookRecommendService.getRecommendBooks(userId);

	}
	
	@GetMapping("/recommend_user_cf/{userId}")  //根据用户给出user-cf推荐书籍
	@ResponseBody
	public List<BookForm> getRecommendBooksByUserCF(@PathVariable int userId) throws IOException{
		return bookRecommendService.getRecommendBooksByUserCF(userId);
	}
	

	
	@GetMapping("/tag/{tag}")   //获得指定某一标签的书籍
	public Object getBooksByTag(@PathVariable String tag,Model model,@RequestParam Optional<Integer> page, @RequestParam Optional<Integer> pageSize){
		PageHelper.startPage(page.orElse(1), pageSize.orElse(20));
		PageInfo<BookForm> books=new PageInfo<BookForm>(tagService.getBookFormsByTag(tag));
		
		model.addAttribute("books",books);
		model.addAttribute("count",books.getTotal());
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
	public Object getFirst_Page(Model model,HttpSession session) throws IOException{
		Map<String, Object> result = new HashMap<>();
		if(session.getAttribute("userId") == null) {
			result.put("success", false);
			result.put("Msg", "Time out,please login again!");
			return result;
		}
		int userId = (int) session.getAttribute("userId");
		//User user=(User)session.getAttribute("CURRENT_USER");
		//List<BookForm> books=bookRecommendService.getRecommendBooks(user.getId());
//		List<BookForm> books=bookRecommendService.getRecommendBooks(123);
//		List<Author> authors=authorRecommendService.getAuthorRecommend(123);
		List<BookForm> user_cf_books=bookRecommendService.getRecommendBooksByUserCF(userId);
//		model.addAttribute("books",books);
//		model.addAttribute("authors",authors);
//	List<BookForm> hotBooks=bookService.getHotBookFormList();
		model.addAttribute("user_cf_books",user_cf_books);
		return "first_page";
		
		
	}
	@GetMapping("/ranklist")   //获得指定某一标签的书籍
	public Object getRank(Model model,@RequestParam Optional<Integer> page, @RequestParam Optional<Integer> pageSize){
		PageHelper.startPage(page.orElse(1), pageSize.orElse(20));
		PageInfo<BookForm> books=new PageInfo<BookForm>(tagService.getBookFormsByTag("文学"));
		
		model.addAttribute("books",books);

		return "ranklist";
  
	}
	
}
