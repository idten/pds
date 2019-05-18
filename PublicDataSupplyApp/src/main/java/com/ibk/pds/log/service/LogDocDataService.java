package com.ibk.pds.log.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibk.pds.common.model.ApiInfo;
import com.ibk.pds.common.model.DocumentInfo;
import com.ibk.pds.common.model.DocumentStatus;
import com.ibk.pds.common.model.UserInfo;
import com.ibk.pds.common.repository.ApiInfoRepository;
import com.ibk.pds.common.service.ApiInfoService;
import com.ibk.pds.data.repository.JobWorldDataRepository;
import com.ibk.pds.log.model.LogDocData;
import com.ibk.pds.log.repository.LogDocDataRepository;
@Service
public class LogDocDataService {
	@Autowired
	LogDocDataRepository logDocDataRepository;

	private Logger logger = LoggerFactory.getLogger(LogDocDataService.class);
	public List<LogDocData> getLogDocDataList(){
		List<LogDocData> logDocDataList = logDocDataRepository.findAll();
		return logDocDataList;
	}
	
	public List<LogDocData> getLogDocDataListByDocId(String docId){
		List<LogDocData> logDocDataList = logDocDataRepository.findByDocId(docId);
		return logDocDataList;
	}
	
	
	public void saveLogDocData(LogDocData logDocData) {
		logger.info("saveUserInfo["+logDocData.toString()+"]");
		logDocDataRepository.save(logDocData);

	}	
	
	
}
