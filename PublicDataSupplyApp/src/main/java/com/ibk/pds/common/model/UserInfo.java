package com.ibk.pds.common.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Document(collection = "PDS_USER_INFO")
public class UserInfo {
	
	@Id
	private String userId; //직원번호 
	private String userName;//이름 
	
	private String depName; //부서명
	private String depCode;
	private String authCode;//사용자/관리자     USER//ADMIN
	//@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
//	private String regDate; //등록일자 
//	private Date updateDate;	//등록일자 
	private String regDate;	//등록일자 
	private String updateDate;

	public UserInfo() {
		
	}
	public UserInfo(String userId, String userName, String depCode,String depName, String authCode,String regDate, String updateDate) {
		this.userId = userId; 
		this.userName = userName;
		this.depCode = depCode;
		
		this.depName = depName;
		this.authCode = authCode;
		this.regDate = regDate;
		this.updateDate = updateDate;
	}

	
	public String getUserId() {
		return userId;
	}
//	public String getId() {
//		return id;
//	}
//	public void setId(String id) {
//		this.id = id;
//	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getDepName() {
		return depName;
	}
	public void setDepName(String depName) {
		this.depName = depName;
	}
	public String getAuthCode() {
		return authCode;
	}
	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
	
	public String getDepCode() {
		return depCode;
	}
	public void setDepCode(String depCode) {
		this.depCode = depCode;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String toString() {
		
		return "USER_ID="+userId 
				+ ",USER_NAME="+ userName  
				+ ",DEP_NAME=" + depName
				+ "AUTH_CODE=" + authCode;

	}
}
