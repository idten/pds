
package com.ibk.pds.api.model.BranchInfo;

import io.swagger.annotations.ApiModelProperty;

public class BranchInfoResponseSub {
	
	@ApiModelProperty(notes = "영업점명")
	String branchName = "";
	@ApiModelProperty(notes = "전화번호")
	String branchPhoneNumber= "";
	@ApiModelProperty(notes = "주소")
	String branchAddress = "";
	
	@ApiModelProperty(notes = "행정구역")
	String branchSection = "";
	@ApiModelProperty(notes = "행정구역코드")
	String branchSectionCode = "";
	
	
	
	
	public BranchInfoResponseSub() {
		
	}
	public String toString() {
		return "[branchName="+branchName+",branchPhoneNUmber="+branchPhoneNumber+",branchAddress="+branchAddress+
				",branchSection="+branchSection
				+",branchSectionCode="+branchSectionCode;
	}
	public BranchInfoResponseSub(
			String branchName, 
			String branchPhoneNumber, 
			String branchAddress,
			String branchSection,
			String branchSectionCode 
			) {
		this.branchName = branchName;
		this.branchPhoneNumber = branchPhoneNumber;
		this.branchAddress = branchAddress;
		this.branchSection = branchSection;
		this.branchSectionCode = branchSectionCode;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	
	public String getBranchPhoneNumber() {
		return branchPhoneNumber;
	}
	public void setBranchPhoneNumber(String branchPhoneNumber) {
		this.branchPhoneNumber = branchPhoneNumber;
	}
	public String getBranchAddress() {
		return branchAddress;
	}
	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}
	public String getBranchSection() {
		return branchSection;
	}
	public void setBranchSection(String branchSection) {
		this.branchSection = branchSection;
	}
	public String getBranchSectionCode() {
		return branchSectionCode;
	}
	public void setBranchSectionCode(String branchSectionCode) {
		this.branchSectionCode = branchSectionCode;
	}
	
}
