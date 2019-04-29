package com.ibk.pds.data.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "PDS_MONTHLYEXCHANGERATE_DATA")
public class MonthlyExchangeRateData {

	
	//stdCurrency + relativeCurrency;
	@Id
	private String dataId;
	
	//기준 통화
	private String stdCurrency;
	//상태 통화
	private String relativeCurrency;
	
	private String monthly1Rate;
	private String monthly2Rate;
	private String monthly3Rate;
	private String monthly4Rate;
	private String monthly5Rate;
	private String monthly6Rate;
	private String monthly7Rate;
	private String monthly8Rate;
	private String monthly9Rate;
	private String monthly10Rate;
	private String monthly11Rate;
	private String monthly12Rate;
	
	//공통 코드 
	private String approval;
	//삭제, 승인 등 관련해서 처리를 위한 구분 값 
	private String updateCode;
	private String uploadDate;
//	//업종 
	public String toString() {
		return "dataId="+dataId+",stdCurrency="+stdCurrency+",relativeCurrency="+relativeCurrency
				+",monthly1Rate=" + monthly1Rate
				+",monthly2Rate=" + monthly2Rate
				+",monthly3Rate=" + monthly3Rate
				+",monthly4Rate=" + monthly4Rate
				+",monthly5Rate=" + monthly5Rate
				+",monthly6Rate=" + monthly6Rate
				+",monthly7Rate=" + monthly7Rate
				+",monthly8Rate=" + monthly8Rate
				+",monthly9Rate=" + monthly9Rate
				+",monthly10Rate=" + monthly10Rate
				+",monthly11Rate=" + monthly11Rate
				+",monthly12Rate=" + monthly12Rate
				+",approval=" + approval
				+",updateCode=" + updateCode
				+",uploadDate=" + uploadDate;
					
	}
	public MonthlyExchangeRateData(String dataId, String stdCurrency, String relativeCurrency,
			String monthly1Rate, String monthly2Rate, String monthly3Rate, String month4y1Rate, 
			String monthly5Rate, String monthly6Rate, String monthly7Rate, String month8y1Rate, 
			String monthly9Rate, String monthly10Rate, String monthly11Rate, String monthl2y1Rate, 
			String approval, 
			String updateCode, 
			String uploadDate)
	{
		this.dataId = dataId;
		this.stdCurrency = stdCurrency;
		this.relativeCurrency = relativeCurrency;
		this.monthly1Rate = monthly1Rate;
		this.monthly2Rate = monthly2Rate;
		this.monthly3Rate = monthly3Rate;
		this.monthly4Rate = monthly4Rate;
		this.monthly5Rate = monthly5Rate;
		this.monthly6Rate = monthly6Rate;
		this.monthly7Rate = monthly7Rate;
		this.monthly8Rate = monthly8Rate;
		this.monthly9Rate = monthly9Rate;
		this.monthly10Rate = monthly10Rate;
		this.monthly11Rate = monthly11Rate;
		this.monthly12Rate = monthly12Rate;
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
	public String getStdCurrency() {
		return stdCurrency;
	}
	public void setStdCurrency(String stdCurrency) {
		this.stdCurrency = stdCurrency;
	}
	public String getRelativeCurrency() {
		return relativeCurrency;
	}
	public void setRelativeCurrency(String relativeCurrency) {
		this.relativeCurrency = relativeCurrency;
	}
	public String getMonthly1Rate() {
		return monthly1Rate;
	}
	public void setMonthly1Rate(String monthly1Rate) {
		this.monthly1Rate = monthly1Rate;
	}
	public String getMonthly2Rate() {
		return monthly2Rate;
	}
	public void setMonthly2Rate(String monthly2Rate) {
		this.monthly2Rate = monthly2Rate;
	}
	public String getMonthly3Rate() {
		return monthly3Rate;
	}
	public void setMonthly3Rate(String monthly3Rate) {
		this.monthly3Rate = monthly3Rate;
	}
	public String getMonthly4Rate() {
		return monthly4Rate;
	}
	public void setMonthly4Rate(String monthly4Rate) {
		this.monthly4Rate = monthly4Rate;
	}
	public String getMonthly5Rate() {
		return monthly5Rate;
	}
	public void setMonthly5Rate(String monthly5Rate) {
		this.monthly5Rate = monthly5Rate;
	}
	public String getMonthly6Rate() {
		return monthly6Rate;
	}
	public void setMonthly6Rate(String monthly6Rate) {
		this.monthly6Rate = monthly6Rate;
	}
	public String getMonthly7Rate() {
		return monthly7Rate;
	}
	public void setMonthly7Rate(String monthly7Rate) {
		this.monthly7Rate = monthly7Rate;
	}
	public String getMonthly8Rate() {
		return monthly8Rate;
	}
	public void setMonthly8Rate(String monthly8Rate) {
		this.monthly8Rate = monthly8Rate;
	}
	public String getMonthly9Rate() {
		return monthly9Rate;
	}
	public void setMonthly9Rate(String monthly9Rate) {
		this.monthly9Rate = monthly9Rate;
	}
	public String getMonthly10Rate() {
		return monthly10Rate;
	}
	public void setMonthly10Rate(String monthly10Rate) {
		this.monthly10Rate = monthly10Rate;
	}
	public String getMonthly11Rate() {
		return monthly11Rate;
	}
	public void setMonthly11Rate(String monthly11Rate) {
		this.monthly11Rate = monthly11Rate;
	}
	public String getMonthly12Rate() {
		return monthly12Rate;
	}
	public void setMonthly12Rate(String monthly12Rate) {
		this.monthly12Rate = monthly12Rate;
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
