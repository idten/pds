package com.ibk.pds.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibk.pds.api.model.FundRateInfo.FundRateInfoAllRequest;
import com.ibk.pds.api.model.FundRateInfo.FundRateInfoByIdivFnptDcdTop5Request;
import com.ibk.pds.api.model.FundRateInfo.FundRateInfoByInvmAecdTop5Request;
import com.ibk.pds.api.model.FundRateInfo.FundRateInfoRequest;
import com.ibk.pds.api.model.FundRateInfo.FundRateInfoResponse;
import com.ibk.pds.api.model.FundRateInfo.FundRateInfoResponseSub;
import com.ibk.pds.auth.service.AuthService;
import com.ibk.pds.common.util.DateUtil;
import com.ibk.pds.data.model.FundRateData;
import com.ibk.pds.data.service.FundRateDataService;
import com.ibk.pds.log.model.LogApiData;
import com.ibk.pds.log.service.LogApiDataService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

//아이원잡 채용공고 통계 조회 서비스 
@RestController
@RequestMapping("/api/fundRateInfo")
@Api(value = "fundRateInfo", description = "펀드수익률정보 조회")
public class FundRateInfoController {
	
	private String docId = "fundRateInfo";
	@Autowired
	LogApiDataService logApiDataService;
	private Logger logger = LoggerFactory.getLogger(FundRateInfoController.class);
	@Autowired
	AuthService authService;
	@Value("${authYN}")
	private String authYN;

	//필터
	@Autowired
	FundRateDataService fundRateDataService;
	

	@ApiOperation(value = "펀드누적수익률 전체 조회(POST)")
	@RequestMapping(value="/fundRateInfoAll",produces="application/xml",method=RequestMethod.POST )
	public  FundRateInfoResponse viewFundRateInfoAll(@RequestBody FundRateInfoAllRequest request) {
		FundRateInfoResponse response = viewFundRateInfoAllCommon(request);
		return response;
		
	}
	
