package cn.zju.springboot.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.entity.Example;

import cn.zju.springboot.mapper.AuthorMapper;
import cn.zju.springboot.mapper.BookMapper;
import cn.zju.springboot.mapper.CommentMapper;
import cn.zju.springboot.mapper.UserMapper;
import cn.zju.springboot.pojo.Author;
import cn.zju.springboot.pojo.Book;
import cn.zju.springboot.pojo.Comment;;

@Service
public class AuthorRecommendServiceImpl implements AuthorRecommendService {

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private CommentMapper commentMapper;
	
	@Autowired
	private BookMapper bookMapper;
	
	@Autowired
	private AuthorMapper authorMapper;
	
	/**
	 * 根据userId给该User推荐相关的作者
	 * @param userId
	 * @return
	 */	
	@Override
	public List<Author> getAuthorRecommend(int userId) {
//		System.out.println("开始调用Service");
		//得到该用户所有的评论信息
		Set<Comment> commentSet = new HashSet<Comment>();
		Set<Book> bookSet = new HashSet<Book>();
		Set<Author> authorSet = new HashSet<Author>();
		Example commentExample = new Example(Comment.class);
		commentExample.createCriteria().andEqualTo("userId", userId);
		commentSet.addAll(commentMapper.selectByExample(commentExample));
		//得到该用户所有的已读书籍
		for(Comment comment:commentSet) {
//			System.out.println(comment);
			Example bookExample = new Example(Book.class);
			bookExample.createCriteria().andEqualTo("id", comment.getBookId());
			bookSet.addAll(bookMapper.selectByExample(bookExample));
		}
		//根据已读书籍得到所有的作者，取三名作者返回
		for(Book book:bookSet) {
//			System.out.println(book);
			Example authorExample = new Example(Author.class);
			authorExample.createCriteria().andEqualTo("id", book.getAuthorId());
			authorSet.addAll(authorMapper.selectByExample(authorExample));
		}
		List<Author> authors = new LinkedList<Author>();
		Iterator<Author> it = authorSet.iterator();
		while(authors.size()<3 && it.hasNext()) {
//			System.out.println(it.next());
			authors.add(it.next());
		}
		return authors;
		
	}

}
