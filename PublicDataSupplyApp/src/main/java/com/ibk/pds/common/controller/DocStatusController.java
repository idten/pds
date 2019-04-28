package com.ibk.pds.common.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.ibk.pds.common.config.ConstantCode;
import com.ibk.pds.common.model.DocumentInfo;
import com.ibk.pds.common.model.DocumentStatus;
import com.ibk.pds.common.model.UserInfo;
import com.ibk.pds.common.repository.DocumentInfoRepository;
import com.ibk.pds.common.repository.UserInfoRepository;
import com.ibk.pds.common.service.DocumentInfoService;
import com.ibk.pds.common.service.DocumentStatusService;
import com.ibk.pds.common.service.UserInfoService;
import com.ibk.pds.common.util.DateUtil;
import com.ibk.pds.data.model.JobWorldData;
import com.ibk.pds.data.service.JobWorldDataService;
import com.ibk.pds.log.model.DocTrxStatus;
import com.ibk.pds.log.model.LogDocData;
import com.ibk.pds.log.service.LogDocDataService;

@Controller
public class DocStatusController {
	@Autowired
	DocumentStatusService documentStatusService;
	//UserInfoRepository userInfoRepository;

	@Autowired 
	DocumentInfoService documentInfoService;

	@Autowired
	JobWorldDataService jobWorldDataService;


	@Autowired
	LogDocDataService logDocDataService;

	private Logger logger = LoggerFactory.getLogger(DocStatusController.class);

	@RequestMapping(value = "/upload.do", method = RequestMethod.GET)
	public ModelAndView upload(ModelAndView mav) {
		logger.info("Doc UPload Test");
		List<DocumentStatus> docStatusList = documentStatusService.getDocStatusList();
		DocumentStatus docStatus = null;
		int len = docStatusList.size();
		for(int i = 0; i<len ; i++){
			docStatus = docStatusList.get(i);

			logger.info("docStatus OUTPUT"+docStatus.toString());
		}

		mav.addObject("doclist",docStatusList);


		logger.info("jobWorld Test");
		List<JobWorldData> jobWorldDataList = jobWorldDataService.getJobWorldDataList();
		//.getApiInfoList();
		JobWorldData jobWorldData=null;
		len = jobWorldDataList.size();
		for(int i = 0; i<len ; i++){
			jobWorldData = jobWorldDataList.get(i);
			logger.info(jobWorldData.toString());
		}

		mav.addObject("datalist",jobWorldDataList);
		//	mav.setViewName("jobworld");
		logger.info("Api Test End");
		mav.setViewName("upload");
		logger.info("Doc Upload Test End");

		return mav;
	}
	@RequestMapping(value = "/approval.do", method = RequestMethod.GET)
	public ModelAndView approval(ModelAndView mav) {
		logger.info("Doc Approval Test");
		//N인것만 리스트 
		List<DocumentStatus> docStatusList = documentStatusService.getDocStatusListByApproval("N");
		DocumentStatus docStatus = null;
		int len = docStatusList.size();
		for(int i = 0; i<len ; i++){
			docStatus = docStatusList.get(i);

			logger.info("docStatus OUTPUT"+docStatus.toString());
		}

		mav.addObject("doclist",docStatusList);


		logger.info("jobWorld Test");
		List<JobWorldData> jobWorldDataList = jobWorldDataService.getJobWorldDataList();
		//.getApiInfoList();
		JobWorldData jobWorldData=null;
		len = jobWorldDataList.size();
		for(int i = 0; i<len ; i++){
			jobWorldData = jobWorldDataList.get(i);
			logger.info(jobWorldData.toString());
		}

		mav.addObject("datalist",jobWorldDataList);
		//	mav.setViewName("jobworld");
		logger.info("Api Test End");
		mav.setViewName("approval");
		logger.info("Doc Approval Test End");

		return mav;
	}


