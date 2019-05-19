package com.ibk.pds.common.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.ibk.pds.auth.service.UserAuthService;
import com.ibk.pds.code.model.DepInfo;
import com.ibk.pds.code.repository.AlarmStdRepository;
import com.ibk.pds.code.repository.DocCycleRepository;
import com.ibk.pds.code.service.DepInfoService;
import com.ibk.pds.common.model.ApiInfo;
import com.ibk.pds.common.model.DocumentInfo;
import com.ibk.pds.common.model.DocumentStatus;
import com.ibk.pds.common.model.UserInfo;
import com.ibk.pds.common.repository.DocumentInfoRepository;
import com.ibk.pds.common.repository.UserInfoRepository;
import com.ibk.pds.common.service.ApiInfoService;
import com.ibk.pds.common.service.DocumentInfoService;
import com.ibk.pds.common.service.DocumentStatusService;
import com.ibk.pds.common.service.UserInfoService;
import com.ibk.pds.common.util.DateUtil;
import com.ibk.pds.log.model.LogDocData;
import com.ibk.pds.log.service.LogDocDataService;

@Controller
public class DocInfoController {
	@Autowired
	DocumentInfoService documentInfoService;
	@Autowired
	DocumentStatusService documentStatusService;
	@Autowired
	DepInfoService depInfoService;
	@Autowired
	UserInfoService userInfoService;
	@Autowired
	ApiInfoService apiInfoService;
	@Autowired
	AlarmStdRepository alarmStdRepository;
	@Autowired
	DocCycleRepository docCycleRepository;
	@Autowired
	LogDocDataService logDocDataService;

	
	private Logger logger = LoggerFactory.getLogger(DocInfoController.class);

	//문서 리스트 화면 
	@RequestMapping(value = "/doc.do", method = RequestMethod.GET)
	public ModelAndView doc(ModelAndView mav) {

		//전체 리스트 
		List<DocumentInfo> docInfoList = documentInfoService.getDocInfoList();
		//logger.info("doc="+)
		DocumentInfo docInfo=null;
		
		UserAuthService userAuthService = new UserAuthService();
		UserInfo userInfo = userAuthService.getUserAuthInfo(userInfoService);
		mav.addObject("userInfo",userInfo);
		
		int len = docInfoList.size();
		for(int i = 0; i<len ; i++){
			docInfo = docInfoList.get(i);
			logger.info("doc OUTPUT"+docInfo.toString());
		}
		mav.addObject("datalist",docInfoList);
		List<UserInfo> userInfoList = userInfoService.getUserInfoList();
		List<DepInfo> depCodeList = depInfoService.getDepCodeList();
		Map<String,String> depCodeMap = depInfoService.getDepCodeMap();
		mav.addObject("depMap", depCodeMap);
		mav.addObject("deplist",depCodeList);
		mav.setViewName("doc");

		return mav;

	}
	//문서 리스트 화면   //현재는 사용하지 않는 화면 
	@RequestMapping(value = "/docUpload.do", method = RequestMethod.GET)
	public ModelAndView docUpload(ModelAndView mav) {

		logger.info("docUpload.do");
		//전체 리스트 
		List<DocumentInfo> docInfoList = documentInfoService.getDocInfoList();
		//logger.info("doc="+)
		DocumentInfo docInfo=null;
		int len = docInfoList.size();
		for(int i = 0; i<len ; i++){
			docInfo = docInfoList.get(i);
			logger.info("doc OUTPUT"+docInfo.toString());
		}
		mav.addObject("datalist",docInfoList);



		List<UserInfo> userInfoList = userInfoService.getUserInfoList();
		List<DepInfo> depCodeList = depInfoService.getDepCodeList();
		Map<String,String> depCodeMap = depInfoService.getDepCodeMap();
		mav.addObject("depMap", depCodeMap);
		mav.addObject("deplist",depCodeList);

		mav.setViewName("docUpload");

		return mav;

	}
	
