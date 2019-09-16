package com.example.expr_1;

import java.io.Serializable;

public class userInfo implements Serializable {


	//存储用户信息
	public userInfo(String userName, String passwd, String phone, String email) {
		this.userName = userName;
		this.passwd = passwd;
		this.phone = phone;
		this.email = email;
	}


	private String userName;
	private String passwd;
	private String phone;
	private String email;


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
}
