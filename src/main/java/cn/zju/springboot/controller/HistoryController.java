package cn.zju.springboot.controller;


import java.sql.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.zju.springboot.pojo.History;
import cn.zju.springboot.service.HistoryServiceImpl;;

@Controller
@RequestMapping("history/")
public class HistoryController {

	@Autowired
	private HistoryServiceImpl historyService;
	
	@RequestMapping(value = "{userId}")
	@ResponseBody
	public Object queryUserHistory(@PathVariable("userId") int userId, HttpServletRequest request) {
		return historyService.getHistoryByUserId(userId);
	}
	
	@RequestMapping(value = "/getone/{historyId}")
	@ResponseBody
	public Object queryHistory(@PathVariable("historyId") int historyId, HttpServletRequest request) {
		return historyService.getHistoryByHistoryId(historyId);
	}
	
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
	
}
