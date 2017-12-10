/**  
 * Project Name:BookRecSystem  
 * File Name:bookTagRelation.java  
 * Package Name:cn.zju.springboot.pojo  
 * Date:2017年12月9日下午4:46:59  
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved.  
 *  
*/  
  
package cn.zju.springboot.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**  
 * ClassName:bookTagRelation <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     2017年12月9日 下午4:46:59 <br/>  
 * @author   john-lin  
 * @version    
 * @since    JDK 1.6  
 * @see        
 */
@Table(name = "book_tag")
public class BookTag {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int bookId;
	private String tag;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	@Override
	public String toString() {
		return "bookTagRelation [id=" + id + ", bookId=" + bookId + ", tag=" + tag + "]";
	}
	
	

}
  
