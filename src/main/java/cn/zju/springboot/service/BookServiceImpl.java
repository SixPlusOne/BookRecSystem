
package cn.zju.springboot.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.entity.Example;
import com.github.abel533.entity.Example.Criteria;

import cn.zju.springboot.mapper.BookMapper;
import cn.zju.springboot.pojo.Book;
import cn.zju.springboot.pojo.History;

@Service
public class BookServiceImpl implements BookService{
	
	@Autowired
	private BookMapper bookMapper;
	
	/**
	 * 根据bookId查找该书的内容
	 * @param bookId
	 * @return Book对象
	 */
	@Override
	public Book getBookById(int id) {
		return this.bookMapper.selectByPrimaryKey(id);
	}
	
	/**
	 * 根据isbn号获取书籍
	 * @param isbn
	 * @return Book对象
	 */
	@Override
	public Book getBookByIsbn(String isbn) {
		Example example = new Example(Book.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("isbn", isbn);
		List<Book> result = this.bookMapper.selectByExample(example);
		return result.get(0);
	}
	
	/**
	 * 根据书名获取书籍
	 * @param name
	 * @return Book对象的list
	 */
	@Override
	public List<Book> getBookByName(String name) {
		Example example = new Example(Book.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("name", name);
		List<Book> result = this.bookMapper.selectByExample(example);
		return result;
	}
	
	/**
	 * 根据作者id获取书籍
	 * @param authorId
	 * @return Book对象的list
	 */
	@Override
	public List<Book> getBookByAuthorId(int authorId) {
		Example example = new Example(Book.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("authorId", authorId);
		List<Book> result = this.bookMapper.selectByExample(example);
		return result;
	}
	
	/**
	 * 根据出版社获取书籍
	 * @param publisher
	 * @return Book对象的list
	 */
	@Override
	public List<Book> getBookByPublisher(String publisher) {
		Example example = new Example(Book.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("publisher", publisher);
		List<Book> result = this.bookMapper.selectByExample(example);
		return result;
	}

	/**
	 * 根据书籍内容插入
	 * @param book对象
	 * return
	 */
	@Override
	public void insertBook(Book book) {
		this.bookMapper.insert(book);
		
	}

	/**
	 * 根据书籍内容删除
	 * @param book对象
	 * return
	 */
	@Override
	public void deleteBook(Book book) {
		this.bookMapper.delete(book);
	}

	@Override
	public List<Book> getHotBookList() {
		List<Book> books=new LinkedList<Book>();  
		books.add(bookMapper.selectByPrimaryKey(1000093));
		books.add(bookMapper.selectByPrimaryKey(1000110));
		books.add(bookMapper.selectByPrimaryKey(1000110));
		
		// TODO Auto-generated method stub  
		return books;
	}

	@Override
	public List<Book> getGoodBookList() {
		  
		// TODO Auto-generated method stub  
		return null;
	}
	
	
}