	//new.do 페이지에서 사용
	//최초 등록시 docInfo 및 docStatus 상태 초기화 수행 
	@RequestMapping(value="/docadd", method = RequestMethod.POST) 
	public String docAdd(
			@RequestParam("docId") String docId,
			@RequestParam("docName") String docName,
			@RequestParam("docDetailInfo") String docDetailInfo,
			@RequestParam("docCycle") String docCycle,
			@RequestParam("docOwners") String docOwners,

			@RequestParam("alarmYN") String alarmYN,
			@RequestParam("autoSendYN") String autoSendYN,
			@RequestParam("autoApprovalYN") String autoApprovalYN,
			@RequestParam("alarmStd") String alarmStd
			)
	{ 
		String today = DateUtil.getDateYYYYMMDD();
		//	String docId = "D"+DateUtil.getDateYYYYMMDDHHMMSS();
		String regDate = today;
		String updateDate = today;
		String docCycleName = docCycleRepository.findByDocCycleCode(docCycle).getDocCycleName();
		String alarmStdName = alarmStdRepository.findByAlarmStdCode(alarmStd).getAlarmStdName();
		//일단 1명에 대한 처리만 먼저 한후에 추후 확대 
		UserInfo userInfo = userInfoService.findByUserId(docOwners);
		String docOwnerName = userInfo.getUserName();
		String depName = userInfo.getDepName();		
		DocumentInfo docInfo = new DocumentInfo(docId, docName, docDetailInfo, docCycle, docCycleName, docOwners,docOwnerName, depName,alarmYN,autoSendYN,autoApprovalYN,alarmStd,alarmStdName,regDate,updateDate);

		DocumentStatus docStatus = new DocumentStatus(docId, "-", docName, docOwners, autoApprovalYN,"대기", "-", 0, "-");
		documentInfoService.addDocInfo(docInfo);

		logger.info("docStatus Add"+docInfo.toString());
		
		documentStatusService.addDocStatus(docStatus);
		logger.info("Doc Add End:");
		return "redirect:/doc.do"; 
	}
	
	
	
	
	//문서상세정보 관련 
	@RequestMapping(value = "/docDetailList.do", method = RequestMethod.GET)
	public ModelAndView docDetailList(
			@RequestParam(value="docId",required=false) String docId,
			ModelAndView mav) {
		
		UserAuthService userAuthService = new UserAuthService();
		UserInfo userInfo = userAuthService.getUserAuthInfo(userInfoService);
	//	mav.addObject("userInfo",userInfo);
		
		//	logger.info("Doc id"+docId);
		List<DocumentInfo> docInfoList;
		if(docId==null) {
			//현재 접속 사용자 정보를 가져와서 
			if("ADMIN".equals(userInfo.getAuthCode())) {
				docInfoList = documentInfoService.getDocInfoList();
				logger.info("현재 대상 문서수 (ADMIN)= "+docInfoList.size());

			}else {
				docInfoList = documentInfoService.getDocInfoListByOwner(userInfo.getUserId());
				logger.info("현재 대상 문서수 = "+docInfoList.size());
			}
		}else {
			docInfoList = documentInfoService.getDocInfobyDocId(docId);
		}

		DocumentInfo docInfo=null;
		int len = docInfoList.size();
		Map<String, List<ApiInfo>> map = new HashMap<String,List<ApiInfo>>(); 


		for(int i = 0; i<len ; i++){
			docInfo = docInfoList.get(i);
			List<ApiInfo> apiInfoList = apiInfoService.getApiInfoListByDocId(docInfo.getDocId());
			map.put(docInfo.getDocId(), apiInfoList);
			logger.info("doc OUTPUT"+docInfo.toString());
		}
		
		List<ApiInfo> apiInfoList = apiInfoService.getApiInfoList();

		mav.addObject("apiMap",map);
		mav.addObject("datalist",docInfoList);
		mav.addObject("apilist",apiInfoList);


		List<UserInfo> userInfoList = userInfoService.getUserInfoList();
		List<DepInfo> depCodeList = depInfoService.getDepCodeList();
		Map<String,String> depCodeMap = depInfoService.getDepCodeMap();
		mav.addObject("depMap", depCodeMap);
		mav.addObject("deplist",depCodeList);
		mav.setViewName("docDetailList");

		return mav;

	}


	//docDetailListApi.do

	@RequestMapping(value = "/docDetailListApi.do", method = RequestMethod.GET)
	public ModelAndView docDetailListApi(@RequestParam("docId") String docId, ModelAndView mav) {

		List<ApiInfo> apiInfoList = apiInfoService.getApiInfoListByDocId(docId);
		logger.info("Api List.size()="+apiInfoList.size());

		DocumentStatus docStatus = documentStatusService.getDocStatus(docId);
		List<LogDocData> logDocDataList = logDocDataService.getLogDocDataListByDocId(docId);
		LogDocData logDocData = null;
		int len = logDocDataList.size();
		for(int i = 0; i<len ; i++){
			logDocData = logDocDataList.get(i);
			logger.info("logDocData OUTPUT"+logDocData.toString());
		}

		mav.addObject("logDoclist",logDocDataList);
		mav.addObject("docStatus",docStatus);
		mav.addObject("apilist",apiInfoList);
		mav.setViewName("docDetailListApi");
		return mav;

	}


