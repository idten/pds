package com.ibk.pds.data.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ibk.pds.common.model.ApiInfo;
import com.ibk.pds.common.model.DocumentInfo;
import com.ibk.pds.common.model.UserInfo;
import com.ibk.pds.common.repository.ApiInfoRepository;
import com.ibk.pds.common.service.ApiInfoService;
import com.ibk.pds.data.model.FundRateData;
import com.ibk.pds.data.model.JobWorldData;
import com.ibk.pds.data.model.MonthlyExchangeRateData;
import com.ibk.pds.data.repository.FundRateDataRepository;
import com.ibk.pds.data.repository.JobWorldDataRepository;
import com.ibk.pds.log.model.DocTrxStatus;
@Service
public class FundRateDataService {

	@Autowired
	FundRateDataRepository fundRateDataRepository;

	private Logger logger = LoggerFactory.getLogger(FundRateDataService.class);

	//전체 리스트 
	public List<FundRateData> getFundRateDataList(){
		List<FundRateData> fundRateDataList = fundRateDataRepository.findAll();
		return fundRateDataList;
	}


	public DocTrxStatus addFundRateData(FundRateData fundRateData) {
		logger.info("addFundRateData["+fundRateData.toString());
		try {
			fundRateDataRepository.insert(fundRateData);
		}catch(Exception e) {
			logger.info("Insert Error:"+e.getLocalizedMessage());
			DocTrxStatus docTrxStatus = new DocTrxStatus("100",e.getLocalizedMessage());			
			return docTrxStatus;
		}
		DocTrxStatus docTrxStatus = new DocTrxStatus("000","");

		//정상일 경우 
		return docTrxStatus;
	}
	
	
	
	
	
	
	
	
	
	public List<FundRateData> findAll(Pageable page){
		List<FundRateData> fundRateDataList = fundRateDataRepository.findAll(page).getContent();
		return fundRateDataList;
	}
	
//	public List<MonthlyExchangeRateData> findAll(Pageable page) {
//		List<MonthlyExchangeRateData> list = monthlyExchangeRateDataRepository.findAll(page).getContent();
//		logger.info("findAll ="+list.size());
//		return list;
//	}
//	
	
	
	//Key값에 해당되는걸로 조회  1번 api 
//	public FundRateData findByFundAstTcdAndFundInvmAecdAndPdrsGdcdAndIdivFnptDcd
//	(String fundAstTcd, String fundInvmAecd,String pdrsGdcd,String idivFnptDcd)
//	{
//
//		logger.info("find.조건="+fundAstTcd+","+fundInvmAecd+","+pdrsGdcd+","+idivFnptDcd);
//
//		FundRateData fundRateData = fundRateDataRepository.findByFundAstTcdAndFundInvmAecdAndPdrsGdcdAndIdivFnptDcd(fundAstTcd, fundInvmAecd, pdrsGdcd, idivFnptDcd);
//		logger.info("findByFundAstTcdAndFundInvmAecdAndPdrsGdcdAndIdivFnptDcd.toString()"+fundRateData.toString());
//		return fundRateData;
//	}

	public List<FundRateData> findOne
	(String fundAstTcd, String fundInvmAecd,String pdrsGdcd,String idivFnptDcd,Pageable page)
	{

		logger.info("find.조건="+fundAstTcd+","+fundInvmAecd+","+pdrsGdcd+","+idivFnptDcd);

		List<FundRateData> list = fundRateDataRepository.findByFundAstTcdAndFundInvmAecdAndPdrsGdcdAndIdivFnptDcd(fundAstTcd, fundInvmAecd, pdrsGdcd, idivFnptDcd,page);
		//logger.info("findByFundAstTcdAndFundInvmAecdAndPdrsGdcdAndIdivFnptDcd.toString()"+fundRateData.toString());
		return list;
	}
	
	//수익률별 TOP5 
	public List<FundRateData> findByIdivFnptDcdTop5(String idivFnptDcd, String ernnRtDcd) {
		//Pageable page = new Pageable();
		Pageable paging = PageRequest.of(1, 5);
		List<FundRateData> list = new ArrayList<FundRateData>();

		//1개월 수익률 기준
		if(ernnRtDcd.contentEquals("01")) {
			list = fundRateDataRepository.findByIdivFnptDcdOrderByTrmMn1ErnnRt(idivFnptDcd, paging);
		//3개월 수익률 기준
		}else if(ernnRtDcd.contentEquals("02")) {
			list = fundRateDataRepository.findByIdivFnptDcdOrderByTrmMn3ErnnRt(idivFnptDcd, paging);
		//6개월 수익률 기준	
		}else if(ernnRtDcd.contentEquals("03")) {
			list = fundRateDataRepository.findByIdivFnptDcdOrderByTrmMn6ErnnRt(idivFnptDcd, paging);
		//12개월 수익률 기준
		}else if(ernnRtDcd.contentEquals("04")) {
			list = fundRateDataRepository.findByIdivFnptDcdOrderByTrmMn12ErnnRt(idivFnptDcd, paging);
		}
		logger.info("idivFnptDcd="+idivFnptDcd);

		logger.info("list.size() ="+list.size());
		return list;
	}
	
	public List<FundRateData> findByFundInvmAecdTop5(String fundInvmAecd, String ernnRtDcd) {
		//Pageable page = new Pageable();
		Pageable paging = PageRequest.of(1, 5);
		List<FundRateData> list = new ArrayList<FundRateData>();

		//1개월 수익률 기준
		if(ernnRtDcd.contentEquals("01")) {
			list = fundRateDataRepository.findByFundInvmAecdOrderByTrmMn1ErnnRt(fundInvmAecd, paging);
		//3개월 수익률 기준
		}else if(ernnRtDcd.contentEquals("02")) {
			list = fundRateDataRepository.findByFundInvmAecdOrderByTrmMn3ErnnRt(fundInvmAecd, paging);
		//6개월 수익률 기준	
		}else if(ernnRtDcd.contentEquals("03")) {
			list = fundRateDataRepository.findByFundInvmAecdOrderByTrmMn6ErnnRt(fundInvmAecd, paging);
		//12개월 수익률 기준
		}else if(ernnRtDcd.contentEquals("04")) {
			list = fundRateDataRepository.findByFundInvmAecdOrderByTrmMn12ErnnRt(fundInvmAecd, paging);
		}
		logger.info("FundInvmAecd="+fundInvmAecd);

		logger.info("list.size() ="+list.size());
		return list;
	}

}
