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

import com.ibk.pds.api.model.ATMInfo.ATMInfoResponseSub;
import com.ibk.pds.api.model.BranchInfo.BranchInfoResponseSub;
import com.ibk.pds.api.model.EmploymentInfo.EmploymentInfoAllRequest;
import com.ibk.pds.api.model.EmploymentInfo.EmploymentInfoByIndustryCodeRequest;
import com.ibk.pds.api.model.EmploymentInfo.EmploymentInfoByStdYmAndIndustryRequest;
import com.ibk.pds.api.model.EmploymentInfo.EmploymentInfoByStdYmRequest;
import com.ibk.pds.api.model.EmploymentInfo.EmploymentInfoResponse;
import com.ibk.pds.api.model.EmploymentInfo.EmploymentInfoResponseSub;
import com.ibk.pds.auth.service.AuthService;
import com.ibk.pds.common.util.DateUtil;
import com.ibk.pds.data.model.ATMInfoData;
import com.ibk.pds.data.model.EmploymentInfoData;
import com.ibk.pds.data.model.JobWorldData;
import com.ibk.pds.data.service.EmploymentInfoDataService;
import com.ibk.pds.data.service.JobWorldDataService;
import com.ibk.pds.log.model.LogApiData;
import com.ibk.pds.log.service.LogApiDataService;

//아이원잡 채용공고 통계 조회 서비스 
@RestController
@RequestMapping("/api/employmentInfo")
public class EmploymentInfoController {

	@Autowired
	LogApiDataService logApiDataService;
	private Logger logger = LoggerFactory.getLogger(EmploymentInfoController.class);
	//전처리

	@Autowired
	AuthService authService;
	@Value("${authYN}")
	private String authYN;


	@Autowired
	EmploymentInfoDataService employmentInfoDataService;

	public EmploymentInfoResponseSub convertToResponseSub(EmploymentInfoData data) {

		String stdYm = "";
		String industryName = "";
		String industryCode = "";

		String detailIndustryName = "";
		int careersCount=0;
		String careersPer="";

		stdYm = data.getStdYM();
		industryName = data.getIndustryName();
		industryCode = data.getIndustryCode();
		detailIndustryName = data.getDetailIndustryName();
		careersCount = data.getCareersCount();
		careersPer = data.getCareersPer();

		EmploymentInfoResponseSub responseSub = 
				new EmploymentInfoResponseSub(stdYm, industryName, industryCode, detailIndustryName,careersCount,careersPer);



		return responseSub;
	}



