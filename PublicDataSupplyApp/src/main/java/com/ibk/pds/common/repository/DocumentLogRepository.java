package com.ibk.pds.common.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ibk.pds.common.model.DocumentInfo;
import com.ibk.pds.common.model.UserInfo;

public interface DocumentLogRepository extends MongoRepository<DocumentInfo,String>{
	void deleteBydocId(String docId);
	
	public List<DocumentInfo> findByDocId(String docId);
	public List<DocumentInfo> findBydocOwnersRegex(String docOwners);
//	List<User> findByUserNameRegex(String userName);
}


