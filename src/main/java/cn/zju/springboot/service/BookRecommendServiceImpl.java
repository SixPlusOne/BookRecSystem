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

import cn.zju.springboot.mapper.BookFormMapper;
import cn.zju.springboot.mapper.BookMapper;
import cn.zju.springboot.mapper.BookTagMapper;
import cn.zju.springboot.mapper.UserMapper;
import cn.zju.springboot.pojo.Book;
import cn.zju.springboot.pojo.BookForm;
import cn.zju.springboot.pojo.BookTag;

@Service
public class BookRecommendServiceImpl {
	
	@Autowired
	BookMapper bookMapper;
	
	@Autowired
	UserMapper userMapper;
	
	@Autowired
	BookFormMapper bookFormMapper;
	
	@Autowired
	BookTagMapper bookTagMapper;
	
	//根据用户获得item-cf算法推荐书籍
	public List<BookForm> getRecommendBooks(int userId) throws IOException{ 
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
		List<BookForm> books=new LinkedList<BookForm>();
		for(Integer bookId:ids){
			books.add(bookFormMapper.findone(bookId));
		}
		
		return books;
	}
	
	//根据用户获得user-cf算法推荐书籍
	public List<BookForm> getRecommendBooksByUserCF(int userId) throws IOException{
		String link="http://127.0.0.1:5000/user_cf/"+userId;
		URL url=new URL(link);
		BufferedReader br=new BufferedReader(new InputStreamReader(url.openStream()));
		StringBuilder sb=new StringBuilder();
		String line="";
		while((line=br.readLine())!=null){
			sb.append(line);
		}
		System.out.println(sb);
		br.close();
		List<String> names=new LinkedList<String>();
		String[] strNames=sb.toString().split("\t");
		for(String strName:strNames){
			names.add(strName);
		}
		
		List<BookForm> books=new LinkedList<BookForm>();
		for (int i = 0; i<strNames.length && books.size() < 6; i++) {
			books.add(bookFormMapper.findbyname(strNames[i]));
		}
		
		return books;
	}
	
	public List<BookForm> getSimilarBooks(int id){
		return bookFormMapper.getSimilarBooks(id);
		
	}
		
	}
	


