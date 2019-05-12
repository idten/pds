package com.ibk.pds.data.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Document(collection = "PDS_ATMINFO_DATA")
public class ATMInfoData {
	//기준일자(stdYm) + 세부구분코드 detailIndustryCode
	@Id
	private String dataId;
	
	//문서 내용 
	private String atmName;			//ATM명
	private String atmCode;
	private String atmDivision;  	//ATM구분  
	private String startTime;		//시작시간
	private String endTime;			//종료시간
	private String atmAddress;		//주소
	private String atmSection;		//지역구분
	private String atmSectionCode;	//지역구분 코드 
	
	
	private String approval;
	//삭제, 승인 등 관련해서 처리를 위한 구분 값 
	private String updateCode;
	private String uploadDate;

	//	//업종 
	public String toString() {
		return "dataId" + dataId
				+"atmName="+atmName
				+"atmCode="+atmCode
				+"atmDivision="+atmDivision
				+"startTime="+startTime
				+"endTime="+endTime
				+"atmAddress="+atmAddress
				+"atmSection="+atmSection
				+"atmSectionCode="+atmSectionCode
				
				;
	}
	public String getAtmCode() {
		return atmCode;
	}
	public void setAtmCode(String atmCode) {
		this.atmCode = atmCode;
	}
	public ATMInfoData(
			String dataId,
			String atmName, 
			String atmCode,
			String atmDivision, 
			String startTime, 
			String endTime,
			String atmAddress,
			String atmSection,
			String atmSectionCode,
			String approval, 
			String updateCode, 
			String uploadDate)
	{
		this.dataId = dataId;	
		this.atmName = atmName;
		this.atmCode = atmCode;
		this.atmDivision = atmDivision;
		this.startTime = startTime;
		this.endTime = endTime;
		this.atmAddress = atmAddress;
		this.atmSection = atmSection;
		this.atmSectionCode = atmSectionCode;
		this.approval = approval;
		this.updateCode = updateCode;
		this.uploadDate = uploadDate;
		
	}
	public String getDataId() {
		return dataId;
	}
	public void setDataId(String dataId) {
		this.dataId = dataId;
	}
	public String getAtmName() {
		return atmName;
	}
	public void setAtmName(String atmName) {
		this.atmName = atmName;
	}
	public String getAtmDivision() {
		return atmDivision;
	}
	public void setAtmDivision(String atmDivision) {
		this.atmDivision = atmDivision;
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
	public String getApproval() {
		return approval;
	}
	public void setApproval(String approval) {
		this.approval = approval;
	}
	public String getUpdateCode() {
		return updateCode;
	}
	public void setUpdateCode(String updateCode) {
		this.updateCode = updateCode;
	}
	public String getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(String uploadDate) {
		this.uploadDate = uploadDate;
	}
	
}
