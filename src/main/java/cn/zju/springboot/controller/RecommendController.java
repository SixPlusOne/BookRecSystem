package cn.zju.springboot.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.abel533.entity.Example;

import cn.zju.springboot.mapper.BookMapper;
import cn.zju.springboot.pojo.Book;
import cn.zju.springboot.pojo.Tag;
import cn.zju.springboot.service.BookRecommendServiceImpl;
import cn.zju.springboot.service.TagServiceImpl;

@Controller
public class RecommendController {
	
	@Autowired
	BookMapper bookMapper;
	
	@Autowired
	BookRecommendServiceImpl bookRecommendService;
	
	@Autowired
	TagServiceImpl tagService;
	
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
	@ResponseBody
	public List<Book> getBooksByTag(@PathVariable String tag){
		return tagService.getBooksByTag(tag);
		
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
	@ResponseBody
	public List<Book> getBooksByMultiplyTags(){
		List<String> tags=new LinkedList<String>();
		tags.add("三国");
		tags.add("火凤燎原");
		return tagService.getBooksByMultiplyTags(tags);
	}

}
