package com.ibk.pds.data.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Document(collection = "PDS_BRANCHINFO_DATA")
public class BranchInfoData {
	//기준일자(stdYm) + 세부구분코드 detailIndustryCode
	@Id
	private String dataId;
	
	//문서 내용 
	private String branchName;			//지점명
	private String branchPhoneNumber;  	//지점전화번호 
	private String branchAddress;		//지점주소
	private String branchSection;		//행정구역(서울)
	private String branchSectionCode;	//행정구역코드
	
	
	private String approval;
	//삭제, 승인 등 관련해서 처리를 위한 구분 값 
	private String updateCode;
	private String uploadDate;

	//	//업종 
	public String toString() {
		return "dataId" + dataId
				+"branchName="+branchName
				+"branchPhoneNumber="+branchPhoneNumber
				+"branchAddress="+branchAddress
				+"branchSection="+branchSection
				+"branchSectionCode="+branchSectionCode
				;
	}
	public BranchInfoData(
			String dataId,
			String branchName, 
			String branchPhoneNumber, 
			String branchAddress, 
			String branchSection,
			String branchSectionCode,
			String approval, 
			String updateCode, 
			String uploadDate)
	{
		this.dataId = dataId;	
		this.branchName = branchName;
		this.branchPhoneNumber = branchPhoneNumber;
		this.branchAddress = branchAddress;
		this.branchSection = branchSection;
		this.branchSectionCode = branchSectionCode;
		this.approval = approval;
		this.updateCode = updateCode;
		this.uploadDate = uploadDate;
		
	}
	
}
