package com.ibk.pds.data.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.ibk.pds.data.model.ATMInfoData;
import com.ibk.pds.data.model.BranchInfoData;
import com.ibk.pds.data.model.JobWorldData;

public interface ATMInfoDataRepository  extends MongoRepository<ATMInfoData,String>{

	public Page<ATMInfoData> findAll(Pageable pageable);
	public List<ATMInfoData> findByATMName(String atmName, Pageable pageable);
	public List<ATMInfoData> findByATMSectionCode(String atmSectionCode, Pageable pageable);	
}
