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

import com.ibk.pds.common.model.ApiInfo;
import com.ibk.pds.common.model.DocumentInfo;
import com.ibk.pds.common.model.UserInfo;
import com.ibk.pds.common.repository.ApiInfoRepository;
import com.ibk.pds.common.repository.DocumentInfoRepository;
import com.ibk.pds.common.repository.UserInfoRepository;
import com.ibk.pds.test.mongodb.Account;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiTests {

	private Logger logger = LoggerFactory.getLogger(ApiTests.class);
	
	@Autowired
	UserInfoRepository userInfoRepository;
	
	@Autowired
	ApiInfoRepository apiInfoRepository;
	
//	@Test
//	public void mongoDBDocumentInfoTests() {
//	//	System.out.println("DocumentInfo MongoDB 테스트");
//		logger.info("API MongoDB 테스트");
//		ApiInfo apiInfo = new ApiInfo("A001", "TEST", "d23358", "D001", "http://localhost:8080/1", "DAT1;DAT2");
//
////		ApiInfo apiInfo = new DocumentInfo();
////		String docId = "D0001";
////		docInfo.setDocId(docId);
////		docInfo.setDocType("EXCEL");
////		//docInfo.setUrl("http://test");
////		docInfo.setDepName("점포전략부");
////		docInfo.setDocName("잡월드 정보2");
////		docInfo.setDocOwners("d23358;d23351;d23311");
//		
//		apiInfoRepository.insert(apiInfo);
//		
//		
//		//UserInfo userInfo = new UserInfo();
//		//Optional<UserInfo> userInfo2 = userInfoRepository.findById(userId);
//		//System.out.println("findByid="+userInfo2.get().getUSER_ID());
//		logger.info("ApiInfo Insert MongoDB 테스트 종료");
//		
////		DocumentInfo	
////		System.out.println("Doc 테스트 종료");
//	}
	
}

