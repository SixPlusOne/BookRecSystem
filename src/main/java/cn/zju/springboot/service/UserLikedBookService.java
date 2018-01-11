package cn.zju.springboot.service;

import cn.zju.springboot.pojo.Favor;
import cn.zju.springboot.pojo.History;

import java.sql.Date;
import java.util.List;
public interface UserLikedBookService {
	
	public Object addLikedBook(int bookId,int userId,Date createdate);
	
	public int deletefaovr(int bookId,int userId);
	
	public int countUserLikedBooks(int userId);
	
	public List<Favor> getFavorByUserId(int userId);
	
	//public Favor get_favor(int uid);
	
	//public void update(int bookId,int userId,Date create_date);
}
