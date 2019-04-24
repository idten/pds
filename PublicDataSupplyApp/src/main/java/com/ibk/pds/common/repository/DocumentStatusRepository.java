package com.ibk.pds.common.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ibk.pds.common.model.DocumentInfo;
import com.ibk.pds.common.model.DocumentStatus;

public interface DocumentStatusRepository extends MongoRepository<DocumentStatus,String>{
	void deleteBydocId(String docId);
	
	public DocumentStatus findByDocId(String docId);
	public List<DocumentStatus> findByApproval(String approval);
	
	public List<DocumentStatus> findBydocOwnersRegex(String docOwners);

//	public List<DocumentStatus> findBydocOwnersRegex(String docOwners);
//	List<User> findByUserNameRegex(String userName);
}


