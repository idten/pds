package com.ibk.pds.log.model;


//로그 처리하는 상태
public class DocTrxStatus {
	String status = "";
	String contents = "";
	public DocTrxStatus(String status, String contents) {
		this.status = status;
		this.contents = contents;
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
}