	//전체 검색 
	@RequestMapping(value="/employmentInfoAll",produces="application/xml")
	public  EmploymentInfoResponse viewEmploymentInfoAll(@RequestBody EmploymentInfoAllRequest request) {

		logger.info("EmploymentInfoAllRequest="+request.toString());
		String key = DateUtil.getDateYYYYMMDDHHMMSSMISSS();
		Random generator = new Random();   
		int num= generator.nextInt(100);    
		String logId = key + Integer.toString(num);

		String apiId= "employmentInfoAll";
		String apiUrl=	"/employmentInfo/employmentInfoAll";
		EmploymentInfoResponse response = new EmploymentInfoResponse();

		int result = 0;
		if(authYN.contentEquals("Y"))
			result = authService.auth();

		if(result!=-1) {
			logger.info("인증성공");

			//	ViewCareersResponse response = new ViewCareersResponse();
			List<EmploymentInfoData> list = new ArrayList<EmploymentInfoData>();
			//응답전문의 List
			List<EmploymentInfoResponseSub> responseSubList = new ArrayList<EmploymentInfoResponseSub> ();

			int page = request.getPageNo();
			int size = request.getNumOfRows();


			logger.info("Paging:"+page+",size="+size);

			if(size==0) {
				size=10;
				logger.info("Paging:"+page+",size="+size);
			}
			Pageable paging = PageRequest.of(page, size);
			list = employmentInfoDataService.findAll(paging);

			logger.info("DB Result:"+list.size());
			for(EmploymentInfoData data : list) {
				EmploymentInfoResponseSub responseSub = convertToResponseSub(data);				
				responseSubList.add(responseSub);
			}

			for(EmploymentInfoResponseSub resSub : responseSubList) {
				logger.info("select Result="+resSub.toString());
			}
			response.setItem(responseSubList);
			logger.info("인증수행 여부 ="+authYN);
			//추후 apiInfo 조회를 통해서 처리 
			String apiName = "아이원잡채용정보월별조회";
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
			String apiName = "아이원잡채용정보월별조회";
			String action = "CALL";
			String statusCode ="1111";//코드 확인필요 
			String requestMessage = request.toString();
			String responseMessage = response.toString();
			String trxDate = DateUtil.getDateYYYY_MM_DDHHMMSSMISSS();


			LogApiData logApiData = new LogApiData(logId,apiId,apiName,apiUrl,action,statusCode,requestMessage,responseMessage,trxDate);

		}

		return response;			
	}
	
	
	//산업분야별 공고 정보 
	@RequestMapping(value="/employmentInfoByIndustryCode",produces="application/xml")
	public  EmploymentInfoResponse viewEmploymentInfoByIndustryCode(@RequestBody EmploymentInfoByIndustryCodeRequest request) {

		logger.info("EmploymentInfoByIndustryCodeRequest="+request.toString());
		String key = DateUtil.getDateYYYYMMDDHHMMSSMISSS();
		Random generator = new Random();   
		int num= generator.nextInt(100);    
		String logId = key + Integer.toString(num);

		String apiId= "employmentInfoByIndustryCode";
		String apiUrl=	"/employmentInfo/employmentInfoByIndustryCode";
		EmploymentInfoResponse response = new EmploymentInfoResponse();

		int result = 0;
		if(authYN.contentEquals("Y"))
			result = authService.auth();

		if(result!=-1) {
			logger.info("인증성공");

			//	ViewCareersResponse response = new ViewCareersResponse();
			List<EmploymentInfoData> list = new ArrayList<EmploymentInfoData>();
			//응답전문의 List
			List<EmploymentInfoResponseSub> responseSubList = new ArrayList<EmploymentInfoResponseSub> ();

			int page = request.getPageNo();
			int size = request.getNumOfRows();


			logger.info("Paging:"+page+",size="+size);

			if(size==0) {
				size=10;
				logger.info("Paging:"+page+",size="+size);
			}
			Pageable paging = PageRequest.of(page, size);
			list = employmentInfoDataService.findByIndustryCode(request.getIndustryCode(), paging);
			
			logger.info("DB Result:"+list.size());
			for(EmploymentInfoData data : list) {
				EmploymentInfoResponseSub responseSub = convertToResponseSub(data);				
				responseSubList.add(responseSub);
			}

			for(EmploymentInfoResponseSub resSub : responseSubList) {
				logger.info("select Result="+resSub.toString());
			}
			response.setItem(responseSubList);
			logger.info("인증수행 여부 ="+authYN);
			//추후 apiInfo 조회를 통해서 처리 
			String apiName = "아이원잡채용정보월별조회";
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
			String apiName = "아이원잡채용정보월별조회";
			String action = "CALL";
			String statusCode ="1111";//코드 확인필요 
			String requestMessage = request.toString();
			String responseMessage = response.toString();
			String trxDate = DateUtil.getDateYYYY_MM_DDHHMMSSMISSS();


			LogApiData logApiData = new LogApiData(logId,apiId,apiName,apiUrl,action,statusCode,requestMessage,responseMessage,trxDate);

		}

		return response;			
	}
	//산업분야별 공고 정보 
	@RequestMapping(value="/employmentInfoByStdYmAndIndustryCode",produces="application/xml")
	public  EmploymentInfoResponse viewEmploymentInfoByStdYmIndustryCode(@RequestBody EmploymentInfoByStdYmAndIndustryRequest request) {

		logger.info("EmploymentInfoByStdYmAndIndustryRequest="+request.toString());
		String key = DateUtil.getDateYYYYMMDDHHMMSSMISSS();
		Random generator = new Random();   
		int num= generator.nextInt(100);    
		String logId = key + Integer.toString(num);

		String apiId= "employmentInfoByIndustryCode";
		String apiUrl=	"/employmentInfo/employmentInfoByIndustryCode";
		EmploymentInfoResponse response = new EmploymentInfoResponse();

		int result = 0;
		if(authYN.contentEquals("Y"))
			result = authService.auth();

		if(result!=-1) {
			logger.info("인증성공");

			//	ViewCareersResponse response = new ViewCareersResponse();
			List<EmploymentInfoData> list = new ArrayList<EmploymentInfoData>();
			//응답전문의 List
			List<EmploymentInfoResponseSub> responseSubList = new ArrayList<EmploymentInfoResponseSub> ();

			int page = request.getPageNo();
			int size = request.getNumOfRows();


			logger.info("Paging:"+page+",size="+size);

			if(size==0) {
				size=10;
				logger.info("Paging:"+page+",size="+size);
			}
			Pageable paging = PageRequest.of(page, size);
			
	//		list = employmentInfoDataService.findByIndustryCode(request.getIndustryCode(), paging);
			list = employmentInfoDataService.findByStdYMAndIndustryCode(request.getStdYm(), request.getIndustryCode(), paging);
					//.findByIndustryCode(request.getIndustryCode(), paging);
			
			logger.info("DB Result:"+list.size());
			for(EmploymentInfoData data : list) {
				EmploymentInfoResponseSub responseSub = convertToResponseSub(data);				
				responseSubList.add(responseSub);
			}

			for(EmploymentInfoResponseSub resSub : responseSubList) {
				logger.info("select Result="+resSub.toString());
			}
			response.setItem(responseSubList);
			logger.info("인증수행 여부 ="+authYN);
			//추후 apiInfo 조회를 통해서 처리 
			String apiName = "아이원잡채용정보월별/산업별 조회";
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
			String apiName = "아이원잡채용정보월별/산업별 조회";
			String action = "CALL";
			String statusCode ="1111";//코드 확인필요 
			String requestMessage = request.toString();
			String responseMessage = response.toString();
			String trxDate = DateUtil.getDateYYYY_MM_DDHHMMSSMISSS();


			LogApiData logApiData = new LogApiData(logId,apiId,apiName,apiUrl,action,statusCode,requestMessage,responseMessage,trxDate);

		}

		return response;			
	}

