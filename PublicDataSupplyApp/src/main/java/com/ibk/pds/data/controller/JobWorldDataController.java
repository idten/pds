package com.ibk.pds.data.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ibk.pds.common.controller.ApiInfoController;
import com.ibk.pds.common.model.ApiInfo;
import com.ibk.pds.common.service.ApiInfoService;
import com.ibk.pds.data.model.JobWorldData;
import com.ibk.pds.data.service.JobWorldDataService;
@Controller
public class JobWorldDataController {
	//
//	public class ApiInfoController {
		@Autowired
		JobWorldDataService jobWorldDataService;
		private Logger logger = LoggerFactory.getLogger(JobWorldDataController.class);
		
		@RequestMapping(value = "/jobworld.do", method = RequestMethod.GET)
		public ModelAndView user(ModelAndView mav) {
			logger.info("jobWorld Test");
			List<JobWorldData> jobWorldDataList = jobWorldDataService.getJobWorldDataList();
			//.getApiInfoList();
			JobWorldData jobWorldData=null;
			int len = jobWorldDataList.size();
	        for(int i = 0; i<len ; i++){
	        	jobWorldData = jobWorldDataList.get(i);
	            logger.info(jobWorldData.toString());
	        }

	        mav.addObject("datalist",jobWorldDataList);
			mav.setViewName("jobworld");
			logger.info("Api Test End");
			
			return mav;
		}
		
}
