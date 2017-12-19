package cn.zju.springboot.service;

import cn.zju.springboot.pojo.UserTag;

/**
 * 用户选择标签页面Service
 * @author BruceKun
 *
 */
public interface UserTagService {
	//添加标签
	public Object addTagByName(Integer userId,String tagName);
	//删除标签
	public Object delTag(Integer userId,String tag);
	//修改标签
	public Object updateTag(Integer userId,String tag1,String tag2);
	//查询标签
	public Object selTagById(Integer id);
}
