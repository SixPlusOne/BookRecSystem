/**  
 * Project Name:BookRecSystem  
 * File Name:BookServiceImpl.java  
 * Package Name:cn.zju.springboot.service  
 * Date:2017年12月18日上午7:18:52  
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved.  
 *  
*/  
  
package cn.zju.springboot.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.entity.Example;

import cn.zju.springboot.mapper.BookMapper;
import cn.zju.springboot.mapper.CommentMapper;
import cn.zju.springboot.pojo.Book;
import cn.zju.springboot.pojo.Comment;

/**  
 * ClassName:BookServiceImpl <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     2017年12月18日 上午7:18:52 <br/>  
 * @author   john-lin  
 * @version    
 * @since    JDK 1.6  
 * @see        
 */
@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookMapper bookMapper;
	
	@Autowired
	private CommentMapper commentMapper;
	
	
	@Override
	public List<Book> getBookByAuthorId(int authorId) {
		
		if(authorId == 0)
			return null;
		Example example = new Example(Book.class);
		example.createCriteria().andEqualTo("authorId", authorId);
		return bookMapper.selectByExample(example);
	}

	@Override
	public List<Book> getHotBookList() {
		Example example = new Example(Comment.class);
		return null;
	}

	@Override
	public List<Book> getGoodBookList() {

		// TODO Auto-generated method stub  
		return null;
	}

}
  
