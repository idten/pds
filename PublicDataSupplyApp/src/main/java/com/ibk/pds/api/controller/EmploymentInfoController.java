package com.ibk.pds.api.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibk.pds.api.model.EmploymentInfo.ViewCareersDetailRequest;
import com.ibk.pds.api.model.EmploymentInfo.ViewCareersRequest;
import com.ibk.pds.api.model.EmploymentInfo.ViewCareersResponse;
import com.ibk.pds.api.model.EmploymentInfo.ViewCareersResponseSub;
import com.ibk.pds.auth.service.AuthService;
import com.ibk.pds.common.util.DateUtil;
import com.ibk.pds.data.model.JobWorldData;
import com.ibk.pds.data.service.JobWorldDataService;
import com.ibk.pds.log.model.LogApiData;
import com.ibk.pds.log.service.LogApiDataService;

//아이원잡 채용공고 통계 조회 서비스 
@RestController
@RequestMapping("/employmentInfo")
public class EmploymentInfoController {

	@Autowired
	LogApiDataService logApiDataService;
	private Logger logger = LoggerFactory.getLogger(EmploymentInfoController.class);
	//전처리

	@Autowired
	AuthService authService;
	@Value("${authYN}")
	private String authYN;

	//필터
	@Autowired
	JobWorldDataService jobWorldDataService;

	//검색기준: stdYm  년월 
	@RequestMapping(value="/viewCareersStatisticsList",produces="application/xml")
	public  ViewCareersResponse viewCareersStatisticsList(@RequestBody ViewCareersRequest request) {

		logger.info("JobWorldRequest="+request.toString());
		String key = DateUtil.getDateYYYYMMDDHHMMSSMISSS();
		Random generator = new Random();   
		int num= generator.nextInt(100);    
		String logId = key + Integer.toString(num);

		String apiId= "viewCareersStatisticsList";
		String apiUrl=	"/employmentInfo/viewCareersStatisticsList";
		ViewCareersResponse response = new ViewCareersResponse();

		int result = 0;
		if(authYN.contentEquals("Y"))
			result = authService.auth();

		if(result!=-1) {
			logger.info("인증성공");

			//	ViewCareersResponse response = new ViewCareersResponse();
			List<JobWorldData> list = new ArrayList<JobWorldData>();
			//응답전문의 List
			List<ViewCareersResponseSub> responseSubList = new ArrayList<ViewCareersResponseSub> ();

			int page = request.getPageNo();
			int size = request.getNumOfRows();


			logger.info("Paging:"+page+",size="+size);

			if(size==0) {
				size=10;
				logger.info("Paging:"+page+",size="+size);
			}
			Pageable paging = PageRequest.of(page, size);
			list = jobWorldDataService.findByStdDatePaging(request.getStdYm(),paging);

			//list = jobWorldDataService.findByStdDatePaging(request.getStdYm(),);

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
						new ViewCareersResponseSub(stdYm, industryName, industryCode, detailIndustryName,careersCount,careersPer);
				responseSubList.add(responseSub);
			}

			for(ViewCareersResponseSub resSub : responseSubList) {
				logger.info("select Result="+resSub.toString());

			}
			response.setItem(responseSubList);
			logger.info("인증수행 여부 ="+authYN);
			//추후 apiInfo 조회를 통해서 처리 
			String apiName = "잡월드채용정보월별조회";
			String action = "CALL";
			String statusCode ="0000";
			String requestMessage = request.toString();
			String responseMessage = response.toString();
			String trxDate = DateUtil.getDateYYYY_MM_DDHHMMSSMISSS();


			LogApiData logApiData = new LogApiData(logId,apiId,apiName,apiUrl,action,statusCode,requestMessage,responseMessage,trxDate);
			logApiDataService.saveApiData(logApiData);
			//.saveLogApiData(logApiData);
		}else {


			//추후 apiInfo 조회를 통해서 처리 
			String apiName = "잡월드채용정보월별조회";
			String action = "CALL";
			String statusCode ="1111";//코드 확인필요 
			String requestMessage = request.toString();
			String responseMessage = response.toString();
			String trxDate = DateUtil.getDateYYYY_MM_DDHHMMSSMISSS();


			LogApiData logApiData = new LogApiData(logId,apiId,apiName,apiUrl,action,statusCode,requestMessage,responseMessage,trxDate);

		}

		return response;			
	}

	//검색기준: stdYm  년월 
	@RequestMapping(value="/viewCareersStatisticsDetailList",produces="application/xml")
	public  ViewCareersResponse viewCareersStatisticsDetailList(@RequestBody ViewCareersDetailRequest request) {
		logger.info("JobWorldRequest="+request.toString());
		String key = DateUtil.getDateYYYYMMDDHHMMSSMISSS();
		Random generator = new Random();   
		int num= generator.nextInt(100);    

		String logId = key + Integer.toString(num);
		String apiId= "A20190423013000";
		String apiUrl=	"/EmploymentInfo/viewCareersStatisticsDetailList";
		ViewCareersResponse response = new ViewCareersResponse();

		int result = 0;
		if(authYN.contentEquals("Y"))
			result = authService.auth();

		if(result!=-1) {
			logger.info("JobWorldRequest="+request.toString());
			//		logger.info("job="+jobWorldRequest);			
			//ViewCareersResponse response = new ViewCareersResponse();
			//JobWorldResponse jobWorldResponse = new JobWorldResponse();
			List<JobWorldData> list = new ArrayList<JobWorldData>();
			//응답전문의 List
			List<ViewCareersResponseSub> responseSubList = new ArrayList<ViewCareersResponseSub> ();
			int page = request.getPageNo();
			int size = request.getNumOfRows();


			logger.info("Paging:"+page+",size="+size);

			if(size==0) {
				size=10;
				logger.info("Paging:"+page+",size="+size);
			}
			Pageable paging = PageRequest.of(page, size);
			//	jobWorldDataService.findByStdDatePaging(stdDate, page);
			//JobWorldData List를 ViewCareersRequestSub List로 변환 
			//list = jobWorldDataService.findByStdDatePaging(request.getStdYm(),paging);

			//JobWorldData List를 ViewCareersRequestSub List로 변환 
			list = jobWorldDataService.findByStdYMAndIndustryCode(request.getStdYm(),request.getIndustryCode(),paging);


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



			//추후 apiInfo 조회를 통해서 처리 
			String apiName = "잡월드채용정산업별조회";
			String action = "CALL";
			String statusCode ="0000";
			String requestMessage = request.toString();
			String responseMessage = response.toString();
			String trxDate = DateUtil.getDateYYYY_MM_DDHHMMSSMISSS();

			LogApiData logApiData = new LogApiData(logId,apiId,apiName,apiUrl,action,statusCode,requestMessage,responseMessage,trxDate);
			logApiDataService.saveApiData(logApiData);
			//.saveLogApiData(logApiData);
		}else {
			String apiName = "잡월드채용정산업별조회";
			String action = "CALL";
			String statusCode ="1111";
			String requestMessage = request.toString();
			String responseMessage = response.toString();
			String trxDate = DateUtil.getDateYYYY_MM_DDHHMMSSMISSS();

			LogApiData logApiData = new LogApiData(logId,apiId,apiName,apiUrl,action,statusCode,requestMessage,responseMessage,trxDate);
			logApiDataService.saveApiData(logApiData);

		}
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
