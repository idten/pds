
package com.ibk.pds.api.model.MonthlyExchangeRateInfo;


public class MonthlyExchangeRateInfoResponseSub {
	
	private String stdCurrency;
	//상태 통화
	private String relativeCurrency;
	
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
