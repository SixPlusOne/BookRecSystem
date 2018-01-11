/**  
 * Project Name:BookRecSystem  
 * File Name:UserService.java  
 * Package Name:cn.zju.springboot.service  
 * Date:2017年12月10日下午8:53:18  
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved.  
 *  
*/  
  
package cn.zju.springboot.service;

import java.util.List;

import cn.zju.springboot.pojo.User;

/**  
 * ClassName:UserService <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     2017年12月10日 下午8:53:18 <br/>  
 * @author   john-lin  
 * @version    
 * @since    JDK 1.6 
 * @see        
 */
public interface UserService {
	
	public String register(User user);
	
	public int login(String userName,String passwd);
	
	public User getUserBySession(int userId);
	
	public User getUserById(int userId);

	public boolean update(String UserName, String oldStaffPwd, String newStaffPwd);
	
	public boolean updateName(int userId, String userName);

}
  
