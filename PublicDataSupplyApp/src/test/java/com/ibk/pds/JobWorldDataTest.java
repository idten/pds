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

import com.ibk.pds.data.model.JobWorldData;
import com.ibk.pds.data.service.JobWorldDataService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JobWorldDataTest {

	private Logger logger = LoggerFactory.getLogger(JobWorldDataTest.class);
	
	@Autowired
	JobWorldDataService jobWorldDataService;
//	public JobWorldData(String dataId, String detailedIndustry, String categoryOfBusiness,
//			Integer announcementNumber,String ratio) {
//			this.dataId = dataId;
//			this.detailedIndustry = detailedIndustry;
//			this.categoryOfBusiness = categoryOfBusiness;
//			this.announcementNumber = announcementNumber;
//			this.ratio = ratio;
//	}
	@Test
	public void mongoDBJobWorldInfoTests() {
	//	System.out.println("DocumentInfo MongoDB 테스트");
		logger.info("JobWorld MongoDB 테스트");
//		public JobWorldData(
//		String stdYM, 
//				String industryName, 
//				String industryCode, 
//				String detailIndustryName,
//		String detailIndustryCode,
		//				int careersCount, 
//				String careersPer, 
//				String approval, 
//				String updateCode, 
//				String uploadDate)
//		
		JobWorldData jobworldData1 = new JobWorldData("201909J101","201809","경영사무","J1","기업.전략.경영","J101",32,"72.33","N","J20181111","20190409");
		JobWorldData jobworldData2 = new JobWorldData("201909J102","201809","경영사무","J1","기업.전략.경영","J102",1,"72.33","N","J20181111","20190409");
		JobWorldData jobworldData3 = new JobWorldData("201909J103","201809","경영사무","J1","기업.전략.경영","J103",3,"72.33","N","J20181111","20190409");
		JobWorldData jobworldData4 = new JobWorldData("201909J104","201809","경영사무","J1","기업.전략.경영","J104",3,"72.33","N","J20181111","20190409");
		JobWorldData jobworldData5 = new JobWorldData("201909J105","201809","경영사무","J1","기업.전략.경영","J105",33,"71","N","J20181111","20190409");
		JobWorldData jobworldData6 = new JobWorldData("201909J106","201809","경영사무","J1","기업.전략.경영","J106",34,"72","N","J20181111","20190409");

//		public DocumentStatus(String docId, String docName,String docOwners,
//				String approval, String status, String fileName, String fileSize
	///	DocumentStatus documentstatus = new DocumentSTatus()
	//	DocumentStatus docStatus = new DocumentStatus("D001", "TEST", "d23358", 
	//			"Y", "정상","TEST.xls","235K");
		
//		ApiInfo apiInfo = new DocumentInfo();
//		String docId = "D0001";
//		docInfo.setDocId(docId);
//		docInfo.setDocType("EXCEL");
//		//docInfo.setUrl("http://test");
//		docInfo.setDepName("점포전략부");
//		docInfo.setDocName("잡월드 정보2");
//		docInfo.setDocOwners("d23358;d23351;d23311");
		
		jobWorldDataService.addJobWorldData(jobworldData1);
		jobWorldDataService.addJobWorldData(jobworldData2);
		jobWorldDataService.addJobWorldData(jobworldData3);
		jobWorldDataService.addJobWorldData(jobworldData4);
		jobWorldDataService.addJobWorldData(jobworldData5);
		jobWorldDataService.addJobWorldData(jobworldData6);

		//.addDocStatus(docStatus);//.insert(apiInfo);
		
		
		//UserInfo userInfo = new UserInfo();
		//Optional<UserInfo> userInfo2 = userInfoRepository.findById(userId);
		//System.out.println("findByid="+userInfo2.get().getUSER_ID());
		logger.info("ApiInfo Insert MongoDB 테스트 종료");
		
//		DocumentInfo	
//		System.out.println("Doc 테스트 종료");
	}
	
}

