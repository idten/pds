package com.ibk.pds;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ibk.pds.common.model.DocumentInfo;
import com.ibk.pds.common.model.UserInfo;
import com.ibk.pds.common.repository.DocumentInfoRepository;
import com.ibk.pds.common.repository.UserInfoRepository;
import com.ibk.pds.test.mongodb.Account;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PublicDataSupplyAppApplicationTests {

	private Logger logger = LoggerFactory.getLogger(PublicDataSupplyAppApplicationTests.class);
	
	@Autowired
	UserInfoRepository userInfoRepository;
	
	@Autowired
	DocumentInfoRepository documentInfoRepository;
	
	
	@Test
	public void mongoDBUserInfoTests() {
	//	System.out.println("UserInfo MongoDB 테스트");
		logger.info("UserInfo MongoDB 테스트");
		UserInfo userInfo = new UserInfo();
		String userId = "d23352";
		userInfo.setUserId("d23353");
		userInfo.setUserName("김기은");
		userInfo.setDepName("IT기획부");
		userInfo.setAuthCode("ADMIN");
		
		userInfoRepository.insert(userInfo);
		UserInfo userInfo2 = userInfoRepository.findByUserId(userId);
		System.out.println("findByid="+userInfo2.getUserName());
	//	userInfoRepository.deleteByUserId(userId);
		logger.info("UserInfo MongoDB 테스트 종료 ");
		
//		System.out.println("UserInfo 종료");
	}
	@Test
	public void mongoDBDocumentInfoTests() {
	//	System.out.println("DocumentInfo MongoDB 테스트");
		logger.info("DocumentInfo MongoDB 테스트");
		
//		DocumentInfo docInfo = new DocumentInfo();
//		String docId = "D0001";
//		docInfo.setDocId(docId);
//		docInfo.setDocType("EXCEL");
//		//docInfo.setUrl("http://test");
//		docInfo.setDepName("점포전략부");
//		docInfo.setDocName("잡월드 정보2");
//		docInfo.setDocOwners("d23358;d23351;d23311");
//		documentInfoRepository.insert(docInfo);
//		
//		
//		//UserInfo userInfo = new UserInfo();
//		//Optional<UserInfo> userInfo2 = userInfoRepository.findById(userId);
//		//System.out.println("findByid="+userInfo2.get().getUSER_ID());
//		logger.info("DocumentInfo MongoDB 테스트 종료");
		
//		DocumentInfo	
//		System.out.println("Doc 테스트 종료");
	}
	@Test
	public void listByOwner() {
		String owner = "d23358";
		List<DocumentInfo> docs = documentInfoRepository.findBydocOwnersRegex(".*"+ owner +"*");
		//System.out.println("findByid="+docs.get(0).getDepName());
		
	}
	
}

