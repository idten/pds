
package com.ibk.pds.api.model.FundRateInfo;


public class FundRateInfoResponseSub {
	//문서 내용 
	private String baseYmd;		//기준 년월일
	private String opcmNm;  	//운용사명 
	private String fundNm;		//펀드명
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
	
	
	
	
	public FundRateInfoResponseSub() {
		
	}
	public String toString() {
		return "[baseYmd="+baseYmd+",opcmNm="+opcmNm+",fundNm="+fundNm+
				",ascnFundCd="+ascnFundCd
				+",fundAstTcd="+fundAstTcd+",fundInvmAecd="+fundInvmAecd				
				+",trmMn1ErnnRt="+trmMn1ErnnRt+",trmMn3ErnnRt="+trmMn3ErnnRt
				+",trmMn6ErnnRt="+trmMn6ErnnRt+",trmMn12ErnnRt="+trmMn12ErnnRt
				+",trmMn6ErnnRt="+trmMn6ErnnRt+",trmMn12ErnnRt="+trmMn12ErnnRt
				+",pdrsGdcd="+pdrsGdcd+",idivFnptDcd="+idivFnptDcd
				+",prcqFeeRt="+prcqFeeRt+",ttalRmnrRt="+ttalRmnrRt
				
				
+				"]";
	}
	
	public FundRateInfoResponseSub(
			String baseYmd, 
			String opcmNm, 
			String fundNm,
			String ascnFundCd,
			String fundAstTcd ,
			String fundInvmAecd,
			String trmMn1ErnnRt,
			String trmMn3ErnnRt,
			String trmMn6ErnnRt,
			String trmMn12ErnnRt,
			String pdrsGdcd,
			String idivFnptDcd,
			String prcqFeeRt,
			String ttalRmnrRt			
			) {
		this.baseYmd = baseYmd;
		this.opcmNm = opcmNm;
		this.fundNm = fundNm;
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
}
