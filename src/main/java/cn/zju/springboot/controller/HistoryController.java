package cn.zju.springboot.controller;


import java.io.IOException;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.zju.springboot.mapper.BookFormMapper;
import cn.zju.springboot.mapper.BookMapper;
import cn.zju.springboot.pojo.Book;
import cn.zju.springboot.pojo.BookForm;
import cn.zju.springboot.pojo.Comment;
import cn.zju.springboot.pojo.History;
import cn.zju.springboot.pojo.User;
import cn.zju.springboot.service.CommentServiceImpl;
import cn.zju.springboot.service.HistoryServiceImpl;
import cn.zju.springboot.service.UserLikedBookServiceImpl;
import cn.zju.springboot.service.UserServiceImpl;;

@Controller
@RequestMapping("history/")
public class HistoryController {

	@Autowired
	private HistoryServiceImpl historyService;
	@Autowired
	private CommentServiceImpl commentService;
	@Autowired
	private UserServiceImpl userService;
	@Autowired
	private UserLikedBookServiceImpl userLikedBookService;
	
	@Autowired
	BookMapper bookMapper;

	/**
	 * 查询一个用户所有的历史记录
	 */
	@GetMapping("/viewed_books")
	public Object queryUserHistory(Model model,HttpSession session) throws IOException{
		User user = userService.getUserById(1);
		int userFavor = userLikedBookService.countUserLikedBooks(1);
		int userRead = commentService.countReadBooks(1);
		List<History> viewedHistorys = historyService.getHistoryByUserId(1);
		List<Book> viewedBooks = new LinkedList<Book>();
		for(History viewedHistory:viewedHistorys) {
			viewedBooks.add(bookMapper.selectByPrimaryKey(viewedHistory.getBookId()));
		}
		model.addAttribute("viewedBooks", viewedBooks);
		model.addAttribute("user", user);
		model.addAttribute("userFavor", userFavor);
		model.addAttribute("userRead", userRead);
		return "viewed_books";
	}

	/**
	 * 根据historyId查询单条历史记录
	 */
	@RequestMapping(value = "/getone/{historyId}")
	@ResponseBody
	public Object queryHistory(@PathVariable("historyId") int historyId, HttpServletRequest request) {
		return historyService.getHistoryByHistoryId(historyId);
	}

	/**
	 * 插入一条历史记录
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public ModelMap addHistory(HttpServletRequest request) {
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		int userId = Integer.parseInt(request.getParameter("userId"));
		java.util.Date time = new java.util.Date();
		Date createDate = new Date(time.getTime());
		History history = new History(bookId, userId, createDate);
		ModelMap result = new ModelMap();
		String msg = "新增成功！";
		this.historyService.insertHistory(history);
		result.put("historyInfo", history);
		result.put("msg", msg);
		return result;
	}

	/**
	 * 删除一条历史记录
	 */
	@RequestMapping(value = "/delete/{historyId}")
	@ResponseBody
	public ModelMap deleteHistory(@PathVariable("historyId") int historyId, HttpServletRequest request) {
		ModelMap result = new ModelMap();
		historyService.deleteHistoryByHistoryId(historyId);
		result.put("msg", "删除成功");
		return result;
	}
	
	
}
