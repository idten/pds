package com.ibk.pds.api.model.FundRateInfo;

//채용정보 산업별 조회
//조회조건: stdYm
//등록일자: 2019.04.23 
//등록자   : 박현조
public class FundRateInfoRequest {
	

	private String serviceKey;   	//서비스키(공공데이터포털에서 받은 인증키)
	private Integer numOfRows;		//한 페이지 결과 수 (10)- 필수 입력 아님 
	private Integer pageNo;			//페이지 번호(1)     - 필수 입력 아님 
	private String fundAstTcd;	//펀드자산유형코드
	private String fundInvmAecd;//펀드투자지역코드 
	private String pdrsGdcd;	//상품리스크 등급코드 
	private String idivFnptDcd;	//펀드유형구분코드
	
	public FundRateInfoRequest(String serviceKey,Integer numOfRows, Integer pageNo
			,String fundAstTcd, String fundInvmAecd, String pdrsGdcd, String idivFnptDcd)
		{
		// TODO Auto-generated constructor stub
		this.numOfRows = numOfRows;
		this.pageNo = pageNo;
		this.serviceKey = serviceKey;
		this.fundAstTcd = fundAstTcd;
		this.fundInvmAecd = fundInvmAecd;
		this.pdrsGdcd = pdrsGdcd;
		this.idivFnptDcd = idivFnptDcd;
	}
	//private String  fundAstTcd;		    //기준 연월일(201903)
	public String getServiceKey() {
		return serviceKey;
	}
	public void setServiceKey(String serviceKey) {
		this.serviceKey = serviceKey;
	}
	public Integer getNumOfRows() {
		return numOfRows;
	}
	public void setNumOfRows(Integer numOfRows) {
		this.numOfRows = numOfRows;
	}
	public Integer getPageNo() {
		return pageNo;
	}
	
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	
	public String toString() {
		return "ServiceKey="+serviceKey+",numberOfRows="+numOfRows+",pageNo="+pageNo+","
			+ "fundAstTcd="+fundAstTcd+ "fundInvmAecd="+fundInvmAecd+ 
			"pdrsGdcd="+pdrsGdcd+ "idivFnptDcd="+idivFnptDcd;
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
	
	
}
