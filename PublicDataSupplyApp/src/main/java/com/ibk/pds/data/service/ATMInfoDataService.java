package com.ibk.pds.data.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ibk.pds.common.model.ApiInfo;
import com.ibk.pds.common.model.DocumentInfo;
import com.ibk.pds.common.model.UserInfo;
import com.ibk.pds.common.repository.ApiInfoRepository;
import com.ibk.pds.common.service.ApiInfoService;
import com.ibk.pds.data.model.ATMInfoData;
import com.ibk.pds.data.model.BranchInfoData;
import com.ibk.pds.data.model.JobWorldData;
import com.ibk.pds.data.repository.ATMInfoDataRepository;
import com.ibk.pds.data.repository.BranchInfoDataRepository;
import com.ibk.pds.data.repository.JobWorldDataRepository;
import com.ibk.pds.log.model.DocTrxStatus;
@Service
public class ATMInfoDataService {
	@Autowired
	ATMInfoDataRepository atmInfoDataRepository;

	private Logger logger = LoggerFactory.getLogger(ATMInfoDataService.class);

	public List<ATMInfoData> getBranchInfoDataList(){
		List<ATMInfoData> atmInfoDataList = atmInfoDataRepository.findAll();
		return atmInfoDataList;
	}

	//전체 목록 리턴 
	public List<ATMInfoData> getATMInfoDataList(Pageable page){
		List<ATMInfoData> atmInfoDataList = atmInfoDataRepository.findAll(page).getContent();
		return atmInfoDataList;
	}
	
	public DocTrxStatus addATMInfoData(ATMInfoData atmInfoData) {
		logger.info("addBranchInfoData["+atmInfoData.toString());
		try {
			atmInfoDataRepository.insert(atmInfoData);
		}catch(Exception e) {
			logger.info("Insert Error:"+e.getLocalizedMessage());
			DocTrxStatus docTrxStatus = new DocTrxStatus("100",e.getLocalizedMessage());			
			return docTrxStatus;
		}
		DocTrxStatus docTrxStatus = new DocTrxStatus("000","");
		//정상일 경우 
		return docTrxStatus;
	}
//	public Page<ATMInfoData> findAll(Pageable pageable);
//	public List<ATMInfoData> findByATMName(String atmName, Pageable pageable);
//	public List<ATMInfoData> findByATMSectionCode(String atmSectionCode, Pageable pageable);	
	
	public List<ATMInfoData> findByATMName(String atmName,Pageable page) {
		logger.info("atmName="+atmName);
		List<ATMInfoData> list = atmInfoDataRepository.findByATMName(atmName, page);//.findByStdYM(stdDate,page);
		logger.info("findByBranchName(Paging) ="+list.size());
		return list;
	}
	
	public List<ATMInfoData> findByATMSectionCode(String atmSectionCode,Pageable page) {
		logger.info("atmSectionCode="+atmSectionCode);
		List<ATMInfoData> list = atmInfoDataRepository.findByATMSectionCode(atmSectionCode, page);
		logger.info("findByATMSectionCode(Paging) ="+list.size());
		return list;
	}
}
