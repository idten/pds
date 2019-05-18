
package com.ibk.pds.api.model.ATMInfo;

import io.swagger.annotations.ApiModelProperty;

public class ATMInfoResponseSub {
	@ApiModelProperty(notes = "ATM코너명")
	String atmName = "";
	//String atmDivisiton= "";
	@ApiModelProperty(notes = "시작시간")
	String startTime = "";
	@ApiModelProperty(notes = "마감시간")
	String endTime = "";
	@ApiModelProperty(notes = "주소")
	String atmAddress = "";
	@ApiModelProperty(notes = "행정구역")
	String atmSection = "";
	@ApiModelProperty(notes = "행정구역코드")
	String atmSectionCode = "";
	
	public ATMInfoResponseSub() {
		
	}
	public String toString() {
		return "[atmName="+atmName+",startTime="+startTime+
				",endTime="+endTime
				+",atmAddress="+atmAddress+",atmSection="+atmSection+"]";
	}
	
	public ATMInfoResponseSub(
			String atmName, 
			String startTime,
			String endTime,
			String atmAddress ,
			String atmSection,
			String atmSectionCode
			) {
		this.atmName = atmName;
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
