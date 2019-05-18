package com.ibk.pds.data.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.ibk.pds.data.model.EmploymentInfoData;

public interface EmploymentInfoDataRepository  extends MongoRepository<EmploymentInfoData,String>{
	
	public long countByStdYM(String  stdYm);
	public List<EmploymentInfoData> findByStdYM(String stdYM,Pageable pageable);
	
	public long countByStdYMAndIndustryCode(String stdYM, String industryCode);
	public List<EmploymentInfoData> findByStdYMAndIndustryCode(String stdYM, String industryCode,Pageable pageRequest);

	public long countByIndustryCode(String industryCode);
	public List<EmploymentInfoData> findByIndustryCode(String industryCode,Pageable pageable);
	
}
