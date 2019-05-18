package com.ibk.pds.log.controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
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

import com.ibk.pds.common.config.ConstantCode;
import com.ibk.pds.common.model.DocumentInfo;
import com.ibk.pds.common.model.DocumentStatus;
import com.ibk.pds.common.model.UserInfo;
import com.ibk.pds.common.repository.DocumentInfoRepository;
import com.ibk.pds.common.repository.UserInfoRepository;
import com.ibk.pds.common.service.DocumentInfoService;
import com.ibk.pds.common.service.DocumentStatusService;
import com.ibk.pds.common.service.UserInfoService;
import com.ibk.pds.log.model.LogApiData;
import com.ibk.pds.log.model.LogDocData;
import com.ibk.pds.log.service.LogApiDataService;
import com.ibk.pds.log.service.LogDocDataService;

@Controller
public class LogApiController {
	@Autowired
	LogApiDataService logApiDataService;
	//UserInfoRepository userInfoRepository;

//	@Autowired
//	JobWorldDataService jobWorldDataService;

	
	private Logger logger = LoggerFactory.getLogger(LogApiController.class);

	@RequestMapping(value = "/logApi.do", method = RequestMethod.GET)
	public ModelAndView logDoc(ModelAndView mav) {
		logger.info("Doc Api List logApi.do");
		List<LogApiData> logApiDataList = logApiDataService.getLogApiDataList();//.getLogDocDataList();
				//getDocStatusList();
		LogApiData logApiData = null;
		
		
		int len = logApiDataList.size();
		for(int i = 0; i<len ; i++){
			logApiData = logApiDataList.get(i);

			logger.info("logApiData List OUTPUT"+logApiData.toString());
		}

		mav.addObject("logApiDataList",logApiDataList);


		logger.info("logApiDataList Test");
		//return mav;
		
		mav.setViewName("logApi");
		return mav;
	}
	
	//1건에 대한 상세 정보 
	@RequestMapping(value = "/apiLogDetail.do", method = RequestMethod.GET)
	public ModelAndView apiLogDetail(
			@RequestParam(value="logId",required=false) String logId,
			ModelAndView mav) {
		LogApiData logApiData = logApiDataService.getLogApiData(logId);
		
		mav.addObject("logApiData",logApiData);
		mav.setViewName("logApiDetail");
		return mav;
	}
		

}