	//상세정보 페이지 
	@RequestMapping(value = "/apidocinfo.do", method = RequestMethod.GET)
	public ModelAndView apidocInfo(ModelAndView mav) {
		logger.info("Doc Test End");
		UserAuthService userAuthService = new UserAuthService();
		UserInfo userInfo = userAuthService.getUserAuthInfo(userInfoService);
		mav.addObject("userInfo",userInfo);
		mav.setViewName("apidocinfo");
		
		return mav;

	}

	@RequestMapping(value = "/docapi.do", method = RequestMethod.GET)
	public ModelAndView docApi(ModelAndView mav) {

		List<DocumentInfo> docInfoList = documentInfoService.getDocInfoList();
		DocumentInfo docInfo=null;
		int len = docInfoList.size();
		for(int i = 0; i<len ; i++){
			docInfo = docInfoList.get(i);

			logger.info("doc OUTPUT"+docInfo.toString());
		}
		mav.addObject("datalist",docInfoList);

		List<ApiInfo> apiInfoList = apiInfoService.getApiInfoList();
		ApiInfo apiInfo=null;
		len = apiInfoList.size();
		for(int i = 0; i<len ; i++){
			apiInfo = apiInfoList.get(i);
			logger.info(apiInfo.toString());
		}

		mav.addObject("apilist",apiInfoList);
		mav.setViewName("docapi");
		logger.info("DocApi End");

		return mav;
	}

	@RequestMapping(value="/docedit", method = RequestMethod.POST) 
	public String userEdit(@RequestParam("docId") String docId,
			@RequestParam("docName") String docName,
			@RequestParam("docDetailInfo") String docDetailInfo,
			@RequestParam("docCycle") String docCycle,
			@RequestParam("docOwners") String docOwners,

			@RequestParam("alarmYN") String alarmYN,
			@RequestParam("autoSendYN") String autoSendYN,
			@RequestParam("autoApprovalYN") String autoApprovalYN,
			@RequestParam("alarmStd") String alarmStd,
			@RequestParam("regDate") String regDate

			)
	{ 
		String today = DateUtil.getDateYYYYMMDD();
		String docCycleName = docCycleRepository.findByDocCycleCode(docCycle).getDocCycleName();
		String alarmStdName = alarmStdRepository.findByAlarmStdCode(alarmStd).getAlarmStdName();

		UserInfo userInfo = userInfoService.findByUserId(docOwners);
		String docOwnerName = userInfo.getUserName();
		String depName = userInfo.getDepName();

		DocumentInfo docInfo = new DocumentInfo(docId, docName, docDetailInfo, docCycle, docCycleName, docOwners,docOwnerName, depName,alarmYN,autoSendYN,autoApprovalYN,alarmStd,alarmStdName,regDate,today);

		//DocumentInfo docInfo = new DocumentInfo(docId, docName, docDetailInfo, docCycle,docOwners,alarmYN,autoSendYN,autoApprovalYN,alarmStd,regDate,today);


		logger.info("docInfo Edit"+docInfo.toString());
		documentInfoService.saveDocInfo(docInfo);//Info(docInfo);
		logger.info("Update End:");
		return "redirect:/doc.do"; 
	}



	@RequestMapping(value="/docdelete", method = RequestMethod.POST) 
	public String docDelete(
			@RequestParam("docId") String docId,
			@RequestParam("docName") String docName,
			@RequestParam("docDetailInfo") String docDetailInfo,
			@RequestParam("docCycle") String docCycle,
			@RequestParam("docOwners") String docOwners,

			@RequestParam("alarmYN") String alarmYN,
			@RequestParam("autoSendYN") String autoSendYN,
			@RequestParam("autoApprovalYN") String autoApprovalYN,
			@RequestParam("alarmStd") String alarmStd

			)
	{ 
		String docCycleName = docCycleRepository.findByDocCycleCode(docCycle).getDocCycleName();
		String alarmStdName = alarmStdRepository.findByAlarmStdCode(alarmStd).getAlarmStdName();
		UserInfo userInfo = userInfoService.findByUserId(docOwners);
		String docOwnerName = userInfo.getUserName();
		String depName = userInfo.getDepName();
		DocumentInfo docInfo = new DocumentInfo(docId, docName, docDetailInfo, docCycle, docCycleName, docOwners,docOwnerName, depName,alarmYN,autoSendYN,autoApprovalYN,alarmStd,alarmStdName,"","");
		logger.info("Doc Delete:"+docInfo.toString());
		if(docInfo.getDocId()==null) {
			logger.info("Doc Delete fail");
			return "error";
		}
		documentInfoService.deleteDocInfo(docInfo);
		logger.info("Doc Delete End:");
		return "redirect:/doc.do"; 
	}

}
