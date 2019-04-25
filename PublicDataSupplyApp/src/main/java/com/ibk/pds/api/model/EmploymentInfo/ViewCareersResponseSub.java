
package com.ibk.pds.api.model.EmploymentInfo;


public class ViewCareersResponseSub {
	String stdYm = "";
	String industryName = "";
	String industryCode = "";
	String detailIndustryName = "";
	int careersCount=0;
	String careersPer="";
	
	public ViewCareersResponseSub() {
		
	}
	public String toString() {
		return "[stdYm="+stdYm+",industryName="+industryName+",industryCode="+industryCode+",detailIndustryName="+detailIndustryName
				+",careersCount="+careersCount+",careersPer="+careersPer+"]";
	}
	
	public ViewCareersResponseSub(String stdYm, 
			String industryName, 
			String industryCode,
			String detailIndustryName,
			int careersCount ,
			String careersPer) {
		this.stdYm = stdYm;
		this.industryName = industryName;
		this.industryCode = industryCode;
		this.detailIndustryName = detailIndustryName;
		this.careersCount = careersCount;
		this.careersPer = careersPer;
		
	}
	
	public String getStdYm() {
		return stdYm;
	}
	public void setStdYm(String stdYm) {
		this.stdYm = stdYm;
	}
	public String getIndustryName() {
		return industryName;
	}
	public void setIndustryName(String industryName) {
		this.industryName = industryName;
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
}
