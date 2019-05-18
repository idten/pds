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
import com.ibk.pds.data.model.BranchInfoData;
import com.ibk.pds.data.model.EmploymentInfoData;
import com.ibk.pds.data.repository.EmploymentInfoDataRepository;
import com.ibk.pds.data.repository.JobWorldDataRepository;
import com.ibk.pds.log.model.DocTrxStatus;
@Service
public class EmploymentInfoDataService {
	@Autowired
	EmploymentInfoDataRepository employmentInfoDataRepository;

	private Logger logger = LoggerFactory.getLogger(EmploymentInfoDataService.class);

	public List<EmploymentInfoData> getJobWorldDataList(){
		List<EmploymentInfoData> employmentInfoDataList = employmentInfoDataRepository.findAll();
		return employmentInfoDataList;
	}
	public DocTrxStatus addEmploymentInfodData(EmploymentInfoData employmentInfoData) {
		logger.info("addJobWorldData["+employmentInfoData.toString());
		try {
			employmentInfoDataRepository.insert(employmentInfoData);
		}catch(Exception e) {
			logger.info("Insert Error:"+e.getLocalizedMessage());
			DocTrxStatus docTrxStatus = new DocTrxStatus("100",e.getLocalizedMessage());			
			return docTrxStatus;
		}
		DocTrxStatus docTrxStatus = new DocTrxStatus("000","");

		//정상일 경우 
		return docTrxStatus;
	}


	public DocTrxStatus addEmploymentInfodDataFromExcel(List<String> cellList,String approval) {
		String today = DateUtil.getDateYYYYMMDD();
		String key = DateUtil.getDateYYYYMMDDHHMMSS();

		DocTrxStatus docTrxStatus = new DocTrxStatus("000","");
		String updateCode;
		String uploadDate;
		updateCode = "D"+key;
		uploadDate = today;

		String stdYM 				= cellList.get(0);
		String industryName 		= cellList.get(1);
		String industryCode 		= cellList.get(2);
		String detailIndustryName   = cellList.get(3);
		String detailIndustryCode   = cellList.get(4);

		int careersCount			= Integer.parseInt(cellList.get(5));
		String careersPer			= cellList.get(6);
		String dataId = stdYM + detailIndustryCode;

		EmploymentInfoData employmentInfoData = new EmploymentInfoData(dataId,stdYM,industryName,industryCode,detailIndustryName,detailIndustryCode,careersCount,careersPer,approval,updateCode,uploadDate);
		addEmploymentInfodData(employmentInfoData);
		logger.info("EmploymentInfoData Data insert:"+employmentInfoData.toString());

		return docTrxStatus;

	}
	//public DocTrxStatus DocTrxStatus()


	public List<EmploymentInfoData> findAll(Pageable page){
		List<EmploymentInfoData> list = employmentInfoDataRepository.findAll(page).getContent();
		//employmentInfoDataRepository.findAll
		return list;
	}
	public List<EmploymentInfoData> findAll(){
		List<EmploymentInfoData> list = employmentInfoDataRepository.findAll();
		return list;
	}


	public int getTotalCount() {
		return (int)employmentInfoDataRepository.count();
	}

	public List<EmploymentInfoData> findByStdDate(String stdDate,Pageable page) {
		logger.info("stdDate="+stdDate);
		List<EmploymentInfoData> list = employmentInfoDataRepository.findByStdYM(stdDate,page);
		logger.info("getByStdDate(Paging) ="+list.size());
		return list;
	}


	public List<EmploymentInfoData> findByStdYMAndIndustryCode(String stdYM, String industryCode,Pageable page) {
		logger.info("stdDate="+stdYM+":"+industryCode+":");

		List<EmploymentInfoData> list = employmentInfoDataRepository.findByStdYMAndIndustryCode(stdYM, industryCode,page);
		//.findByStdYM(stdDate);

		logger.info("getByStdDat(Paging)e ="+list.size());
		return list;
	}
	public List<EmploymentInfoData> findByIndustryCode(String industryCode,Pageable page) {
		logger.info("stdDate="+industryCode);
		List<EmploymentInfoData> list = employmentInfoDataRepository.findByIndustryCode(industryCode, page);
		//.findByStdYM(stdDate);

		logger.info("findByIndustryCode(Paging)e ="+list.size());
		return list;
	}
	public int findByIndustryCodeTotalCount(String industryCode) {
		logger.info("stdDate="+industryCode);
		int size = (int) employmentInfoDataRepository.countByIndustryCode(industryCode);//.findByIndustryCode(industryCode).size();
		//.findByStdYM(stdDate);

		logger.info("findByIndustryCodeTotalCount(Paging)e ="+size);
		return size;
	}
	public int findByStdYMAndIndustryCodeTotalCount(String stdYM, String industryCode) {
		logger.info("stdDate="+industryCode);
		int size = (int) employmentInfoDataRepository.countByStdYMAndIndustryCode(stdYM, industryCode);
		logger.info("findByStdYMAndIndustryCodeTotalCount(Paging)e ="+size);
		return size;
	}

	public int findByStdDateTotalCount(String stdDate) {
		logger.info("stdDate="+stdDate);
		return (int) employmentInfoDataRepository.countByStdYM(stdDate);//.findByStdYM(stdDate).size();
	}



}
