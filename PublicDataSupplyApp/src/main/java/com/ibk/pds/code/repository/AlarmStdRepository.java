package com.ibk.pds.code.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ibk.pds.code.model.AlarmStd;

public interface AlarmStdRepository extends MongoRepository<AlarmStd,String> {

	public AlarmStd findByAlarmStdCode(String alarmStdCode);
	
}