	@RequestMapping(value="/uploadFile", method=RequestMethod.POST)
	//@RequestMapping(value = "uploadFile2", method = RequestMethod.POST)
	public String upload(
			@RequestParam("docId") String docId,
			//	@RequestParam("approval") String approval,
			MultipartHttpServletRequest request) throws Exception {
		//	logger.info("Receive Data:"+docId+":"+approval);
		Iterator<String> itr =  request.getFileNames();
		String path = ConstantCode.rootDirectory;
		String key = DateUtil.getDateYYYYMMDDHHMMSS();

		String today = DateUtil.getDateYYYYMMDD();

		//여기서문서 문서별로 function 처리 해야함 일단 처음이니깐 여기다가 

		String dataId;
		String stdYM;
		String industryName;
		String industryCode;
		String detailIndustryName;
		String detailIndustryCode;
		int careersCount;
		String careersPer;
		//		// 문서 내용이 아닌 관려용 daa 
		// String approval;
		//		//삭제, 승인 등 관련해서 처리를 위한 구분 값 
		String updateCode;
		String uploadDate;
		List<String>cellList = new ArrayList<String>();
		DataFormatter dataFormatter = new DataFormatter();
		DocumentInfo docInfo = documentInfoService.getDocInfobyDocId(docId).get(0);
		String docName = docInfo.getDocName();
		String docOwners = docInfo.getDocOwners();
		String approval = docInfo.getAutoApprovalYN();
		DocTrxStatus docTrxStatus = new DocTrxStatus("","");
		if(itr.hasNext()) {
			List<MultipartFile> mpf = request.getFiles( itr.next());
			// 임시 파일을 복사한다.
			for(int i = 0; i < mpf.size(); i++) {
				String orgfileName = mpf.get(i).getOriginalFilename();
				String newFileName = orgfileName+"."
						+ System.currentTimeMillis();

				File file = new File(path +newFileName );
				logger.info(file.getAbsolutePath());
				mpf.get(i).transferTo(file);


				System.out.println("fileInfo="+file.getAbsolutePath()+":"+file.length());
				//poi excel to DB
				Workbook workbook = WorkbookFactory.create(file);
				Sheet sheet = workbook.getSheetAt(0);
				Iterator<Row> rowIterator = sheet.rowIterator();
				int rowCount = 0;
				int result = 0;

				while (rowIterator.hasNext()) {
					Row row = rowIterator.next();

					// Now let's iterate over the columns of the current row
					Iterator<Cell> cellIterator = row.cellIterator();

					// System.out.print(count+"\t");
					rowCount++;

					int columnCount = 0;
					//        cellIterator.
					cellList.clear();
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						String cellValue = dataFormatter.formatCellValue(cell);
						cellList.add(cellValue);
						//db에 insert하는 로직 추가 필요 
						//데이터별로 추가하는것 분리 
						System.out.print(cellValue + "\t");
					}
					if(rowCount!=1) {
						stdYM = cellList.get(0);
						industryName = cellList.get(1);
						industryCode = cellList.get(2);
						detailIndustryName = cellList.get(3);
						detailIndustryCode = cellList.get(4);

						dataId = stdYM + detailIndustryCode;
						careersCount = Integer.parseInt(cellList.get(5));
						careersPer = cellList.get(6);
						//approval 은 문서의 자동승이네 따라서 자동
						//삭제 등을 수행할 키 
						updateCode = "D"+key;
						uploadDate = today;

						JobWorldData jobWorldData = new JobWorldData(dataId,stdYM,industryName,industryCode,detailIndustryName,detailIndustryCode,careersCount,careersPer,approval,updateCode,uploadDate);
						logger.info("JobWorld Data insert:"+jobWorldData.toString());
						docTrxStatus = jobWorldDataService.addJobWorldData(jobWorldData);
						//정상아닌 경우에 break;
						if(!"000".equals(docTrxStatus.getStatus())) {
							logger.info("Input Error"+docTrxStatus.getContents());
							break;
						}
					}
					System.out.println();
				}

				logger.info("=========================================================");
				String docUpId = "U"+key;
				//상태 추가 
				//				DocumentInfo docInfo = documentInfoService.getDocInfobyDocId(docId).get(0);
				//
				//				String docName = docInfo.getDocName();
				//				String docOwners = docInfo.getDocOwners();
				//				String approval = docInfo.getAutoApprovalYN();
				String status = ""; 
				//입력하다가 시랲시에는 상태 

				if(!docTrxStatus.getStatus().equals("000")) {
					logger.info("Status:"+docTrxStatus.getStatus());
					status = "입력실패";
					//자동승인이더라도 N으로 해서 리스트업 한 후에 관리자가 삭제 
					approval = "N";
				}else {

					if(approval.equals("N")) {
						status = "승인대기";
					}else {
						status = "승인완료";
					}

				}
				
				
				//이때까지 들어간 것들에 대한 처리를 위해서 
				DocumentStatus documentStatus = new DocumentStatus(docId,docUpId,docName,docOwners,approval,status,newFileName,file.length(),today);
				documentStatusService.saveDocStatus(documentStatus);
				String logId = "L"+key;
				String logStatus = docTrxStatus.getStatus();
				String logContents = docTrxStatus.getContents();

				//logId = 
				logger.info("========================================================="+docTrxStatus.getStatus()+":"+docTrxStatus.getContents());
					
				LogDocData logDocData = new LogDocData(logId,docId,docName,"INSERT",docUpId,docOwners,logStatus,logContents);
				logDocDataService.saveLogDocData(logDocData);
			}

		}
		return "redirect:docDetailList.do";
	}


	//docId 기준 으로 승인 
	//추후 코드로 바꿔야함 현재 문자열로 처리 중 
	//                       	updateapproval
	@RequestMapping(value="/updateapproval", method = RequestMethod.POST) 
	public String updateApproval(@RequestParam("docId") String docId) {

		//documentStatusService
		logger.info("UpdateApproval");

		documentStatusService.approvalDocStatusByDocId(docId);

		//	documentStatusService.updateDocStatusByDocUpId(docUpId);
		return "redirect:approval.do";
	}
}
