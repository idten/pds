package com.ibk.pds.log.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ibk.pds.log.model.LogApiData;

public interface LogApiDataRepository  extends MongoRepository<LogApiData,String>{	
	List<LogApiData> findAllByOrderByLogIdDesc();
	List<LogApiData> findOrderByLogId();
	List<LogApiData> findAllByOrderByTrxDateDesc();
	LogApiData findByLogId(String logId);
}
