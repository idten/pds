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
import com.ibk.pds.common.util.DateUtil;
import com.ibk.pds.data.model.ATMInfoData;
import com.ibk.pds.data.model.BranchInfoData;
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
	public DocTrxStatus deleteBranchInfoDataAll(){
		DocTrxStatus docTrxStatus = new DocTrxStatus("000","");
		try {
			branchInfoDataRepository.deleteAll();
		}catch(Exception e) {
			e.printStackTrace();
			docTrxStatus.setStatus("111");
			return docTrxStatus;
		}
		return docTrxStatus;
	}

	public DocTrxStatus addBranchInfodDataFromExcel(List<String> cellList,String approval) {
		String today = DateUtil.getDateYYYYMMDD();
		String key = DateUtil.getDateYYYYMMDDHHMMSS();
		
		DocTrxStatus docTrxStatus = new DocTrxStatus("000","");
		String updateCode;
		String uploadDate;
		updateCode = "D"+key;
		uploadDate = today;
		
		String branchName		 = cellList.get(0);			//지점명
		String branchCode  		 = cellList.get(1);;
		String branchPhoneNumber = cellList.get(2);;  	//지점전화번호 
		String branchAddress	 = cellList.get(3);	//지점주소
		String branchSection	 = cellList.get(4);		//행정구역(서울)
		String branchSectionCode = cellList.get(5);;	//행정구역코드
		
		String dataId=branchCode; 

		BranchInfoData branchInfoData = 
				new BranchInfoData(dataId,branchName, branchCode,branchPhoneNumber, branchAddress, branchSection,
						branchSectionCode,approval, updateCode, uploadDate);
		addBranchInfoData(branchInfoData);
		logger.info("BranchInfoData Data insert:"+branchInfoData.toString());
		return docTrxStatus;
		
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
	public List<BranchInfoData> findAll(){
		List<BranchInfoData> branchInfoDataList = branchInfoDataRepository.findAll();
		return branchInfoDataList;
	}
	
	public List<BranchInfoData> findByBranchName(String branchName,Pageable page) {
		logger.info("branchName="+branchName);
		List<BranchInfoData> list = branchInfoDataRepository.findByBranchNameLike(branchName, page);//.findByStdYM(stdDate,page);
		logger.info("findByBranchName(Paging) ="+list.size());
		return list;
	}
	
	public List<BranchInfoData> findByBranchAddress(String branchAddress,Pageable page) {
		logger.info("branchAddress="+branchAddress);
		List<BranchInfoData> list = branchInfoDataRepository.findByBranchAddressLike(branchAddress, page);//.findByBranchAddressLike(branchAddress, page);//.findByStdYM(stdDate,page);
		logger.info("findByBranchName(Paging) ="+list.size());
		return list;
	}
	
	
	public List<BranchInfoData> findByBranchSectionCode(String branchSectionCode,Pageable page) {
		logger.info("branchSectionCode="+branchSectionCode);
		List<BranchInfoData> list = branchInfoDataRepository.findByBranchSectionCode(branchSectionCode, page);
		logger.info("findByBranchSectionCode(Paging) ="+list.size());
		return list;
	}
	public int findAllTotalCount() {
		return (int)branchInfoDataRepository.count();
	}
	public int findByBranchNameTotalCount(String branchName) {
		logger.info("branchName="+branchName);
		return (int)branchInfoDataRepository.countByBranchNameLike(branchName);
	}
	public int findByBranchAddressTotalCount(String branchAddress) {
		logger.info("branchAddress="+branchAddress);
		return (int)branchInfoDataRepository.countByBranchNameLike(branchAddress);
	}
	public int findByBranchSectionCodeTotalCount(String branchSectionCode) {
		logger.info("branchSectionCode="+branchSectionCode);
		return (int) branchInfoDataRepository.countByBranchSectionCode(branchSectionCode);
	}
	

}
