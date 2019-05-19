package com.ibk.pds.common.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.ibk.pds.auth.service.UserAuthService;
import com.ibk.pds.code.model.DepInfo;
import com.ibk.pds.code.service.DepInfoService;
import com.ibk.pds.common.model.ApiInfo;
import com.ibk.pds.common.model.DocumentInfo;
import com.ibk.pds.common.model.DocumentStatus;
import com.ibk.pds.common.model.UserInfo;
import com.ibk.pds.common.repository.DocumentInfoRepository;
import com.ibk.pds.common.repository.UserInfoRepository;
import com.ibk.pds.common.service.ApiInfoService;
import com.ibk.pds.common.service.DocumentInfoService;
import com.ibk.pds.common.service.DocumentStatusService;
import com.ibk.pds.common.service.UserInfoService;
import com.ibk.pds.common.util.DateUtil;

@Controller
public class NewRegInfoController {

	@Autowired
	UserInfoService userInfoService;
	
	private Logger logger = LoggerFactory.getLogger(NewRegInfoController.class);
	
	@RequestMapping(value = "/newreg.do", method = RequestMethod.GET)
	public ModelAndView newreg(ModelAndView mav) {
		UserAuthService userAuthService = new UserAuthService();
		UserInfo userInfo = userAuthService.getUserAuthInfo(userInfoService);
		mav.addObject("userInfo",userInfo);
		mav.setViewName("newreg");
		
		logger.info("new Reg");
		return mav;
		
	}
	
	
	@RequestMapping(value = "/new.do", method = RequestMethod.GET)
	public ModelAndView new1(ModelAndView mav) {
		//접속시 문서 ID를 최초 생성하여 보여줌 
		String docId = "D"+DateUtil.getDateYYYYMMDDHHMMSS();	
		mav.addObject("docId",docId);
		
		Map<String,String> userInfoMap = userInfoService.getUserInfoMap();
	
		mav.addObject("userInfoMap", userInfoMap);
	//	mav.addObject("deplist",depCodeList);
    //    mav.addObject("datalist",userInfoList);
		
		
		
		
		
		mav.setViewName("new");
		logger.info("Doc Test End");
		return mav;
		
	}
	
	@RequestMapping(value = "/new2.do", method = RequestMethod.GET)
	public ModelAndView new2(ModelAndView mav) {
		mav.setViewName("new2");
		logger.info("Doc Test End");
		return mav;
		
	}
	
	@RequestMapping(value = "/apidetail.do", method = RequestMethod.GET)
	public ModelAndView apiDetailInfo(ModelAndView mav) {
		mav.setViewName("detail");
		logger.info("Doc Test End");
		return mav;
		
	}
	

	


}
