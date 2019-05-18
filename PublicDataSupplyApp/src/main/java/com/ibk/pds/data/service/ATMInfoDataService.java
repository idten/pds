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
import com.ibk.pds.data.model.EmploymentInfoData;
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
	public List<ATMInfoData> findATMInfoDataList(Pageable page){
		List<ATMInfoData> atmInfoDataList = atmInfoDataRepository.findAll(page).getContent();
		return atmInfoDataList;
	}


	public DocTrxStatus deleteATMInfoDataAll(){
		DocTrxStatus docTrxStatus = new DocTrxStatus("000","");

		try {
			atmInfoDataRepository.deleteAll();
		}catch(Exception e) {
			e.printStackTrace();
			return docTrxStatus;
		}
		return docTrxStatus;
	}
	public DocTrxStatus addATMInfodDataFromExcel(List<String> cellList,String approval) {
		String today = DateUtil.getDateYYYYMMDD();
		String key = DateUtil.getDateYYYYMMDDHHMMSS();

		DocTrxStatus docTrxStatus = new DocTrxStatus("000","");
		String updateCode;
		String uploadDate;
		updateCode = "D"+key;
		uploadDate = today;
		String atmName			= cellList.get(0);			//ATM명
		String atmCode 			= cellList.get(1);
		String startTime		= cellList.get(2);//시작시간
		String endTime			= cellList.get(3);//종료시간
		String atmAddress       = cellList.get(4);		//주소
		String atmSection       = cellList.get(5);		//지역구분
		String atmSectionCode	= cellList.get(6);	//지역구분 코드 
		String dataId=atmCode; 

		ATMInfoData atmInfoData = new ATMInfoData(dataId,atmName,atmCode, startTime, endTime,atmAddress,
				atmSection,atmSectionCode,approval, updateCode, uploadDate);

		addATMInfoData(atmInfoData);
		logger.info("ATMInfoData Data insert:"+atmInfoData.toString());
		return docTrxStatus;

	}


	//자료 입력시 
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

	//API: 전체 리스트 정보 
	public List<ATMInfoData> findAll(Pageable page){
		List<ATMInfoData> atmInfoDataList = atmInfoDataRepository.findAll(page).getContent();
		return atmInfoDataList;
	}
	//API: 전체 리스트 정보 
	public List<ATMInfoData> findAll(){
		List<ATMInfoData> atmInfoDataList = atmInfoDataRepository.findAll();
		return atmInfoDataList;
	}
	

	public List<ATMInfoData> findByATMName(String atmName,Pageable page) {
		logger.info("atmName="+atmName);
		List<ATMInfoData> list = atmInfoDataRepository.findByAtmNameLike(atmName, page);//.findByStdYM(stdDate,page);
		logger.info("findByBranchName(Paging) ="+list.size());
		return list;
	}
	//주소기준 조회 Like 검색 
	public List<ATMInfoData> findByATMAddress(String atmAddress,Pageable page) {
		logger.info("atmAddress="+atmAddress);
		List<ATMInfoData> list = atmInfoDataRepository.findByAtmAddressLike(atmAddress, page);//.findByStdYM(stdDate,page);
		logger.info("findByATMAddress(Paging) ="+list.size());
		return list;
	}

	
	public List<ATMInfoData> findByATMSectionCode(String atmSectionCode,Pageable page) {
		logger.info("atmSectionCode="+atmSectionCode);
		List<ATMInfoData> list = atmInfoDataRepository.findByAtmSectionCode(atmSectionCode, page);
		logger.info("findByATMSectionCode(Paging) ="+list.size());
		return list;
	}
	public int findAllTotalCount(){
		return  (int)atmInfoDataRepository.count();
	}
	public int findByATMNameTotalCount(String atmName) {
		logger.info("atmName="+atmName);
		return (int)atmInfoDataRepository.countByAtmNameLike(atmName);//.findByAtmNameLike(atmName).size();//.findByStdYM(stdDate,page);
	}
	public int findByATMSectionCodeTotalCount(String atmSectionCode) {
		logger.info("atmSectionCode="+atmSectionCode);
		return (int)atmInfoDataRepository.countByAtmSectionCode(atmSectionCode);
	}
	
	public int findByATMAddressTotalCount(String atmAddress) {
		logger.info("atmAddress="+atmAddress);
		return (int)atmInfoDataRepository.countByAtmAddressLike(atmAddress);//.findByStdYM(stdDate,page);
	}
	
	

}
