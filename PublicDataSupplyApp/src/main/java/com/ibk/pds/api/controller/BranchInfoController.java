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
import com.ibk.pds.api.model.BranchInfo.BranchInfoAllRequest;
import com.ibk.pds.api.model.BranchInfo.BranchInfoByNameRequest;
import com.ibk.pds.api.model.BranchInfo.BranchInfoBySectionRequest;
import com.ibk.pds.api.model.BranchInfo.BranchInfoResponse;
import com.ibk.pds.api.model.BranchInfo.BranchInfoResponseSub;
import com.ibk.pds.auth.service.AuthService;
import com.ibk.pds.common.util.DateUtil;
import com.ibk.pds.data.model.ATMInfoData;
import com.ibk.pds.data.model.BranchInfoData;
import com.ibk.pds.data.model.JobWorldData;
import com.ibk.pds.data.service.ATMInfoDataService;
import com.ibk.pds.data.service.BranchInfoDataService;
import com.ibk.pds.data.service.JobWorldDataService;
import com.ibk.pds.log.model.LogApiData;
import com.ibk.pds.log.service.LogApiDataService;

//아이원잡 채용공고 통계 조회 서비스 
@RestController
@RequestMapping("/api/branchInfo")
public class BranchInfoController {
	private String docId = "branchInfo";
	@Autowired
	LogApiDataService logApiDataService;
	private Logger logger = LoggerFactory.getLogger(BranchInfoController.class);
	//전처리

	@Autowired
	AuthService authService;
	@Value("${authYN}")
	private String authYN;

	//필터
	@Autowired
	BranchInfoDataService branchInfoDataService;

	public BranchInfoResponseSub convertToResponseSub(BranchInfoData data) {
		String branchName = "";
		String branchPhoneNumber= "";
		String branchAddress = "";
		String branchSection = "";
		String branchSectionCode = "";

		branchName = data.getBranchName();
		branchPhoneNumber = data.getBranchPhoneNumber();
		branchAddress = data.getBranchPhoneNumber();
		branchSection = data.getBranchSection();
		branchSectionCode = data.getBranchSectionCode();

		BranchInfoResponseSub responseSub = new BranchInfoResponseSub(branchName, branchPhoneNumber, 
				branchAddress, branchSection,
				branchSectionCode
				);


		return responseSub;
	}

	@RequestMapping(value="/branchInfoAll",produces="application/xml",method=RequestMethod.POST )
	public  BranchInfoResponse viewBranchInfoAll(@RequestBody BranchInfoAllRequest request) {
		
		BranchInfoResponse response = viewBranchInfoAllCommon(request);
		return response;
	}
	
	@RequestMapping(value="/branchInfoAll",produces="application/xml",method=RequestMethod.GET )
	public  BranchInfoResponse viewBranchInfoAll(
			@RequestParam(value="numOfRows", 	required=false, defaultValue="10") int numOfRows, 
			@RequestParam(value="pageNo", 		required=false, defaultValue="0") int pageNo,
			@RequestParam(value="serviceKey", 	required=false, defaultValue="defaultKey") String serviceKey,
			@RequestParam(value="SG_APIM", 		required=false, defaultValue="defaultKey") String SG_APIM
			
			) {
		BranchInfoAllRequest request = new BranchInfoAllRequest(serviceKey,numOfRows,pageNo,SG_APIM);
		BranchInfoResponse response = viewBranchInfoAllCommon(request);
		return response;
	}

