package cn.zju.springboot.service;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.entity.Example;

import cn.zju.springboot.mapper.AuthorMapper;
import cn.zju.springboot.mapper.BookFormMapper;
import cn.zju.springboot.mapper.BookMapper;
import cn.zju.springboot.mapper.BookTagMapper;
import cn.zju.springboot.pojo.Author;
import cn.zju.springboot.pojo.Book;
import cn.zju.springboot.pojo.BookForm;
import cn.zju.springboot.pojo.BookTag;

@Service
public class SearchServiceImpl implements SearchService{
	
	@Autowired
	BookMapper bookMapper;
	
	@Autowired
	BookTagMapper bookTagMapper;
	
	@Autowired
	AuthorMapper authorMapper;
	
	@Autowired
	BookFormMapper bookFormMapper;
	
	@Override
	public  List<BookForm> searchByBook(String word){  //根据书的信息搜索
		word="%"+word+"%";
		Set<BookForm> set=new HashSet<BookForm>();
		Example example1=new Example(BookForm.class);
		example1.createCriteria().andLike("name", word);
		set.addAll(bookFormMapper.selectByExample(example1));
		Example example2=new Example(BookForm.class);
		example2.createCriteria().andLike("summary",word);
		set.addAll(bookFormMapper.selectByExample(example2));
		Example example3=new Example(BookForm.class);
		example3.createCriteria().andLike("publisher", word);
		
		set.addAll(bookFormMapper.selectByExample(example3));
		Example example4=new Example(BookForm.class);
		example4.createCriteria().andLike("id", word);
		set.addAll(bookFormMapper.selectByExample(example4));
		
		Example example5=new Example(BookForm.class);
		example5.createCriteria().andLike("isbn", word);
		set.addAll(bookFormMapper.selectByExample(example5));
		List<BookForm> books=new LinkedList<BookForm>();
		books.addAll(set);
		return books;
		
	}
	
	@Override
	public  List<BookForm> searchByTag(String word){   //根据标签信息搜索
		word="%"+word+"%";
		Set<BookForm> set=new HashSet<BookForm>();
		
		List<BookTag> bookTags=new LinkedList<BookTag>();
		Example tagExample=new Example(BookTag.class);
		tagExample.createCriteria().andLike("tag", word);
		bookTags=bookTagMapper.selectByExample(tagExample);
		for(BookTag bookTag:bookTags){
			BookForm book=bookFormMapper.selectByPrimaryKey(bookTag.getBookId());
			set.add(book);
			
		}
		
		List<BookForm> books=new LinkedList<BookForm>();
		books.addAll(set);
		return books;
		
	}
	
	@Override
	public List<BookForm> searchByAuthor(String word){  //根据作者信息搜索
		word="%"+word+"%";
		Set<BookForm> set=new HashSet<BookForm>();
		Set<Author> authorSet=new HashSet<Author>();
		Example authorExample=new Example(Author.class);
		authorExample.createCriteria().andLike("name", word);
		authorSet.addAll(authorMapper.selectByExample(authorExample));
		
		Example author_introExample=new Example(Author.class);
		author_introExample.createCriteria().andLike("summary", word);
		authorSet.addAll(authorMapper.selectByExample(author_introExample));
		for(Author author:authorSet){
			Example example=new Example(Book.class);
			example.createCriteria().andEqualTo("authorId", author.getId());
			set.addAll(bookFormMapper.selectByExample(example));
			
		}
		List<BookForm> books=new LinkedList<BookForm>();
		books.addAll(set);
		return books;
		
	}
	
	public List<BookForm> search(String word){  //根据所有信息搜索
		List<BookForm> books=new LinkedList<BookForm>();
		books.addAll(searchByBook(word));
		books.addAll(searchByAuthor(word));
		books.addAll(searchByTag(word));
		return books;
	
	}	
	
	// 根据指定单一标签获得书籍
		public List<BookForm> getBookFormsByWords(String words) {
			return bookFormMapper.findBooksByWords(words);
		}
		
	
		
}

