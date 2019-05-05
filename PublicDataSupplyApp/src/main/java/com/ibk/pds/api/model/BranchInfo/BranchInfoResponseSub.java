
package com.ibk.pds.api.model.BranchInfo;


public class BranchInfoResponseSub {
	
	String branchName = "";
	String branchPhoneNUmber= "";
	String branchAddress = "";
	String branchSection = "";
	String branchSectionCode = "";
	
	
	
	
	public BranchInfoResponseSub() {
		
	}
	public String toString() {
		return "[branchName="+branchName+",branchPhoneNUmber="+branchPhoneNUmber+",branchAddress="+branchAddress+
				",branchSection="+branchSection
				+",branchSectionCode="+branchSectionCode;
	}
	public BranchInfoResponseSub(
			String branchName, 
			String branchPhoneNUmber, 
			String branchAddress,
			String branchSection,
			String branchSectionCode 
			) {
		this.branchName = branchName;
		this.branchPhoneNUmber = branchPhoneNUmber;
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
	public String getBranchPhoneNUmber() {
		return branchPhoneNUmber;
	}
	public void setBranchPhoneNUmber(String branchPhoneNUmber) {
		this.branchPhoneNUmber = branchPhoneNUmber;
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
