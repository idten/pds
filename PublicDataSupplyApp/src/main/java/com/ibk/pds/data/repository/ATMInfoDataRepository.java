package com.ibk.pds.data.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.ibk.pds.data.model.ATMInfoData;
import com.ibk.pds.data.model.BranchInfoData;

public interface ATMInfoDataRepository  extends MongoRepository<ATMInfoData,String>{

	public Page<ATMInfoData> findAll(Pageable pageable);

	public List<ATMInfoData> findByAtmNameLike(String atmName, Pageable pageable);
	public long countByAtmNameLike(String atmName);
	
	
	public List<ATMInfoData> findByAtmSectionCode(String atmSectionCode, Pageable pageable);	
	public long countByAtmSectionCode(String atmSectionCode);
	
	
	public List<ATMInfoData> findByAtmAddressLike(String atmAddress, Pageable pageable);	
	public long countByAtmAddressLike(String atmAddress);
}
