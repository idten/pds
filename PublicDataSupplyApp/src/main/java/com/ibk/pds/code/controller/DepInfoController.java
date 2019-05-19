package com.ibk.pds.code.controller;

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
import com.ibk.pds.code.service.DepInfoService;
import com.ibk.pds.common.controller.UserInfoController;
import com.ibk.pds.common.model.UserInfo;
import com.ibk.pds.common.service.UserInfoService;

@Controller
public class DepInfoController {
	@Autowired
	DepInfoService depInfoService;
	//UserInfoRepository userInfoRepository;
	@Autowired
	UserInfoService userInfoService;
	
		
	//@Autowired
	//DocumentInfoRepository documentInfoRepository;

	private Logger logger = LoggerFactory.getLogger(DepInfoController.class);
	

	@RequestMapping(value = "/depinfo.do", method = RequestMethod.GET)
	public ModelAndView depCode(ModelAndView mav) {
		UserAuthService userAuthService = new UserAuthService();
		UserInfo userInfo = userAuthService.getUserAuthInfo(userInfoService);
		
		logger.info("DepCode Init");
		List<DepInfo> depCodeList = depInfoService.getDepCodeList();
		Map<String,String> depCodeMap = depInfoService.getDepCodeMap();
		mav.addObject("userInfo",userInfo);
		mav.addObject("deplist",depCodeList);
		mav.addObject("depMap", depCodeMap);
		mav.setViewName("depinfo");
		return mav;
	}
	
	@RequestMapping(value="/depedit", method = RequestMethod.POST) 
	public String userEdit(@RequestParam("DEPCODE") String depCode,
			@RequestParam("DEPNAME") String depName)
	{ 
		DepInfo depInfo = new DepInfo(depCode,depName);
		logger.info("depInfo Edit"+depInfo.toString());
		depInfoService.saveDepInfo(depInfo);
		//.saveUserInfo(userInfo);
		logger.info("Update DepInfo End:");
		return "redirect:/depinfo.do"; 
	}
	@RequestMapping(value="/depadd", method = RequestMethod.POST) 
	public String depAdd(@RequestParam("DEPCODE") String depCode,
			@RequestParam("DEPNAME") String depName)
	{ 
		DepInfo depInfo = new DepInfo(depCode,depName);		
		logger.info("DepInfo Add:"+depInfo.toString());
		if(depInfo.getDepCode()==null) {
			logger.info("DepInfo Add fail");
			return "error";
		}
		depInfoService.addDepInfo(depInfo);//.addUserInfo(userInfo);
		logger.info("depInfo Add End:");
		return "redirect:/depinfo.do"; 
	}

	@RequestMapping(value="/depdelete", method = RequestMethod.POST) 
	public String depDelete(@RequestParam("DEPCODE") String depCode,
			@RequestParam("DEPNAME") String depName)
	{ 
		DepInfo depInfo = new DepInfo(depCode,depName);		

//		UserInfo userInfo = new UserInfo(id,name,dep,auth,null,null);
		
		logger.info("Dep Delete:"+depInfo.toString());
		if(depInfo.getDepCode()==null) {
			logger.info("DepInfo delete fail");
			return "error";
		}
		depInfoService.deleteUserInfo(depInfo);//.deleteUserInfo(userInfo);
	//	userInfoService.addUserInfo(userInfo);
		logger.info("depInfo Delete End:");
		return "redirect:/depinfo.do"; 
	}
}
