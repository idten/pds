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
import com.ibk.pds.api.model.ATMInfo.ATMInfoByAddressRequest;
import com.ibk.pds.api.model.ATMInfo.ATMInfoByNameRequest;
import com.ibk.pds.api.model.ATMInfo.ATMInfoBySectionRequest;
import com.ibk.pds.api.model.ATMInfo.ATMInfoResponse;
import com.ibk.pds.api.model.ATMInfo.ATMInfoResponseSub;
import com.ibk.pds.api.model.BranchInfo.BranchInfoResponseSub;
import com.ibk.pds.auth.service.AuthService;
import com.ibk.pds.common.util.DateUtil;
import com.ibk.pds.data.model.ATMInfoData;
import com.ibk.pds.data.model.BranchInfoData;
import com.ibk.pds.data.service.ATMInfoDataService;
import com.ibk.pds.log.model.LogApiData;
import com.ibk.pds.log.service.LogApiDataService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

//ATM정보 
//1. ATM정보 전체 리스트
//2. ATM명칭에 따른 조회
//3. 지역코드(ex> 서울02)에 따른 대상 조회 

@RestController
@RequestMapping("/api/atmInfo")
@Api(value = "atmInfo", description = "ATM 정보 조회")
public class ATMInfoController {

	private String docId = "atmInfo";
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


	@ApiOperation(value = "ATM 전체 목록 조회(POST)")
	@RequestMapping(value="/atmInfoAll",produces="application/xml", method=RequestMethod.POST)
	public  ATMInfoResponse viewATMInfoAll(@RequestBody ATMInfoAllRequest request) {
		ATMInfoResponse response = viewATMInfoAllCommon(request);
		return response;
	}

