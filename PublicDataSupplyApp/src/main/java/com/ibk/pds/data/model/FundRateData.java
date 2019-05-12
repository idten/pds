package com.ibk.pds.data.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Document(collection = "PDS_FUNDRATEINFO_DATA")
public class FundRateData {
	//기준일자(stdYm) + 세부구분코드 detailIndustryCode
	@Id
	private String dataId;
	
	//문서 내용 
	private String baseYmd;		//기준 년월일
	private String opcmNm;  	//운용사명 
	private String fundNm;		//펀드명
	private String fundCode;
	private String ascnFundCd;	//협회펀드코드 
	private String fundAstTcd;	//펀드자산유형코드
	private String fundInvmAecd;//펀드투자지역코드 
	private String trmMn1ErnnRt;//1개월 수익률
	private String trmMn3ErnnRt;//3개월 수익률
	private String trmMn6ErnnRt;//6개월 수익률
	private String trmMn12ErnnRt;//12개월 수익률
	private String pdrsGdcd;	//상품리스크 등급코드 
	private String idivFnptDcd;	//펀드유형구분코드
	private String prcqFeeRt;	//선취수수료율
	private String ttalRmnrRt;	//총보수료;
	
	
	private String approval;
	//삭제, 승인 등 관련해서 처리를 위한 구분 값 
	private String updateCode;
	private String uploadDate;

	//	//업종 
	public String toString() {
		return "dataId" + dataId
				+"baseYmd="+baseYmd
				+"fundNm="+fundNm
				+"ascnFundCd="+ascnFundCd
				+"fundAstTcd="+fundAstTcd
				+"fundInvmAecd="+fundInvmAecd
				+"trmMn1ErnnRt="+trmMn1ErnnRt
				+"uploadDate="+uploadDate
				
				
				;
	}
	public FundRateData(
			String dataId,
			String baseYmd, 
			String opcmNm, 
			String fundNm, 
			String fundCode,
			String ascnFundCd,
			String fundAstTcd,
			String fundInvmAecd,
			String trmMn1ErnnRt,
			String trmMn3ErnnRt,
			String trmMn6ErnnRt,
			String trmMn12ErnnRt,
			String pdrsGdcd,
			String idivFnptDcd,
			String prcqFeeRt,
			String ttalRmnrRt,
			String approval, 
			String updateCode, 
			String uploadDate)
	{
		this.dataId = dataId;	
		this.baseYmd = baseYmd;
		this.opcmNm = opcmNm;
		this.fundNm = fundNm;
		this.fundCode = fundCode;
		this.ascnFundCd = ascnFundCd;
		this.fundAstTcd = fundAstTcd;
		this.fundInvmAecd = fundInvmAecd;
		this.trmMn1ErnnRt = trmMn1ErnnRt;
		this.trmMn3ErnnRt = trmMn3ErnnRt;
		this.trmMn6ErnnRt = trmMn6ErnnRt;
		this.trmMn12ErnnRt = trmMn12ErnnRt;
		this.pdrsGdcd = pdrsGdcd;
		this.idivFnptDcd = idivFnptDcd;
		this.prcqFeeRt = prcqFeeRt;
		this.ttalRmnrRt = ttalRmnrRt;
		this.approval = approval;
		this.updateCode = updateCode;
		this.uploadDate = uploadDate;
		
	}
	public String getFundCode() {
		return fundCode;
	}
	public void setFundCode(String fundCode) {
		this.fundCode = fundCode;
	}
	public String getDataId() {
		return dataId;
	}
	public void setDataId(String dataId) {
		this.dataId = dataId;
	}
	public String getBaseYmd() {
		return baseYmd;
	}
	public void setBaseYmd(String baseYmd) {
		this.baseYmd = baseYmd;
	}
	public String getOpcmNm() {
		return opcmNm;
	}
	public void setOpcmNm(String opcmNm) {
		this.opcmNm = opcmNm;
	}
	public String getFundNm() {
		return fundNm;
	}
	public void setFundNm(String fundNm) {
		this.fundNm = fundNm;
	}
	public String getAscnFundCd() {
		return ascnFundCd;
	}
	public void setAscnFundCd(String ascnFundCd) {
		this.ascnFundCd = ascnFundCd;
	}
	public String getFundAstTcd() {
		return fundAstTcd;
	}
	public void setFundAstTcd(String fundAstTcd) {
		this.fundAstTcd = fundAstTcd;
	}
	public String getFundInvmAecd() {
		return fundInvmAecd;
	}
	public void setFundInvmAecd(String fundInvmAecd) {
		this.fundInvmAecd = fundInvmAecd;
	}
	public String getTrmMn1ErnnRt() {
		return trmMn1ErnnRt;
	}
	public void setTrmMn1ErnnRt(String trmMn1ErnnRt) {
		this.trmMn1ErnnRt = trmMn1ErnnRt;
	}
	public String getTrmMn3ErnnRt() {
		return trmMn3ErnnRt;
	}
	public void setTrmMn3ErnnRt(String trmMn3ErnnRt) {
		this.trmMn3ErnnRt = trmMn3ErnnRt;
	}
	public String getTrmMn6ErnnRt() {
		return trmMn6ErnnRt;
	}
	public void setTrmMn6ErnnRt(String trmMn6ErnnRt) {
		this.trmMn6ErnnRt = trmMn6ErnnRt;
	}
	public String getTrmMn12ErnnRt() {
		return trmMn12ErnnRt;
	}
	public void setTrmMn12ErnnRt(String trmMn12ErnnRt) {
		this.trmMn12ErnnRt = trmMn12ErnnRt;
	}
	public String getPdrsGdcd() {
		return pdrsGdcd;
	}
	public void setPdrsGdcd(String pdrsGdcd) {
		this.pdrsGdcd = pdrsGdcd;
	}
	public String getIdivFnptDcd() {
		return idivFnptDcd;
	}
	public void setIdivFnptDcd(String idivFnptDcd) {
		this.idivFnptDcd = idivFnptDcd;
	}
	public String getPrcqFeeRt() {
		return prcqFeeRt;
	}
	public void setPrcqFeeRt(String prcqFeeRt) {
		this.prcqFeeRt = prcqFeeRt;
	}
	public String getTtalRmnrRt() {
		return ttalRmnrRt;
	}
	public void setTtalRmnrRt(String ttalRmnrRt) {
		this.ttalRmnrRt = ttalRmnrRt;
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
