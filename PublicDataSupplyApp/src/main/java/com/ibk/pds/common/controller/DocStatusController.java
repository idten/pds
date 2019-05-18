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
import com.ibk.pds.data.model.ATMInfoData;
import com.ibk.pds.data.model.EmploymentInfoData;
import com.ibk.pds.data.service.ATMInfoDataService;
import com.ibk.pds.data.service.BranchInfoDataService;
import com.ibk.pds.data.service.EmploymentInfoDataService;
import com.ibk.pds.data.service.FundRateDataService;
import com.ibk.pds.data.service.MonthlyExchangeRateDataService;
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
	EmploymentInfoDataService employmentInfoDataService;


	@Autowired 
	ATMInfoDataService atmInfoDataService;

	@Autowired 
	BranchInfoDataService branchInfoDataService;

	@Autowired 
	FundRateDataService fundRateDataService;


	@Autowired 
	MonthlyExchangeRateDataService monthlyExchangeRateDataService;

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
		List<EmploymentInfoData> list = employmentInfoDataService.findAll();//.getJobWorldDataList();
		//.getApiInfoList();
		EmploymentInfoData employmentInfoData=null;

		mav.addObject("datalist",list);
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
		List<EmploymentInfoData> list = employmentInfoDataService.findAll();//.getJobWorldDataList();


		mav.addObject("datalist",list);
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
				//String orgfileName2 = mpf.get(i)
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
						System.out.print(cellValue + "\t");
					}
					if(rowCount!=1) {

						docTrxStatus = insertDocument(docId,cellList,approval);
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

	//cell List -> db insert를 수행하는 
	public DocTrxStatus clearDocument(String docId) {
		DocTrxStatus status = new DocTrxStatus("000","OK");
		if(docId.equals(ConstantCode.employmentInfoDocId)) {

		}else if(docId.equals(ConstantCode.atmInfoDocId)){
			//ATMInfo 는 기존 데이터 삭제 
			status= atmInfoDataService.deleteATMInfoDataAll();
		}else if(docId.equals(ConstantCode.branchInfoDocId)) {
			//BranchInfo도 초기화 
			status= branchInfoDataService.deleteBranchInfoDataAll();
		}else if(docId.equals(ConstantCode.fundRateInfoDocId)) {
			//펀드수익률도 초기화
			status = fundRateDataService.deleteFundRateDataAll();
		}else if(docId.equals(ConstantCode.monthlyExchangeRateInfoDocId)) {
			//여기는 필요 없음 

		}



		return status;
	}


	public DocTrxStatus insertDocument(String docId, List<String>cellList,String approval) {
		DocTrxStatus status = new DocTrxStatus("000","OK");
		if(docId.equals(ConstantCode.employmentInfoDocId)) {
			status = employmentInfoDataService.addEmploymentInfodDataFromExcel(cellList,approval);
		}else if(docId.equals(ConstantCode.atmInfoDocId)){
			status = atmInfoDataService.addATMInfodDataFromExcel(cellList, approval);
		}else if(docId.equals(ConstantCode.branchInfoDocId)) {
			status = branchInfoDataService.addBranchInfodDataFromExcel(cellList, approval);
		}else if(docId.equals(ConstantCode.fundRateInfoDocId)) {
			status = fundRateDataService.addFundRateDataFromExcel(cellList, approval);
		}else if(docId.equals(ConstantCode.monthlyExchangeRateInfoDocId)) {
			status = monthlyExchangeRateDataService.addMonthlyExchangeRateDataFromExcel(cellList, approval);
		}
		return status;
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
