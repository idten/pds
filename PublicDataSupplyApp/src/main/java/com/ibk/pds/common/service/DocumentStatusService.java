package com.ibk.pds.common.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibk.pds.common.model.DocumentInfo;
import com.ibk.pds.common.model.DocumentStatus;
import com.ibk.pds.common.model.UserInfo;
import com.ibk.pds.common.repository.DocumentInfoRepository;
import com.ibk.pds.common.repository.DocumentStatusRepository;
import com.ibk.pds.common.repository.UserInfoRepository;
import com.ibk.pds.common.util.DateUtil;
import com.ibk.pds.log.model.LogDocData;
import com.ibk.pds.log.service.LogDocDataService;
@Service
public class DocumentStatusService {

	@Autowired
	DocumentStatusRepository documentStatusRepository;

	@Autowired
	LogDocDataService logDocDataService;
	//UserInfoRepository userInfoRepository;

	private Logger logger = LoggerFactory.getLogger(DocumentStatusService.class);

	public List<DocumentStatus> getDocStatusList() {
		// TODO Auto-generated method stub
		System.out.println("getDocInfoList()");
		List<DocumentStatus> docStatusList = documentStatusRepository.findAll();
		return docStatusList;
	}
	public List<DocumentStatus> getDocStatusListByApproval(String approval) {
		// TODO Auto-generated method stub
		System.out.println("getDocInfoList()");
		List<DocumentStatus> docStatusList = documentStatusRepository.findByApproval(approval);//.findAll();
		return docStatusList;
	}

	public void saveDocStatus(DocumentStatus docStatus) {
		logger.info("saveUserInfo["+docStatus.toString());
		documentStatusRepository.save(docStatus);

	}	


	public void addDocStatus(DocumentStatus docStatus) {
		logger.info("addDocumentInfo["+docStatus.toString());
		documentStatusRepository.insert(docStatus);
	}
	
	public void deleteDocStatus(DocumentStatus docStatus) {
		logger.info("delete DocumentInfo["+docStatus.toString());
		documentStatusRepository.deleteBydocId(docStatus.getDocId());
	}
	public void deleteDocStatus(String docId) {
		logger.info("delete DocumentInfo["+docId+"]");
		documentStatusRepository.deleteBydocId(docId);
	}
	

	public DocumentStatus getDocStatus(String docId) {
		// TODO Auto-generated method stub
		System.out.println("getDocInfoList()");
		DocumentStatus docStatus =  documentStatusRepository.findByDocId(docId);
		return docStatus;		
	}
	
	
	public void approvalDocStatusByDocId(String docId) {
		// TODO Auto-generated method stub
		logger.info("updateDocStatusByDocId execute Before");
		DocumentStatus docStatus =  documentStatusRepository.findByDocId(docId);
		docStatus.setStatus("승인완료");
		docStatus.setApproval("Y");
		documentStatusRepository.save(docStatus);
		
		logger.info("updateDocStatusByDocId execute After");
		String key = DateUtil.getDateYYYYMMDDHHMMSS();
		String logId  = "L"+key;
		String docName = docStatus.getDocName();
		String docOwners = docStatus.getDocOwners();
		String docUpId = docStatus.getDocUpId();
		
		LogDocData logDocData = new LogDocData(logId,docId,docName,"INSERT",docUpId,docOwners,"","");
		logDocDataService.saveLogDocData(logDocData);
	}
	public List<DocumentStatus> getDocStatusListByOwner(String owner) {
		List<DocumentStatus> docs = documentStatusRepository.findBydocOwnersRegex(".*"+ owner +"*");
		//==================================docOwners
		logger.info("getDocStatusListByOwner DocumentInfo[]");
		return docs;
	}
}
