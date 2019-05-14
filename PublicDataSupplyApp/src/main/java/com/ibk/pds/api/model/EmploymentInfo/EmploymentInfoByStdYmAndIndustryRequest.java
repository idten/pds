package com.ibk.pds.api.model.EmploymentInfo;

//채용정보 산업별 조회
//조회조건: stdYm
//등록일자: 2019.04.23 
//등록자   : 박현조
public class EmploymentInfoByStdYmAndIndustryRequest {
	private String serviceKey;   	//서비스키(공공데이터포털에서 받은 인증키)
	private Integer numOfRows;		//한 페이지 결과 수 (10)- 필수 입력 아님 
	private Integer pageNo;			//페이지 번호(1)     - 필수 입력 아님 
	private String stdYm;		    //기준 연월일(201903)
	private String industryCode;
	
	public EmploymentInfoByStdYmAndIndustryRequest(String serviceKey, Integer numOfRows, Integer pageNo,
			String stdYm, String industryCode
			) {
		this.serviceKey = serviceKey;
		this.numOfRows = numOfRows;
		this.pageNo = pageNo;
		this.stdYm = stdYm;
		this.industryCode = industryCode;
	}
	
	public String getIndustryCode() {
		return industryCode;
	}
	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode;
	}
	public void setStdYm(String stdYm) {
		this.stdYm = stdYm;
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
	
	public String getStdYm() {
		return stdYm;
	}
	public String toString() {
		return "ServiceKey="+serviceKey+",numberOfRows="+numOfRows+",pageNo="+pageNo+",stdYm="+stdYm+",Industry="+industryCode;
	}
}
