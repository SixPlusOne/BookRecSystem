/**  
 * Project Name:BookRecSystem  
 * File Name:AuthorService.java  
 * Package Name:cn.zju.springboot.service  
 * Date:2017年12月10日下午9:32:44  
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved.  
 *  
*/  
  
package cn.zju.springboot.service;  
/**  
 * ClassName:AuthorService <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     2017年12月10日 下午9:32:44 <br/>  
 * @author   john-lin  
 * @version    
 * @since    JDK 1.6  
 * @see        
 */

import java.util.List;

import cn.zju.springboot.pojo.Author;

public interface AuthorService {
	
	public Author getAuthorByName(String authorName);
	
	public List<Author> getAuthorListByPage(int pageSize,int pageNum);

}
  
