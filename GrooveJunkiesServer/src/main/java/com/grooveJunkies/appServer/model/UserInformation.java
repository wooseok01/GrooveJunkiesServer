package com.grooveJunkies.appServer.model;

import java.io.Serializable;

public class UserInformation implements Serializable{
	private int uId;
	private String uEmail;
	private String uPwd;
	private String uName;
	private String uImageUrl;
	private char uPermission;
	
	public UserInformation(){}
	public UserInformation(String uEmail, String uPwd, String uName, String uImageUrl) {
		super();
		this.uEmail = uEmail;
		this.uPwd = uPwd;
		this.uName = uName;
		this.uImageUrl = uImageUrl;
		this.uPermission = 'N';
	}



	public char getuPermission() {
		return uPermission;
	}
	public void setuPermission(char uPermission) {
		this.uPermission = uPermission;
	}
	public int getuId() {
		return uId;
	}

	public void setuId(int uId) {
		this.uId = uId;
	}

	public String getuEmail() {
		return uEmail;
	}

	public void setuEmail(String uEmail) {
		this.uEmail = uEmail;
	}

	public String getuPwd() {
		return uPwd;
	}

	public void setuPwd(String uPwd) {
		this.uPwd = uPwd;
	}

	public String getuName() {
		return uName;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}

	public String getuImageUrl() {
		return uImageUrl;
	}

	public void setuImageUrl(String uImageUrl) {
		this.uImageUrl = uImageUrl;
	}
	@Override
	public String toString() {
		return "UserInformation [uId=" + uId + ", uEmail=" + uEmail + ", uPwd=" + uPwd + ", uName=" + uName
				+ ", uImageUrl=" + uImageUrl + ", uPermission=" + uPermission + "]";
	}



	

	
	
	
	
	
	
	
	
	
	

}
