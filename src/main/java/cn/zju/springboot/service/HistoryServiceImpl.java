package cn.zju.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.entity.Example;

import cn.zju.springboot.mapper.HistoryMapper;
import cn.zju.springboot.pojo.History;

@Service
public class HistoryServiceImpl implements HistoryService {
	
	@Autowired
	private HistoryMapper historyMapper;

	/**
	 * 根据userId查找该user的所有历史浏览记录
	 * @param userId
	 * @return
	 */
	@Override
	public List<History> getHistoryByUserId(int userId) {
		return this.historyMapper.select(new History());
	}

	/**
	 * 根据historyId查找该条历史浏览记录
	 * @param historyId
	 * @return
	 */
	@Override
	public History getHistoryByHistoryId(int historyId) {
		return this.historyMapper.selectByPrimaryKey(historyId);
	}

	@Override
	public void insertHistory(History history) {
		this.historyMapper.insert(history);
	}

	@Override
	public int deleteHistoryByHistoryId(int historyId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateHistory(History history) {
		// TODO Auto-generated method stub
		return 0;
	}

}
