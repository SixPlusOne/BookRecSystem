/**  
 * Project Name:BookRecSystem  
 * File Name:CommentService.java  
 * Package Name:cn.zju.springboot.service  
 * Date:2017年12月18日上午6:51:52  
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved.  
 *  
*/  
  
package cn.zju.springboot.service;

import java.util.List;

import cn.zju.springboot.pojo.Comment;

/**  
 * ClassName:CommentService <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     2017年12月18日 上午6:51:52 <br/>  
 * @author   john-lin  
 * @version    
 * @since    JDK 1.6  
 * @see        
 */
public interface CommentService {
	
	public boolean insert(Comment comment);
	
	public List<Comment> getCommentByUserId(int userId);
	
	public List<Comment> getCommentByBookId(int bookId);
	
}
  