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
	
	public List<Book> getGoodBookList();
	

}
  
