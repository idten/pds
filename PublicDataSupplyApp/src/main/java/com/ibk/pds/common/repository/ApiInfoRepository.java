package com.ibk.pds.common.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ibk.pds.common.model.ApiInfo;

public interface ApiInfoRepository extends MongoRepository<ApiInfo,String>{
	void deleteByApiId(String apiId);
	public ApiInfo findByApiId(String apiId);

	
	public List<ApiInfo> findByDocId(String docId);

}
