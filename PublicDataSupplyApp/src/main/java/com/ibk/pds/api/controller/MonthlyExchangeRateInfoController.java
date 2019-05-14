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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibk.pds.api.model.BranchInfo.BranchInfoResponseSub;
import com.ibk.pds.api.model.MonthlyExchangeRateInfo.MonthlyExchangeRateInfoAllRequest;
import com.ibk.pds.api.model.MonthlyExchangeRateInfo.MonthlyExchangeRateInfoByStdCurrencyRequest;
import com.ibk.pds.api.model.MonthlyExchangeRateInfo.MonthlyExchangeRateInfoResponse;
import com.ibk.pds.api.model.MonthlyExchangeRateInfo.MonthlyExchangeRateInfoResponseSub;
import com.ibk.pds.auth.service.AuthService;
import com.ibk.pds.common.util.DateUtil;
import com.ibk.pds.data.model.BranchInfoData;
import com.ibk.pds.data.model.JobWorldData;
import com.ibk.pds.data.model.MonthlyExchangeRateData;
import com.ibk.pds.data.service.JobWorldDataService;
import com.ibk.pds.data.service.MonthlyExchangeRateDataService;
import com.ibk.pds.log.model.LogApiData;
import com.ibk.pds.log.service.LogApiDataService;

//아이원잡 채용공고 통계 조회 서비스 
@RestController
@RequestMapping("/api/monthlyExchangeRateInfo")
public class MonthlyExchangeRateInfoController {

	@Autowired
	LogApiDataService logApiDataService;
	private Logger logger = LoggerFactory.getLogger(MonthlyExchangeRateInfoController.class);
	//전처리

	@Autowired
	AuthService authService;
	@Value("${authYN}")
	private String authYN;

	//필터
	@Autowired
	MonthlyExchangeRateDataService monthlyExchangeRateDataService;

	public MonthlyExchangeRateInfoResponseSub convertToResponseSub(MonthlyExchangeRateData data) {
		String stdCurrency;
		//		//상태 통화
		String relativeCurrency;
		String monthly1Rate;
		String monthly2Rate;
		String monthly3Rate;
		String monthly4Rate;
		String monthly5Rate;
		String monthly6Rate;
		String monthly7Rate;
		String monthly8Rate;
		String monthly9Rate;
		String monthly10Rate;
		String monthly11Rate;
		String monthly12Rate;


		stdCurrency = data.getStdCurrency();
		relativeCurrency = data.getRelativeCurrency();
		monthly1Rate = data.getMonthly1Rate();
		monthly2Rate = data.getMonthly2Rate();
		monthly3Rate = data.getMonthly3Rate();
		monthly4Rate = data.getMonthly4Rate();
		monthly5Rate = data.getMonthly5Rate();
		monthly6Rate = data.getMonthly6Rate();
		monthly7Rate = data.getMonthly7Rate();
		monthly8Rate = data.getMonthly8Rate();
		monthly9Rate = data.getMonthly9Rate();
		monthly10Rate = data.getMonthly10Rate();
		monthly11Rate = data.getMonthly11Rate();
		monthly12Rate = data.getMonthly12Rate();

		MonthlyExchangeRateInfoResponseSub responseSub = new MonthlyExchangeRateInfoResponseSub(
				stdCurrency,relativeCurrency, 
				monthly1Rate,
				monthly2Rate,
				monthly3Rate,
				monthly4Rate,
				monthly5Rate,
				monthly6Rate,
				monthly7Rate,
				monthly8Rate,
				monthly9Rate,
				monthly10Rate,
				monthly11Rate,
				monthly12Rate
				);

		return responseSub;
	}
	@RequestMapping(value="/montlyExchangeRateAll",produces="application/xml",method=RequestMethod.POST)
	public  MonthlyExchangeRateInfoResponse viewMontlyExchangeAll(@RequestBody MonthlyExchangeRateInfoAllRequest request) {

		MonthlyExchangeRateInfoResponse response = viewMontlyExchangeAllCommon(request);
		return response;
	}

