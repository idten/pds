package com.ibk.pds.api.model.EmploymentInfo;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import io.swagger.annotations.ApiModelProperty;

//채용정보 산업별 조회 응답 
//조회조건: stdYm
//등록일자: 2019.04.23 
//등록자   : 박현조
@JacksonXmlRootElement(localName="response")
public class EmploymentInfoResponse {
	
	
	
//	@JacksonXmlProperty(localName="items")
	@JacksonXmlElementWrapper(localName="items" , useWrapping=true)
	private List<EmploymentInfoResponseSub> item;

	
	@ApiModelProperty(notes = "응답코드")
	private String resultCode = "";//결과코드 
	@ApiModelProperty(notes = "응답메세지")
	private String resultMsg = "";
	@ApiModelProperty(notes = "전체수")
	private Integer totalCount = 0;
	@ApiModelProperty(notes = "한페이지 결과 수 ")
	private Integer numOfRows=0;
	@ApiModelProperty(notes = "페이지 번호")
	private Integer pageNo=0;
	
	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	
	public String toString() {

		 String result = "resultCode="+resultCode+",resultMsg="+resultMsg+",numOfRows"+numOfRows;
		 String subResult = "";
		 int size = item.size();
		 for(int i = 0 ;i<size;i++) {
			 EmploymentInfoResponseSub subItem = item.get(0);
			 subResult += "["+(i+1)+"]"+subItem.toString()+"\n";
		 }
		 
		 return result+subResult;
	}
	
	
	public List<EmploymentInfoResponseSub> getItem() {
		return item;
	}

	public void setItem(List<EmploymentInfoResponseSub> item) {
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
