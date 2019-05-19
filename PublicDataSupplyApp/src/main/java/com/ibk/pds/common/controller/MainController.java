package com.ibk.pds.common.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.ibk.pds.auth.service.UserAuthService;
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

@Controller
public class MainController 
{
	@Autowired
	DocumentStatusService documentStatusService;

	@Autowired
	DocumentInfoService documentInfoService;
	//UserInfoRepository userInfoRepository;
	@Autowired
	ApiInfoService apiInfoService;
	@Autowired
	UserInfoService userInfoService;

	private Logger logger = LoggerFactory.getLogger(MainController.class);


	@RequestMapping(value = "/login")
	public ModelAndView loginSecurity(ModelAndView mav) {
		mav.setViewName("login");
		return mav;
	}

	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public ModelAndView login(ModelAndView mav) {
		mav.setViewName("login");
		return mav;
	}
	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public ModelAndView loginPost(ModelAndView mav) {
		System.out.println("====");
		mav.setViewName("login");
		return mav;
	}

	@RequestMapping(value = "/logincheck.do", method = RequestMethod.POST)
	public ModelAndView loginCheck(ModelAndView mav) {
		logger.info("loginCHeck--");
		//ModelAndView mav2 = new ModelAndView();
		//"redirect:/user"; 
		mav.setViewName("redirect:/index2");


		return mav;
	}

	//시작 페이지 처리 최초 대시보드 겸용 
	@RequestMapping(value = "/index.do", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView mav,HttpServletRequest request) {

		UserAuthService userAuthService = new UserAuthService();
		UserInfo userInfo = userAuthService.getUserAuthInfo(userInfoService);

		DocumentInfo docInfo=null;
		List<DocumentInfo> docInfoList = new ArrayList<DocumentInfo>();
		List<DocumentStatus> docStatusList = new ArrayList<DocumentStatus>();
		List<ApiInfo> apiInfoList = new ArrayList<ApiInfo>();
		
		if("ADMIN".equals(userInfo.getAuthCode()))
		{
			docInfoList = documentInfoService.getDocInfoList();
			docStatusList = documentStatusService.getDocStatusList();
			apiInfoList = apiInfoService.getApiInfoList();
		}else {
			docInfoList = documentInfoService.getDocInfoListByOwner(userInfo.getUserId());
			docStatusList = documentStatusService.getDocStatusListByOwner(userInfo.getUserId());
			apiInfoList = apiInfoService.getApiInfoListByUserId(userInfo.getUserId());

		}
		
		mav.addObject("userInfo",userInfo);
		mav.addObject("datalist",docInfoList);	
		mav.addObject("dataStatusList",docStatusList);
		mav.addObject("apilist",apiInfoList);
		mav.setViewName("index");
		logger.info("index Test End");
		return mav;
	}

	@RequestMapping(value = "/doctest", method = RequestMethod.GET)
	public ModelAndView doc(ModelAndView mav) {
		logger.info("Doc Test");
		List<DocumentInfo> docInfoList = documentInfoService.getDocInfoList();
		DocumentInfo docInfo=null;
		int len = docInfoList.size();
		for(int i = 0; i<len ; i++){
			docInfo = docInfoList.get(i);

			logger.info("doc OUTPUT"+docInfo.toString());
		}

		mav.addObject("datalist",docInfoList);
		mav.setViewName("doc");
		logger.info("Doc Test End");

		return new ModelAndView("redirect:/user");
	}

	@RequestMapping(value="/loginPDS", method = RequestMethod.POST) 
	public ModelAndView redirectPost(RedirectAttributes redirectAttr,@RequestParam("id") String id,
			@RequestParam("password") String password

			) 
	{ 
		Map<String, String> map = new HashMap<String,String>();
		System.out.println("id="+id);
		redirectAttr.addFlashAttribute("id", id); 
		return new ModelAndView("redirect:/index"); 
	}
}
