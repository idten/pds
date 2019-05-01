package com.ibk.pds.data.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.ibk.pds.data.model.BranchInfoData;
import com.ibk.pds.data.model.JobWorldData;

public interface BranchInfoDataRepository  extends MongoRepository<BranchInfoData,String>{

	public Page<BranchInfoData> findAll(Pageable pageable);
	public List<BranchInfoData> findByBranchName(String branchName,Pageable pageable);
	public List<BranchInfoData> findByBranchSectionCode(String branchSectionCode,Pageable pageable);	
}
