/**  
 * Project Name:BookRecSystem  
 * File Name:tag.java  
 * Package Name:cn.zju.springboot.pojo  
 * Date:2017年12月9日下午6:41:24  
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved.  
 *  
*/  
  
package cn.zju.springboot.pojo;

import javax.persistence.Id;
import javax.persistence.Table;

/**  
 * ClassName:tag <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     2017年12月9日 下午6:41:24 <br/>  
 * @author   john-lin  
 * @version    
 * @since    JDK 1.6  
 * @see        
 */
@Table(name = "tag")
public class Tag {
	@Id
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "tag [name=" + name + "]";
	}
	
	

}
  
