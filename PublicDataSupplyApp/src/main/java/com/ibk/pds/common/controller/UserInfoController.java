package com.ibk.pds.common.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ibk.pds.code.model.DepInfo;
import com.ibk.pds.code.service.DepInfoService;
import com.ibk.pds.common.model.UserInfo;
import com.ibk.pds.common.repository.DocumentInfoRepository;
import com.ibk.pds.common.repository.UserInfoRepository;
import com.ibk.pds.common.service.UserInfoService;
import com.ibk.pds.common.util.DateUtil;

@Controller
public class UserInfoController {
	@Autowired
	UserInfoService userInfoService;
	//UserInfoRepository userInfoRepository;
	
	@Autowired
	DepInfoService depInfoService;
	
	//@Autowired
	//DocumentInfoRepository documentInfoRepository;

	private Logger logger = LoggerFactory.getLogger(UserInfoController.class);
	

	@RequestMapping(value = "/user.do", method = RequestMethod.GET)
	public ModelAndView user(ModelAndView mav,HttpServletRequest httpServletRequest) {
		                                                         //remoteUser
		logger.info("User Test"+httpServletRequest.getRemoteUser());
			//	+ "//.getPar.getParameter("remoteUser"));
		List<UserInfo> userInfoList = userInfoService.getUserInfoList();
		List<DepInfo> depCodeList = depInfoService.getDepCodeList();
		Map<String,String> depCodeMap = depInfoService.getDepCodeMap();
		
		
//		UserInfo userInfo=null;
//		int len = userInfoList.size();
//        for(int i = 0; i<len ; i++){
//        	userInfo = userInfoList.get(i);
//            logger.info(userInfo.getUserId()+":"+userInfo.getUserName());
//        }
		mav.addObject("depMap", depCodeMap);
		mav.addObject("deplist",depCodeList);
        mav.addObject("datalist",userInfoList);
		mav.setViewName("user");
		logger.info("User Test End");
		
		return mav;
	}
	@RequestMapping(value="/useradd", method = RequestMethod.POST) 
	public String userAdd(@RequestParam("ID") String id,
			@RequestParam("NAME") String name,
			@RequestParam("DEP") String depCode,
			@RequestParam("AUTH") String auth
			)
	{ 
		String today = DateUtil.getDateYYYYMMDD();
		//	String docId = "D"+DateUtil.getDateYYYYMMDDHHMMSS();
		String regDate = today;
		String updateDate = today;
		//public UserInfo(String userId, String userName, String depCode,String depName, String authCode,String regDate, String updateDate) {
		String depName = depInfoService.getByDepCode(depCode).getDepName();
		UserInfo userInfo = new UserInfo(id,name,depCode,depName,auth,regDate,updateDate);
		
		logger.info("User Add:"+userInfo.toString());
		if(userInfo.getUserId()==null) {
			logger.info("User Add fail");
			return "error";
		}
		userInfoService.addUserInfo(userInfo);
		logger.info("User Add End:");
		return "redirect:/user.do"; 
	}
	
	@RequestMapping(value="/useredit", method = RequestMethod.POST) 
	public String userEdit(@RequestParam("ID") String id,
			@RequestParam("NAME") String name,
			@RequestParam("DEP") String depCode,
			@RequestParam("AUTH") String auth,
			@RequestParam("REGDATE") String regDate
			)
	{ 
		//public UserInfo(String userId, String userName, String depCode,String depName, String authCode,String regDate, String updateDate) {
		String today = DateUtil.getDateYYYYMMDD();
		String depName = depInfoService.getByDepCode(depCode).getDepName();
		UserInfo userInfo = new UserInfo(id,name,depCode,depName,auth,regDate,today);
		
		//UserInfo userInfo = new UserInfo(id,name,depCode, depName,auth,null,today);
		logger.info("User Edit"+userInfo.toString());
		userInfoService.saveUserInfo(userInfo);
		logger.info("Update End:");
		return "redirect:/user.do"; 
	}
	

	@RequestMapping(value="/userdelete", method = RequestMethod.POST) 
	public String userDelete(@RequestParam("ID") String id,
			@RequestParam("NAME") String name,
			@RequestParam("DEP") String depCode,
			@RequestParam("AUTH") String auth
			)
	{ 
		UserInfo userInfo = new UserInfo(id,name,depCode,"",auth,"","");
		
		logger.info("User Delete:"+userInfo.toString());
		if(userInfo.getUserId()==null) {
			logger.info("User Add fail");
			return "error";
		}
		userInfoService.deleteUserInfo(userInfo);
	//	userInfoService.addUserInfo(userInfo);
		logger.info("User Delete End:");
		return "redirect:/user.do"; 
	}

}
