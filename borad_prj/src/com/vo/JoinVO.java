package com.vo;

public class JoinVO {
	private String userID;
	private String userPassword;
	private String userName;
	private String userGender;
	private String userEmail;
	
	
	public JoinVO() {
		super();
	}
	
	public JoinVO(String userID, String userPassword, String userName, String userGender, String userEmail) {
		super();
		this.userID = userID;
		this.userPassword = userPassword;
		this.userName = userName;
		this.userGender = userGender;
		this.userEmail = userEmail;
	}
	
	public String getUserID() {
		return userID;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public String getUserName() {
		return userName;
	}
	public String getUserGender() {
		return userGender;
	}
	public String getUserEmail() {
		return userEmail;
	}
	
}
