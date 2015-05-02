/**
 * @Project:assistant
 * @Title: Merchant.java
 * @date: 2015-1-17 下午9:03:56
 * @version 1.0
 */
package assistant.app.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import assistant.app.base.models.basic.BasicModel;

/**
 * @ClassName Merchant
 * @Description 用户信息类
 * @author minlingchao
 * @date 2015-1-17 下午9:03:56
 */
@Entity(name=Merchant.TABLE_NAME)
public class Merchant extends BasicModel {
	
	public static final String TABLE_NAME="merchant";
	
	/**
	 *用户地址
	 */
	@Column(name="address")
	private String address;
	
	/**
	 * 电子邮箱
	 */
	@Column(name="email")
	private String email;
	
	/**
	 * 手机号
	 */
	@Column(name="mobile")
	private String mobile;
	
	/**
	 * 密码：MD5加密
	 */
	@Column(name="passwd")
	private String passwd;
	
	/**
	 * 用户名
	 */
	@Column(name="user_name")
	private String userName;
	
	/**
	 * QQ号
	 */
	@Column(name="qq")
	private String qq;
	
    public Merchant() {
		// TODO Auto-generated constructor stub
	}
    
    public Merchant(String address,String email,String mobile,String passwd,String userName,String qq){
    	this.address=address;
    	this.email=email;
    	this.mobile=mobile;
    	this.passwd=passwd;
    	this.userName=userName;
    	this.qq=qq;
    }
    
    public Merchant(String userName,String email,String mobile,String qq){
    	this.userName=userName;
    	this.email=email;
    	this.mobile=mobile;
    	this.qq=qq;
    }

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	
	

}
