package cn.zju.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.entity.Example;

import cn.zju.springboot.mapper.BookMapper;
import cn.zju.springboot.pojo.Book;
import cn.zju.springboot.pojo.History;

@Service
public interface BookService {
	
	//实现增删改查找
	
	public Book getBookById(int id);
	
	public Book getBookByIsbn(String isbn);
	
	public List<Book> getBookByName(String name);
	
	public List<Book> getBookByAuthorId(int authorId);
	
	public List<Book> getBookByPublisher(String publisher);
	
	public void insertBook(Book book);
	
	public void deleteBook(Book book);
}
