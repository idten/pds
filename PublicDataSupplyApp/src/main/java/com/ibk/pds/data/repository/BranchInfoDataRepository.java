package com.ibk.pds.data.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.ibk.pds.data.model.ATMInfoData;
import com.ibk.pds.data.model.BranchInfoData;

public interface BranchInfoDataRepository  extends MongoRepository<BranchInfoData,String>{

	public Page<BranchInfoData> findAll(Pageable pageable);

	public List<BranchInfoData> findByBranchNameLike(String branchName,Pageable pageable);
	public long countByBranchNameLike(String branchName);

	public List<BranchInfoData> findByBranchSectionCode(String branchSectionCode,Pageable pageable);	
	public long countByBranchSectionCode(String branchSectionCode);
	
	public List<BranchInfoData> findByBranchAddressLike(String branchAddress, Pageable pageable);	
	public long countByBranchAddressLike(String branchAddress);
}