	@RequestMapping(value="/employmentInfoByStdYm",produces="application/xml")
	public  EmploymentInfoResponse viewEmploymentInfoByStdYm(@RequestBody EmploymentInfoByStdYmRequest request) {

		logger.info("EmploymentInfoByStdYmRequest="+request.toString());
		String key = DateUtil.getDateYYYYMMDDHHMMSSMISSS();
		Random generator = new Random();   
		int num= generator.nextInt(100);    
		String logId = key + Integer.toString(num);

		String apiId= "employmentInfoByStdYm";
		String apiUrl=	"/employmentInfo/employmentInfoByStdYm";
		EmploymentInfoResponse response = new EmploymentInfoResponse();

		int result = 0;
		if(authYN.contentEquals("Y"))
			result = authService.auth();

		if(result!=-1) {
			logger.info("인증성공");

			//	ViewCareersResponse response = new ViewCareersResponse();
			List<EmploymentInfoData> list = new ArrayList<EmploymentInfoData>();
			//응답전문의 List
			List<EmploymentInfoResponseSub> responseSubList = new ArrayList<EmploymentInfoResponseSub> ();

			int page = request.getPageNo();
			int size = request.getNumOfRows();


			logger.info("Paging:"+page+",size="+size);

			if(size==0) {
				size=10;
				logger.info("Paging:"+page+",size="+size);
			}
			Pageable paging = PageRequest.of(page, size);
			
	//		list = employmentInfoDataService.findByIndustryCode(request.getIndustryCode(), paging);
			list = employmentInfoDataService.findByStdDatePaging(request.getStdYm(), paging);
					//.findByIndustryCode(request.getIndustryCode(), paging);
			
			logger.info("DB Result:"+list.size());
			for(EmploymentInfoData data : list) {
				EmploymentInfoResponseSub responseSub = convertToResponseSub(data);				
				responseSubList.add(responseSub);
			}

			for(EmploymentInfoResponseSub resSub : responseSubList) {
				logger.info("select Result="+resSub.toString());
			}
			response.setItem(responseSubList);
			logger.info("인증수행 여부 ="+authYN);
			//추후 apiInfo 조회를 통해서 처리 
			String apiName = "아이원잡채용정보월별 조회";
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
			String apiName = "아이원잡채용정보월별  조회";
			String action = "CALL";
			String statusCode ="1111";//코드 확인필요 
			String requestMessage = request.toString();
			String responseMessage = response.toString();
			String trxDate = DateUtil.getDateYYYY_MM_DDHHMMSSMISSS();


			LogApiData logApiData = new LogApiData(logId,apiId,apiName,apiUrl,action,statusCode,requestMessage,responseMessage,trxDate);

		}

		return response;			
	}



}
