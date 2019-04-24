package com.ibk.pds.api.model;

import java.util.List;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.ibk.pds.data.model.JobWorldData;

@JacksonXmlRootElement(localName="response")
public class JobWorldResponse {
	
	
	//private
	//공통 코드 
	private String resultCode = "";
	private String resultMsg = "";
	
	private Integer numOfRows=0;
	
	@JacksonXmlProperty(localName="items")
	@JacksonXmlElementWrapper(useWrapping=false)
	
	private List<JobWorldData> listJobWorldData;
	
	
	
	
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

	public List<JobWorldData> getListJobWorldData() {
		return listJobWorldData;
	}

	public void setListJobWorldData(List<JobWorldData> listJobWorldData) {
		this.listJobWorldData = listJobWorldData;
	}


	



	
}
