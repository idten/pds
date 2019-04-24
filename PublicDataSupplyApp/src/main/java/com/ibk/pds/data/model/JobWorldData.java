package com.ibk.pds.data.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Document(collection = "PDS_JOBWORLD_DATA")
public class JobWorldData {
	//기준일자(stdYm) + 세부구분코드 detailIndustryCode
	@Id
	private String dataId;
	private String stdYM;
	private String industryName;
	private String industryCode;
	private String detailIndustryName;
	private String detailIndustryCode;
	
	private int careersCount;
	private String careersPer;
	// 문서 내용이 아닌 관려용 daa 
	private String approval;
	//삭제, 승인 등 관련해서 처리를 위한 구분 값 
	private String updateCode;
	private String uploadDate;
//	//업종 
	public String toString() {
		return "dataId" + dataId
				+"stdYM="+stdYM
				+"industryName="+industryName
				+"detailIndustryName="+detailIndustryName
				+"careersPer="+careersPer
				+"approval="+approval
				+"updateCode="+updateCode
				+"uploadDate="+uploadDate
				
				
				;
	}
	public JobWorldData(
			String dataId,
			String stdYM, 
			String industryName, 
			String industryCode, 
			String detailIndustryName,
			String detailIndustryCode,
			int careersCount, 
			String careersPer, 
			String approval, 
			String updateCode, 
			String uploadDate)
	{
		this.dataId = dataId;	
		this.stdYM = stdYM;
		this.industryName = industryName;
		this.industryCode = industryCode;
		this.detailIndustryName = detailIndustryName;
		this.detailIndustryCode = detailIndustryCode;
		this.careersCount = careersCount;
		this.careersPer = careersPer;
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
	public String getDetailIndustryCode() {
		return detailIndustryCode;
	}
	public void setDetailIndustryCode(String detailIndustryCode) {
		this.detailIndustryCode = detailIndustryCode;
	}
	
	public String getStdYM() {
		return stdYM;
	}
	public void setStdYM(String stdYM) {
		this.stdYM = stdYM;
	}
	public String getIndustryName() {
		return industryName;
	}
	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}
	public String getIndustryCode() {
		return industryCode;
	}
	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode;
	}
	public String getDetailIndustryName() {
		return detailIndustryName;
	}
	public void setDetailIndustryName(String detailIndustryName) {
		this.detailIndustryName = detailIndustryName;
	}
	public int getCareersCount() {
		return careersCount;
	}
	public void setCareersCount(int careersCount) {
		this.careersCount = careersCount;
	}
	public String getCareersPer() {
		return careersPer;
	}
	public void setCareersPer(String careersPer) {
		this.careersPer = careersPer;
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
	
	
	
	
	
	
	
//	private String categoryOfBusiness;
//	//공고수 
//	private String announcementNumber;
//	
//	private String ratio;

	//기준 년월일 
	//@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)

}
