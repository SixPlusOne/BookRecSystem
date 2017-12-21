package cn.zju.springboot.service;

import java.util.List;

import cn.zju.springboot.pojo.Book;
import cn.zju.springboot.pojo.History;

/**
 * 读过的书
 * @author BruceKun
 *
 */
public interface UserReadBookService {
	//通过用户id查询读过的书
	public List<History> getHistoryByUserId(Integer userId);
	//根据historyId查询单条历史记录
	public List<History> getHistoryByHistoryId(Integer historyId);
	//返回读过书的数目
	public Integer countReadBook();

}
