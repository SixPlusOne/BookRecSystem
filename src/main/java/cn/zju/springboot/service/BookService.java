package cn.zju.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.entity.Example;

import cn.zju.springboot.mapper.BookMapper;
import cn.zju.springboot.pojo.Book;

@Service
public class BookService {
	
	@Autowired
	private BookMapper bookMapper;
	
	public Book getBookById(int id) {
		return this.bookMapper.selectByPrimaryKey(id);
	}

}
