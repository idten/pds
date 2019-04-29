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
import com.ibk.pds.data.model.JobWorldData;
import com.ibk.pds.data.repository.JobWorldDataRepository;
import com.ibk.pds.log.model.DocTrxStatus;
@Service
public class JobWorldDataService {
	@Autowired
	JobWorldDataRepository jobWorldDataRepository;

	private Logger logger = LoggerFactory.getLogger(JobWorldDataService.class);

	public List<JobWorldData> getJobWorldDataList(){
		List<JobWorldData> jobWorldDataList = jobWorldDataRepository.findAll();
		return jobWorldDataList;
	}
	public DocTrxStatus addJobWorldData(JobWorldData jobWorldData) {

		//DocTrxStatus docTrxStatus = new DocTrxStatus("","");
		
		logger.info("addJobWorldData["+jobWorldData.toString());

		try {
			jobWorldDataRepository.insert(jobWorldData);

		}catch(Exception e) {
			logger.info("Insert Error:"+e.getLocalizedMessage());
			DocTrxStatus docTrxStatus = new DocTrxStatus("100",e.getLocalizedMessage());
			
			return docTrxStatus;
		}
		DocTrxStatus docTrxStatus = new DocTrxStatus("000","");
		
		//정상일 경우 
		return docTrxStatus;
	}
	public List<JobWorldData> findByStdDate(String stdDate) {
		logger.info("stdDate="+stdDate);
		//List<JobWorldData> list = jobWorldDataRepository.findByStdYM(stdDate);
	//	Pageable firstPageWithTwoElements = PageRequest.of(1, 2);
		
		List<JobWorldData> list = jobWorldDataRepository.findByStdYM(stdDate);
	//	List<JobWorldData> list = jobWorldDataRepository.findByStdYM(stdDate,firstPageWithTwoElements);
		logger.info("getByStdDate ="+list.size());
		return list;
	}
	public List<JobWorldData> findByStdDatePaging(String stdDate,Pageable page) {
		logger.info("stdDate="+stdDate);
		//List<JobWorldData> list = jobWorldDataRepository.findByStdYM(stdDate);
		
	//	List<JobWorldData> list = jobWorldDataRepository.findByStdYM(stdDate);
		List<JobWorldData> list = jobWorldDataRepository.findByStdYM(stdDate,page);
		logger.info("getByStdDate(Paging) ="+list.size());
		return list;
	}
	

	
	
	//public List<JobWorldData> findByStdYMAndIndustryCode(String stdYM, String industryCode);
	public List<JobWorldData> findByStdYMAndIndustryCode(String stdYM, String industryCode) {
		logger.info("stdDate="+stdYM);
		List<JobWorldData> list = jobWorldDataRepository.findByStdYMAndIndustryCode(stdYM, industryCode);
		//.findByStdYM(stdDate);

		logger.info("getByStdDate ="+list.size());
		return list;
	}
	public List<JobWorldData> findByStdYMAndIndustryCode(String stdYM, String industryCode,Pageable page) {
		logger.info("stdDate="+stdYM);
		List<JobWorldData> list = jobWorldDataRepository.findByStdYMAndIndustryCode(stdYM, industryCode,page);
		//.findByStdYM(stdDate);

		logger.info("getByStdDat(Paging)e ="+list.size());
		return list;
	}
	

}
