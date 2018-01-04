package cn.zju.springboot.service;

import cn.zju.springboot.pojo.Favor;
import java.sql.Date;
public interface UserLikedBookService {
	
	public Object addLikedBook(int bookId,int userId,Date createdate);
	
	public int deletefaovr(int bookId);
	
	public int countUserLikedBooks(int userId);
	
	//public Favor get_favor(int uid);
	
	//public void update(int bookId,int userId,Date create_date);
}
