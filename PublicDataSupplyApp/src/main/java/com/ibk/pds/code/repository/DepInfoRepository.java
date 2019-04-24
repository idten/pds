package com.ibk.pds.code.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ibk.pds.code.model.DepInfo;
import com.ibk.pds.common.model.UserInfo;

public interface DepInfoRepository extends MongoRepository<DepInfo,String> {

	void deleteByDepCode(String depCode);
	public DepInfo findByDepCode(String depCode);
	
	//public Map<DepInfo,String> findByDepCode(String depCode);
	
}
