package cn.zju.springboot.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.zju.springboot.service.AuthorRecommendServiceImpl;

@Controller
@RequestMapping("author_recommend/")
public class AuthorRecommendController {
	
	@Autowired
	private AuthorRecommendServiceImpl authorRecommendService;

	@RequestMapping(value = "{userId}")
	@ResponseBody	
	public Object queryAuthorRecommend(@PathVariable("userId") int userId, HttpServletRequest request) {
		return authorRecommendService.getAuthorRecommend(userId);
	}
}
