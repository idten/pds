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
import com.ibk.pds.data.model.BranchInfoData;
import com.ibk.pds.data.model.JobWorldData;
import com.ibk.pds.data.repository.BranchInfoDataRepository;
import com.ibk.pds.data.repository.JobWorldDataRepository;
import com.ibk.pds.log.model.DocTrxStatus;
@Service
public class BranchInfoDataService {
	@Autowired
	BranchInfoDataRepository branchInfoDataRepository;

	private Logger logger = LoggerFactory.getLogger(BranchInfoDataService.class);

	public List<BranchInfoData> getBranchInfoDataList(){
		List<BranchInfoData> branchInfoDataList = branchInfoDataRepository.findAll();
		return branchInfoDataList;
	}

	//전체 목록 리턴 
	public List<BranchInfoData> getBranchInfoDataList(Pageable page){
		List<BranchInfoData> branchInfoDataList = branchInfoDataRepository.findAll(page).getContent();
		return branchInfoDataList;
	}
	
	public DocTrxStatus addBranchInfoData(BranchInfoData branchInfoData) {
		logger.info("addBranchInfoData["+branchInfoData.toString());
		try {
			branchInfoDataRepository.insert(branchInfoData);
		}catch(Exception e) {
			logger.info("Insert Error:"+e.getLocalizedMessage());
			DocTrxStatus docTrxStatus = new DocTrxStatus("100",e.getLocalizedMessage());			
			return docTrxStatus;
		}
		DocTrxStatus docTrxStatus = new DocTrxStatus("000","");
		//정상일 경우 
		return docTrxStatus;
	}

	
	
	public List<BranchInfoData> findAll(Pageable page){
		List<BranchInfoData> branchInfoDataList = branchInfoDataRepository.findAll(page).getContent();
		return branchInfoDataList;
	}
	
	
	public List<BranchInfoData> findByBranchName(String branchName,Pageable page) {
		logger.info("branchName="+branchName);
		List<BranchInfoData> list = branchInfoDataRepository.findByBranchName(branchName, page);//.findByStdYM(stdDate,page);
		logger.info("findByBranchName(Paging) ="+list.size());
		return list;
	}
	
	public List<BranchInfoData> findByBranchSectionCode(String branchSectionCode,Pageable page) {
		logger.info("branchSectionCode="+branchSectionCode);
		List<BranchInfoData> list = branchInfoDataRepository.findByBranchSectionCode(branchSectionCode, page);
		logger.info("findByBranchSectionCode(Paging) ="+list.size());
		return list;
	}
}
