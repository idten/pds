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
import com.ibk.pds.common.util.DateUtil;
import com.ibk.pds.data.model.EmploymentInfoData;
import com.ibk.pds.data.model.FundRateData;
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


	public DocTrxStatus addMonthlyExchangeRateData(MonthlyExchangeRateData monthlyExchangeRateData) {

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
	
	public DocTrxStatus addMonthlyExchangeRateDataFromExcel(List<String> cellList,String approval) {
		String today = DateUtil.getDateYYYYMMDD();
		String key = DateUtil.getDateYYYYMMDDHHMMSS();
		
		DocTrxStatus docTrxStatus = new DocTrxStatus("000","");
		String updateCode;
		String uploadDate;
		updateCode = "D"+key;
		uploadDate = today;
		
		
		
		String stdCurrency = cellList.get(0);
		//상태 통화
		String relativeCurrency = cellList.get(1);
		
		String monthly1Rate = cellList.get(2);
		String monthly2Rate = cellList.get(3);
		String monthly3Rate = cellList.get(4);
		String monthly4Rate = cellList.get(5);
		String monthly5Rate = cellList.get(6);
		String monthly6Rate = cellList.get(7);
		String monthly7Rate = cellList.get(8);
		String monthly8Rate = cellList.get(9);
		String monthly9Rate = cellList.get(10);
		String monthly10Rate = cellList.get(11);
		String monthly11Rate = cellList.get(12);
		String monthly12Rate = cellList.get(13);
		
		//공통 코드 
		

		String dataId=stdCurrency+relativeCurrency; 
		
		
		MonthlyExchangeRateData monthlyExchangeRateData = new MonthlyExchangeRateData(dataId, stdCurrency, relativeCurrency,
				monthly1Rate, monthly2Rate, monthly3Rate, monthly4Rate, monthly5Rate, monthly6Rate, 
				monthly7Rate, monthly8Rate, monthly9Rate, monthly10Rate, monthly11Rate, monthly12Rate, 
				approval,updateCode,uploadDate);
		
		addMonthlyExchangeRateData(monthlyExchangeRateData);
	//	addFundRateData(fundRateData);
		logger.info("MonthlyExchangeRateData Data insert:"+monthlyExchangeRateData.toString());
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
	public List<MonthlyExchangeRateData> findAll() {
		List<MonthlyExchangeRateData> list = monthlyExchangeRateDataRepository.findAll();
		logger.info("findAll ="+list.size());
		return list;
	}
	public int totalCount() {
		return  (int)monthlyExchangeRateDataRepository.count();
	}
	
	public int findByStdCurrencyTotalCount(String stdCurrency) {
		return monthlyExchangeRateDataRepository.findByStdCurrency(stdCurrency).size();
		
	}
	
	
//	public List<EmploymentInfoData> findAll(Pageable page){
//		List<EmploymentInfoData> list = employmentInfoDataRepository.findAll(page).getContent();
//		return list;
//	}
//	
	
}
