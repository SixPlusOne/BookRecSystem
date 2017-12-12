package cn.zju.springboot.controller;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.zju.springboot.pojo.Book;
import cn.zju.springboot.service.SearchServiceImpl;

@Controller
public class SearchController {
	
	@Autowired
	SearchServiceImpl searchService;
	
	@GetMapping("/search/{words}")   //搜索，关键词用一个以上空格隔开
	@ResponseBody
	public List<Book> search(@PathVariable String words){
		String[] strWords=words.split("\\s+");
		Set<Book> set=new HashSet<Book>();
		for(String word:strWords){
		set.addAll(searchService.search(word));
		}
		List<Book> books=new LinkedList<Book>();
		books.addAll(set);
		return books;
		
	}
	
	

}
