package com.ibk.pds.data.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibk.pds.common.model.ApiInfo;
import com.ibk.pds.common.model.DocumentInfo;
import com.ibk.pds.common.model.UserInfo;
import com.ibk.pds.common.repository.ApiInfoRepository;
import com.ibk.pds.common.service.ApiInfoService;
import com.ibk.pds.data.model.JobWorldData;
import com.ibk.pds.data.repository.JobWorldDataRepository;
@Service
public class JobWorldDataService {
	@Autowired
	JobWorldDataRepository jobWorldDataRepository;

	private Logger logger = LoggerFactory.getLogger(JobWorldDataService.class);
	
	public List<JobWorldData> getJobWorldDataList(){
		List<JobWorldData> jobWorldDataList = jobWorldDataRepository.findAll();
		return jobWorldDataList;
	}
	public void addJobWorldData(JobWorldData jobWorldData) {
		logger.info("addJobWorldData["+jobWorldData.toString());
		jobWorldDataRepository.insert(jobWorldData);
	}
//	public List<JobWorldData> getApiInfoList(){
//		List<ApiInfo> apiInfoList = apiInfoRepository.findAll();
//		return apiInfoList;
//	}
	//public  List<JobWorldData> findByStdDate(String stdDate);
	public List<JobWorldData> findByStdDate(String stdDate) {
		logger.info("stdDate="+stdDate);
		List<JobWorldData> list = jobWorldDataRepository.findByStdYM(stdDate);
		
		logger.info("getByStdDate ="+list.size());
		return list;
	}
	
}
