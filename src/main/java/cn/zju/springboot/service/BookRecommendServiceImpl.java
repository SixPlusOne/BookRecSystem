package cn.zju.springboot.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.entity.Example;
import com.github.abel533.entity.Example.Criteria;
import com.github.abel533.entity.Example.Criterion;

import cn.zju.springboot.mapper.BookMapper;
import cn.zju.springboot.mapper.BookTagMapper;
import cn.zju.springboot.pojo.Book;
import cn.zju.springboot.pojo.BookTag;

@Service
public class BookRecommendServiceImpl {
	
	@Autowired
	BookMapper bookMapper;
	
	@Autowired
	BookTagMapper bookTagMapper;
	//根据用户获得item-cf算法推荐书籍
	public List<Book> getRecommendBooks(int userId) throws IOException{ 
		String link="http://127.0.0.1:5000/recommend/"+userId;
		URL url=new URL(link);
		BufferedReader br=new BufferedReader(new InputStreamReader(url.openStream()));
		StringBuilder sb=new StringBuilder();
		String line="";
		while((line=br.readLine())!=null){
			sb.append(line);
		}
		br.close();
		List<Integer> ids=new LinkedList<Integer>();
		String[] strIds=sb.toString().split("\t");
		for(String strId:strIds){
			ids.add(Integer.parseInt(strId));
			
		}
		List<Book> books=new LinkedList<Book>();
		for(Integer bookId:ids){
			books.add(bookMapper.selectByPrimaryKey(bookId));
		}
		
		return books;
	}

		
		
		
	}
	


