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
import com.ibk.pds.common.model.DocumentStatus;
import com.ibk.pds.common.model.UserInfo;
import com.ibk.pds.common.repository.ApiInfoRepository;
import com.ibk.pds.common.repository.DocumentInfoRepository;
import com.ibk.pds.common.repository.UserInfoRepository;
import com.ibk.pds.common.service.DocumentStatusService;
import com.ibk.pds.test.mongodb.Account;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DocumentStatusTest {

	private Logger logger = LoggerFactory.getLogger(DocumentStatusTest.class);
	
	@Autowired
	DocumentStatusService documentStatusService;
	
	@Test
	public void mongoDBDocumentInfoTests() {
	//	System.out.println("DocumentInfo MongoDB 테스트");
		logger.info("API MongoDB 테스트");
//		public DocumentStatus(String docId, String docName,String docOwners,
//				String approval, String status, String fileName, String fileSize
	///	DocumentStatus documentstatus = new DocumentSTatus()
		DocumentStatus docStatus = new DocumentStatus("D001","U001", "TEST", "d23358", 
				"Y", "정상","TEST.xls",235L,"20190311");

//		ApiInfo apiInfo = new DocumentInfo();
//		String docId = "D0001";
//		docInfo.setDocId(docId);
//		docInfo.setDocType("EXCEL");
//		//docInfo.setUrl("http://test");
//		docInfo.setDepName("점포전략부");
//		docInfo.setDocName("잡월드 정보2");
//		docInfo.setDocOwners("d23358;d23351;d23311");
		
		documentStatusService.addDocStatus(docStatus);//.insert(apiInfo);
		
		
		//UserInfo userInfo = new UserInfo();
		//Optional<UserInfo> userInfo2 = userInfoRepository.findById(userId);
		//System.out.println("findByid="+userInfo2.get().getUSER_ID());
		logger.info("ApiInfo Insert MongoDB 테스트 종료");
		
//		DocumentInfo	
//		System.out.println("Doc 테스트 종료");
	}
	
}

