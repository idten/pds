
package com.ibk.pds.api.model.FundRateInfo;


public class FundRateInfoResponseSub {
	//문서 내용 
	private String baseYmd;		//기준 년월일
	private String opcmNm;  	//운용사명 
	private String fundNm;		//펀드명
	private String ascnFundCd;	//협회펀드코드 
	private String fundAstTcd;	//펀드자산유형코드
	private String fundInvmAecd;//펀드투자지역코드 
	private String trmMn1ErnnRt;//1개월 수익률
	private String trmMn3ErnnRt;//3개월 수익률
	private String trmMn6ErnnRt;//6개월 수익률
	private String trmMn12ErnnRt;//12개월 수익률
	private String pdrsGdcd;	//상품리스크 등급코드 
	private String idivFnptDcd;	//펀드유형구분코드
	private String prcqFeeRt;	//선취수수료율
	private String ttalRmnrRt;	//총보수료;
	
	
	
	
	public FundRateInfoResponseSub() {
		
	}
	public String toString() {
		return "[baseYmd="+baseYmd+",opcmNm="+opcmNm+",fundNm="+fundNm+
				",ascnFundCd="+ascnFundCd
				+",fundAstTcd="+fundAstTcd+",fundInvmAecd="+fundInvmAecd				
				+",trmMn1ErnnRt="+trmMn1ErnnRt+",trmMn3ErnnRt="+trmMn3ErnnRt
				+",trmMn6ErnnRt="+trmMn6ErnnRt+",trmMn12ErnnRt="+trmMn12ErnnRt
				+",trmMn6ErnnRt="+trmMn6ErnnRt+",trmMn12ErnnRt="+trmMn12ErnnRt
				+",pdrsGdcd="+pdrsGdcd+",idivFnptDcd="+idivFnptDcd
				+",prcqFeeRt="+prcqFeeRt+",ttalRmnrRt="+ttalRmnrRt
				
				
+				"]";
	}
	
	public FundRateInfoResponseSub(
			String baseYmd, 
			String opcmNm, 
			String fundNm,
			String ascnFundCd,
			String fundAstTcd ,
			String fundInvmAecd,
			String trmMn1ErnnRt,
			String trmMn3ErnnRt,
			String trmMn6ErnnRt,
			String trmMn12ErnnRt,
			String pdrsGdcd,
			String idivFnptDcd,
			String prcqFeeRt,
			String ttalRmnrRt			
			) {
		this.baseYmd = baseYmd;
		this.opcmNm = opcmNm;
		this.fundNm = fundNm;
		this.ascnFundCd = ascnFundCd;
		this.fundAstTcd = fundAstTcd;
		this.fundInvmAecd = fundInvmAecd;
		this.trmMn1ErnnRt = trmMn1ErnnRt;
		this.trmMn3ErnnRt = trmMn3ErnnRt;
		this.trmMn6ErnnRt = trmMn6ErnnRt;
		this.trmMn12ErnnRt = trmMn12ErnnRt;
		this.pdrsGdcd = pdrsGdcd;
		this.idivFnptDcd = idivFnptDcd;
		this.prcqFeeRt = prcqFeeRt;
		this.ttalRmnrRt = ttalRmnrRt;
		
	}
}
