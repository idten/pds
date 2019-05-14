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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
@RequestMapping("/api/atmInfo")
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
		String startTime;		//시작시간
		String endTime;			//종료시간
		String atmAddress;		//주소
		String atmSection;		//지역구분
		String atmSectionCode;	//지역구분 코드 
		atmName = data.getAtmName();
		startTime = data.getStartTime() ;
		endTime = data.getEndTime();
		atmAddress = data.getAtmAddress();
		atmSection = data.getAtmSection();
		atmSectionCode = data.getAtmSectionCode();

		ATMInfoResponseSub responseSub = new ATMInfoResponseSub(atmName,startTime, endTime,atmAddress,atmSection, atmSectionCode);
		return responseSub;
	}

	
	@RequestMapping(value="/atmInfoAll",produces="application/xml", method=RequestMethod.POST)
	public  ATMInfoResponse viewATMInfoAll(@RequestBody ATMInfoAllRequest request) {
		ATMInfoResponse response = viewATMInfoAllCommon(request);
		return response;
	}
	
	@RequestMapping(value="/atmInfoAll",produces="application/xml", method=RequestMethod.GET)
	public  ATMInfoResponse viewATMInfoAll(
			@RequestParam(value="numOfRows", 	required=false, defaultValue="10") int numOfRows, 
			@RequestParam(value="pageNo", 		required=false, defaultValue="0") int pageNo,
			@RequestParam(value="serviceKey", 	required=false, defaultValue="defaultKey") String serviceKey,
			@RequestParam(value="SG_APIM", 		required=false, defaultValue="defaultKey") String SG_APIM
			) {
		ATMInfoAllRequest request = new ATMInfoAllRequest(serviceKey,numOfRows,pageNo,SG_APIM);
		ATMInfoResponse response = viewATMInfoAllCommon(request);
		return response;
	}
	//검색기준: atmName
	@RequestMapping(value="/atmInfoByName",produces="application/xml", method=RequestMethod.POST)
	public  ATMInfoResponse viewATMInfoByNameRequest(@RequestBody ATMInfoByNameRequest request) {
		ATMInfoResponse response = viewATMInfoByNameRequestCommon(request);
		return response;
	
	}
	
	@RequestMapping(value="/atmInfoByName",produces="application/xml", method=RequestMethod.GET)
	public  ATMInfoResponse viewATMInfoByNameRequest(
			@RequestParam(value="numOfRows", 	required=false, defaultValue="10") int numOfRows, 
			@RequestParam(value="pageNo", 		required=false, defaultValue="0") int pageNo,
			@RequestParam(value="serviceKey", 	required=false, defaultValue="defaultKey") String serviceKey,
			@RequestParam(value="atmName",      required=false, defaultValue="atmName") String atmName,
			@RequestParam(value="SG_APIM", 		required=false, defaultValue="defaultKey") String SG_APIM
			) {
		ATMInfoByNameRequest request = new ATMInfoByNameRequest(serviceKey,numOfRows,pageNo,atmName,SG_APIM);
		ATMInfoResponse response = viewATMInfoByNameRequestCommon(request);
		return response;
	
	}
	@RequestMapping(value="/atmInfoBySection",produces="application/xml", method=RequestMethod.POST)
	public  ATMInfoResponse viewATMInfoBySectionCode(@RequestBody ATMInfoBySectionRequest request) {
		ATMInfoResponse response = viewATMInfoBySectionCodeCommon(request);
		return response;
	}
	
	@RequestMapping(value="/atmInfoBySection",produces="application/xml", method=RequestMethod.GET)
	public  ATMInfoResponse viewATMInfoBySectionCode(
			@RequestParam(value="numOfRows", 	required=false, defaultValue="10") int numOfRows, 
			@RequestParam(value="pageNo", 		required=false, defaultValue="0") int pageNo,
			@RequestParam(value="serviceKey", 	required=false, defaultValue="defaultKey") String serviceKey,
			@RequestParam(value="atmSectionCode",required=false, defaultValue="02") String atmSectionCode,
			@RequestParam(value="SG_APIM", 		required=false, defaultValue="defaultKey") String SG_APIM
				
			) {
		ATMInfoBySectionRequest request =  new ATMInfoBySectionRequest(serviceKey,numOfRows,pageNo,atmSectionCode,SG_APIM);
		ATMInfoResponse response = viewATMInfoBySectionCodeCommon(request);
		return response;

	}
			
	//검색기준: atmAll
	//전체 목록 
	//@RequestMapping(value="/atmInfoAll",produces="application/xml")
	public  ATMInfoResponse viewATMInfoAllCommon(ATMInfoAllRequest request) {

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
			result = authService.auth(request.getSG_APIM());

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
			int totalCount = atmInfoDataService.getTotalCount();

			for(ATMInfoData data : list) {
				ATMInfoResponseSub responseSub = convertToResponseSub(data);
				responseSubList.add(responseSub);
			}			

			logger.info("DB Result:"+list.size());

			for(ATMInfoResponseSub resSub : responseSubList) {
				logger.info("select Result="+resSub.toString());

			}
			response.setItem(responseSubList);
			
			//response.setItem(responseSubList);
			response.setTotalCount(totalCount);
			response.setResultCode("00");
			response.setResultMsg("OK");
			response.setNumOfRows(size);
			response.setPageNo(page);
			
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
	

	public  ATMInfoResponse viewATMInfoByNameRequestCommon(ATMInfoByNameRequest request) {

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
			result = authService.auth(request.getSG_APIM());

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
			//int totalCount = atmInfoDataService.find
			
			for(ATMInfoData data : list) {
				ATMInfoResponseSub responseSub = convertToResponseSub(data);
				responseSubList.add(responseSub);
			}			

			logger.info("DB Result:"+list.size());

			for(ATMInfoResponseSub resSub : responseSubList) {
				logger.info("select Result="+resSub.toString());

			}
			response.setItem(responseSubList);
			response.setResultCode("00");
			response.setResultMsg("OK");
			response.setNumOfRows(size);
			response.setPageNo(page);
			
			//1개 이상은 아님 
			response.setTotalCount(list.size());
			
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

	
	public  ATMInfoResponse viewATMInfoBySectionCodeCommon(ATMInfoBySectionRequest request) {

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
			result = authService.auth(request.getSG_APIM());

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
			list = atmInfoDataService.findByATMSectionCode(request.getAtmSectionCode(), paging);//.findByStdDatePaging(request.getStdYm(),paging);
			int totalCount = atmInfoDataService.findByATMSectionCode(request.getAtmSectionCode()).size();
			
			logger.info("DB Result:"+list.size());

			for(ATMInfoData data : list) {
				ATMInfoResponseSub responseSub = convertToResponseSub(data);
				responseSubList.add(responseSub);
			}			

			for(ATMInfoResponseSub resSub : responseSubList) {
				logger.info("select Result="+resSub.toString());

			}
			response.setItem(responseSubList);
			response.setResultCode("00");
			response.setResultMsg("OK");
			response.setNumOfRows(size);
			response.setPageNo(page);
			
			//1개 이상은 아님 
			response.setTotalCount(totalCount);
			
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