	@RequestMapping(value="/branchInfoByName",produces="application/xml",method=RequestMethod.POST)
	public  BranchInfoResponse viewBranchInfoByNameRequest(@RequestBody BranchInfoByNameRequest request) {
		BranchInfoResponse response = viewBranchInfoByNameRequestCommon(request);
		return response;
	}
	
	
	@RequestMapping(value="/branchInfoByName",produces="application/xml",method=RequestMethod.GET)
	public  BranchInfoResponse viewBranchInfoByNameRequest(
			@RequestParam(value="numOfRows", 	required=false, defaultValue="10") int numOfRows, 
			@RequestParam(value="pageNo", 		required=false, defaultValue="0") int pageNo,
			@RequestParam(value="serviceKey", 	required=false, defaultValue="defaultKey") String serviceKey,
			@RequestParam(value="SG_APIM", 		required=false, defaultValue="defaultKey") String SG_APIM,
			@RequestParam(value="branchName",	required=false, defaultValue="default")	 String branchName
			
			) {
		BranchInfoByNameRequest request = new BranchInfoByNameRequest(serviceKey,numOfRows,pageNo,branchName,SG_APIM);
		BranchInfoResponse response = viewBranchInfoByNameRequestCommon(request);
		return response;

	}
	@RequestMapping(value="/branchInfoBySection",produces="application/xml",method=RequestMethod.POST)
	public  BranchInfoResponse viewBranchInfoBySectionRequest(@RequestBody BranchInfoBySectionRequest request) {
		BranchInfoResponse response = viewBranchInfoBySectionRequestCommon(request);
		return response;
	}
	@RequestMapping(value="/branchInfoBySection",produces="application/xml",method=RequestMethod.GET)
	public  BranchInfoResponse viewBranchInfoBySectionRequest(
			@RequestParam(value="numOfRows", 	required=false, defaultValue="10") int numOfRows, 
			@RequestParam(value="pageNo", 		required=false, defaultValue="0") int pageNo,
			@RequestParam(value="serviceKey", 	required=false, defaultValue="defaultKey") String serviceKey,
			@RequestParam(value="SG_APIM", 		required=false, defaultValue="defaultKey") String SG_APIM,
			@RequestParam(value="sectionCode",  required=false, defaultValue="02") String sectionCode
			) {
		BranchInfoBySectionRequest request = new BranchInfoBySectionRequest(serviceKey,numOfRows,pageNo,sectionCode,SG_APIM);
		BranchInfoResponse response = viewBranchInfoBySectionRequestCommon(request);
		return response;

		
	}
	
	
	public  BranchInfoResponse viewBranchInfoAllCommon(BranchInfoAllRequest request) {

		logger.info("BranchInfoAllRequest="+request.toString());
		String key = DateUtil.getDateYYYYMMDDHHMMSSMISSS();
		Random generator = new Random();   
		int num= generator.nextInt(100);    
		String logId = key + Integer.toString(num);

		String apiId= "branchInfoByName";
		String apiUrl=	"/branchInfo/branchInfoByName";
		String apiName = "지점정보조회(전체) ";
		String action = "CALL";
		String statusCode ="";
		String requestMessage = request.toString();
		String responseMessage = "";
		String trxDate = DateUtil.getDateYYYY_MM_DDHHMMSSMISSS();

		BranchInfoResponse response = new BranchInfoResponse();

		int result = 0;
		if(authYN.contentEquals("Y"))
			result = authService.auth(request.getSG_APIM());

		if(result!=-1) {
			logger.info("인증성공");
			//	ViewCareersResponse response = new ViewCareersResponse();
			List<BranchInfoData> list = new ArrayList<BranchInfoData>();
			List<BranchInfoResponseSub> responseSubList = new ArrayList<BranchInfoResponseSub> ();

			int page = request.getPageNo();
			int size = request.getNumOfRows();


			logger.info("Paging:"+page+",size="+size);

			if(size==0) {
				size=10;
				logger.info("Paging:"+page+",size="+size);
			}
			Pageable paging = PageRequest.of(page, size);
			list = branchInfoDataService.findAll(paging);//.findByATMName(request.getAtmName(), paging);//.findByStdDatePaging(request.getStdYm(),paging);
			int totalCount = branchInfoDataService.getTotalCount();
			
			//list = jobWorldDataService.findByStdDatePaging(request.getStdYm(),);

			logger.info("DB Result:"+list.size());

			for(BranchInfoData data : list) {
				BranchInfoResponseSub responseSub = convertToResponseSub(data);
				responseSubList.add(responseSub);
			}

			for(BranchInfoResponseSub resSub : responseSubList) {
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


			//추후 apiInfo 조회를 통해서 처리 
			statusCode ="1111";//코드 확인필요 
			responseMessage ="Error";
			
			response.setResultCode("99");
			response.setResultMsg("인증실패");

		}
		LogApiData logApiData = new LogApiData(logId,docId,apiId,apiName,apiUrl,action,statusCode,requestMessage,responseMessage,trxDate);
		logApiDataService.saveApiData(logApiData);
		
		return response;			
	}
	

	public  BranchInfoResponse viewBranchInfoByNameRequestCommon( BranchInfoByNameRequest request) {

		logger.info("BranchInfoByNameRequest="+request.toString());
		String key = DateUtil.getDateYYYYMMDDHHMMSSMISSS();
		Random generator = new Random();   
		int num= generator.nextInt(100);    
		String logId = key + Integer.toString(num);

		String apiId= "branchInfoByName";
		String apiUrl=	"/branchInfo/branchInfoByName";
		String apiName = "지점정보조회(이름) ";
		String action = "CALL";
		String statusCode ="0000";
		String requestMessage = request.toString();
		String responseMessage = "";//response.toString();
		String trxDate = DateUtil.getDateYYYY_MM_DDHHMMSSMISSS();

		
		BranchInfoResponse response = new BranchInfoResponse();

		int result = 0;
		if(authYN.contentEquals("Y"))
			result = authService.auth(request.getSG_APIM());

		if(result!=-1) {
			logger.info("인증성공");
			//	ViewCareersResponse response = new ViewCareersResponse();
			List<BranchInfoData> list = new ArrayList<BranchInfoData>();
			List<BranchInfoResponseSub> responseSubList = new ArrayList<BranchInfoResponseSub> ();

			int page = request.getPageNo();
			int size = request.getNumOfRows();


			logger.info("Paging:"+page+",size="+size);

			if(size==0) {
				size=10;
				logger.info("Paging:"+page+",size="+size);
			}
			Pageable paging = PageRequest.of(page, size);
			list = branchInfoDataService.findByBranchName(request.getBranchName(), paging);//.findByATMName(request.getAtmName(), paging);//.findByStdDatePaging(request.getStdYm(),paging);
			//list = jobWorldDataService.findByStdDatePaging(request.getStdYm(),);
			int totalCount = branchInfoDataService.findByBranchNameTotalCount(request.getBranchName());
			logger.info("DB Result:"+list.size());

			for(BranchInfoData data : list) {
				BranchInfoResponseSub responseSub = convertToResponseSub(data);
				responseSubList.add(responseSub);
			}

			for(BranchInfoResponseSub resSub : responseSubList) {
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
		}else {


			//추후 apiInfo 조회를 통해서 처리 
			statusCode ="1111";//코드 확인필요 
			responseMessage = "Error" ;
			//LogApiData logApiData = new LogApiData(logId,apiId,apiName,apiUrl,action,statusCode,requestMessage,responseMessage,trxDate);
			response.setResultCode("99");
			response.setResultMsg("인증실패");

		}
		LogApiData logApiData = new LogApiData(logId,docId,apiId,apiName,apiUrl,action,statusCode,requestMessage,responseMessage,trxDate);
		logApiDataService.saveApiData(logApiData);
		
		return response;			
	}

	
	
	
//	@RequestMapping(value="/branchInfoBySection",produces="application/xml")
	public  BranchInfoResponse viewBranchInfoBySectionRequestCommon(BranchInfoBySectionRequest request) {

		logger.info("BranchInfoBySectionRequest="+request.toString());
		String key = DateUtil.getDateYYYYMMDDHHMMSSMISSS();
		Random generator = new Random();   
		int num= generator.nextInt(100);    
		String logId = key + Integer.toString(num);

		String apiId= "branchInfoBySection";
		String apiUrl=	"/branchInfo/branchInfoBySection";
		String apiName = "지점정보조회(지역코드)";
		String action = "CALL";
		String statusCode ="";
		String requestMessage = request.toString();
		String responseMessage = "";
		String trxDate = DateUtil.getDateYYYY_MM_DDHHMMSSMISSS();


		
		BranchInfoResponse response = new BranchInfoResponse();

		int result = 0;
		if(authYN.contentEquals("Y"))
			result = authService.auth(request.getSG_APIM());

		if(result!=-1) {
			logger.info("인증성공");

			//	ViewCareersResponse response = new ViewCareersResponse();
			List<BranchInfoData> list = new ArrayList<BranchInfoData>();
			//응답TMInfo전문의 List
			List<BranchInfoResponseSub> responseSubList = new ArrayList<BranchInfoResponseSub> ();

			int page = request.getPageNo();
			int size = request.getNumOfRows();


			logger.info("Paging:"+page+",size="+size);

			if(size==0) {
				size=10;
				logger.info("Paging:"+page+",size="+size);
			}
			Pageable paging = PageRequest.of(page, size);
			list = branchInfoDataService.findByBranchSectionCode(request.getSectionCode(), paging);
			//.findByBranchName(request.(), paging);//.findByATMName(request.getAtmName(), paging);//.findByStdDatePaging(request.getStdYm(),paging);
			int totalCount = branchInfoDataService.findByBranchSectionCodeTotalCount(request.getSectionCode());

			//list = jobWorldDataService.findByStdDatePaging(request.getStdYm(),);
			logger.info("DB Result:"+list.size());

			for(BranchInfoData data : list) {
				BranchInfoResponseSub responseSub = convertToResponseSub(data);
				responseSubList.add(responseSub);
			}

			for(BranchInfoResponseSub resSub : responseSubList) {
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


}
