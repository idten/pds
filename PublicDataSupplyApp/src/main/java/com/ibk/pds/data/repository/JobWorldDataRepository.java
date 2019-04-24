package com.ibk.pds.data.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ibk.pds.data.model.JobWorldData;

public interface JobWorldDataRepository  extends MongoRepository<JobWorldData,String>{
	public  List<JobWorldData> findByStdYM(String stdYM);
}
