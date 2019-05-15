package com.ibk.pds.log.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Document(collection = "PDS_APILOG_DATA")
public class LogApiData {
	
	
	@Id
	private String logId;//생성	
	private String docId;
	//LogId
	private String apiId;//API ID
	private String apiName;//API 명칭 
	private String apiUrl;
	//업로드, 삭제, 
	private String action;
	private String statusCode;
	//요청 
	private String request;
	//응답
	private String response;
	//처리 시간 
	private String trxDate;
	//업종 
	public String toString() {
		return "logId" + logId
				+"apiId="+apiId
				+"apiName="+apiName
				+"action="+action
				+"trxDate="+trxDate
				;
	}
	public LogApiData(
			String logId,
			String docId,
			String apiId, 
			String apiName,
			String apiUrl,
			String action,
			String statusCode,
			String request, 
			String response,
			String trxDate) 
	{
		this.logId = logId;
		this.docId = docId;
		this.apiId = apiId;
		this.apiName = apiName;
		this.apiUrl = apiUrl;
		this.action = action;
		this.statusCode = statusCode;
		this.request = request;
		this.response = response;
		this.trxDate = trxDate;
		
	}
	public String getDocId() {
		return docId;
	}
	public void setDocId(String docId) {
		this.docId = docId;
	}
	public String getApiUrl() {
		return apiUrl;
	}
	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}
	public String getLogId() {
		return logId;
	}
	public void setLogId(String logId) {
		this.logId = logId;
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
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getRequest() {
		return request;
	}
	public void setRequest(String request) {
		this.request = request;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public String getTrxDate() {
		return trxDate;
	}
	public void setTrxDate(String trxDate) {
		this.trxDate = trxDate;
	}
	
}
