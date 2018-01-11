package cn.zju.springboot.controller;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.zju.springboot.pojo.Book;
import cn.zju.springboot.pojo.BookForm;
import cn.zju.springboot.service.SearchServiceImpl;

@Controller
public class SearchController {
	
	@Autowired
	SearchServiceImpl searchService;
	
	@GetMapping("/search/{words}")   //搜索，关键词用一个以上空格隔开
	public Object search(@PathVariable String words,Model model,@RequestParam Optional<Integer> page, @RequestParam Optional<Integer> pageSize){
		/*String[] strWords=words.split("\\s+");
		Set<Book> set=new HashSet<Book>();
		for(String word:strWords){
		set.addAll(searchService.search(word));
		}
		List<Book> books=new LinkedList<Book>();
		books.addAll(set);
		return books;*/
		
		PageHelper.startPage(page.orElse(1), pageSize.orElse(20));
		PageInfo<BookForm> books=new PageInfo<BookForm>(searchService.getBookFormsByWords(words));
		model.addAttribute("books",books);
		model.addAttribute("count",books.getTotal());
		model.addAttribute("words",words);
		return "search";	
	}
	
	

}
