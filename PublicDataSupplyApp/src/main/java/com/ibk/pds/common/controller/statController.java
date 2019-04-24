package com.ibk.pds.common.controller;

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

@Controller
public class statController {

	@Autowired
	DocumentStatusService documentStatusService;
	//UserInfoRepository userInfoRepository;

	@Autowired
	JobWorldDataService jobWorldDataService;

	
	private Logger logger = LoggerFactory.getLogger(statController.class);

	@RequestMapping(value = "/stat.do", method = RequestMethod.GET)
	public ModelAndView upload(ModelAndView mav) {
		logger.info("Doc Approval Test");
		List<DocumentStatus> docStatusList = documentStatusService.getDocStatusList();
		DocumentStatus docStatus = null;
		int len = docStatusList.size();
		for(int i = 0; i<len ; i++){
			docStatus = docStatusList.get(i);

			logger.info("docStatus OUTPUT"+docStatus.toString());
		}

		mav.addObject("doclist",docStatusList);


		logger.info("jobWorld Test");
		List<JobWorldData> jobWorldDataList = jobWorldDataService.getJobWorldDataList();
		//.getApiInfoList();
		JobWorldData jobWorldData=null;
		len = jobWorldDataList.size();
		for(int i = 0; i<len ; i++){
			jobWorldData = jobWorldDataList.get(i);
			logger.info(jobWorldData.toString());
		}

		mav.addObject("datalist",jobWorldDataList);
		//	mav.setViewName("jobworld");
		logger.info("Api Test End");
		//mav.setViewName("approval");
		logger.info("Doc Approval Test End");

		//return mav;
		
		mav.setViewName("stat");
		return mav; 
		
	}

}
