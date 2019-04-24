package com.ibk.pds.common.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibk.pds.code.model.DepInfo;
import com.ibk.pds.common.model.UserInfo;
import com.ibk.pds.common.repository.UserInfoRepository;
@Service
public class UserInfoService {
	
	@Autowired
	UserInfoRepository userInfoRepository;

	private Logger logger = LoggerFactory.getLogger(UserInfoService.class);
	
	public List<UserInfo> getUserInfoList(){
		List<UserInfo> userInfoList = userInfoRepository.findAll();
		return userInfoList;
	}
	public void saveUserInfo(UserInfo userInfo) {
		logger.info("saveUserInfo["+userInfo.toString());
		userInfoRepository.save(userInfo);
		
	}
	public void addUserInfo(UserInfo userInfo) {
		logger.info("addUserInfo["+userInfo.toString());
		userInfoRepository.insert(userInfo);
	}
	public void deleteUserInfo(UserInfo userInfo) {
		logger.info("delete UserInfo["+userInfo.toString());
//		userInfoRepository.deleteById(userInfo.getUSER_ID());
		userInfoRepository.deleteByUserId(userInfo.getUserId());
		//userInfoRepository.
	}
	
	public UserInfo findByUserId(String userId) {
		logger.info("findByUserId==");
		UserInfo userInfo = userInfoRepository.findByUserId(userId);
		logger.info("findByUserId==");
		if(null==userInfo) {
			logger.info("userInfo is null");
		}
		return userInfo;
	}
	
	
	
	public Map<String,String> getUserInfoMap(){

		List<UserInfo> userInfoList = userInfoRepository.findAll();
		
		Map<String, String> map = new HashMap<>(); 
		  
        // put every value list to Map 
        for (UserInfo userInfo : userInfoList) { 
            map.put(userInfo.getUserId(),userInfo.getUserName()); 
        } 
        return map;
	}
	
}
