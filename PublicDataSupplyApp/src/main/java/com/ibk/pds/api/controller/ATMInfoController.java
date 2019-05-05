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
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibk.pds.api.model.ATMInfo.ATMInfoAllRequest;
import com.ibk.pds.api.model.ATMInfo.ATMInfoByNameRequest;
import com.ibk.pds.api.model.ATMInfo.ATMInfoBySectionRequest;
import com.ibk.pds.api.model.ATMInfo.ATMInfoResponse;
import com.ibk.pds.api.model.ATMInfo.ATMInfoResponseSub;
import com.ibk.pds.api.model.BranchInfo.BranchInfoResponseSub;
import com.ibk.pds.auth.service.AuthService;
import com.ibk.pds.common.util.DateUtil;
import com.ibk.pds.data.model.ATMInfoData;
import com.ibk.pds.data.model.BranchInfoData;
import com.ibk.pds.data.model.JobWorldData;
import com.ibk.pds.data.service.ATMInfoDataService;
import com.ibk.pds.data.service.JobWorldDataService;
import com.ibk.pds.log.model.LogApiData;
import com.ibk.pds.log.service.LogApiDataService;

//ATM정보 
//1. ATM정보 전체 리스트
//2. ATM명칭에 따른 조회
//3. 지역코드(ex> 서울02)에 따른 대상 조회 

@RestController
@RequestMapping("/atmInfo")
public class ATMInfoController {

	@Autowired
	LogApiDataService logApiDataService;
	private Logger logger = LoggerFactory.getLogger(ATMInfoController.class);
	//전처리

	@Autowired
	AuthService authService;
	@Value("${authYN}")
	private String authYN;

	//필터
	@Autowired
	ATMInfoDataService atmInfoDataService;




	public ATMInfoResponseSub convertToResponseSub(ATMInfoData data) {
		String atmName;			//ATM명
		String atmDivision;  	//ATM구분  
		String startTime;		//시작시간
		String endTime;			//종료시간
		String atmAddress;		//주소
		String atmSection;		//지역구분
		String atmSectionCode;	//지역구분 코드 
		atmName = data.getAtmName();
		atmDivision = data.getAtmDivision();
		startTime = data.getStartTime() ;
		endTime = data.getEndTime();
		atmAddress = data.getAtmAddress();
		atmSection = data.getAtmSection();
		atmSectionCode = data.getAtmSectionCode();

		ATMInfoResponseSub responseSub = new ATMInfoResponseSub(atmName, atmDivision, 
				startTime, endTime,
				atmAddress,
				atmSection, atmSectionCode
				);


		return responseSub;
	}

	//검색기준: atmAll
	//전체 목록 
	@RequestMapping(value="/atmInfoAll",produces="application/xml")
	public  ATMInfoResponse viewATMInfoAll(@RequestBody ATMInfoAllRequest request) {

		logger.info("ATMInfoAll="+request.toString());
		String key = DateUtil.getDateYYYYMMDDHHMMSSMISSS();
		Random generator = new Random();   
		int num= generator.nextInt(100);    
		String logId = key + Integer.toString(num);

		String apiId= "atmInfoByName";
		String apiUrl=	"/atmInfo/atmInfoByName";
		ATMInfoResponse response = new ATMInfoResponse();

		int result = 0;
		if(authYN.contentEquals("Y"))
			result = authService.auth();

		if(result!=-1) {
			logger.info("인증성공");

			//	ViewCareersResponse response = new ViewCareersResponse();
			List<ATMInfoData> list = new ArrayList<ATMInfoData>();
			//응답TMInfo전문의 List
			List<ATMInfoResponseSub> responseSubList = new ArrayList<ATMInfoResponseSub> ();

			int page = request.getPageNo();
			int size = request.getNumOfRows();


			logger.info("Paging:"+page+",size="+size);

			if(size==0) {
				size=10;
				logger.info("Paging:"+page+",size="+size);
			}
			Pageable paging = PageRequest.of(page, size);
			
			
			
			list = atmInfoDataService.findAll(paging);//.findByStdDatePaging(request.getStdYm(),paging);			


			for(ATMInfoData data : list) {
				ATMInfoResponseSub responseSub = convertToResponseSub(data);
				responseSubList.add(responseSub);
			}			

			logger.info("DB Result:"+list.size());

			for(ATMInfoResponseSub resSub : responseSubList) {
				logger.info("select Result="+resSub.toString());

			}
			response.setItem(responseSubList);
			logger.info("인증수행 여부 ="+authYN);
			//추후 apiInfo 조회를 통해서 처리 
			String apiName = "atmInfoByName ";
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
			String apiName = "atmInfoByName";
			String action = "CALL";
			String statusCode ="1111";//코드 확인필요 
			String requestMessage = request.toString();
			String responseMessage = response.toString();
			String trxDate = DateUtil.getDateYYYY_MM_DDHHMMSSMISSS();


			LogApiData logApiData = new LogApiData(logId,apiId,apiName,apiUrl,action,statusCode,requestMessage,responseMessage,trxDate);

		}

		return response;			
	}