	@RequestMapping(value="/montlyExchangeRateAll",produces="application/xml", method=RequestMethod.GET)
	public  MonthlyExchangeRateInfoResponse viewMontlyExchangeAll(
			@RequestParam(value="numOfRows", 	required=false, defaultValue="10") int numOfRows, 
			@RequestParam(value="pageNo", 		required=false, defaultValue="0") int pageNo,
			@RequestParam(value="serviceKey", 	required=false, defaultValue="defaultKey") String serviceKey,
			@RequestParam(value="SG_APIM", 		required=false, defaultValue="defaultKey") String SG_APIM
	
			) {
		MonthlyExchangeRateInfoAllRequest request =new MonthlyExchangeRateInfoAllRequest(serviceKey,numOfRows,pageNo,SG_APIM);
		MonthlyExchangeRateInfoResponse response = viewMontlyExchangeAllCommon(request);
		return response;
	}
	@RequestMapping(value="/montlyExchangeRateByStdCurrency",produces="application/xml",method=RequestMethod.POST)
	public  MonthlyExchangeRateInfoResponse viewMontlyExchangeByStdCurrency(@RequestBody MonthlyExchangeRateInfoByStdCurrencyRequest request) {
		MonthlyExchangeRateInfoResponse response = viewMontlyExchangeByStdCurrencyCommon(request);
		return response;
	}
	@RequestMapping(value="/montlyExchangeRateByStdCurrency",produces="application/xml",method=RequestMethod.GET)
	public  MonthlyExchangeRateInfoResponse viewMontlyExchangeByStdCurrency(
			@RequestParam(value="numOfRows", 	required=false, defaultValue="10") int numOfRows, 
			@RequestParam(value="pageNo", 		required=false, defaultValue="0") int pageNo,
			@RequestParam(value="serviceKey", 	required=false, defaultValue="defaultKey") String serviceKey,
			@RequestParam(value="SG_APIM", 		required=false, defaultValue="defaultKey") String SG_APIM,
			@RequestParam(value="stdCurrency",	required=false, defaultValue="USD") String stdCurrency
			
			) {
		MonthlyExchangeRateInfoByStdCurrencyRequest request =new MonthlyExchangeRateInfoByStdCurrencyRequest(
				serviceKey,numOfRows,pageNo,stdCurrency,SG_APIM);
		MonthlyExchangeRateInfoResponse response = viewMontlyExchangeByStdCurrencyCommon(request);
		return response;
	}
	//기준 통화 조건 
	//@RequestMapping(value="/montlyExchangeRateAll",produces="application/xml")
	public  MonthlyExchangeRateInfoResponse viewMontlyExchangeAllCommon(MonthlyExchangeRateInfoAllRequest request) {

		logger.info("MonthlyExchangeRateInfoAllRequest="+request.toString());
		String key = DateUtil.getDateYYYYMMDDHHMMSSMISSS();
		Random generator = new Random();   
		int num= generator.nextInt(100);    
		String logId = key + Integer.toString(num);

		String apiId= "montlyExchangeRateAll";
		String apiUrl=	"/monthlyExchangeRateInfo/montlyExchangeRateAll";
		//		ViewCareersResponse response = new ViewCareersResponse();
		MonthlyExchangeRateInfoResponse response = new MonthlyExchangeRateInfoResponse() ;
		int result = 0;
		if(authYN.contentEquals("Y"))
			result = authService.auth(request.getSG_APIM());

		if(result!=-1) {
			logger.info("인증성공");

			List<MonthlyExchangeRateData> list = new ArrayList<MonthlyExchangeRateData>();
			//응답전문의 List
			List<MonthlyExchangeRateInfoResponseSub> responseSubList = new ArrayList<MonthlyExchangeRateInfoResponseSub> ();

			int page = request.getPageNo();
			int size = request.getNumOfRows();


			logger.info("Paging:"+page+",size="+size);

			if(size==0) {
				size=10;
				logger.info("Paging:"+page+",size="+size);
			}
			Pageable paging = PageRequest.of(page, size);
			list = monthlyExchangeRateDataService.findAll(paging);
			int totalCount = monthlyExchangeRateDataService.totalCount();
			String resultCode;// = resultCode;
			String resultMsg;
			Integer numOfRows = list.size();
			//			
			logger.info("DB Result:"+list.size());


			for(MonthlyExchangeRateData data : list) {
				MonthlyExchangeRateInfoResponseSub responseSub = convertToResponseSub(data);
				responseSubList.add(responseSub);
			}
			response.setItem(responseSubList);
			response.setTotalCount(totalCount);
			response.setResultCode("00");
			response.setResultMsg("OK");
			response.setNumOfRows(size);
			response.setPageNo(page);







			logger.info("인증수행 여부 ="+authYN);
			//추후 apiInfo 조회를 통해서 처리 
			String apiName = apiId;
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
			String apiName = apiId;
			String action = "CALL";
			String statusCode ="1111";//코드 확인필요 
			String requestMessage = request.toString();
			String responseMessage = response.toString();
			String trxDate = DateUtil.getDateYYYY_MM_DDHHMMSSMISSS();


			LogApiData logApiData = new LogApiData(logId,apiId,apiName,apiUrl,action,statusCode,requestMessage,responseMessage,trxDate);

		}

		return response;			
	}
	//기준 통화 조건 
	

