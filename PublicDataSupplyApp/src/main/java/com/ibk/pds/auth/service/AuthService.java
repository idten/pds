package com.ibk.pds.auth.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.ibk.pds.common.controller.ApiInfoController;

import kr.co.smartguru.apim.gateway.util.APIMGatewayUtil;

@Service
public class AuthService {
	
	private Logger logger = LoggerFactory.getLogger(AuthService.class);
	
	
	public int auth() {
	//	mav.setViewName("authTest");
		String apimKey = "lNWxryx-e4WE2ibp6SzLQknGNlQCcMfA8LHYjAE75iU";
		int checkResult = -1;
		if(apimKey!=null)checkResult = APIMGatewayUtil.SG_APIM_Check(apimKey);
		if(checkResult==1) {
			logger.info("인증 성공");
		}else {
			logger.info("인증 실패");
		}
		
		return checkResult;
		//return mav;
	}
}
