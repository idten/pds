package com.ibk.pds.auth.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ibk.pds.common.controller.MainController;
import com.ibk.pds.common.model.UserInfo;
import com.ibk.pds.common.service.UserInfoService;

public class UserAuthService {
	//@Autowired
	//UserInfoService userInfoService;
	private Logger logger = LoggerFactory.getLogger(UserAuthService.class);


	public UserInfo getUserAuthInfo(UserInfoService userInfoService) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
		User user = (User) authentication.getPrincipal();
		logger.info("User SessionInfo = "+user.getUsername());


		String userId="";
		//로그인 처리가 없다면 기본 값은 d23358 
		userId = user.getUsername();
		logger.info("login id="+userId);
		
		UserInfo userInfo = userInfoService.findByUserId(userId);//   findByUserId
		logger.info("output="+userInfo.getUserId());
		return userInfo;
	}

}
