
package com.ibk.pds.api.model.FundRateInfo;


public class ATMInfoResponseSub {
	String atmName = "";
	String atmDivisiton= "";
	String startTime = "";
	String endTime = "";
	String atmAddress = "";
	String atmSection = "";
	String atmSectionCode = "";
	
	
	
	
	public ATMInfoResponseSub() {
		
	}
	public String toString() {
		return "[atmName="+atmName+",atmDivisiton="+atmDivisiton+",startTime="+startTime+
				",endTime="+endTime
				+",atmAddress="+atmAddress+",atmSection="+atmSection+"]";
	}
	
	public ATMInfoResponseSub(
			String atmName, 
			String atmDivisiton, 
			String startTime,
			String endTime,
			String atmAddress ,
			String atmSection,
			String atmSectionCode
			) {
		this.atmName = atmName;
		this.atmDivisiton = atmDivisiton;
		this.startTime = startTime;
		this.endTime = endTime;
		this.atmAddress = atmAddress;
		this.atmSection = atmSection;
		this.atmSectionCode = atmSectionCode;		
	}
	public String getAtmName() {
		return atmName;
	}
	public void setAtmName(String atmName) {
		this.atmName = atmName;
	}
	public String getAtmDivisiton() {
		return atmDivisiton;
	}
	public void setAtmDivisiton(String atmDivisiton) {
		this.atmDivisiton = atmDivisiton;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getAtmAddress() {
		return atmAddress;
	}
	public void setAtmAddress(String atmAddress) {
		this.atmAddress = atmAddress;
	}
	public String getAtmSection() {
		return atmSection;
	}
	public void setAtmSection(String atmSection) {
		this.atmSection = atmSection;
	}
	public String getAtmSectionCode() {
		return atmSectionCode;
	}
	public void setAtmSectionCode(String atmSectionCode) {
		this.atmSectionCode = atmSectionCode;
	}
	
}
