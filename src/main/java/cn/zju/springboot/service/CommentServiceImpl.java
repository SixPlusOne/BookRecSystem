/**  
 * Project Name:BookRecSystem  
 * File Name:CommentServiceImpl.java  
 * Package Name:cn.zju.springboot.service  
 * Date:2017年12月18日上午6:54:15  
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved.  
 *  
*/  
  
package cn.zju.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.entity.Example;

import cn.zju.springboot.mapper.CommentMapper;
import cn.zju.springboot.pojo.Book;
import cn.zju.springboot.pojo.BookForm;
import cn.zju.springboot.pojo.Comment;

/**  
 * ClassName:CommentServiceImpl <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     2017年12月18日 上午6:54:15 <br/>  
 * @author   john-lin  
 * @version    
 * @since    JDK 1.6  
 * @see        
 */
@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentMapper commentMapper;

	@Override
	public boolean insert(Comment comment) {
		
		if(comment == null)
			return false;
		int signal = commentMapper.insert(comment);
		
		if(signal > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<Comment> getCommentByUserId(int userId) {
		
		if(userId == 0)
			return null;
		Example example = new Example(Comment.class);
		example.createCriteria().andEqualTo("userId", userId);
		return commentMapper.selectByExample(example);
	}

	@Override
	public List<Comment> getCommentByBookId(int bookId) {
		if(bookId == 0)
			return null;
		Example example = new Example(Comment.class);
		example.createCriteria().andEqualTo("bookId", bookId);
		return commentMapper.selectByExample(example);
	}
	
	@Override
	public List<Book> getReadBookByUserId(int userId) {
		if(userId == 0)
			return null;
		return commentMapper.findReadBooks(userId);
	}

	@Override
	public int countReadBooks(int userId) {
		Example example = new Example(Comment.class);
		example.createCriteria().andEqualTo("userId", userId);
		List<Comment> commentList = this.commentMapper.selectByExample(example);
		return commentList.size();
	}

}
  
