package com.ibk.pds.code.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//부서 코드 
@Document(collection = "PDS_DEPINFO")
public class DepInfo {

	@Id
	private String depCode;
	private String depName;
	public DepInfo(String depCode, String depName) {
		this.depCode = depCode;
		this.depName = depName;
	}
	public String getDepCode() {
		return depCode;
	}
	public void setDepCode(String depCode) {
		this.depCode = depCode;
	}
	public String getDepName() {
		return depName;
	}
	public void setDepName(String depName) {
		this.depName = depName;
	}
	public String toString() {
		return "DepCode="+depCode + ",DepName="+depName;
	}
}
