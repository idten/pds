package com.ibk.pds.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ibk.pds.api.model.JobWorldRequest;
import com.ibk.pds.api.model.JobWorldResponse;
import com.ibk.pds.api.model.Test;
import com.ibk.pds.common.util.DateUtil;
import com.ibk.pds.data.controller.JobWorldDataController;
import com.ibk.pds.data.model.JobWorldData;
import com.ibk.pds.data.service.JobWorldDataService;
import com.ibk.pds.log.model.LogApiData;
import com.ibk.pds.log.model.LogDocData;
import com.ibk.pds.log.service.LogApiDataService;

@RestController
@RequestMapping("/api")
public class ApiController {

	@Autowired
	LogApiDataService logApiDataService;
	private Logger logger = LoggerFactory.getLogger(ApiController.class);
	//전처리 
	//필터
	@Autowired
	JobWorldDataService jobWorldDataService;


	@RequestMapping(value="/jobworld",produces="application/xml")
	public  JobWorldResponse restJson(@RequestBody JobWorldRequest jobWorldRequest) {
		logger.info("JobWorldRequest="+jobWorldRequest.toString());
		logger.info("jobWorldRequest="+jobWorldRequest.getStdYm());


		logger.info("job="+jobWorldRequest);			
		JobWorldResponse jobWorldResponse = new JobWorldResponse();

		List<JobWorldData> list = new ArrayList<JobWorldData>();
		list = jobWorldDataService.findByStdDate(jobWorldRequest.getStdYm());

		jobWorldResponse.setListJobWorldData(list);
		logger.info("job="+jobWorldResponse);			

		// api 로그도 추가
		String key = DateUtil.getDateYYYYMMDDHHMMSSMISSS();
		Random generator = new Random();   
		int num= generator.nextInt(100);    

		String logId = key + Integer.toString(num);
		String apiId="APIID";
		String apiName = "IONEJOB";
		String apiUrl = "test";
		String action = "호출 ";
		String statusCode ="0000";
		String request = "<xml>요청 전문<xml>";
		String response = "<xml>응답 전문<xml>";
		String trxDate = key;
//		public LogApiData(
//				String logId,
//				String apiId, 
//				String apiName,
//				String action,
//				String statusCode,
//				String request, 
//				String response,
//				String trxDate) 

		LogApiData logApiData = new LogApiData(logId,apiId,apiName,apiUrl, action,statusCode,request,response,trxDate);
		logApiDataService.saveApiData(logApiData);
		//.saveLogApiData(logApiData);
		
		
		
		return jobWorldResponse;			
	}
//
//
//	@RequestMapping(value="/ionejob",produces="application/xml")
//	public  JobWorldResponse restJson(@RequestBody JobWorldRequest jobWorldRequest) {
//		logger.info("JobWorldRequest="+jobWorldRequest.toString());
//		logger.info("jobWorldRequest="+jobWorldRequest.getStdYYYYmm());
//
//
//		logger.info("job="+jobWorldRequest);			
//		JobWorldResponse jobWorldResponse = new JobWorldResponse();
//
//		List<JobWorldData> list = new ArrayList<JobWorldData>();
//		list = jobWorldDataService.findByStdDate(jobWorldRequest.getStdYYYYmm());
//
//		jobWorldResponse.setListJobWorldData(list);
//		logger.info("job="+jobWorldResponse);			
//
//		// api 로그도 추가
//		String key = DateUtil.getDateYYYYMMDDHHMMSSMISSS();
//		Random generator = new Random();   
//		int num= generator.nextInt(100);    
//
//		String logId = key + Integer.toString(num);
//		String apiId="APIID";
//		String apiName = "IONEJOB";
//		String action = "호출 ";
//		String statusCode ="0000";
//		String request = "<xml>요청 전문<xml>";
//		String response = "<xml>응답 전문<xml>";
//		String trxDate = key;
////		public LogApiData(
////				String logId,
////				String apiId, 
////				String apiName,
////				String action,
////				String statusCode,
////				String request, 
////				String response,
////				String trxDate) 
//
//		LogApiData logApiData = new LogApiData(logId,apiId,apiName,action,statusCode,request,response,trxDate);
//		logApiDataService.saveApiData(logApiData);
//		//.saveLogApiData(logApiData);
//		
//		
//		
//		return jobWorldResponse;			
//	}



	//list = jobWorldDataService.findByStdDate("20190319");

	//	list =jobWorldDataService.getJobWorldDataList();
	//	jobWorldDataService.getJobWorldDataList();
	//List<Test> list = new ArrayList<Test>();
	//Test t1  = new Test("1","2");
	//JobWorldData jobWorldData = new JobWorldData("1","2","3","4","5","6");
	//list.add(jobWorldData);
	//list.add(jobWorldData);



}
