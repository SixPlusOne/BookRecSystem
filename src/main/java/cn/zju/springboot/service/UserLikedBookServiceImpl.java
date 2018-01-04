package cn.zju.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.entity.Example;
import com.github.abel533.entity.Example.Criteria;

import cn.zju.springboot.mapper.UserLikedBookMapper;
import cn.zju.springboot.pojo.Favor;
import cn.zju.springboot.pojo.History;

import java.sql.Date;
import java.util.List;


@Service
public class UserLikedBookServiceImpl implements UserLikedBookService {
	
	@Autowired
	private UserLikedBookMapper userLikedBookMapper;
	
	
	//add liked book to favor table
	public Object addLikedBook(int bookId,int userId,Date createdate)
	{
		
		if(userId == 0 || bookId == 0)
			return null;
		Favor likedbook = new Favor();
		likedbook.setUserId(userId);
		likedbook.setBookId(bookId);
		likedbook.setCreateDate(createdate);
		return this.userLikedBookMapper.insert(likedbook);
	}
	
	// delete liked book from favor table
	
	public int deletefaovr(int bookId)
	{
		Example example = new Example(Favor.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("bookId", bookId);
		return this.userLikedBookMapper.deleteByExample(example);
	}
	
	
	public int countUserLikedBooks(int userId)
	{
		Example example=new Example(Favor.class);
		example.createCriteria().andEqualTo("userId", userId);
		//得到History的List
		List<Favor> FavorList=this.userLikedBookMapper.selectByExample(example);
		/*List<Integer> bookIds=new ArrayList<Integer>();
		//遍历HistoryList 得到所有bookId返回bookId列表
		for(int i=0;i<historyList.size();i++) {
			int bookId=historyList.get(i).getBookId();
			bookIds.add(bookId);
		}*/
		return FavorList.size();
	}
	
	// get liked book from favor table
	/*public Favor get_favor(int uid)
	{
		if (uid == 0)
			return null;
		return this.userLikedBookMapper.selectByPrimaryKey(uid);
		
		
	}
	
	public void update(int bookId,int userId,Date create_date)
	{
		this.delete_faovr(bookId);
		this.addLikedBookByID(bookId, userId, create_date);
		
	}*/
	
}
