package com.ibk.pds.api.model;

public class CommonHeaderResponse {
	private String resultCode = "";
	private String resultMsg = "";
	
	public CommonHeaderResponse(String resultCode,String resultMsg) {
		this.resultCode= resultCode;
		this.resultMsg = resultMsg;
	}
//	private Integer numOfRows=0;
	
}
