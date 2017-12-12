package cn.zju.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.entity.Example;
import com.github.abel533.entity.Example.Criteria;

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
		Example example = new Example(History.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("userId", userId);
		List<History> result = this.historyMapper.selectByExample(example);
		return result;
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

	/**
	 * 插入一条history
	 * @param history
	 * @return
	 */
	@Override
	public void insertHistory(History history) {
		this.historyMapper.insert(history);
	}

	/**
	 * 根据historyId删除一条history
	 * @param historyId
	 * @return
	 */
	@Override
	public void deleteHistoryByHistoryId(int historyId) {
		this.historyMapper.deleteByPrimaryKey(historyId);
	}

}
