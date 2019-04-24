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

import com.ibk.pds.code.model.AlarmStd;
import com.ibk.pds.code.model.DepInfo;
import com.ibk.pds.code.repository.AlarmStdRepository;
import com.ibk.pds.code.repository.DepInfoRepository;
import com.ibk.pds.common.model.ApiInfo;
import com.ibk.pds.common.model.DocumentInfo;
import com.ibk.pds.common.model.UserInfo;
import com.ibk.pds.common.repository.ApiInfoRepository;
import com.ibk.pds.common.repository.DocumentInfoRepository;
import com.ibk.pds.common.repository.UserInfoRepository;
import com.ibk.pds.test.mongodb.Account;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AlarmStdTest {

	private Logger logger = LoggerFactory.getLogger(AlarmStdTest.class);
	
	@Autowired
	DepInfoRepository depInfoRepository;
	
	@Autowired
	ApiInfoRepository apiInfoRepository;
	
	@Autowired 
	AlarmStdRepository alarmStdRepository;
	@Test
	public void alarmAddTest() {
		AlarmStd alarmStd1= new AlarmStd("W001","월");
		AlarmStd alarmStd2= new AlarmStd("W002","화");
		AlarmStd alarmStd3= new AlarmStd("W003","수");
		AlarmStd alarmStd4= new AlarmStd("W004","목");
		AlarmStd alarmStd5= new AlarmStd("W005","금");

		
		AlarmStd alarmStd6= new AlarmStd("M001","1일");
		AlarmStd alarmStd7= new AlarmStd("M005","5일");
		AlarmStd alarmStd8= new AlarmStd("M010","10일");
		AlarmStd alarmStd9= new AlarmStd("M020","20일");
		AlarmStd alarmStd10= new AlarmStd("M028","28일");
		AlarmStd alarmStd11= new AlarmStd("M030","30일");
		alarmStdRepository.insert(alarmStd1);
		alarmStdRepository.insert(alarmStd2);
		alarmStdRepository.insert(alarmStd3);
		alarmStdRepository.insert(alarmStd4);
		alarmStdRepository.insert(alarmStd5);
		alarmStdRepository.insert(alarmStd6);
		alarmStdRepository.insert(alarmStd7);
		alarmStdRepository.insert(alarmStd8);
		alarmStdRepository.insert(alarmStd9);
		alarmStdRepository.insert(alarmStd10);
		alarmStdRepository.insert(alarmStd11);
		
		
		
		
		
	}
	public void depInfoTest() {
	//	System.out.println("DocumentInfo MongoDB 테스트");
		logger.info("API MongoDB 테스트");
		DepInfo depInfo1 = new DepInfo("DEP001","IT기획부");
		DepInfo depInfo2 = new DepInfo("DEP002","전략기획부");
		//ApiInfo apiInfo = new ApiInfo("A001", "TEST", "d23358", "D001", "http://localhost:8080/1", "DAT1;DAT2");

//		ApiInfo apiInfo = new DocumentInfo();
//		String docId = "D0001";
//		docInfo.setDocId(docId);
//		docInfo.setDocType("EXCEL");
//		//docInfo.setUrl("http://test");
//		docInfo.setDepName("점포전략부");
//		docInfo.setDocName("잡월드 정보2");
//		docInfo.setDocOwners("d23358;d23351;d23311");
		
		depInfoRepository.insert(depInfo1);
		
		depInfoRepository.insert(depInfo2);
		
		
		//UserInfo userInfo = new UserInfo();
		//Optional<UserInfo> userInfo2 = userInfoRepository.findById(userId);
		//System.out.println("findByid="+userInfo2.get().getUSER_ID());
		logger.info("ApiInfo Insert MongoDB 테스트 종료");
		
//		DocumentInfo	
//		System.out.println("Doc 테스트 종료");
	}
	//@Test
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

