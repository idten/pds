package com.ibk.pds.common.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibk.pds.common.model.ApiInfo;
import com.ibk.pds.common.model.DocumentInfo;
import com.ibk.pds.common.model.DocumentStatus;
import com.ibk.pds.common.repository.ApiInfoRepository;
@Service
public class ApiInfoService {
	
	@Autowired
	ApiInfoRepository apiInfoRepository;

	private Logger logger = LoggerFactory.getLogger(ApiInfoService.class);
	
	public List<ApiInfo> getApiInfoList(){
		List<ApiInfo> apiInfoList = apiInfoRepository.findAll();
		return apiInfoList;
	}
	public void saveApiInfo(ApiInfo apiInfo) {
		logger.info("save ApiInfo["+apiInfo.toString());
		apiInfoRepository.save(apiInfo);
	}
	public void addApiInfo(ApiInfo apiInfo) {
		logger.info("add ApiInfo["+apiInfo.toString());
		apiInfoRepository.insert(apiInfo);
	}
	public void deleteApiInfo(ApiInfo apiInfo) {
		logger.info("delete ApiInfo["+apiInfo.toString());
		apiInfoRepository.deleteByApiId(apiInfo.getApiId());
	}	
	public void deleteApiInfo(String apiId) {
		//logger.info("delete ApiInfo["+apiInfo.toString());
		apiInfoRepository.deleteByApiId(apiId);
	}	
	public List<ApiInfo> getApiInfoListByDocId(String docId) {
		List<ApiInfo> apis = apiInfoRepository.findByDocId(docId);
		
		logger.info("getApiInfoListByDocId List[]");
		return apis;
	}
	public List<ApiInfo> getApiInfoListByUserId(String userId) {
		List<ApiInfo> apis = apiInfoRepository.findByUserId(userId);//.findByDocId(userId);
		
		logger.info("getApiInfoListByUserId List.size()="+apis.size());
		return apis;
	}
	
	
	public ApiInfo getApiInfoByApiId(String apiId) {
		ApiInfo apiInfo = apiInfoRepository.findByApiId(apiId);//.findByDocId(docId);
		
		logger.info("getApiInfoByApiId");
		return apiInfo;
	}
	
	
}
