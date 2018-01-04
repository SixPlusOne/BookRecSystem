package cn.zju.springboot.controller;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.zju.springboot.service.TagService;
import cn.zju.springboot.service.UserLikedBookService;
import cn.zju.springboot.pojo.Favor;
import cn.zju.springboot.pojo.History;
import cn.zju.springboot.pojo.Tag;

@Controller
@RequestMapping("userLikedBook")
public class UserLikedBookController {
	@Autowired
	private UserLikedBookService userLikedBook;
	
//	Favor favorite;
	
	@RequestMapping("deleteFavor")
	@ResponseBody
	public Object deletefavor(HttpServletRequest request) 
	{
		int bookId = Integer.parseInt(request.getParameter("bookId"));
//		return "hello";
		return userLikedBook.deletefaovr(bookId);
	
	}
	
	public static java.sql.Date strToDate(String strDate) {  
        String str = strDate;  
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");  
        java.util.Date d = null;  
        try {  
            d = format.parse(str);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        java.sql.Date date = new java.sql.Date(d.getTime());  
        return date;  
    }  
	
	@RequestMapping("addLikedBook")
	@ResponseBody
	public Object addLikedBook(HttpServletRequest request) throws ParseException
	{
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		int userId = Integer.parseInt(request.getParameter("userId"));
		String createDate=request.getParameter("createDate");
		
		System.out.print(createDate);
		java.sql.Date test_Date= strToDate(createDate);
        System.out.println(test_Date);
		//Date createDate = new Date(time.getTime());
		
		return userLikedBook.addLikedBook(bookId, userId, test_Date);
	}
	
	
	@RequestMapping("countLikeddBooks")
	@ResponseBody
	public int countUserLikedBook(HttpServletRequest request)
	{
		int userId = Integer.parseInt(request.getParameter("userId"));
		return userLikedBook.countUserLikedBooks(userId);
	}

}
