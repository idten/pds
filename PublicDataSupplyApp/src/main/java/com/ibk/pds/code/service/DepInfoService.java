package com.ibk.pds.code.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibk.pds.code.model.DepInfo;
import com.ibk.pds.code.repository.DepInfoRepository;
@Service
public class DepInfoService {
	@Autowired
	DepInfoRepository depInfoRepository;

	private Logger logger = LoggerFactory.getLogger(DepInfoService.class);

	//private String depCode;
	//private String depName;
	
	//depCode 기준으로 부서 명을 리턴 한다. 
	public DepInfo getByDepCode(String depCode) {
		
		DepInfo depInfo = depInfoRepository.findByDepCode(depCode);
		return depInfo;
	}
	
	public List<DepInfo> getDepCodeList(){

		List<DepInfo> depCodeDataList = depInfoRepository.findAll();
		return depCodeDataList;
	}
	
	public Map<String,String> getDepCodeMap(){

		List<DepInfo> depCodeDataList = depInfoRepository.findAll();
		
		Map<String, String> map = new HashMap<>(); 
		  
        // put every value list to Map 
        for (DepInfo depInfo : depCodeDataList) { 
            map.put(depInfo.getDepCode(),depInfo.getDepName()); 
        } 
        return map;
	}
	public Map<String, DepInfo> convertListToMap(List<DepInfo> list) {
	    Map<String, DepInfo> map = list.stream()
	      .collect(Collectors.toMap(DepInfo::getDepCode, depInfo -> depInfo));
	    return map;
	}


	public void addDepInfo(DepInfo depInfo) {
		logger.info("addUserInfo["+depInfo.toString());
		depInfoRepository.insert(depInfo);
	}


	public void saveDepInfo(DepInfo depInfo) {
		logger.info("saveUserInfo["+depInfo.toString());
		depInfoRepository.save(depInfo);

	}

	public void deleteUserInfo(DepInfo depInfo) {
		logger.info("delete UserInfo["+depInfo.toString());
		depInfoRepository.deleteByDepCode(depInfo.getDepCode());
	}



}
