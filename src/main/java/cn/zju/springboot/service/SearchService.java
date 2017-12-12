package cn.zju.springboot.service;

import java.util.List;

import cn.zju.springboot.pojo.Book;

public interface SearchService {
	public  List<Book> searchByBook(String word);//根据书籍信息搜索
	public  List<Book> searchByTag(String word);   //根据标签信息搜索
	public List<Book> searchByAuthor(String word); //根据作者信息搜索
	public List<Book> search(String word);  //根据所有信息搜索

}
