package com.ibk.pds.data.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.ibk.pds.data.model.FundRateData;
import com.ibk.pds.data.model.JobWorldData;

public interface FundRateDataRepository  extends MongoRepository<FundRateData,String>{
	//자산유형코드(fundAstTcd), 펀드투자지역코드(fundInvmAecd), 상품리스크등급코드(pdrGdcd), 펀드 유형구분코드(idivFnptDcd)
	public FundRateData findByFundAstTcdAndFundInvmAecdAndPdrsGdcdAndIdivFnptDcd
				(String fundAstTcd, String fundInvmAecd,String pdrsGdcd,String idivFnptDcd);

	
	public List<FundRateData> findByIdivFnptDcd(String idivFnptDcd,Pageable pageable);

	//1개월 
	public List<FundRateData> findByIdivFnptDcdOrderByTrmMn1ErnnRt(String idivFnptDcd,Pageable pageable);
	public List<FundRateData> findByIdivFnptDcdOrderByTrmMn3ErnnRt(String idivFnptDcd,Pageable pageable);
	public List<FundRateData> findByIdivFnptDcdOrderByTrmMn6ErnnRt(String idivFnptDcd,Pageable pageable);
	public List<FundRateData> findByIdivFnptDcdOrderByTrmMn12ErnnRt(String idivFnptDcd,Pageable pageable);
	
	
	
	public List<FundRateData> findByFundInvmAecd(String fundInvmAecd,Pageable pageable);
	public List<FundRateData> findByFundInvmAecdOrderByTrmMn1ErnnRt(String fundInvmAecd,Pageable pageable);
	public List<FundRateData> findByFundInvmAecdOrderByTrmMn3ErnnRt(String fundInvmAecd,Pageable pageable);
	public List<FundRateData> findByFundInvmAecdOrderByTrmMn6ErnnRt(String fundInvmAecd,Pageable pageable);
	public List<FundRateData> findByFundInvmAecdOrderByTrmMn12ErnnRt(String fundInvmAecd,Pageable pageable);


}