	@ApiOperation(value = "ATM 전체 목록 조회(GET)")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "numOfRows", 	value = "한페이지 결과수",	required=false, defaultValue="10",paramType = "query"),
		@ApiImplicitParam(name = "pageNo", 		value = "페이지수 ",		required=false, defaultValue="0",paramType = "query" 	),
		@ApiImplicitParam(name = "serviceKey", 	value = "인증키",			required=false, defaultValue="defaultKey",paramType = "query"),
		@ApiImplicitParam(name = "SG_APIM", 	value = "인증키(공공포털)",	required=false, defaultValue="defaultKey",paramType = "query")

	})
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
	@ApiOperation(value = "ATM 이름조회(POST)")	
	@RequestMapping(value="/atmInfoByName",produces="application/xml", method=RequestMethod.POST)
	public  ATMInfoResponse viewATMInfoByName(@RequestBody ATMInfoByNameRequest request) {
		ATMInfoResponse response = viewATMInfoByNameCommon(request);
		return response;

	}
	@ApiOperation(value = "ATM 이름조회(GET)")	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "numOfRows", 	value = "한페이지 결과수",	required=false, defaultValue="10",			paramType = "query"),
		@ApiImplicitParam(name = "pageNo", 		value = "페이지수 ",		required=false, defaultValue="0",			paramType = "query" 	),
		@ApiImplicitParam(name = "serviceKey", 	value = "인증키",			required=false, defaultValue="defaultKey",	paramType = "query"),
		@ApiImplicitParam(name = "atmName", 	value = "ATM코너명",		required=false, defaultValue="",	paramType = "query"),
		@ApiImplicitParam(name = "SG_APIM", 	value = "인증키(공공포털)",	required=false, defaultValue="defaultKey",	paramType = "query")

	})
	@RequestMapping(value="/atmInfoByName",produces="application/xml", method=RequestMethod.GET)
	public  ATMInfoResponse viewATMInfoByName(
			@RequestParam(value="numOfRows", 	required=false, defaultValue="10") int numOfRows, 
			@RequestParam(value="pageNo", 		required=false, defaultValue="0") int pageNo,
			@RequestParam(value="serviceKey", 	required=false, defaultValue="defaultKey") String serviceKey,
			@RequestParam(value="atmName",      required=false, defaultValue="atmName") String atmName,
			@RequestParam(value="SG_APIM", 		required=false, defaultValue="defaultKey") String SG_APIM
			) {
		ATMInfoByAddressRequest request = new ATMInfoByAddressRequest(serviceKey,numOfRows,pageNo,atmName,SG_APIM);
		ATMInfoResponse response = viewATMInfoByAddressCommon(request);
		return response;
	}


	//검색기준: atmName
	@ApiOperation(value = "ATM 주소조회(POST)")	
	@RequestMapping(value="/atmInfoByAddress",produces="application/xml", method=RequestMethod.POST)
	public  ATMInfoResponse viewATMInfoByAddress(@RequestBody ATMInfoByAddressRequest request) {
		ATMInfoResponse response = viewATMInfoByAddressCommon(request);
		return response;

	}
	@ApiOperation(value = "ATM 주소조회(GET)")	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "numOfRows", 	value = "한페이지 결과수",	required=false, defaultValue="10",			paramType = "query"),
		@ApiImplicitParam(name = "pageNo", 		value = "페이지수 ",		required=false, defaultValue="0",			paramType = "query" 	),
		@ApiImplicitParam(name = "serviceKey", 	value = "인증키",			required=false, defaultValue="defaultKey",	paramType = "query"),
		@ApiImplicitParam(name = "atmName", 	value = "ATM코너명",		required=false, defaultValue="을지로",	paramType = "query"),
		@ApiImplicitParam(name = "SG_APIM", 	value = "인증키(공공포털)",	required=false, defaultValue="defaultKey",	paramType = "query")

	})
	@RequestMapping(value="/atmInfoByAddress",produces="application/xml", method=RequestMethod.GET)
	public  ATMInfoResponse viewATMInfoByAddress(
			@RequestParam(value="numOfRows", 	required=false, defaultValue="10") int numOfRows, 
			@RequestParam(value="pageNo", 		required=false, defaultValue="0") int pageNo,
			@RequestParam(value="serviceKey", 	required=false, defaultValue="defaultKey") String serviceKey,
			@RequestParam(value="atmName",      required=false, defaultValue="atmName") String atmName,
			@RequestParam(value="SG_APIM", 		required=false, defaultValue="defaultKey") String SG_APIM
			) {
		ATMInfoByAddressRequest request = new ATMInfoByAddressRequest(serviceKey,numOfRows,pageNo,atmName,SG_APIM);
		ATMInfoResponse response = viewATMInfoByAddressCommon(request);
		return response;
	}


	@ApiOperation(value = "ATM 지역별 목록 조회(POST)")	
	@RequestMapping(value="/atmInfoBySection",produces="application/xml", method=RequestMethod.POST)
	public  ATMInfoResponse viewATMInfoBySectionCode(@RequestBody ATMInfoBySectionRequest request) {
		ATMInfoResponse response = viewATMInfoBySectionCodeCommon(request);
		return response;
	}

	@ApiOperation(value = "ATM 지역별 목록 조회(GET)")		
	@ApiImplicitParams({
		@ApiImplicitParam(name = "numOfRows", 	value = "한페이지 결과수",	required=false, defaultValue="10",			paramType = "query"),
		@ApiImplicitParam(name = "pageNo", 		value = "페이지수 ",		required=false, defaultValue="0",			paramType = "query" 	),
		@ApiImplicitParam(name = "serviceKey", 	value = "인증키",			required=false, defaultValue="defaultKey",	paramType = "query"),
		@ApiImplicitParam(name = "atmSectionCode", 	value = "ATM행정구역코드",		required=false, defaultValue="02",	paramType = "query"),
		@ApiImplicitParam(name = "SG_APIM", 	value = "인증키(공공포털)",	required=false, defaultValue="defaultKey",	paramType = "query")

	})
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

	public  ATMInfoResponse viewATMInfoAllCommon(ATMInfoAllRequest request) {

		logger.info("ATMInfoAll="+request.toString());
		String key = DateUtil.getDateYYYYMMDDHHMMSSMISSS();
		Random generator = new Random();   
		int num= generator.nextInt(100);    
		String logId = key + Integer.toString(num);

		String apiId= "atmInfoByName";
		String apiUrl=	"/atmInfo/atmInfoByName";
		String apiName = "ATM정보조회(ALL)";
		String action = "CALL";
		String statusCode ="0000";//코드 확인필요 
		String requestMessage = request.toString();
		String responseMessage = "";
		String trxDate = DateUtil.getDateYYYY_MM_DDHHMMSSMISSS();

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
			int totalCount = atmInfoDataService.findAllTotalCount();

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
			statusCode ="0000";
			responseMessage = response.toString();
			//	LogApiData logApiData = new LogApiData(logId,docId,apiId,apiName,apiUrl,action,statusCode,requestMessage,responseMessage,trxDate);
			//.saveLogApiData(logApiData);
		}else {
			//추후 apiInfo 조회를 통해서 처리 
			statusCode ="1111";//코드 확인필요 
			responseMessage = "Error";
			response.setResultCode("99");
			response.setResultMsg("인증실패");

		}

		LogApiData logApiData = new LogApiData(logId,docId,apiId,apiName,apiUrl,action,statusCode,requestMessage,responseMessage,trxDate);
		logApiDataService.saveApiData(logApiData);

		return response;			
	}


	public  ATMInfoResponse viewATMInfoByNameCommon(ATMInfoByNameRequest request) {

		logger.info("ATMInfoByNameRequest="+request.toString());
		String key = DateUtil.getDateYYYYMMDDHHMMSSMISSS();
		Random generator = new Random();   
		int num= generator.nextInt(100);    
		String logId = key + Integer.toString(num);

		String apiId= "atmInfoByName";
		String apiUrl=	"/atmInfo/atmInfoByName";
		String apiName = "ATM정보조회(이름)";
		String action = "CALL";
		String statusCode ="0000";//코드 확인필요 
		String requestMessage = request.toString();
		String responseMessage = "";
		String trxDate = DateUtil.getDateYYYY_MM_DDHHMMSSMISSS();


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
			int totalCount = atmInfoDataService.findByATMNameTotalCount(request.getAtmName());

			for(ATMInfoData data : list) {
				ATMInfoResponseSub responseSub = convertToResponseSub(data);
				responseSubList.add(responseSub);
			}			

			logger.info("DB Result:"+list.size());

			for(ATMInfoResponseSub resSub : responseSubList) {
				logger.info("select Result="+resSub.toString());

			}
			response.setItem(responseSubList);
			response.setTotalCount(totalCount);
			response.setResultCode("00");
			response.setResultMsg("OK");
			response.setNumOfRows(size);
			response.setPageNo(page);

			//1개 이상은 아님 
			response.setTotalCount(list.size());

			logger.info("인증수행 여부 ="+authYN);
			//추후 apiInfo 조회를 통해서 처리 
			statusCode ="0000";
			responseMessage = response.toString();

		}else {
			//추후 apiInfo 조회를 통해서 처리 
			statusCode ="1111";//코드 확인필요 
			responseMessage = "Error";

			response.setResultCode("99");
			response.setResultMsg("인증실패");

		}
		LogApiData logApiData = new LogApiData(logId,docId,apiId,apiName,apiUrl,action,statusCode,requestMessage,responseMessage,trxDate);
		logApiDataService.saveApiData(logApiData);

		return response;			
	}

	public  ATMInfoResponse viewATMInfoByAddressCommon(ATMInfoByAddressRequest request) {

		logger.info("ATMInfoByAddressRequest="+request.toString());
		String key = DateUtil.getDateYYYYMMDDHHMMSSMISSS();
		Random generator = new Random();   
		int num= generator.nextInt(100);    
		String logId = key + Integer.toString(num);

		String apiId= "atmInfoByAddress";
		String apiUrl=	"/atmInfo/atmInfoByAddress";
		String apiName = "ATM정보조회(주소)";
		String action = "CALL";
		String statusCode ="";//코드 확인필요 
		String requestMessage = request.toString();
		String responseMessage = "";
		String trxDate = DateUtil.getDateYYYY_MM_DDHHMMSSMISSS();


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
			list = atmInfoDataService.findByATMAddress(request.getAtmAddress(), paging);//.findByStdDatePaging(request.getStdYm(),paging);			
			//int totalCount = atmInfoDataService.find
			int totalCount = atmInfoDataService.findByATMAddressTotalCount(request.getAtmAddress());
			for(ATMInfoData data : list) {
				ATMInfoResponseSub responseSub = convertToResponseSub(data);
				responseSubList.add(responseSub);
			}			

			logger.info("DB Result:"+list.size());

			
			for(ATMInfoResponseSub resSub : responseSubList) {
				logger.info("select Result="+resSub.toString());

			}
			response.setItem(responseSubList);
			response.setTotalCount(totalCount);
			response.setResultCode("00");
			response.setResultMsg("OK");
			response.setNumOfRows(size);
			response.setPageNo(page);

			//1개 이상은 아님 
			response.setTotalCount(list.size());

			logger.info("인증수행 여부 ="+authYN);
			//추후 apiInfo 조회를 통해서 처리 
			statusCode ="0000";
			responseMessage = response.toString();

		}else {
			//추후 apiInfo 조회를 통해서 처리 
			statusCode ="1111";//코드 확인필요 
			responseMessage = "Error";

			response.setResultCode("99");
			response.setResultMsg("인증실패");

		}
		LogApiData logApiData = new LogApiData(logId,docId,apiId,apiName,apiUrl,action,statusCode,requestMessage,responseMessage,trxDate);
		logApiDataService.saveApiData(logApiData);

		return response;			
	}

	public  ATMInfoResponse viewATMInfoBySectionCodeCommon(ATMInfoBySectionRequest request) {

		logger.info("ATMInfoBySectionRequest="+request.toString());
		String key = DateUtil.getDateYYYYMMDDHHMMSSMISSS();
		Random generator = new Random();   
		int num= generator.nextInt(100);    
		String logId = key + Integer.toString(num);

		String apiId= "atmInfoBySectionCode";
		String apiUrl=	"/atmInfo/atmInfoBySectionCode";
		String apiName = "ATM정보조회(지역코드)  ";
		String action = "CALL";
		String statusCode ="0000";
		String requestMessage = request.toString();
		String responseMessage = "";
		String trxDate = DateUtil.getDateYYYY_MM_DDHHMMSSMISSS();


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
			int totalCount = atmInfoDataService.findByATMSectionCodeTotalCount(request.getAtmSectionCode());

			logger.info("DB Result:"+list.size());

			for(ATMInfoData data : list) {
				ATMInfoResponseSub responseSub = convertToResponseSub(data);
				responseSubList.add(responseSub);
			}			

			for(ATMInfoResponseSub resSub : responseSubList) {
				logger.info("select Result="+resSub.toString());

			}
			response.setItem(responseSubList);
			response.setTotalCount(totalCount);
			response.setResultCode("00");
			response.setResultMsg("OK");
			response.setNumOfRows(size);
			response.setPageNo(page);

			//1개 이상은 아님 
			response.setTotalCount(totalCount);

			logger.info("인증수행 여부 ="+authYN);
			//추후 apiInfo 조회를 통해서 처리 
			statusCode ="0000";
			responseMessage = response.toString();


			//.saveLogApiData(logApiData);
		}else {
			statusCode ="1111";//코드 확인필요 
			responseMessage = "Error";
			response.setResultCode("99");
			response.setResultMsg("인증실패");

		}
		LogApiData logApiData = new LogApiData(logId,docId,apiId,apiName,apiUrl,action,statusCode,requestMessage,responseMessage,trxDate);
		logApiDataService.saveApiData(logApiData);


		return response;			
	}
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
}
