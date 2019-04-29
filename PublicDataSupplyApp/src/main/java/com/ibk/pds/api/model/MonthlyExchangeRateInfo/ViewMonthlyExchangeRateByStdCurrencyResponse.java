package com.ibk.pds.api.model.MonthlyExchangeRateInfo;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.ibk.pds.data.model.JobWorldData;

//채용정보 월별 환율 조회 응답 
//조회조건: stdCurrency
//등록일자: 2019.04.30 
//등록자   : 박현조
public class ViewMonthlyExchangeRateByStdCurrencyResponse {

	//private
	//공통 코드 
	private String resultCode = "";
	private String resultMsg = "";
	
	private Integer numOfRows=0;
	
//	@JacksonXmlProperty(localName="items")
	
	
	
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
	
//	@JacksonXmlElementWrapper(localName="items" , useWrapping=true)
//	private List<ViewCareersResponseSub> item;

	public String toString() {

		 String result = "resultCode="+resultCode+",resultMsg="+resultMsg+",numOfRows"+numOfRows;
		 
		 return result;
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
