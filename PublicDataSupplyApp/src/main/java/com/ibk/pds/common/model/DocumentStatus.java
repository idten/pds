package com.ibk.pds.common.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Document(collection = "PDS_DOC_STATUS")
public class DocumentStatus {

	//파일업로드시 상태 변경 
	
	@Id
	private String docId;
	//문서 업로드할때 신규로 생성되는 id  
	//해당 id기준으로 삭제/등 문서 control 진행예정 
	private String docUpId;
	public String getDocUpId() {
		return docUpId;
	}
	public void setDocUpId(String docUpId) {
		this.docUpId = docUpId;
	}
	private String docName;
	private String docOwners;
	//승인 
	private String approval;
	
	//백그라운드에서 시간별로 체크하여 상태를 체크해놓음 
	//정상여부(업로드 등 최근 처리)
	private String status;
	private String fileName;
	private long fileSize;
//	private String fileLocation;
	
//	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private String updateDate ;
	//= new Date();	//등록일자 
	public String toString() {
		return "docId="+docId+"fileName="+fileName;
	}
	public DocumentStatus(String docId, 
			String docUpId,
			String docName,
			String docOwners,
			String approval, String status, 
			String fileName, 
			long fileSize,
			String updateDate
		//	String fileLocation
			) {
		this.docUpId = docUpId;
		this.docId = docId;
		
		this.docName = docName;
		this.docOwners = docOwners;
		this.approval = approval;
		this.status = status ;
		this.fileName = fileName;
		this.fileSize = fileSize;
		this.updateDate = updateDate;
		//this.fileLocation = fileLocation;
	}
	
	
//	public String getDocUpId() {
//		return docUpId;
//	}
//
//
//	public void setDocUpId(String docUpId) {
//		this.docUpId = docUpId;
//	}


	public String getUpdateDate() {
		return updateDate;
	}


	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}


	public String getDocOwners() {
		return docOwners;
	}


	public void setDocOwners(String docOwners) {
		this.docOwners = docOwners;
	}


	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
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
	public String getApproval() {
		return approval;
	}
	public void setApproval(String approval) {
		this.approval = approval;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	
	
	
	

}
