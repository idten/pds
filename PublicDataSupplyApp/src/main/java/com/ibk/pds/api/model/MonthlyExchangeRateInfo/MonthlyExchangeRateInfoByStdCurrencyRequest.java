package com.ibk.pds.api.model.MonthlyExchangeRateInfo;

//월별 환율조회
//조회조건: stdCurrency
//등록일자: 2019.04.30 
//등록자   : 박현조
public class MonthlyExchangeRateInfoByStdCurrencyRequest {
	private String serviceKey;   	//서비스키(공공데이터포털에서 받은 인증키)
	private Integer numOfRows;		//한 페이지 결과 수 (10)- 필수 입력 아님 
	private Integer pageNo;			//페이지 번호(1)     - 필수 입력 아님 
	private String stdCurrency;		    //기준 연월일(201903)
	private String SG_APIM;
	
	public MonthlyExchangeRateInfoByStdCurrencyRequest(String serviceKey, Integer numOfRows, 
			Integer pageNo,String stdCurrency,String SG_APIM) {
		this.serviceKey = serviceKey;
		this.numOfRows = numOfRows;
		this.pageNo = pageNo;
		this.stdCurrency = stdCurrency;
		this.SG_APIM = SG_APIM;
	}
	public String getSG_APIM() {
		return SG_APIM;
	}
	public void setSG_APIM(String sG_APIM) {
		SG_APIM = sG_APIM;
	}
	public String getStdCurrency() {
		return stdCurrency;
	}
	public void setStdCurrency(String stdCurrency) {
		this.stdCurrency = stdCurrency;
	}
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
		return "ServiceKey="+serviceKey+",numberOfRows="+numOfRows+",pageNo="+pageNo+",stdCurrency="+stdCurrency;
	}
}