	//검색기준: atmName
	@RequestMapping(value="/atmInfoByName",produces="application/xml")
	public  ATMInfoResponse viewATMInfoByNameRequest(@RequestBody ATMInfoByNameRequest request) {

		logger.info("ATMInfoByNameRequest="+request.toString());
		String key = DateUtil.getDateYYYYMMDDHHMMSSMISSS();
		Random generator = new Random();   
		int num= generator.nextInt(100);    
		String logId = key + Integer.toString(num);

		String apiId= "atmInfoByName";
		String apiUrl=	"/atmInfo/atmInfoByName";
		ATMInfoResponse response = new ATMInfoResponse();

		int result = 0;
		if(authYN.contentEquals("Y"))
			result = authService.auth();

		if(result!=-1) {
			logger.info("인증성공");

			//	ViewCareersResponse response = new ViewCareersResponse();
			List<ATMInfoData> list = new ArrayList<ATMInfoData>();
			//응답TMInfo전문의 List
			List<ATMInfoResponseSub> responseSubList = new ArrayList<ATMInfoResponseSub> ();

			int page = request.getPageNo();
			int size = request.getNumOfRows();


			logger.info("Paging:"+page+",size="+size);

			if(size==0) {
				size=10;
				logger.info("Paging:"+page+",size="+size);
			}
			Pageable paging = PageRequest.of(page, size);
			list = atmInfoDataService.findByATMName(request.getAtmName(), paging);//.findByStdDatePaging(request.getStdYm(),paging);			


			for(ATMInfoData data : list) {
				ATMInfoResponseSub responseSub = convertToResponseSub(data);
				responseSubList.add(responseSub);
			}			

			logger.info("DB Result:"+list.size());

			for(ATMInfoResponseSub resSub : responseSubList) {
				logger.info("select Result="+resSub.toString());

			}
			response.setItem(responseSubList);
			logger.info("인증수행 여부 ="+authYN);
			//추후 apiInfo 조회를 통해서 처리 
			String apiName = "atmInfoByName ";
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
			String apiName = "atmInfoByName";
			String action = "CALL";
			String statusCode ="1111";//코드 확인필요 
			String requestMessage = request.toString();
			String responseMessage = response.toString();
			String trxDate = DateUtil.getDateYYYY_MM_DDHHMMSSMISSS();


			LogApiData logApiData = new LogApiData(logId,apiId,apiName,apiUrl,action,statusCode,requestMessage,responseMessage,trxDate);

		}

		return response;			
	}

	//findByATMSectionCode
	@RequestMapping(value="/atmInfoBySection",produces="application/xml")
	public  ATMInfoResponse viewATMInfoBySectionCode(@RequestBody ATMInfoBySectionRequest request) {

		logger.info("ATMInfoBySectionRequest="+request.toString());
		String key = DateUtil.getDateYYYYMMDDHHMMSSMISSS();
		Random generator = new Random();   
		int num= generator.nextInt(100);    
		String logId = key + Integer.toString(num);

		String apiId= "atmInfoByName";
		String apiUrl=	"/atmInfo/atmInfoBySection";
		ATMInfoResponse response = new ATMInfoResponse();

		int result = 0;
		if(authYN.contentEquals("Y"))
			result = authService.auth();

		if(result!=-1) {
			logger.info("인증성공");

			//	ViewCareersResponse response = new ViewCareersResponse();
			List<ATMInfoData> list = new ArrayList<ATMInfoData>();
			//응답TMInfo전문의 List
			List<ATMInfoResponseSub> responseSubList = new ArrayList<ATMInfoResponseSub> ();

			int page = request.getPageNo();
			int size = request.getNumOfRows();


			logger.info("Paging:"+page+",size="+size);

			if(size==0) {
				size=10;
				logger.info("Paging:"+page+",size="+size);
			}
			Pageable paging = PageRequest.of(page, size);
			list = atmInfoDataService.findByATMName(request.getAtmSectionCode(), paging);//.findByStdDatePaging(request.getStdYm(),paging);


			logger.info("DB Result:"+list.size());

			for(ATMInfoData data : list) {
				ATMInfoResponseSub responseSub = convertToResponseSub(data);
				responseSubList.add(responseSub);
			}			

			for(ATMInfoResponseSub resSub : responseSubList) {
				logger.info("select Result="+resSub.toString());

			}
			response.setItem(responseSubList);
			logger.info("인증수행 여부 ="+authYN);
			//추후 apiInfo 조회를 통해서 처리 
			String apiName = "ATMatmInfoBySection ";
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
			String apiName = "ATM정보";
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
