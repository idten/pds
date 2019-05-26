package com.ibk.pds.common.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibk.pds.common.model.DocumentInfo;
import com.ibk.pds.common.model.UserInfo;
import com.ibk.pds.common.repository.DocumentInfoRepository;
import com.ibk.pds.common.repository.UserInfoRepository;
@Service
public class DocumentInfoService {

	@Autowired
	DocumentInfoRepository documentInfoRepository;

	//UserInfoRepository userInfoRepository;

	private Logger logger = LoggerFactory.getLogger(DocumentInfoService.class);

	public List<DocumentInfo> getDocInfobyDocId(String docId) {
		List<DocumentInfo> docInfoList = documentInfoRepository.findByDocId(docId);
		
		if(docInfoList.size()==0) {
			logger.info("getDocInfobyDocId("+docId+") is null");
			
			return null;
		}
		logger.info("getDocInfobyDocId("+docId+")");
		
		return documentInfoRepository.findByDocId(docId);
		
	}
	
	
	
	public List<DocumentInfo> getDocInfoList() {
		// TODO Auto-generated method stub
		System.out.println("getDocInfoList()");
		List<DocumentInfo> docInfoList = documentInfoRepository.findAll();
		return docInfoList;
	}

	public void saveDocInfo(DocumentInfo docInfo) {
		logger.info("saveUserInfo["+docInfo.toString());
		documentInfoRepository.save(docInfo);

	}	


	public void addDocInfo(DocumentInfo docInfo) {
		logger.info("addDocumentInfo["+docInfo.toString());
		documentInfoRepository.insert(docInfo);
	}
	
	public void deleteDocInfo(DocumentInfo docInfo) {
		logger.info("delete DocumentInfo["+docInfo.toString());
		documentInfoRepository.deleteBydocId(docInfo.getDocId());
	}
	public void deleteDocInfo(String docId) {
		logger.info("delete DocumentInfo["+docId);
		documentInfoRepository.deleteBydocId(docId);
	}
	public List<DocumentInfo> getDocInfoListByOwner(String owner) {
		List<DocumentInfo> docs = documentInfoRepository.findBydocOwnersRegex(".*"+ owner +"*");
		//==================================
		logger.info("getDocInfoListByOwner DocumentInfo[]");
		return docs;
	}

}
