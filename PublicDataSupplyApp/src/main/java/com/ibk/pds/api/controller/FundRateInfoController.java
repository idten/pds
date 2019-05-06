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

import com.ibk.pds.api.model.ATMInfo.ATMInfoByNameRequest;
import com.ibk.pds.api.model.ATMInfo.ATMInfoBySectionRequest;
import com.ibk.pds.api.model.ATMInfo.ATMInfoResponse;
import com.ibk.pds.api.model.ATMInfo.ATMInfoResponseSub;
import com.ibk.pds.api.model.BranchInfo.BranchInfoAllRequest;
import com.ibk.pds.api.model.BranchInfo.BranchInfoByNameRequest;
import com.ibk.pds.api.model.BranchInfo.BranchInfoBySectionRequest;
import com.ibk.pds.api.model.BranchInfo.BranchInfoResponse;
import com.ibk.pds.api.model.BranchInfo.BranchInfoResponseSub;
import com.ibk.pds.api.model.FundRateInfo.FundRateInfoAllRequest;
import com.ibk.pds.api.model.FundRateInfo.FundRateInfoByIdivFnptDcdTop5Request;
import com.ibk.pds.api.model.FundRateInfo.FundRateInfoByInvmAecdTop5Request;
import com.ibk.pds.api.model.FundRateInfo.FundRateInfoRequest;
import com.ibk.pds.api.model.FundRateInfo.FundRateInfoResponse;
import com.ibk.pds.api.model.FundRateInfo.FundRateInfoResponseSub;
import com.ibk.pds.auth.service.AuthService;
import com.ibk.pds.common.util.DateUtil;
import com.ibk.pds.data.model.ATMInfoData;
import com.ibk.pds.data.model.BranchInfoData;
import com.ibk.pds.data.model.FundRateData;
import com.ibk.pds.data.model.JobWorldData;
import com.ibk.pds.data.service.ATMInfoDataService;
import com.ibk.pds.data.service.BranchInfoDataService;
import com.ibk.pds.data.service.FundRateDataService;
import com.ibk.pds.data.service.JobWorldDataService;
import com.ibk.pds.log.model.LogApiData;
import com.ibk.pds.log.service.LogApiDataService;

//아이원잡 채용공고 통계 조회 서비스 
@RestController
@RequestMapping("/api/fundRateInfo")
public class FundRateInfoController {

	@Autowired
	LogApiDataService logApiDataService;
	private Logger logger = LoggerFactory.getLogger(FundRateInfoController.class);
	//전처리
	//	//Key값에 해당되는걸로 조회  1번 api 
	//		public FundRateData findByFundAstTcdAndFundInvmAecdAndPdrsGdcdAndIdivFnptDcd
	//		(String fundAstTcd, String fundInvmAecd,String pdrsGdcd,String idivFnptDcd)
	////수익률별 TOP5 
	//		public List<FundRateData> findByIdivFnptDcd(String idivFnptDcd, String ernnRtDcd) {
	//		public List<FundRateData> findByFundInvmAecd(String fundInvmAecd, String ernnRtDcd) {
	@Autowired
	AuthService authService;
	@Value("${authYN}")
	private String authYN;

	//필터
	@Autowired
	FundRateDataService fundRateDataService;
	public FundRateInfoResponseSub convertToResponseSub(FundRateData data) {
		//		String branchName = "";
		//		String branchPhoneNumber= "";
		//		String branchAddress = "";
		//		String branchSection = "";
		//		String branchSectionCode = "";
		//		
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


	//검색기준: atmName
	@RequestMapping(value="/fundRateInfoAll",produces="application/xml")
	public  FundRateInfoResponse viewFundRateInfoAll(@RequestBody FundRateInfoAllRequest request) {

		logger.info("FundRateInfoAllRequest="+request.toString());
		String key = DateUtil.getDateYYYYMMDDHHMMSSMISSS();
		Random generator = new Random();   
		int num= generator.nextInt(100);    
		String logId = key + Integer.toString(num);

		String apiId= "fundRateInfoAll";
		String apiUrl=	"/fundRateInfo/fundRateInfoAll";
		FundRateInfoResponse response = new FundRateInfoResponse();

		int result = 0;
		if(authYN.contentEquals("Y"))
			result = authService.auth();

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

			logger.info("DB Result:"+list.size());

			for(FundRateData data : list) {
				FundRateInfoResponseSub responseSub = convertToResponseSub(data);
				responseSubList.add(responseSub);
			}

			for(FundRateInfoResponseSub resSub : responseSubList) {
				logger.info("select Result="+resSub.toString());

			}
			response.setItem(responseSubList);
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


	//검색기준: 1개검색 
	@RequestMapping(value="/fundRateInfo",produces="application/xml")
	public  FundRateInfoResponse viewFundRateInfo(@RequestBody FundRateInfoRequest request) {

		logger.info("FundRateInfoRequest="+request.toString());
		String key = DateUtil.getDateYYYYMMDDHHMMSSMISSS();
		Random generator = new Random();   
		int num= generator.nextInt(100);    
		String logId = key + Integer.toString(num);

		String apiId= "fundRateInfo";
		String apiUrl=	"/fundRateInfo/fundRateInfo";
		FundRateInfoResponse response = new FundRateInfoResponse();

		int result = 0;
		if(authYN.contentEquals("Y"))
			result = authService.auth();

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
			response.setItem(responseSubList);
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

	//검색기준: atmName
	@RequestMapping(value="/fundRateInfoByIdivFnptDcdTop5",produces="application/xml")
	public  FundRateInfoResponse ViewFundRateInfoByIdivFnptDcdTop5(@RequestBody FundRateInfoByIdivFnptDcdTop5Request request) {

		logger.info("FundRateInfoByIdivFnptDcdTop5Request="+request.toString());
		String key = DateUtil.getDateYYYYMMDDHHMMSSMISSS();
		Random generator = new Random();   
		int num= generator.nextInt(100);    
		String logId = key + Integer.toString(num);

		String apiId= "fundRateInfoAll";
		String apiUrl=	"/fundRateInfo/fundRateInfoByIdivFnptDcdTop5";
		FundRateInfoResponse response = new FundRateInfoResponse();

		int result = 0;
		if(authYN.contentEquals("Y"))
			result = authService.auth();

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
			//list = jobWorldDataService.findByStdDatePaging(request.getStdYm(),);

			logger.info("DB Result:"+list.size());

			for(FundRateData data : list) {
				FundRateInfoResponseSub responseSub = convertToResponseSub(data);
				responseSubList.add(responseSub);
			}

			for(FundRateInfoResponseSub resSub : responseSubList) {
				logger.info("select Result="+resSub.toString());

			}
			response.setItem(responseSubList);
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

	//검색기준: atmName
	@RequestMapping(value="/fundRateInfoByInvmAecdTop5",produces="application/xml")
	public  FundRateInfoResponse ViewFundRateInfoByInvmAecdTop5(@RequestBody FundRateInfoByInvmAecdTop5Request request) {

		logger.info("FundRateInfoByInvmAecdTop5Request="+request.toString());
		String key = DateUtil.getDateYYYYMMDDHHMMSSMISSS();
		Random generator = new Random();   
		int num= generator.nextInt(100);    
		String logId = key + Integer.toString(num);

		String apiId= "fundRateInfoAll";
		String apiUrl=	"/fundRateInfo/FundRateInfoByInvmAecdTop5Request";
		FundRateInfoResponse response = new FundRateInfoResponse();

		int result = 0;
		if(authYN.contentEquals("Y"))
			result = authService.auth();

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
			response.setItem(responseSubList);
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
