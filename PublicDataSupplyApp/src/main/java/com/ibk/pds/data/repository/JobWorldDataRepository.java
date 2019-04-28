package com.ibk.pds.data.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.ibk.pds.data.model.JobWorldData;

public interface JobWorldDataRepository  extends MongoRepository<JobWorldData,String>{
	public  List<JobWorldData> findByStdYM(String stdYM);
	public  List<JobWorldData> findByStdYM(String stdYM,Pageable pageable);
	
	public List<JobWorldData> findByStdYMAndIndustryCode(String stdYM, String industryCode);
	public List<JobWorldData> findByStdYMAndIndustryCode(String stdYM, String industryCode,Pageable pageable);
	
}
