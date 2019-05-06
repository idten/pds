package com.ibk.pds.api.model.FundRateInfo;

//채용정보 산업별 조회
//조회조건: stdYm
//등록일자: 2019.04.23 
//등록자   : 박현조
public class FundRateInfoByInvmAecdTop5Request {
	public String getFundInvmAecd() {
		return fundInvmAecd;
	}
	public void setFundInvmAecd(String fundInvmAecd) {
		this.fundInvmAecd = fundInvmAecd;
	}
	public String getErnnRtDcd() {
		return ernnRtDcd;
	}
	public void setErnnRtDcd(String ernnRtDcd) {
		this.ernnRtDcd = ernnRtDcd;
	}

	private String serviceKey;   	//서비스키(공공데이터포털에서 받은 인증키)
	private Integer numOfRows;		//한 페이지 결과 수 (10)- 필수 입력 아님 
	private Integer pageNo;			//페이지 번호(1)     - 필수 입력 아님 
//	private String fundAstTcd;	//펀드자산유형코드
	private String fundInvmAecd;//펀드투자지역코드 
	private String ernnRtDcd;
	
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
				+ "fundInvmAecd="+fundInvmAecd+ "ernnRtDcd="+ernnRtDcd;
	}
}
