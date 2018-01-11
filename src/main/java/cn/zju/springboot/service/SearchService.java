package cn.zju.springboot.service;

import java.util.List;

import cn.zju.springboot.pojo.Book;
import cn.zju.springboot.pojo.BookForm;

public interface SearchService {
	public  List<BookForm> searchByBook(String word);//根据书籍信息搜索
	public  List<BookForm> searchByTag(String word);   //根据标签信息搜索
	public List<BookForm> searchByAuthor(String word); //根据作者信息搜索
	public List<BookForm> search(String word);  //根据所有信息搜索

}
