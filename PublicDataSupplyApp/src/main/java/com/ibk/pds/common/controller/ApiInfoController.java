package com.ibk.pds.common.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ibk.pds.common.model.ApiInfo;
import com.ibk.pds.common.model.DocumentInfo;
import com.ibk.pds.common.model.UserInfo;
import com.ibk.pds.common.repository.DocumentInfoRepository;
import com.ibk.pds.common.repository.UserInfoRepository;
import com.ibk.pds.common.service.ApiInfoService;
import com.ibk.pds.common.service.DocumentInfoService;
import com.ibk.pds.common.util.DateUtil;

@Controller
public class ApiInfoController {
	@Autowired
	ApiInfoService apiInfoService;

	@Autowired
	DocumentInfoService docInfoService;
	
	private Logger logger = LoggerFactory.getLogger(ApiInfoController.class);
	
	@RequestMapping(value = "/apiinfo.do", method = RequestMethod.GET)
	public ModelAndView apiinfo(ModelAndView mav) {
		mav.setViewName("apiinfo");
		return mav;
	}
	
	@RequestMapping(value = "/api.do", method = RequestMethod.GET)
	public ModelAndView user(ModelAndView mav) {
		logger.info("Api Test");
		List<ApiInfo> apiInfoList = apiInfoService.getApiInfoList();
		ApiInfo apiInfo=null;
		int len = apiInfoList.size();
        for(int i = 0; i<len ; i++){
        	apiInfo = apiInfoList.get(i);
            logger.info(apiInfo.toString());
        }

        mav.addObject("datalist",apiInfoList);
		mav.setViewName("api");
		logger.info("Api Test End");
		
		return mav;
	}
	@RequestMapping(value = "/apilist", method = RequestMethod.GET)
	public ModelAndView apiList(
			@RequestParam("docId") String docId,ModelAndView mav
			) {
		logger.info("Api Test");
		List<ApiInfo> apiInfoList = apiInfoService.getApiInfoList();
		ApiInfo apiInfo=null;
		int len = apiInfoList.size();
        for(int i = 0; i<len ; i++){
        	apiInfo = apiInfoList.get(i);
            logger.info(apiInfo.toString());
        }

        mav.addObject("datalist",apiInfoList);
		mav.setViewName("api");
		logger.info("Api Test End");
		
		return mav;
	}
	
	@RequestMapping(value="/apiedit", method = RequestMethod.POST) 
	public String apiEdit(
			@RequestParam("apiId") String apiId,
			@RequestParam("apiName") String apiName,
			@RequestParam("apiDetailName") String apiDetailName,
			@RequestParam("apiExplanation") String apiExplanation,
			@RequestParam("userId") String userId,
			@RequestParam("docId") String docId,
			@RequestParam("regDate") String regDate
			
			)
	{ 
		String today = DateUtil.getDateYYYYMMDD();
		
		DocumentInfo docInfo = docInfoService.getDocInfobyDocId(docId).get(0);
		
		ApiInfo apiInfo = new ApiInfo(apiId, apiName, apiDetailName, apiExplanation,userId, docId,docInfo.getDocName(), "", "","","N",regDate,today,"","");
		
		//ApiInfo apiInfo = new ApiInfo(apiId, apiName, userId, docId, apiUrl, conditions);
//		UserInfo userInfo = new UserInfo(id,name,dep,auth,null,null);
		logger.info("Api Edit"+apiInfo.toString());
		
		apiInfoService.saveApiInfo(apiInfo);
		logger.info("Api Update End:");
		return "redirect:/api.do"; 
	}
	@RequestMapping(value="/apiadd", method = RequestMethod.POST) 
	public String apiAdd(
		//	@RequestParam("APIID") String apiId,
			@RequestParam("apiName") String apiName,
			@RequestParam("apiDetailName") String apiDetailName,
			@RequestParam("apiExplanation") String apiExplanation,
			@RequestParam("userId") String userId,
			@RequestParam("docId") String docId
			)
	{ 
		String apiId = "A"+DateUtil.getDateYYYYMMDDHHMMSS();	
		DocumentInfo docInfo = docInfoService.getDocInfobyDocId(docId).get(0);
		
		String today = DateUtil.getDateYYYYMMDD();
		ApiInfo apiInfo = new ApiInfo(apiId, apiName, apiDetailName, apiExplanation,userId, docId, docInfo.getDocName(),"", "","","N",today,today,"","");
//		UserInfo userInfo = new UserInfo(id,name,dep,auth,null,null);
		logger.info("Api Add"+apiInfo.toString());
		if(apiInfo.getApiId()==null) {
			logger.info("User Add fail");
			return "error";
		}
		apiInfoService.addApiInfo(apiInfo);
		logger.info("Api Add End:");
		return "redirect:/api.do"; 
	}
	
	@RequestMapping(value="/apidelete", method = RequestMethod.POST) 
	public String apiDelete(
			@RequestParam("apiId") String apiId,
			@RequestParam("apiName") String apiName,
			@RequestParam("apiDetailName") String apiDetailName,
			@RequestParam("apiExplanation") String apiExplanation,
			@RequestParam("userId") String userId,
			@RequestParam("docId") String docId,
			@RequestParam("regDate") String regDate
			)
	{ 
		String today = DateUtil.getDateYYYYMMDD();
		ApiInfo apiInfo = new ApiInfo(apiId, apiName, apiDetailName, apiExplanation,userId, docId, "","", "","","N",regDate,today,"","");
//		UserInfo userInfo = new UserInfo(id,name,dep,auth,null,null);
		
		logger.info("Api Delete:"+apiInfo.toString());
		if(apiInfo.getUserId()==null) {
			logger.info("API Add fail");
			return "error";
		}
		apiInfoService.deleteApiInfo(apiInfo);
		//.deleteUserInfo(userInfo);
	//	userInfoService.addUserInfo(userInfo);
		logger.info("Api Delete End:");
		return "redirect:/api.do"; 
	}
}
