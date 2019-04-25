package com.ibk.pds.auth.service;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import kr.co.smartguru.apim.gateway.util.APIMGatewayUtil;

@Service
public class AuthService {
	public int auth() {
	//	mav.setViewName("authTest");
		String apimKey = "lNWxryx-e4WE2ibp6SzLQknGNlQCcMfA8LHYjAE75iU";
		int checkResult = -1;
		if(apimKey!=null)checkResult = APIMGatewayUtil.SG_APIM_Check(apimKey);
		if(checkResult==1) {
			System.out.println("인증성공");
		}else {
			System.out.println("인증실패");
		}
		
		return checkResult;
		//return mav;
	}
}
