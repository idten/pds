package com.ibk.pds.log.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibk.pds.log.model.LogApiData;
import com.ibk.pds.log.repository.LogApiDataRepository;
@Service
public class LogApiDataService {
	@Autowired
	LogApiDataRepository logApiDataRepository;

	private Logger logger = LoggerFactory.getLogger(LogApiDataService.class);
	
	public List<LogApiData> getLogApiDataList(){
		List<LogApiData> logApiDataList = logApiDataRepository.findAllByOrderByTrxDateDesc();
		return logApiDataList;
	}
	
	public LogApiData getLogApiData(String logId){
		LogApiData logApiData = logApiDataRepository.findByLogId(logId);
		return logApiData;
	}
//	
	
	
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
