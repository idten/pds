package com.ibk.pds.test.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.co.smartguru.apim.gateway.util.APIMGatewayUtil;

@Controller
public class AuthController {
	@RequestMapping(value = "/authTest.do", method = RequestMethod.GET)
	public ModelAndView depCode(ModelAndView mav) {
		mav.setViewName("authTest");
		String apimKey = "lNWxryx-e4WE2ibp6SzLQknGNlQCcMfA8LHYjAE75iU";
		int checkResult = -1;
		if(apimKey!=null)checkResult = APIMGatewayUtil.SG_APIM_Check(apimKey);
		if(checkResult==1) {
			System.out.println("인증성공");
		}else {
			System.out.println("인증실패");
		}
		
		return mav;
	}
	
}
