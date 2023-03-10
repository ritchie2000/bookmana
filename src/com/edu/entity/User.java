package com.edu.entity;

/**
 * 映射User表实体类
 * 
 * @author ZEYU
 *
 */
public class User {
	// 用户ID
	private Integer userId;
	// 邮箱
	private String email;
	// 密码
	private String password;

	public User() {
	}

	public User(Integer userId, String email, String password) {
		this.userId = userId;
		this.email = email;
		this.password = password;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", email=" + email + ", password=" + password + "]";
	}

}
