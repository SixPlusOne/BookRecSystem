package cn.zju.springboot.service;

import java.util.List;

import cn.zju.springboot.pojo.Book;
import cn.zju.springboot.pojo.BookForm;

public interface BookRecommendService {
	
	public List<Book> getRecommendBooksByUser(int userId);
	
	public List<BookForm> getSimilarBooks(int id);

}
