package cn.zju.springboot.service;

import java.util.List;

import cn.zju.springboot.pojo.History;

public interface HistoryService {
	
	public List<History> getHistoryByUserId(int userId);
	
	public History getHistoryByHistoryId(int historyId);
	
	public void insertHistory(History history);
	
	public void deleteHistoryByHistoryId(int historyId);

}
