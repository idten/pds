package com.ibk.pds.api.model;

//아이원잡 채용공고통계 조회 서비스 
//입력 파라미터를 이용하여 아이원잡 채용공고의 업종 세부 산업통계정보를 조회 할 수 있는 서비스 

public class JobWorldRequest {
	private String serviceKey;   	//서비스키(공공데이터포털에서 받은 인증키)
	private Integer numOfRows;		//한 페이지 결과 수 (10)- 필수 입력 아님 
	private Integer pageNo;			//페이지 번호(1)     - 필수 입력 아님 
	private String stdYm;		//기준 연월일(20190311)
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
	public void setStdYYYYmm(String stdYm) {
		this.stdYm = stdYm;
	}
	public String toString() {
		return "ServiceKey="+serviceKey+",numberOfRows="+numOfRows+",pageNo="+pageNo+",stdYm="+stdYm;
	}
}
