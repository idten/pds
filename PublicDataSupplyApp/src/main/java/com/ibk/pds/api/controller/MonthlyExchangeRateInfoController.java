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
import com.ibk.pds.api.model.MonthlyExchangeRateInfo.ViewMonthlyExchangeRateByStdCurrencyRequest;
import com.ibk.pds.auth.service.AuthService;
import com.ibk.pds.common.util.DateUtil;
import com.ibk.pds.data.model.JobWorldData;
import com.ibk.pds.data.model.MonthlyExchangeRateData;
import com.ibk.pds.data.service.JobWorldDataService;
import com.ibk.pds.data.service.MonthlyExchangeRateDataService;
import com.ibk.pds.log.model.LogApiData;
import com.ibk.pds.log.service.LogApiDataService;

//아이원잡 채용공고 통계 조회 서비스 
@RestController
@RequestMapping("/monthlyExchangeRateInfo")
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

	//검색기준: stdYm  년월 
	@RequestMapping(value="/viewMontlyExchangeByStdCurrency",produces="application/xml")
	public  ViewCareersResponse viewMontlyExchangeByStdCurrency(@RequestBody ViewMonthlyExchangeRateByStdCurrencyRequest request) {

		logger.info("viewMontlyExchangeByStdCurrency="+request.toString());
		String key = DateUtil.getDateYYYYMMDDHHMMSSMISSS();
		Random generator = new Random();   
		int num= generator.nextInt(100);    
		String logId = key + Integer.toString(num);

		String apiId= "viewMontlyExchangeByStdCurrency";
		String apiUrl=	"/monthlyExchangeRateInfo/viewMontlyExchangeByStdCurrency";
		ViewCareersResponse response = new ViewCareersResponse();

		int result = 0;
		if(authYN.contentEquals("Y"))
			result = authService.auth();

		if(result!=-1) {
			logger.info("인증성공");

			//	ViewCareersResponse response = new ViewCareersResponse();
			List<MonthlyExchangeRateData> list = new ArrayList<MonthlyExchangeRateData>();
			//응답전문의 List
		//	List<ViewCareersResponseSub> responseSubList = new ArrayList<ViewCareersResponseSub> ();

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
			list = monthlyExchangeRateDataService.findByStdDatePaging(request.getStdCurrency(),paging);

			//list = jobWorldDataService.findByStdDatePaging(request.getStdYm(),);

//			String stdYm = "";
//			String industryName = "";
//			String industryCode = "";
//
//			String detailIndustryName = "";
//			int careersCount=0;
//			String careersPer="";
//			private String stdCurrency;
//			//상태 통화
//			private String relativeCurrency;
//			
//			private String monthly1Rate;
//			private String monthly2Rate;
//			private String monthly3Rate;
//			private String monthly4Rate;
//			private String monthly5Rate;
//			private String monthly6Rate;
//			private String monthly7Rate;
//			private String monthly8Rate;
//			private String monthly9Rate;
//			private String monthly10Rate;
//			private String monthly11Rate;
//			private String monthly12Rate;
//			
			String stdCurrency = "";
//			String 
			logger.info("DB Result:"+list.size());
			for(MonthlyExchangeRateData data : list) {

				
				
				
				stdYm = data.getStdYM();
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

}
