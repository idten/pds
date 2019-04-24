package com.ibk.pds.api.model.IonejobCareerStaticsview;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.ibk.pds.data.model.JobWorldData;

//채용정보 산업별 조회 응답 
//조회조건: stdYm
//등록일자: 2019.04.23 
//등록자   : 박현조
public class ViewCareersResponse {

	//private
	//공통 코드 
	private String resultCode = "";
	private String resultMsg = "";
	
	private Integer numOfRows=0;
	
	@JacksonXmlProperty(localName="items")
	@JacksonXmlElementWrapper(useWrapping=false)
	
	private List<ViewCareersResponseSub> listJobWorldData;

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

	public List<ViewCareersResponseSub> getListJobWorldData() {
		return listJobWorldData;
	}

	public void setListJobWorldData(List<ViewCareersResponseSub> listJobWorldData) {
		this.listJobWorldData = listJobWorldData;
	}
	
	
	

}
