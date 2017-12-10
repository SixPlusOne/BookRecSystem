/**  
 * Project Name:BookRecSystem  
 * File Name:booksimilarity.java  
 * Package Name:cn.zju.springboot.pojo  
 * Date:2017年12月9日下午6:30:37  
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved.  
 *  
*/  
  
package cn.zju.springboot.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**  
 * ClassName:booksimilarity <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     2017年12月9日 下午6:30:37 <br/>  
 * @author   john-lin  
 * @version    
 * @since    JDK 1.6  
 * @see        
 */
@Table(name = "book_similarity_icf")
public class BookSimilarityIcf {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int bookId1; 
	private int bookId2; 
	private double similarity;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBookId1() {
		return bookId1;
	}
	public void setBookId1(int bookId1) {
		this.bookId1 = bookId1;
	}
	public int getBookId2() {
		return bookId2;
	}
	public void setBookId2(int bookId2) {
		this.bookId2 = bookId2;
	}
	public double getSimilarity() {
		return similarity;
	}
	public void setSimilarity(double similarity) {
		this.similarity = similarity;
	}
	@Override
	public String toString() {
		return "BookSimilarityUcf [id=" + id + ", bookId1=" + bookId1 + ", bookId2=" + bookId2 + ", similarity="
				+ similarity + "]";
	}
	
	
	
}
  
