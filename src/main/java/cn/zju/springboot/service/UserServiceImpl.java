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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.abel533.entity.Example;

import cn.zju.springboot.mapper.UserMapper;
import cn.zju.springboot.pojo.User;
import cn.zju.springboot.service.UserService;

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

	@Override
	public String login(String userName,String passwd) {
		if(StringUtils.isEmpty(userName)&& StringUtils.isEmpty(passwd))
			return "账号名密码不能为空";
		
		Example example = new Example(User.class);
		example.createCriteria().andEqualTo("name", userName);
		List<User> userList = userMapper.selectByExample(example);
		if(userList == null) {
			return "用户尚未注册";
		}
		if(userList.get(0).getPassword().equals(passwd)) {
			return "登录成功";
		}else {
			return "用户名密码错误";
		}

	}

	@Override
	public User getUserByName(String userName) {
		if(StringUtils.isEmpty(userName))
			return null;
		Example example = new Example(User.class);
		example.createCriteria().andEqualTo("name", userName);
		List<User> userList = userMapper.selectByExample(example);
		
		return userList.get(0);
	}

	@Override
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

}
  
