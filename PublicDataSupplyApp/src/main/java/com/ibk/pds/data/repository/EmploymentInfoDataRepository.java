package com.ibk.pds.data.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.ibk.pds.data.model.EmploymentInfoData;
import com.ibk.pds.data.model.JobWorldData;

public interface EmploymentInfoDataRepository  extends MongoRepository<EmploymentInfoData,String>{
	public  List<EmploymentInfoData> findByStdYM(String stdYM);
	public  List<EmploymentInfoData> findByStdYM(String stdYM,Pageable pageable);
	
	public List<EmploymentInfoData> findByStdYMAndIndustryCode(String stdYM, String industryCode);
	public List<EmploymentInfoData> findByStdYMAndIndustryCode(String stdYM, String industryCode,Pageable pageable);
	public List<EmploymentInfoData> findByIndustryCode(String industryCode,Pageable pageable);
	public List<EmploymentInfoData> findByIndustryCode(String industryCode);
	
}
