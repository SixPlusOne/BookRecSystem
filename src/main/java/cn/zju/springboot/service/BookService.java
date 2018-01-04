/**  
 * Project Name:BookRecSystem  
 * File Name:BookService.java  
 * Package Name:cn.zju.springboot.service  
 * Date:2017年12月18日上午7:16:52  
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved.  
 *  
*/  
  
package cn.zju.springboot.service;

import java.util.List;


import cn.zju.springboot.pojo.Book;
import cn.zju.springboot.pojo.BookForm;

/**  
 * ClassName:BookService <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     2017年12月18日 上午7:16:52 <br/>  
 * @author   john-lin  
 * @version    
 * @since    JDK 1.6  
 * @see        
 */
public interface BookService {
	
	public List<Book> getBookByAuthorId(int authorId);
	
	public List<Book> getHotBookList();
	//实现增删改查找
	
	public Book getBookById(int id);
	
	public Book getBookByIsbn(String isbn);
	
	public List<BookForm> getHotBookFormList();
	
	public List<Book> getBookByName(String name);
	
	public List<Book> getBookByPublisher(String publisher);
	
	public List<Book> getGoodBookList();
	
	public void insertBook(Book book);
	
	public void deleteBook(Book book);
	
	public List<Book> getBookByPage(int pageSize,int pageNum);

	public String getBookNameByIdTest(int id);
	
	
}
  
