package com.ibk.pds.log.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ibk.pds.data.model.JobWorldData;
import com.ibk.pds.log.model.LogDocData;

public interface LogDocDataRepository  extends MongoRepository<LogDocData,String>{
	public  List<LogDocData> findByDocId(String docId);
	
}
