package cn.zju.springboot.service;

import java.util.List;

import cn.zju.springboot.pojo.Author;

public interface AuthorRecommendService {
	
	public List<Author> getAuthorRecommend(int userId);
	
}
