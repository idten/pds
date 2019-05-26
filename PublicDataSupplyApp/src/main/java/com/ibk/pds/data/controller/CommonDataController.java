package com.ibk.pds.data.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ibk.pds.auth.service.UserAuthService;
import com.ibk.pds.code.model.DepInfo;
import com.ibk.pds.common.model.ApiInfo;
import com.ibk.pds.common.model.DocumentInfo;
import com.ibk.pds.common.model.UserInfo;
import com.ibk.pds.common.service.ApiInfoService;
import com.ibk.pds.common.service.DocumentInfoService;
import com.ibk.pds.common.service.UserInfoService;

@Controller
public class CommonDataController {
	//문서상세정보 관련 
	private Logger logger = LoggerFactory.getLogger(CommonDataController.class);
	@Autowired
	UserInfoService userInfoService;
	@Autowired
	DocumentInfoService documentInfoService;
	//상세정보 페이지 
	@RequestMapping(value = "/dataDetailView.do", method = RequestMethod.GET)
	public ModelAndView apidocInfo(ModelAndView mav) {
		logger.info("Doc Test End");
		UserAuthService userAuthService = new UserAuthService();
		UserInfo userInfo = userAuthService.getUserAuthInfo(userInfoService);
		mav.addObject("userInfo",userInfo);
		mav.setViewName("dataDetailView");

		return mav;

	}

	@RequestMapping(value = "/dataDetailList.do", method = RequestMethod.GET)
	public ModelAndView docDetailList(
			@RequestParam(value="docId",required=false) String docId,
			ModelAndView mav) {

		UserAuthService userAuthService = new UserAuthService();
		UserInfo userInfo = userAuthService.getUserAuthInfo(userInfoService);
		List<DocumentInfo> docInfoList;
		if(docId==null) {
			//현재 접속 사용자 정보를 가져와서 
			if("ADMIN".equals(userInfo.getAuthCode())) {
				docInfoList = documentInfoService.getDocInfoList();
				logger.info("현재 대상 문서수 (ADMIN)= "+docInfoList.size());

			}else {
				docInfoList = documentInfoService.getDocInfoListByOwner(userInfo.getUserId());
				logger.info("현재 대상 문서수 = "+docInfoList.size());
			}
		}else {
			docInfoList = documentInfoService.getDocInfobyDocId(docId);
		}

		DocumentInfo docInfo=null;
		int len = docInfoList.size();

		mav.addObject("datalist",docInfoList);
		mav.setViewName("dataDetailList");

		return mav;

	}
}
