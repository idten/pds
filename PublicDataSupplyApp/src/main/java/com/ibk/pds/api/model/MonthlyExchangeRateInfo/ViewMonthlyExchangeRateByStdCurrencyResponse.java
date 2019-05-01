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
	
	
	private String stdCurrency="";
	private String relativeCurrency="";
	
	private String monthly1Rate="";
	private String monthly2Rate="";
	private String monthly3Rate="";
	private String monthly4Rate="";
	private String monthly5Rate="";
	private String monthly6Rate="";
	private String monthly7Rate="";
	private String monthly8Rate="";
	private String monthly9Rate="";
	private String monthly10Rate="";
	private String monthly11Rate="";
	private String monthly12Rate="";
	

	//	@JacksonXmlElementWrapper(localName="items" , useWrapping=true)
//	private List<ViewCareersResponseSub> item;
	
	public ViewMonthlyExchangeRateByStdCurrencyResponse() {
		
	}
			
	public ViewMonthlyExchangeRateByStdCurrencyResponse(
			String resultCode, String resultMsg, Integer numOfRows,
			String stdCurrency, String relativeCurrency, String monthly1Rate, String monthly2Rate, String monthly3Rate,
			String monthly4Rate, String monthly5Rate, String monthly6Rate, String monthly7Rate, String monthly8Rate,
			String monthly9Rate, String monthly10Rate, String monthly11Rate, String monthly12Rate) {
		
		this.resultCode = resultCode;
		this.resultMsg = resultMsg;
		this.numOfRows = numOfRows;
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
	//	return null;
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
	public String toString() {

		 String result = "resultCode="+resultCode+",resultMsg="+resultMsg+",numOfRows"+numOfRows
				 ;
		 
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
