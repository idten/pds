package com.ibk.pds.log.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Document(collection = "PDS_DOCLOG_DATA")
public class LogDocData {
	
	
	@Id
	private String logId;//생성	
	
	private String docId;//문서 ID
	private String docName;//문서 명칭 
	
	//업로드, 삭제, 
	private String action;
	
	//업로드, 삭제등 수행할 때 키 
	private String docUpId;
	private String owner;
	
	private String status ;
	//에러 발생시 등
	private String contents;
	
	//처리 시간 
	//@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private Date trxDate;
	
	
//	//업종 
	public String toString() {
		return "logId" + logId
				+"docId="+docId
				+"docName="+docName
				+"action="+action
				+"docUpId="+docUpId
				+"owner="+owner
				+"status="+status
				+"contents="+contents
				+"trxDate="+trxDate
				//+"uploadDate="+uploadDate
				;
	}
	public LogDocData(
			String logId,
			String docId, 
			String docName, 
			String action, 
			String docUpId,
			String owner,
			String status,
			String contents		
			) 
	{
		this.logId = logId;
		this.docId = docId;
		this.docName = docName;
		this.action = action;
		this.docUpId = docUpId;
		this.owner = owner;
		this.status = status;
		this.contents = contents;
		this.trxDate = new Date();
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getLogId() {
		return logId;
	}
	public void setLogId(String logId) {
		this.logId = logId;
	}
	public String getDocId() {
		return docId;
	}
	public void setDocId(String docId) {
		this.docId = docId;
	}
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getDocUpId() {
		return docUpId;
	}
	public void setDocUpId(String docUpId) {
		this.docUpId = docUpId;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public Date getTrxDate() {
		return trxDate;
	}
	public void setTrxDate(Date trxDate) {
		this.trxDate = trxDate;
	}
	
}
