package com.ibk.pds.api.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibk.pds.api.model.EmploymentInfo.ViewCareersDetailRequest;
import com.ibk.pds.api.model.EmploymentInfo.ViewCareersRequest;
import com.ibk.pds.api.model.EmploymentInfo.ViewCareersResponse;
import com.ibk.pds.api.model.EmploymentInfo.ViewCareersResponseSub;
import com.ibk.pds.common.util.DateUtil;
import com.ibk.pds.data.model.JobWorldData;
import com.ibk.pds.data.service.JobWorldDataService;
import com.ibk.pds.log.model.LogApiData;
import com.ibk.pds.log.service.LogApiDataService;

//아이원잡 채용공고 통계 조회 서비스 
@RestController
@RequestMapping("/EmploymentInfo")
public class EmploymentInfoController {

	@Autowired
	LogApiDataService logApiDataService;
	private Logger logger = LoggerFactory.getLogger(EmploymentInfoController.class);
	//전처리 
	//필터
	@Autowired
	JobWorldDataService jobWorldDataService;

	//검색기준: stdYm  년월 
	@RequestMapping(value="/viewCareersStatisticsList",produces="application/xml")
	public  ViewCareersResponse viewCareersStatisticsList(@RequestBody ViewCareersRequest request) {
		logger.info("JobWorldRequest="+request.toString());
//		logger.info("job="+jobWorldRequest);			
		ViewCareersResponse response = new ViewCareersResponse();
		//JobWorldResponse jobWorldResponse = new JobWorldResponse();

		
		List<JobWorldData> list = new ArrayList<JobWorldData>();
		
		
		//응답전문의 List
		List<ViewCareersResponseSub> responseSubList = new ArrayList<ViewCareersResponseSub> ();
		
		//JobWorldData List를 ViewCareersRequestSub List로 변환 
		list = jobWorldDataService.findByStdDate(request.getStdYm());
		
		
		
		//ViewCareersResponseSub responseSub = new ViewCareersResponseSub();
		
		//JobWorldData jobworldData = new JobWorldData (null, null, null, null, null, null, 0, null, null, null, null);
		String stdYm = "";
		String industryName = "";
		String industryCode = "";
		
		String detailIndustryName = "";
		int careersCount=0;
		String careersPer="";
		
		logger.info("DB Result:"+list.size());
		for(JobWorldData jobworldData : list) {
			stdYm = jobworldData.getStdYM();
			industryName = jobworldData.getIndustryName();
			industryCode = jobworldData.getIndustryCode();
			detailIndustryName = jobworldData.getDetailIndustryName();
			careersCount = jobworldData.getCareersCount();
			careersPer = jobworldData.getCareersPer();
			
			ViewCareersResponseSub responseSub = 
					new ViewCareersResponseSub(stdYm, industryName, industryCode, detailIndustryName,
							careersCount,careersPer);
			responseSubList.add(responseSub);
		}
		
		for(ViewCareersResponseSub resSub : responseSubList) {
			logger.info("select Result="+resSub.toString());
		
		}
		response.setItem(responseSubList);
		
		String key = DateUtil.getDateYYYYMMDDHHMMSSMISSS();
		Random generator = new Random();   
		int num= generator.nextInt(100);    

		String logId = key + Integer.toString(num);
		String apiId="APIID";
		String apiName = "IONEJOB";
		String action = "호출 ";
		String statusCode ="0000";
		String requestMessage = "<xml>요청 전문<xml>";
		String responseMessage = "<xml>응답 전문<xml>";
		String trxDate = key;

		LogApiData logApiData = new LogApiData(logId,apiId,apiName,action,statusCode,requestMessage,responseMessage,trxDate);
		logApiDataService.saveApiData(logApiData);
		//.saveLogApiData(logApiData);
		
		
		
		return response;			
	}
	
	//검색기준: stdYm  년월 
	@RequestMapping(value="/viewCareersStatisticsDetailList",produces="application/xml")
	public  ViewCareersResponse viewCareersStatisticsDetailList(@RequestBody ViewCareersDetailRequest request) {
		logger.info("JobWorldRequest="+request.toString());
//		logger.info("job="+jobWorldRequest);			
		ViewCareersResponse response = new ViewCareersResponse();
		//JobWorldResponse jobWorldResponse = new JobWorldResponse();

		
		List<JobWorldData> list = new ArrayList<JobWorldData>();
		
		
		//응답전문의 List
		List<ViewCareersResponseSub> responseSubList = new ArrayList<ViewCareersResponseSub> ();
		
		//JobWorldData List를 ViewCareersRequestSub List로 변환 
		list = jobWorldDataService.findByStdYMAndIndustryCode(request.getStdYm(),request.getIndustryCode());
		
		
		
		//ViewCareersResponseSub responseSub = new ViewCareersResponseSub();
		
		//JobWorldData jobworldData = new JobWorldData (null, null, null, null, null, null, 0, null, null, null, null);
		String stdYm = "";
		String industryName = "";
		String industryCode = "";
		
		String detailIndustryName = "";
		int careersCount=0;
		String careersPer="";
		
		logger.info("DB Result:"+list.size());
		for(JobWorldData jobworldData : list) {
			stdYm = jobworldData.getStdYM();
			industryName = jobworldData.getIndustryName();
			industryCode = jobworldData.getIndustryCode();
			detailIndustryName = jobworldData.getDetailIndustryName();
			careersCount = jobworldData.getCareersCount();
			careersPer = jobworldData.getCareersPer();
			
			ViewCareersResponseSub responseSub = 
					new ViewCareersResponseSub(stdYm, industryName, industryCode, detailIndustryName,
							careersCount,careersPer);
			responseSubList.add(responseSub);
		}
		
		for(ViewCareersResponseSub resSub : responseSubList) {
			logger.info("select Result="+resSub.toString());
		
		}
		response.setItem(responseSubList);
		
		String key = DateUtil.getDateYYYYMMDDHHMMSSMISSS();
		Random generator = new Random();   
		int num= generator.nextInt(100);    

		String logId = key + Integer.toString(num);
		String apiId="APIID";
		String apiName = "IONEJOB";
		String action = "호출 ";
		String statusCode ="0000";
		String requestMessage = "<xml>요청 전문<xml>";
		String responseMessage = "<xml>응답 전문<xml>";
		String trxDate = key;

		LogApiData logApiData = new LogApiData(logId,apiId,apiName,action,statusCode,requestMessage,responseMessage,trxDate);
		logApiDataService.saveApiData(logApiData);
		//.saveLogApiData(logApiData);
		
		
		
		return response;			
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