	@ApiOperation(value = "펀드누적수익률 전체 조회(GET)")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "numOfRows", 	value = "한페이지 결과수",	required=false, defaultValue="10",paramType = "query"),
		@ApiImplicitParam(name = "pageNo", 		value = "페이지수 ",		required=false, defaultValue="0",paramType = "query" 	),
		@ApiImplicitParam(name = "serviceKey", 	value = "인증키",			required=false, defaultValue="defaultKey",paramType = "query"),
		@ApiImplicitParam(name = "SG_APIM", 	value = "인증키(공공포털)",	required=false, defaultValue="defaultKey",paramType = "query")
	})
	@RequestMapping(value="/fundRateInfoAll",produces="application/xml",method=RequestMethod.GET )
	public  FundRateInfoResponse viewFundRateInfoAll(
			@RequestParam(value="numOfRows", 	required=false, defaultValue="10") int numOfRows, 
			@RequestParam(value="pageNo", 		required=false, defaultValue="0") int pageNo,
			@RequestParam(value="serviceKey", 	required=false, defaultValue="defaultKey") String serviceKey,
			@RequestParam(value="SG_APIM", 		required=false, defaultValue="defaultKey") String SG_APIM
			
			) {
		FundRateInfoAllRequest request = new FundRateInfoAllRequest(serviceKey,numOfRows, pageNo,SG_APIM);
		FundRateInfoResponse response = viewFundRateInfoAllCommon(request);
		return response;
	
	}
	@ApiOperation(value = "펀드누적수익률 조건별 조회(POST)")
	@RequestMapping(value="/fundRateInfo",produces="application/xml",method=RequestMethod.POST )
	public  FundRateInfoResponse viewFundRateInfo(@RequestBody FundRateInfoRequest request) {
		FundRateInfoResponse response = viewFundRateInfoCommon(request);
		return response;
	}

	@ApiOperation(value = "펀드누적수익률 조건별 조회(GET)")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "numOfRows", 	value = "한페이지 결과수",	required=false, defaultValue="10",paramType = "query"),
		@ApiImplicitParam(name = "pageNo", 		value = "페이지수 ",		required=false, defaultValue="0",paramType = "query" 	),
		@ApiImplicitParam(name = "serviceKey", 	value = "인증키",			required=false, defaultValue="defaultKey",paramType = "query"),
		@ApiImplicitParam(name = "SG_APIM", 	value = "인증키(공공포털)",	required=false, defaultValue="defaultKey",paramType = "query"),
		@ApiImplicitParam(name = "fundAstTcd", 	value = "펀드자산유형코드",	required=false, defaultValue="10",paramType = "query"),
		@ApiImplicitParam(name = "fundInvmAecd",value = "펀드투자지역코드",	required=false, defaultValue="01",paramType = "query"),
		@ApiImplicitParam(name = "pdrsGdcd", 	value = "상품리스크등급코드",	required=false, defaultValue="01",paramType = "query"),
		@ApiImplicitParam(name = "idivFnptDcd", value = "펀드유형구분코드",	required=false, defaultValue="11",paramType = "query")
	})
	@RequestMapping(value="/fundRateInfo",produces="application/xml",method=RequestMethod.GET )
	public  FundRateInfoResponse viewFundRateInfo(
			@RequestParam(value="numOfRows", 	required=false, defaultValue="10") int numOfRows, 
			@RequestParam(value="pageNo", 		required=false, defaultValue="0") int pageNo,
			@RequestParam(value="serviceKey", 	required=false, defaultValue="defaultKey") String serviceKey,
			@RequestParam(value="SG_APIM", 		required=false, defaultValue="defaultKey") String SG_APIM,
			@RequestParam(value="fundAstTcd",	required=false, defaultValue="10") String fundAstTcd,
			@RequestParam(value="fundInvmAecd",	required=false, defaultValue="01") String fundInvmAecd,
			@RequestParam(value="pdrsGdcd",		required=false, defaultValue="01") String pdrsGdcd,
			@RequestParam(value="idivFnptDcd",	required=false, defaultValue="11") String idivFnptDcd
			
			) {
		FundRateInfoRequest request = new FundRateInfoRequest(serviceKey,numOfRows,pageNo,
				fundAstTcd,fundInvmAecd,pdrsGdcd,idivFnptDcd,SG_APIM);
		FundRateInfoResponse response = viewFundRateInfoCommon(request);
		return response;
	}
	
	@ApiOperation(value = "펀드유형별 누적수익률 TOP 5(POST)")
	@RequestMapping(value="/fundRateInfoByIdivFnptDcdTop5",produces="application/xml",method=RequestMethod.POST)
	public  FundRateInfoResponse viewFundRateInfoByIdivFnptDcdTop5(@RequestBody FundRateInfoByIdivFnptDcdTop5Request request) {
		FundRateInfoResponse response = viewFundRateInfoByIdivFnptDcdTop5Common(request);
		return response;
	}
	@ApiOperation(value = "펀드유형별 누적수익률 TOP 5(GET)")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "numOfRows", 	value = "한페이지 결과수",	required=false, defaultValue="10",paramType = "query"),
		@ApiImplicitParam(name = "pageNo", 		value = "페이지수 ",		required=false, defaultValue="0",paramType = "query" 	),
		@ApiImplicitParam(name = "serviceKey", 	value = "인증키",			required=false, defaultValue="defaultKey",paramType = "query"),
		@ApiImplicitParam(name = "SG_APIM", 	value = "인증키(공공포털)",	required=false, defaultValue="defaultKey",paramType = "query"),
		@ApiImplicitParam(name = "idivFnptDcd", value = "펀드유형구분코드",	required=false, defaultValue="11",paramType = "query"),
		@ApiImplicitParam(name = "ernnRtDcd",	value = "수익률구분코드",	required=false, defaultValue="01",paramType = "query")
	})
	@RequestMapping(value="/fundRateInfoByIdivFnptDcdTop5",produces="application/xml",method=RequestMethod.GET)
	public  FundRateInfoResponse viewFundRateInfoByIdivFnptDcdTop5(
			@RequestParam(value="numOfRows", 	required=false, defaultValue="10") int numOfRows, 
			@RequestParam(value="pageNo", 		required=false, defaultValue="0") int pageNo,
			@RequestParam(value="serviceKey", 	required=false, defaultValue="defaultKey") String serviceKey,
			@RequestParam(value="SG_APIM", 		required=false, defaultValue="defaultKey") String SG_APIM,
			@RequestParam(value="idivFnptDcd", 	required=false, defaultValue="11") String idivFnptDcd,
			@RequestParam(value="ernnRtDcd", 	required=false, defaultValue="01") String ernnRtDcd
			
			) {
		//public FundRateInfoByIdivFnptDcdTop5Request(String serviceKey,Integer numOfRows, Integer pageNo,String idivFnptDcd, String ernnRtDcd) {
			
		FundRateInfoByIdivFnptDcdTop5Request request = new FundRateInfoByIdivFnptDcdTop5Request(serviceKey,numOfRows, pageNo, idivFnptDcd,ernnRtDcd,SG_APIM);
		
		FundRateInfoResponse response = viewFundRateInfoByIdivFnptDcdTop5Common(request);
		return response;

	
	}
	@ApiOperation(value = "펀드투자지역별 누적수익률 TOP 5(POST)")
	@RequestMapping(value="/fundRateInfoByInvmAecdTop5",produces="application/xml",method=RequestMethod.POST)
	public  FundRateInfoResponse viewFundRateInfoByInvmAecdTop5(@RequestBody FundRateInfoByInvmAecdTop5Request request) {
		FundRateInfoResponse response = viewFundRateInfoByInvmAecdTop5Common(request);
		return response;

	}
	
	@ApiOperation(value = "펀드투자지역별 누적수익률 TOP 5(GET)")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "numOfRows", 	value = "한페이지 결과수",	required=false, defaultValue="10",paramType = "query"),
		@ApiImplicitParam(name = "pageNo", 		value = "페이지수 ",		required=false, defaultValue="0",paramType = "query" 	),
		@ApiImplicitParam(name = "serviceKey", 	value = "인증키",			required=false, defaultValue="defaultKey",paramType = "query"),
		@ApiImplicitParam(name = "SG_APIM", 	value = "인증키(공공포털)",	required=false, defaultValue="defaultKey",paramType = "query"),
		@ApiImplicitParam(name = "fundInvmAecd", value = "펀드투자지역코드",	required=false, defaultValue="01",paramType = "query"),
		@ApiImplicitParam(name = "ernnRtDcd",	value = "수익률구분코드",	required=false, defaultValue="01",paramType = "query")
	})
	@RequestMapping(value="/fundRateInfoByInvmAecdTop5",produces="application/xml",method=RequestMethod.GET)
	public  FundRateInfoResponse viewFundRateInfoByInvmAecdTop5(
			@RequestParam(value="numOfRows", 	required=false, defaultValue="10") int numOfRows, 
			@RequestParam(value="pageNo", 		required=false, defaultValue="0") int pageNo,
			@RequestParam(value="serviceKey", 	required=false, defaultValue="defaultKey") String serviceKey,
			@RequestParam(value="SG_APIM", 		required=false, defaultValue="defaultKey") String SG_APIM,
			@RequestParam(value="fundInvmAecd",	required=false, defaultValue="01") String fundInvmAecd,
			@RequestParam(value="ernnRtDcd", 	required=false, defaultValue="01") String ernnRtDcd
			
			) {
		FundRateInfoByInvmAecdTop5Request request = new FundRateInfoByInvmAecdTop5Request(
				serviceKey,numOfRows, pageNo, fundInvmAecd, ernnRtDcd,SG_APIM);
		FundRateInfoResponse response = viewFundRateInfoByInvmAecdTop5Common(request);
		return response;

	
	}
	
	
	
	
	//검색기준: atmName
	public  FundRateInfoResponse viewFundRateInfoAllCommon(FundRateInfoAllRequest request) {

		logger.info("FundRateInfoAllRequest="+request.toString());
		String key = DateUtil.getDateYYYYMMDDHHMMSSMISSS();
		Random generator = new Random();   
		int num= generator.nextInt(100);    
		String logId = key + Integer.toString(num);

		String apiId= "fundRateInfoAll";
		String apiUrl=	"/fundRateInfo/fundRateInfoAll";
		String apiName = "펀드정보조회(전체)";
		String action = "CALL";
		String statusCode ="";
		String requestMessage = request.toString();
		String responseMessage = "";//response.toString();
		String trxDate = DateUtil.getDateYYYY_MM_DDHHMMSSMISSS();

		
		FundRateInfoResponse response = new FundRateInfoResponse();

		int result = 0;
		if(authYN.contentEquals("Y"))
			result = authService.auth(request.getSG_APIM());

		if(result!=-1) {
			logger.info("인증성공");
			//	ViewCareersResponse response = new ViewCareersResponse();
			List<FundRateData> list = new ArrayList<FundRateData>();
			List<FundRateInfoResponseSub> responseSubList = new ArrayList<FundRateInfoResponseSub> ();

			int page = request.getPageNo();
			int size = request.getNumOfRows();


			logger.info("Paging:"+page+",size="+size);

			if(size==0) {
				size=10;
				logger.info("Paging:"+page+",size="+size);
			}
			Pageable paging = PageRequest.of(page, size);
			list = fundRateDataService.findAll(paging);//.findByATMName(request.getAtmName(), paging);//.findByStdDatePaging(request.getStdYm(),paging);
			//list = jobWorldDataService.findByStdDatePaging(request.getStdYm(),);
			int totalCount = fundRateDataService.findAllTotalCount();
			logger.info("DB Result:"+list.size());

			for(FundRateData data : list) {
				FundRateInfoResponseSub responseSub = convertToResponseSub(data);
				responseSubList.add(responseSub);
			}

			for(FundRateInfoResponseSub resSub : responseSubList) {
				logger.info("select Result="+resSub.toString());

			}
			
			response.setItem(responseSubList);
			response.setTotalCount(totalCount);
			response.setResultCode("00");
			response.setResultMsg("OK");
			response.setNumOfRows(size);
			response.setPageNo(page);
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

	
	//검색기준: 1개검색 
//	@RequestMapping(value="/fundRateInfo",produces="application/xml")
	public  FundRateInfoResponse viewFundRateInfoCommon(FundRateInfoRequest request) {

		logger.info("FundRateInfoRequest="+request.toString());
		String key = DateUtil.getDateYYYYMMDDHHMMSSMISSS();
		Random generator = new Random();   
		int num= generator.nextInt(100);    
		String logId = key + Integer.toString(num);

		String apiId= "fundRateInfo";
		String apiUrl=	"/fundRateInfo/fundRateInfo";
		String apiName = "펀드정보조회(단건)";
		String action = "CALL";
		String statusCode ="";
		String requestMessage = request.toString();
		String responseMessage = "";//response.toString();
		String trxDate = DateUtil.getDateYYYY_MM_DDHHMMSSMISSS();

		
		FundRateInfoResponse response = new FundRateInfoResponse();

		int result = 0;
		if(authYN.contentEquals("Y"))
			result = authService.auth(request.getSG_APIM());

		if(result!=-1) {
			logger.info("인증성공");
			//	ViewCareersResponse response = new ViewCareersResponse();
			List<FundRateData> list = new ArrayList<FundRateData>();
			List<FundRateInfoResponseSub> responseSubList = new ArrayList<FundRateInfoResponseSub> ();

			int page = request.getPageNo();
			int size = request.getNumOfRows();


			logger.info("Paging:"+page+",size="+size);

			if(size==0) {
				size=10;
				logger.info("Paging:"+page+",size="+size);
			}
			Pageable paging 	= PageRequest.of(page, size);
			String fundAstTcd 	= request.getFundAstTcd();	//펀드자산유형코드
			String fundInvmAecd	= request.getFundInvmAecd();//펀드투자지역코드 
			String pdrsGdcd		= request.getPdrsGdcd();	//상품리스크 등급코드 
			String idivFnptDcd	= request.getIdivFnptDcd();	//펀드유형구분코드

			list = fundRateDataService.findOne(fundAstTcd, fundInvmAecd, pdrsGdcd, idivFnptDcd,paging)//.findAll(paging);//.findByATMName(request.getAtmName(), paging);//.findByStdDatePaging(request.getStdYm(),paging);
					//list = jobWorldDataService.findByStdDatePaging(request.getStdYm(),);
					;
			
			logger.info("DB Result:"+list.size());

			for(FundRateData data : list) {
				FundRateInfoResponseSub responseSub = convertToResponseSub(data);
				responseSubList.add(responseSub);
			}

			for(FundRateInfoResponseSub resSub : responseSubList) {
				logger.info("select Result="+resSub.toString());

			}
			int totalCount = list.size();
			response.setItem(responseSubList);
			response.setTotalCount(totalCount);
			response.setResultCode("00");
			response.setResultMsg("OK");
			response.setNumOfRows(size);
			response.setPageNo(page);
			
			
			
			
			
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

	
	
	
	//@RequestMapping(value="/fundRateInfoByIdivFnptDcdTop5",produces="application/xml")
	public  FundRateInfoResponse viewFundRateInfoByIdivFnptDcdTop5Common( FundRateInfoByIdivFnptDcdTop5Request request) {

		logger.info("FundRateInfoByIdivFnptDcdTop5Request="+request.toString());
		String key = DateUtil.getDateYYYYMMDDHHMMSSMISSS();
		Random generator = new Random();   
		int num= generator.nextInt(100);    
		String logId = key + Integer.toString(num);

		String apiId= "fundRateInfoAll";
		String apiUrl=	"/fundRateInfo/fundRateInfoByIdivFnptDcdTop5";
		String apiName = "펀드조회유형별Top5";
		String action = "CALL";
		String statusCode ="";
		String requestMessage = request.toString();
		String responseMessage = "";//response.toString();
		String trxDate = DateUtil.getDateYYYY_MM_DDHHMMSSMISSS();

		
		FundRateInfoResponse response = new FundRateInfoResponse();

		int result = 0;
		if(authYN.contentEquals("Y"))
			result = authService.auth(request.getSG_APIM());

		if(result!=-1) {
			logger.info("인증성공");
			//	ViewCareersResponse response = new ViewCareersResponse();
			List<FundRateData> list = new ArrayList<FundRateData>();
			List<FundRateInfoResponseSub> responseSubList = new ArrayList<FundRateInfoResponseSub> ();

			int page = request.getPageNo();
			int size = request.getNumOfRows();


			logger.info("Paging:"+page+",size="+size);

			if(size==0) {
				size=10;
				logger.info("Paging:"+page+",size="+size);
			}
			Pageable paging = PageRequest.of(page, size);
			list = fundRateDataService.findByIdivFnptDcdTop5(request.getIdivFnptDcd(),request.getErnnRtDcd());//.findByATMName(request.getAtmName(), paging);//.findByStdDatePaging(request.getStdYm(),paging);
			int totalCount = list.size();

			logger.info("DB Result:"+list.size());

			for(FundRateData data : list) {
				FundRateInfoResponseSub responseSub = convertToResponseSub(data);
				responseSubList.add(responseSub);
			}

			for(FundRateInfoResponseSub resSub : responseSubList) {
				logger.info("select Result="+resSub.toString());

			}
			//response.setItem(responseSubList);
			response.setItem(responseSubList);
			response.setTotalCount(totalCount);
			response.setResultCode("00");
			response.setResultMsg("OK");
			response.setNumOfRows(size);
			response.setPageNo(page);
			
			
			
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

	
	
	//검색기준: atmName
	//@RequestMapping(value="/fundRateInfoByInvmAecdTop5",produces="application/xml")
	public  FundRateInfoResponse viewFundRateInfoByInvmAecdTop5Common(FundRateInfoByInvmAecdTop5Request request) {

		logger.info("FundRateInfoByInvmAecdTop5Request="+request.toString());
		String key = DateUtil.getDateYYYYMMDDHHMMSSMISSS();
		Random generator = new Random();   
		int num= generator.nextInt(100);    
		String logId = key + Integer.toString(num);

		String apiId= "fundRateInfoAll";
		String apiUrl=	"/fundRateInfo/FundRateInfoByInvmAecdTop5Request";
		String apiName = "펀드정보조회(지역별top5)";
		String action = "CALL";
		String statusCode ="";
		String requestMessage = request.toString();
		String responseMessage = "";//response.toString();
		String trxDate = DateUtil.getDateYYYY_MM_DDHHMMSSMISSS();

		FundRateInfoResponse response = new FundRateInfoResponse();

		int result = 0;
		if(authYN.contentEquals("Y"))
			result = authService.auth(request.getSG_APIM());

		if(result!=-1) {
			logger.info("인증성공");
			//	ViewCareersResponse response = new ViewCareersResponse();
			List<FundRateData> list = new ArrayList<FundRateData>();
			List<FundRateInfoResponseSub> responseSubList = new ArrayList<FundRateInfoResponseSub> ();

			int page = request.getPageNo();
			int size = request.getNumOfRows();


			logger.info("Paging:"+page+",size="+size);

			if(size==0) {
				size=10;
				logger.info("Paging:"+page+",size="+size);
			}
			Pageable paging = PageRequest.of(page, size);
			list = fundRateDataService.findByFundInvmAecdTop5(request.getFundInvmAecd(), request.getErnnRtDcd());
			//list = jobWorldDataService.findByStdDatePaging(request.getStdYm(),);

			logger.info("DB Result:"+list.size());

			for(FundRateData data : list) {
				FundRateInfoResponseSub responseSub = convertToResponseSub(data);
				responseSubList.add(responseSub);
			}

			for(FundRateInfoResponseSub resSub : responseSubList) {
				logger.info("select Result="+resSub.toString());

			}
			//response.setItem(responseSubList);
			int totalCount = list.size();
			response.setItem(responseSubList);
			response.setTotalCount(totalCount);
			response.setResultCode("00");
			response.setResultMsg("OK");
			response.setNumOfRows(size);
			response.setPageNo(page);
			
			
			logger.info("인증수행 여부 ="+authYN);
			//추후 apiInfo 조회를 통해서 처리 
			statusCode ="0000";
			responseMessage = response.toString();
			//.saveLogApiData(logApiData);
		}else {


			//추후 apiInfo 조회를 통해서 처리 
			statusCode ="1111";//코드 확인필요 
			responseMessage = "Error";
		
			LogApiData logApiData = new LogApiData(logId,docId,apiId,apiName,apiUrl,action,statusCode,requestMessage,responseMessage,trxDate);
			response.setResultCode("99");
			response.setResultMsg("인증실패");

		}
		LogApiData logApiData = new LogApiData(logId,docId,apiId,apiName,apiUrl,action,statusCode,requestMessage,responseMessage,trxDate);
		logApiDataService.saveApiData(logApiData);
		
		return response;			
	}


	public FundRateInfoResponseSub convertToResponseSub(FundRateData data) {
		String baseYmd;		//기준 년월일
		String opcmNm;  	//운용사명 
		String fundNm;		//펀드명
		String ascnFundCd;	//협회펀드코드 
		String fundAstTcd;	//펀드자산유형코드
		String fundInvmAecd;//펀드투자지역코드 
		String trmMn1ErnnRt;//1개월 수익률
		String trmMn3ErnnRt;//3개월 수익률
		String trmMn6ErnnRt;//6개월 수익률
		String trmMn12ErnnRt;//12개월 수익률
		String pdrsGdcd;	//상품리스크 등급코드 
		String idivFnptDcd;	//펀드유형구분코드
		String prcqFeeRt;	//선취수수료율
		String ttalRmnrRt;	//총보수료;




		baseYmd = data.getBaseYmd();
		opcmNm = data.getOpcmNm();
		fundNm = data.getFundNm();
		ascnFundCd = data.getAscnFundCd();
		fundAstTcd = data.getFundAstTcd();
		fundInvmAecd = data.getFundInvmAecd();

		trmMn1ErnnRt = data.getTrmMn1ErnnRt();
		trmMn3ErnnRt = data.getTrmMn3ErnnRt();
		trmMn6ErnnRt = data.getTrmMn6ErnnRt();
		trmMn12ErnnRt = data.getTrmMn12ErnnRt();
		pdrsGdcd = data.getPdrsGdcd();
		idivFnptDcd = data.getIdivFnptDcd();
		prcqFeeRt = data.getPrcqFeeRt();
		ttalRmnrRt = data.getTtalRmnrRt();



		FundRateInfoResponseSub responseSub = new FundRateInfoResponseSub(

				baseYmd, opcmNm, 
				fundNm, ascnFundCd,
				fundAstTcd,
				fundInvmAecd,
				trmMn1ErnnRt,
				trmMn3ErnnRt,
				trmMn6ErnnRt,
				trmMn12ErnnRt,
				pdrsGdcd,
				idivFnptDcd,

				prcqFeeRt,
				ttalRmnrRt

				);

		return responseSub;
	}

}
