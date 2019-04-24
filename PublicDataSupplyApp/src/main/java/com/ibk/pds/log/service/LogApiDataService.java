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
import com.ibk.pds.data.model.JobWorldData;
import com.ibk.pds.data.repository.JobWorldDataRepository;
import com.ibk.pds.log.model.LogApiData;
import com.ibk.pds.log.model.LogDocData;
import com.ibk.pds.log.repository.LogApiDataRepository;
import com.ibk.pds.log.repository.LogDocDataRepository;
@Service
public class LogApiDataService {
	@Autowired
	LogApiDataRepository logApiDataRepository;

	private Logger logger = LoggerFactory.getLogger(LogApiDataService.class);
	
	public List<LogApiData> getLogDocDataList(){
		List<LogApiData> logApiDataList = logApiDataRepository.findAll();
		return logApiDataList;
	}
//	
//	public List<LogDocData> getLogDocDataListByDocId(String docId){
//		List<LogDocData> logDocDataList = logApiDataRepository.findByDocId(docId);
//		return logDocDataList;
//	}
	
	
	public void saveApiData(LogApiData logApiData) {
		logger.info("saveUserInfo["+logApiData.toString()+"]");
		logApiDataRepository.save(logApiData);
	}	
	
	
}
