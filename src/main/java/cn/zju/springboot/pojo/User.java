/**  
 * Project Name:BookRecSystem  
 * File Name:user.java  
 * Package Name:cn.zju.springboot.pojo  
 * Date:2017年12月9日下午6:43:07  
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved.  
 *  
*/  
  
package cn.zju.springboot.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**  
 * ClassName:user <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     2017年12月9日 下午6:43:07 <br/>  
 * @author   john-lin  
 * @version    
 * @since    JDK 1.6  
 * @see        
 */
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String account;
	private String password;
	private String mail;
	private int sex;
	private int age;
	private String sign;
	private String introduction;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	@Override
	public String toString() {
		return "user [id=" + id + ", name=" + name + ", account=" + account + ", password=" + password + ", mail="
				+ mail + ", sex=" + sex + ", age=" + age + ", sign=" + sign + ", introduction=" + introduction + "]";
	}
	
	
}
  
