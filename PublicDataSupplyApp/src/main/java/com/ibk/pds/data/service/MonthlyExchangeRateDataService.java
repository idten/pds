package com.ibk.pds.data.service;

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
import com.ibk.pds.data.model.EmploymentInfoData;
import com.ibk.pds.data.model.JobWorldData;
import com.ibk.pds.data.model.MonthlyExchangeRateData;
import com.ibk.pds.data.repository.JobWorldDataRepository;
import com.ibk.pds.data.repository.MonthlyExchangeRateDataRepository;
import com.ibk.pds.log.model.DocTrxStatus;

// 2019.04.30 박현조
// 월별환율정보 조회 
// 조회기준- 기준 환율

@Service
public class MonthlyExchangeRateDataService {
	@Autowired
	MonthlyExchangeRateDataRepository monthlyExchangeRateDataRepository;

	private Logger logger = LoggerFactory.getLogger(MonthlyExchangeRateDataService.class);
	public List<MonthlyExchangeRateData> getJobWorldDataList(){
		List<MonthlyExchangeRateData> monthlyExchangeRateDataList = monthlyExchangeRateDataRepository.findAll();
		return monthlyExchangeRateDataList;
	}


	public DocTrxStatus addJobWorldData(MonthlyExchangeRateData monthlyExchangeRateData) {

		logger.info("MonthlyExchangeRateData["+monthlyExchangeRateData.toString());

		try {
			monthlyExchangeRateDataRepository.insert(monthlyExchangeRateData);
		}catch(Exception e) {
			logger.info("Insert Error:"+e.getLocalizedMessage());
			DocTrxStatus docTrxStatus = new DocTrxStatus("100",e.getLocalizedMessage());

			return docTrxStatus;
		}
		DocTrxStatus docTrxStatus = new DocTrxStatus("000","");
		return docTrxStatus;
	}
	public List<MonthlyExchangeRateData> findByStdCurrency(String stdCurrency) {
		logger.info("stdCurrency="+stdCurrency);
		List<MonthlyExchangeRateData> list = monthlyExchangeRateDataRepository.findByStdCurrency(stdCurrency);
		logger.info("findByStdCurrency ="+list.size());
		return list;
	}
	public List<MonthlyExchangeRateData> findByStdCurrency(String stdCurrency,Pageable page) {
		logger.info("stdCurrency="+stdCurrency);
		List<MonthlyExchangeRateData> list = monthlyExchangeRateDataRepository.findByStdCurrency(stdCurrency,page);
		logger.info("findByStdCurrency ="+list.size());
		return list;
	}
	public List<MonthlyExchangeRateData> findAll(Pageable page) {
		List<MonthlyExchangeRateData> list = monthlyExchangeRateDataRepository.findAll(page).getContent();
		logger.info("findAll ="+list.size());
		return list;
	}
	
//	public List<EmploymentInfoData> findAll(Pageable page){
//		List<EmploymentInfoData> list = employmentInfoDataRepository.findAll(page).getContent();
//		return list;
//	}
//	
	
}
