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
import com.ibk.pds.api.model.BranchInfo.BranchInfoByNameRequest;
import com.ibk.pds.api.model.BranchInfo.BranchInfoBySectionRequest;
import com.ibk.pds.api.model.BranchInfo.BranchInfoResponse;
import com.ibk.pds.api.model.BranchInfo.BranchInfoResponseSub;
import com.ibk.pds.api.model.EmploymentInfo.ViewCareersDetailRequest;
import com.ibk.pds.api.model.EmploymentInfo.ViewCareersRequest;
import com.ibk.pds.api.model.EmploymentInfo.ViewCareersResponse;
import com.ibk.pds.api.model.EmploymentInfo.ViewCareersResponseSub;
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
@RequestMapping("/fundInfo")
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
	
	
	//검색기준: atmName
	@RequestMapping(value="/branchInfoByName",produces="application/xml")
	public  BranchInfoResponse viewBranchInfoByNameRequest(@RequestBody BranchInfoByNameRequest request) {

		logger.info("BranchInfoByNameRequest="+request.toString());
		String key = DateUtil.getDateYYYYMMDDHHMMSSMISSS();
		Random generator = new Random();   
		int num= generator.nextInt(100);    
		String logId = key + Integer.toString(num);

		String apiId= "branchInfoByName";
		String apiUrl=	"/branchInfo/branchInfoByName";
		BranchInfoResponse response = new BranchInfoResponse();

		int result = 0;
		if(authYN.contentEquals("Y"))
			result = authService.auth();

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

			logger.info("DB Result:"+list.size());
			
			for(BranchInfoData data : list) {
				BranchInfoResponseSub responseSub = convertToResponseSub(data);
				responseSubList.add(responseSub);
			}

			for(BranchInfoResponseSub resSub : responseSubList) {
				logger.info("select Result="+resSub.toString());

			}
			response.setItem(responseSubList);
			logger.info("인증수행 여부 ="+authYN);
			//추후 apiInfo 조회를 통해서 처리 
			String apiName = "branchInfoByName ";
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
