package com.ibk.pds.code.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ibk.pds.code.model.AlarmStd;
import com.ibk.pds.code.model.DocCycle;

public interface DocCycleRepository extends MongoRepository<DocCycle,String> {

	public DocCycle findByDocCycleCode(String docCycleCode);
	
}
