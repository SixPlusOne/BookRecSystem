package cn.zju.springboot.service;

import java.util.List;
import java.util.Set;

import cn.zju.springboot.pojo.Book;
import cn.zju.springboot.pojo.Tag;

public interface TagService {

	public Tag getTagByName(String name);

	public int insertTag(Tag tag);

	public int deleteTagByName(String name);

	public int updateTag(String oldName, String newName);
	
	public List<String> getTagsByUserId(int userId);
	// 根据单个tag获得书籍
	public Set<Book> getBooksByTagName(String name);
	
	// 根据多个tag获得书籍
	public Set<Book> getBooksByTagNameList(List<String> names);
}
