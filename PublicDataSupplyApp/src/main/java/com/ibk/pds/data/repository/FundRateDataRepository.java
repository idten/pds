package com.ibk.pds.data.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.ibk.pds.data.model.FundRateData;

public interface FundRateDataRepository  extends MongoRepository<FundRateData,String>{

	public List<FundRateData> findByFundAstTcdAndFundInvmAecdAndPdrsGdcdAndIdivFnptDcd
			(String fundAstTcd, String fundInvmAecd,String pdrsGdcd,String idivFnptDcd,Pageable pageable);
	
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
