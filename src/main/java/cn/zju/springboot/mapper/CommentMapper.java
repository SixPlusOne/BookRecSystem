/**  
 * Project Name:BookRecSystem  
 * File Name:commentMapper.java  
 * Package Name:cn.zju.springboot.mapper  
 * Date:2017年12月9日下午6:48:28  
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved.  
 *  
*/  
  
package cn.zju.springboot.mapper;  
/**  
 * ClassName:commentMapper <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     2017年12月9日 下午6:48:28 <br/>  
 * @author   john-lin  
 * @version    
 * @since    JDK 1.6  
 * @see        
 */

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;

import cn.zju.springboot.pojo.Book;
import cn.zju.springboot.pojo.BookForm;
import cn.zju.springboot.pojo.Comment;


public interface CommentMapper extends Mapper<Comment> {
	List<Book> findReadBooks(@Param("user_id") int user_id);
}
  
