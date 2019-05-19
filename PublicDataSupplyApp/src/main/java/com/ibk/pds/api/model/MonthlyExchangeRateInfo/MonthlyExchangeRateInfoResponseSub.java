
package com.ibk.pds.api.model.MonthlyExchangeRateInfo;

import io.swagger.annotations.ApiModelProperty;

public class MonthlyExchangeRateInfoResponseSub {
	
	@ApiModelProperty(notes = "기준통화")
	private String stdCurrency;
	//상태 통화

	@ApiModelProperty(notes = "상대통화")
	private String relativeCurrency;
	

	@ApiModelProperty(notes = "1월환율")
	private String monthly1Rate;
	@ApiModelProperty(notes = "2월환율")
	private String monthly2Rate;
	@ApiModelProperty(notes = "3월환율")
	private String monthly3Rate;
	@ApiModelProperty(notes = "4월환율")
	private String monthly4Rate;
	@ApiModelProperty(notes = "5월환율")
	private String monthly5Rate;
	@ApiModelProperty(notes = "6월환율")
	private String monthly6Rate;
	@ApiModelProperty(notes = "7월환율")
	private String monthly7Rate;
	@ApiModelProperty(notes = "8월환율")
	private String monthly8Rate;
	@ApiModelProperty(notes = "9월환율")
	private String monthly9Rate;
	@ApiModelProperty(notes = "10월환율")
	private String monthly10Rate;
	@ApiModelProperty(notes = "11월환율")
	private String monthly11Rate;
	@ApiModelProperty(notes = "12월환율")
	private String monthly12Rate;
	
	
	
	
	public MonthlyExchangeRateInfoResponseSub() {
		
	}
	public String toString() {
		return "[stdCurrency="+stdCurrency+",relativeCurrency="+relativeCurrency+",monthly1Rate="+monthly1Rate;
	}
	public MonthlyExchangeRateInfoResponseSub(
			String stdCurrency, 
			String relativeCurrency, 
			String monthly1Rate,
			String monthly2Rate,
			String monthly3Rate,
			String monthly4Rate,
			String monthly5Rate,
			String monthly6Rate,
			String monthly7Rate,
			String monthly8Rate,
			String monthly9Rate,
			String monthly10Rate,
			String monthly11Rate,
			String monthly12Rate
			
			) {
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

	}
	
	
}
