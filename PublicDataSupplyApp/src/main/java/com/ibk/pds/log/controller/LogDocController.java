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
import com.ibk.pds.data.model.JobWorldData;
import com.ibk.pds.data.service.JobWorldDataService;
import com.ibk.pds.log.model.LogApiData;
import com.ibk.pds.log.model.LogDocData;
import com.ibk.pds.log.service.LogApiDataService;
import com.ibk.pds.log.service.LogDocDataService;

@Controller
public class LogDocController {


	@Autowired
	LogDocDataService logDocDataService;
	//UserInfoRepository userInfoRepository;

	@Autowired
	JobWorldDataService jobWorldDataService;

	
	private Logger logger = LoggerFactory.getLogger(LogDocController.class);

	@RequestMapping(value = "/logDoc.do", method = RequestMethod.GET)
	public ModelAndView logDoc(ModelAndView mav) {
		logger.info("Doc Doc Test");
		List<LogDocData> logDocDataList = logDocDataService.getLogDocDataList();
				//getDocStatusList();
		LogDocData logDocData = null;
		
		
		int len = logDocDataList.size();
		for(int i = 0; i<len ; i++){
			logDocData = logDocDataList.get(i);

			logger.info("logDocData OUTPUT"+logDocData.toString());
		}

		mav.addObject("logDoclist",logDocDataList);


		logger.info("logDoc Test");
		//return mav;
		
		mav.setViewName("logDoc");
		return mav;
	}

}
