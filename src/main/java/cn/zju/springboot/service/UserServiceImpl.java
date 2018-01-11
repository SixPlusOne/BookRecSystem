/**  
 * Project Name:BookRecSystem  
 * File Name:UserServiceImpl.java  
 * Package Name:cn.zju.springboot.service  
 * Date:2017年12月10日下午9:12:51  
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved.  
 *  
*/  
  
package cn.zju.springboot.service;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.abel533.entity.Example;

import cn.zju.springboot.mapper.UserMapper;
import cn.zju.springboot.pojo.User;

/**  
 * ClassName:UserServiceImpl <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     2017年12月10日 下午9:12:51 <br/>  
 * @author   john-lin  
 * @version    
 * @since    JDK 1.6  
 * @see        
 */
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
    private StringRedisTemplate stringRedisTemplate;

	@Override
	public String register(User user) {
		int insertResult = 0;
		if(user == null)
			return "注册信息不能为空";
		User selectOne = userMapper.selectOne(user);
		
		if(selectOne == null) {
			insertResult = userMapper.insert(user);
		}else {
			return "用户已注册";
		}
		
		if(insertResult > 0) {
			return "注册成功";
		}else {
			return "注册失败";
		}

	}
	
	/**
	 * 
	 * TODO 简单描述该方法的实现功能（可选）. 
	 * 返回值：
	 * -1 代表用户没有注册
	 * -2 代表用户密码错误
	 * 用户登录成功 返回用户Id 
	 * @see cn.zju.springboot.service.UserService#login(java.lang.String, java.lang.String)
	 */
	@Override
	public int login(String userName,String passwd) {
		
		Example example = new Example(User.class);
		example.createCriteria().andEqualTo("name", userName);
		List<User> userList = userMapper.selectByExample(example);
		if(userList == null) {
			return -1;
		}
		if(userList.get(0).getPassword().equals(passwd)) {
			return userList.get(0).getId();
		}else {
			return -2;
		}

	}

	@Override
	public User getUserBySession(int userId) {
		return userMapper.selectByPrimaryKey(userId);
	}

	@Override
	public User getUserById(int userId) {
		return this.userMapper.selectByPrimaryKey(userId);
	}
	
	public boolean update(String UserName, String oldStaffPwd, String newStaffPwd) {
		
		Example example = new Example(User.class);
		example.createCriteria().andEqualTo("name", UserName);
		User user = userMapper.selectByExample(example).get(0);
		if(oldStaffPwd.equals(user.getPassword())) {
			user.setPassword(newStaffPwd);
			if(userMapper.updateByPrimaryKey(user)>0)
				return true;
		}
		
		return false;
	}

	@Override
	public boolean updateName(int userId, String userName) {
		User user = userMapper.selectByPrimaryKey(userId);
		user.setName(userName);
		if(userMapper.updateByPrimaryKey(user)>0) {
			return true;
		}
		return false;
	}

}
  
