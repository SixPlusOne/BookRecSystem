package cn.zju.springboot.service;

import java.util.List;

import cn.zju.springboot.pojo.Book;

public interface BookRecommendService {
	
	public List<Book> getRecommendBooksByUser(int userId);

}
