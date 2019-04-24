package com.ibk.pds.common.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Document(collection = "PDS_API_INFO")
public class ApiInfo {
	
	@Id
	private String apiId;		//자동생성 
	//API명
	private String apiName;
	//API상세기능명
	private String apiDetailName;
	//API상세 설명
	private String apiExplanation;
	//여기까지 최초 생성시 등록자 입력 사항 
	//=========================================
	//사용자
	private String userId; //직원번호 
	//문서번호 
	private String docId;
	//개발 완료 후 에 개발자 입력 사항
	private String docName;
	//내부 URL
	private String apiINTUrl;
	//외부 URL
	private String apiEXTUrl;
	//db table 명 
	private String collectionName;
	//승인 여부 
	private String approvalYN;
	private String regDate ;//= new Date();	//등록일자 
	private String updateDate;// = new Date();	//등록일자 
	private String requestLayout;
	private String responseLayout;
	
	public ApiInfo(String apiId, String apiName, String apiDetailName, String apiExplanation,
			String userId,String docId, String docName, String apiINTUrl, String apiEXTUrl, String collectionName,
			String approvalYN, String regDate, String updateDate, String  requestLayout, String responseLayout) {

		
		
		this.apiId = apiId;
		this.apiName = apiName;
		this.apiDetailName = apiDetailName;
		this.apiExplanation = apiExplanation;
		this.userId = userId;
		this.docId = docId;
		this.docName = docName;
		this.apiINTUrl = apiINTUrl;
		this.apiEXTUrl = apiEXTUrl;
		this.collectionName = collectionName;
		this.approvalYN = approvalYN;
		this.regDate = regDate;
		this.updateDate = updateDate;
		this.requestLayout = requestLayout;
		this.responseLayout = responseLayout;
		
		//this.apiUrl = apiUrl;
		//this.conditions = conditions;
		
	}
	
	public String toString() {
		
		return "apiId="+apiId +"apiName="+apiName+",userId="+userId
				+",docId="+docId+",Condition="+collectionName
				;
	}

	public String getApiId() {
		return apiId;
	}

	public void setApiId(String apiId) {
		this.apiId = apiId;
	}

	public String getApiName() {
		return apiName;
	}

	public void setApiName(String apiName) {
		this.apiName = apiName;
	}

	public String getApiDetailName() {
		return apiDetailName;
	}

	public void setApiDetailName(String apiDetailName) {
		this.apiDetailName = apiDetailName;
	}

	public String getApiExplanation() {
		return apiExplanation;
	}

	public void setApiExplanation(String apiExplanation) {
		this.apiExplanation = apiExplanation;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	public String getApiINTUrl() {
		return apiINTUrl;
	}

	public void setApiINTUrl(String apiINTUrl) {
		this.apiINTUrl = apiINTUrl;
	}

	public String getApiEXTUrl() {
		return apiEXTUrl;
	}

	public void setApiEXTUrl(String apiEXTUrl) {
		this.apiEXTUrl = apiEXTUrl;
	}

	public String getCollectionName() {
		return collectionName;
	}

	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}

	public String getApprovalYN() {
		return approvalYN;
	}

	public void setApprovalYN(String approvalYN) {
		this.approvalYN = approvalYN;
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

	public String getRequestLayout() {
		return requestLayout;
	}

	public void setRequestLayout(String requestLayout) {
		this.requestLayout = requestLayout;
	}

	public String getResponseLayout() {
		return responseLayout;
	}

	public void setResponseLayout(String responseLayout) {
		this.responseLayout = responseLayout;
	}

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}



}
