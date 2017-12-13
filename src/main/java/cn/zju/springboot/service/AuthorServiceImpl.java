/**  
 * Project Name:BookRecSystem  
 * File Name:AuthorServiceImpl.java  
 * Package Name:cn.zju.springboot.service.impl  
 * Date:2017年12月10日下午9:36:02  
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved.  
 *  
*/  
  
package cn.zju.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.abel533.entity.Example;

import cn.zju.springboot.mapper.AuthorMapper;
import cn.zju.springboot.pojo.Author;
import cn.zju.springboot.service.AuthorService;

/**  
 * ClassName:AuthorServiceImpl <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     2017年12月10日 下午9:36:02 <br/>  
 * @author   john-lin  
 * @version    
 * @since    JDK 1.6  
 * @see        
 */
@Service
public class AuthorServiceImpl implements AuthorService {
	
	@Autowired
	private AuthorMapper authorMapper;

	@Override
	public Author getAuthorByName(String authorName) {
		
		if(StringUtils.isEmpty(authorName))
			return null;
		
		Example example = new Example(Author.class);
		example.createCriteria().andEqualTo("name", authorName);
		List<Author> authorList = authorMapper.selectByExample(example);
		if(authorList == null) {
			System.out.println("作者不存在");
			return null;
		}
		return authorList.get(0);
	}

}
  
