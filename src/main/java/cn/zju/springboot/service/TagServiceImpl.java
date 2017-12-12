package cn.zju.springboot.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.entity.Example;

import cn.zju.springboot.mapper.BookMapper;
import cn.zju.springboot.mapper.BookTagMapper;
import cn.zju.springboot.mapper.TagMapper;
import cn.zju.springboot.mapper.UserTagMapper;
import cn.zju.springboot.pojo.Book;
import cn.zju.springboot.pojo.BookTag;
import cn.zju.springboot.pojo.Tag;
import cn.zju.springboot.pojo.UserTag;

@Service
public class TagServiceImpl implements TagService{

	@Autowired
	private TagMapper tagMapper;
	
	@Autowired
	UserTagMapper userTagMapper;
	
	@Autowired
	BookMapper bookMapper;
	
	@Autowired
	BookTagMapper bookTagMapper;

	/**
	 * 根据tag的name查找tag
	 * @param name
	 * @return
	 */
	@Override
	public Tag getTagByName(String name) {
		return this.tagMapper.selectByPrimaryKey(name);
	}
	
	// c, 增加一条tag
	// 返回值, 不确定是什么, 是插入后的影响记录数么
	/**
	 * 
	 * @param tag
	 * @return
	 */
	@Override
	public int insertTag(Tag tag) {
		return tagMapper.insert(tag);
	}
	
	/**
	 * delete by tag name
	 * @param name
	 * @return
	 */
	@Override
	public int deleteTagByName(String name) {
		return tagMapper.deleteByPrimaryKey(name);
	}
	
	/**
	 * update tag by tag
	 * @param record
	 * @return
	 */
	@Override
	public int updateTag(String oldName, String newName) {
		Example example = new Example(Tag.class);
		example.createCriteria().andEqualTo("name", oldName);
		Tag record = new Tag();
		record.setName(newName);
//		return tagMapper.updateByExampleSelective(record, example);
		return tagMapper.updateByExample(record, example);
	}
	

	//将指定标签加入user_tag表中
	public void addTagsToUser(List<String> tags,int userId){
		for(String tag:tags){
			UserTag userTag=new UserTag();
			userTag.setTag(tag);
			userTag.setUserId(userId);
			userTagMapper.insert(userTag);
		}
	}
	
	//根据指定用户返回对应标签
	public List<String> getTagsByUserId(long userId){
		Example example=new Example(UserTag.class);
		example.createCriteria().andEqualTo("user_id", userId);
		List<UserTag> userTags=userTagMapper.selectByExample(example);
		List<String> tags=new LinkedList<String>();
		for(UserTag userTag:userTags){
			tags.add(userTag.getTag());
		}
		return tags;
	}
	
	
	//根据指定单一标签获得书籍
	public List<Book> getBooksByTag(String tag){
		Example example=new Example(BookTag.class);
		example.createCriteria().andLike("tag", tag);
		List<BookTag> relations=bookTagMapper.selectByExample(example);
		List<Book> books=new LinkedList<Book>();
		for(BookTag relation:relations){
			books.add(bookMapper.selectByPrimaryKey(relation.getBookId()));
		}
		
		return books;
		
	}
	//根据多个标签获得书籍,根据评分对书籍排序后返回
	public List<Book> getBooksByMultiplyTags(List<String> tags){
		
		Set<Book> set=new HashSet<Book>();
		for(String tag:tags){
			set.addAll(getBooksByTag(tag));
		}
		
		List<Book> books=new LinkedList<Book>();
		books.addAll(set);
		Collections.sort(books,new Comparator<Book>(){
			@Override
			public int compare(Book o1,Book o2){
			
				if(o1.getRating()>o2.getRating()){
					return 1;
				}
				if(o1.getRating()==o2.getRating()){
					return 0;
				}
				return -1;
			}
		});
		
		return books;
		

		}



}
