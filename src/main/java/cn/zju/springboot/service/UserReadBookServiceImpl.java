package cn.zju.springboot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.abel533.entity.Example;
import com.github.abel533.entity.Example.Criterion;

import cn.zju.springboot.mapper.BookMapper;
import cn.zju.springboot.mapper.HistoryMapper;
import cn.zju.springboot.pojo.Book;
import cn.zju.springboot.pojo.History;
/**  
 * ClassName:UserReadBookServiceImpl <br/>  
 * Function: TODO implement service. <br/>  
 * Date:     2017年12月14日10:51:00<br/>  
 * @author   BruceKun
 * @version  JDK 1.8        
 */
public class UserReadBookServiceImpl implements UserReadBookService{

	@Autowired
	private HistoryMapper historyMapper; 
	@Autowired
	private BookMapper bookMapper;
	
	/**
	 * 根据用户id查询读过的书
	 */
	@Override
	public List<History> getHistoryByUserId(Integer userId) {
		Example example=new Example(History.class);
		example.createCriteria().andEqualTo("userId", userId);
		//得到History的List
		List<History> historyList=this.historyMapper.selectByExample(example);
		/*List<Integer> bookIds=new ArrayList<Integer>();
		//遍历HistoryList 得到所有bookId返回bookId列表
		for(int i=0;i<historyList.size();i++) {
			int bookId=historyList.get(i).getBookId();
			bookIds.add(bookId);
		}*/
		return historyList;
	}

	/**
	 * 根据historyId查询单条历史记录
	 */
	@Override
	public List<History> getHistoryByHistoryId(Integer historyId) {
		Example example=new Example(History.class);
		example.createCriteria().andEqualTo("historyId", historyId);
		return this.historyMapper.selectByExample(example);
	}

	/**
	 * 统计读过书的总数目
	 */
	@Override
	public Integer countReadBook() {
		Example example=new Example(History.class);
		List<Criterion> historyList=example.createCriteria().getAllCriteria();
		Integer count=historyList.size();
		return count;
	}


	
	
}
