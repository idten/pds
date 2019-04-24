package com.ibk.pds.log.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Document(collection = "PDS_APILOG_DATA")
public class LogApiData {
	
	
	@Id
	private String logId;//생성	
	//LogId
	private String apiId;//API ID
	private String apiName;//API 명칭 
	
	//업로드, 삭제, 
	private String action;
	
	private String statusCode;
	//업로드, 삭제등 수행할 때 키 
//	private String docUpId;
//	private String owner;
	
	//요청 
	private String request;
		//응답
	private String response;
	//처리 시간 
	private String trxDate;
	
	
//	//업종 
	public String toString() {
		return "logId" + logId
				+"apiId="+apiId
				+"apiName="+apiName
				+"action="+action
//				+"docUpId="+docUpId
//				+"owner="+owner
				+"trxDate="+trxDate
				//+"uploadDate="+uploadDate
				
				
				;
	}
	public LogApiData(
			String logId,
			String apiId, 
			String apiName,
			String action,
			String statusCode,
			String request, 
			String response,
			String trxDate) 
	{
		this.logId = logId;
		this.apiId = apiId;
		this.apiName = apiName;
		this.action = action;
		this.statusCode = statusCode;
		this.request = request;
		this.response = response;
		this.trxDate = trxDate;
		
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