	//기준 통화 조건 
	//@RequestMapping(value="/montlyExchangeRateByStdCurrency",produces="application/xml")
	public  MonthlyExchangeRateInfoResponse viewMontlyExchangeByStdCurrencyCommon(MonthlyExchangeRateInfoByStdCurrencyRequest request) {

		logger.info("viewMontlyExchangeByStdCurrency="+request.toString());
		String key = DateUtil.getDateYYYYMMDDHHMMSSMISSS();
		Random generator = new Random();   
		int num= generator.nextInt(100);    
		String logId = key + Integer.toString(num);

		String apiId= "montlyExchangeRateAll";
		String apiUrl=	"/monthlyExchangeRateInfo/montlyExchangeRateAll";
		//		ViewCareersResponse response = new ViewCareersResponse();
		MonthlyExchangeRateInfoResponse response = new MonthlyExchangeRateInfoResponse() ;
		int result = 0;
		if(authYN.contentEquals("Y"))
			result = authService.auth(request.getSG_APIM());

		if(result!=-1) {
			logger.info("인증성공");

			List<MonthlyExchangeRateData> list = new ArrayList<MonthlyExchangeRateData>();
			//응답전문의 List
			List<MonthlyExchangeRateInfoResponseSub> responseSubList = new ArrayList<MonthlyExchangeRateInfoResponseSub> ();

			int page = request.getPageNo();
			int size = request.getNumOfRows();


			logger.info("Paging:"+page+",size="+size);

			if(size==0) {
				size=10;
				logger.info("Paging:"+page+",size="+size);
			}
			Pageable paging = PageRequest.of(page, size);
			list = monthlyExchangeRateDataService.findByStdCurrency(request.getStdCurrency(), paging);
			int totalCount = monthlyExchangeRateDataService.findByStdCurrencyTotalCount(request.getStdCurrency());
			String resultCode;// = resultCode;
			String resultMsg;
			Integer numOfRows = list.size();
			//			
			logger.info("DB Result:"+list.size());


			for(MonthlyExchangeRateData data : list) {
				MonthlyExchangeRateInfoResponseSub responseSub = convertToResponseSub(data);
				responseSubList.add(responseSub);
			}
			response.setItem(responseSubList);
			response.setTotalCount(totalCount);
			response.setResultCode("00");
			response.setResultMsg("OK");
			response.setNumOfRows(size);
			response.setPageNo(page);


			logger.info("인증수행 여부 ="+authYN);
			//추후 apiInfo 조회를 통해서 처리 
			String apiName = apiId;
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
			String apiName = apiId;
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
