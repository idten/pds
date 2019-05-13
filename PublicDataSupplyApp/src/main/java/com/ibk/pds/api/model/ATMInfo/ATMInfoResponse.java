package com.ibk.pds.api.model.ATMInfo;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.ibk.pds.data.model.JobWorldData;

//채용정보 산업별 조회 응답 
//조회조건: stdYm
//등록일자: 2019.04.23 
//등록자   : 박현조
public class ATMInfoResponse {

	//private
	//공통 코드 
	//	private String resultCode = "";
	//	private String resultMsg = "";
	//	private Integer numOfRows=0;
	//	

	private String resultCode = "";
	private String resultMsg = "";
	private Integer totalCount = 0;
	private Integer numOfRows=0;
	private Integer pageNo=0;



	//	@JacksonXmlProperty(localName="items")
	@JacksonXmlElementWrapper(localName="items" , useWrapping=true)
	private List<ATMInfoResponseSub> item;

	public String toString() {

		String result = "resultCode="+resultCode+",resultMsg="+resultMsg+",numOfRows"+numOfRows;
		String subResult = "";
		int size = item.size();
		for(int i = 0 ;i<size;i++) {
			ATMInfoResponseSub subItem = item.get(0);
			subResult += "["+(i+1)+"]"+subItem.toString()+"\n";
		}

		return result+subResult;
	}
	public Integer getTotalCount() {
		return totalCount;
	}


	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}


	public Integer getPageNo() {
		return pageNo;
	}


	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public List<ATMInfoResponseSub> getItem() {
		return item;
	}

	public void setItem(List<ATMInfoResponseSub> item) {
		this.item = item;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	public Integer getNumOfRows() {
		return numOfRows;
	}

	public void setNumOfRows(Integer numOfRows) {
		this.numOfRows = numOfRows;
	}






}
