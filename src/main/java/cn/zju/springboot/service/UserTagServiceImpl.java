package cn.zju.springboot.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.abel533.entity.Example;

import cn.zju.springboot.mapper.TagMapper;
import cn.zju.springboot.mapper.UserTagMapper;
import cn.zju.springboot.pojo.UserTag;

/**  
 * ClassName:UserTagServiceImpl <br/>  
 * Function: TODO implement service. <br/>  
 * Date:     2017年12月12日10:51:00<br/>  
 * @author   BruceKun
 * @version  JDK 1.8        
 */

@Service
public class UserTagServiceImpl implements UserTagService{
	
	@Autowired
	private UserTagMapper userTagMapper;
	
	/**
	 * 添加标签
	 * @return 
	 */
	public Object addTagByName(Integer userId,String tagName){
		if(userId == 0 || StringUtils.isEmpty(tagName))
			return null;
		
		UserTag insertTag =  new UserTag();
		insertTag.setUserId(userId);
		insertTag.setTag(tagName);
		
		
		return this.userTagMapper.insert(insertTag);
	}
	
	/**
	 * 删除标签
	 * @param name
	 */
	public Object delTag(Integer userId,String tag){
		if(userId == 0 || StringUtils.isEmpty(tag))
			return null;
		Example example=new Example(UserTag.class);
		example.createCriteria().andEqualTo("userId", userId).andEqualTo("tag", tag);
		return this.userTagMapper.deleteByExample(example);
	}
	
	/**
	 * 修改标签（tag1为现有的，tag2为要更新的）
	 */
	public Object updateTag(Integer userId,String tag1,String tag2){
		if(userId == 0 || StringUtils.isEmpty(tag1)||StringUtils.isEmpty(tag2))
			return null;
		Example example1=new Example(UserTag.class);
		//查询到要修改的项
		example1.createCriteria().andEqualTo("userId", userId).andEqualTo("tag", tag1);
		UserTag userTag=new UserTag();
		userTag.setUserId(userId);
		userTag.setTag(tag2);
		Object re=userTagMapper.updateByExample(userTag, example1);
		return re;
	}
	
	/**
	 * 查询标签
	 * @param name
	 * @return
	 */
	public UserTag selTagById(Integer id) {
		return this.userTagMapper.selectByPrimaryKey(id);
	}

	
	

}
