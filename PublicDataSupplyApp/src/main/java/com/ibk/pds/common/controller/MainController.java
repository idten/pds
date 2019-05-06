package com.ibk.pds.common.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.ibk.pds.common.model.ApiInfo;
import com.ibk.pds.common.model.DocumentInfo;
import com.ibk.pds.common.model.UserInfo;
import com.ibk.pds.common.repository.DocumentInfoRepository;
import com.ibk.pds.common.repository.UserInfoRepository;
import com.ibk.pds.common.service.ApiInfoService;
import com.ibk.pds.common.service.DocumentInfoService;
import com.ibk.pds.common.service.UserInfoService;

@Controller
public class MainController 
{
	@Autowired
	DocumentInfoService documentInfoService;
	//UserInfoRepository userInfoRepository;
	@Autowired
	ApiInfoService apiInfoService;
	@Autowired
	UserInfoService userInfoService;

	private Logger logger = LoggerFactory.getLogger(MainController.class);


	@RequestMapping(value = "/login")
	public ModelAndView loginSecurity(ModelAndView mav) {
		mav.setViewName("login");
		return mav;
	}


	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public ModelAndView login(ModelAndView mav) {
		mav.setViewName("login");
		return mav;
	}
	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public ModelAndView loginPost(ModelAndView mav) {
		System.out.println("====");
		mav.setViewName("login");
		return mav;
	}

	@RequestMapping(value = "/logincheck.do", method = RequestMethod.POST)
	public ModelAndView loginCheck(ModelAndView mav) {
		logger.info("loginCHeck--");
		//ModelAndView mav2 = new ModelAndView();
		//"redirect:/user"; 
		mav.setViewName("redirect:/index2");


		return mav;
	}

	//시작 페이지 처리 최초 대시보드 겸용 
	@RequestMapping(value = "/index.do", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView mav,HttpServletRequest request) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
		User user = (User) authentication.getPrincipal();
		System.out.println("User SessionInfo = "+user.getUsername());


		String userId="";
		//로그인 처리가 없다면 기본 값은 d23358 
		userId = user.getUsername();

//		if(userId.equals("")) {

//			if(null==request.getParameter("userId"))
//			{	
//				userId="d23358";
//			}else {
//				userId = request.getParameter("userId");

//			}
//		}
		//String userId="d23358";
		logger.info("login id="+userId);

		UserInfo userInfo = userInfoService.findByUserId(userId);//   findByUserId
		if(userInfo==null) {
			userId="d23358";
		}
		userInfo = userInfoService.findByUserId(userId);//   findByUserId
		DocumentInfo docInfo=null;
		List<DocumentInfo> docInfoList = documentInfoService.getDocInfoListByOwner(userId);
		List<ApiInfo> apiInfoList = apiInfoService.getApiInfoList();
		mav.addObject("userInfo",userInfo);
		mav.addObject("datalist",docInfoList);	
		mav.addObject("apilist",apiInfoList);
		mav.setViewName("index");
		logger.info("index Test End");
		return mav;
	}

	@RequestMapping(value = "/doctest", method = RequestMethod.GET)
	public ModelAndView doc(ModelAndView mav) {
		logger.info("Doc Test");
		List<DocumentInfo> docInfoList = documentInfoService.getDocInfoList();
		DocumentInfo docInfo=null;
		int len = docInfoList.size();
		for(int i = 0; i<len ; i++){
			docInfo = docInfoList.get(i);

			logger.info("doc OUTPUT"+docInfo.toString());
		}

		mav.addObject("datalist",docInfoList);
		mav.setViewName("doc");
		logger.info("Doc Test End");

		return new ModelAndView("redirect:/user");
	}

	@RequestMapping(value="/loginPDS", method = RequestMethod.POST) 
	public ModelAndView redirectPost(RedirectAttributes redirectAttr,@RequestParam("id") String id,
			@RequestParam("password") String password

			) 
	{ 
		Map<String, String> map = new HashMap<String,String>();
		System.out.println("id="+id);
		redirectAttr.addFlashAttribute("id", id); 
		return new ModelAndView("redirect:/index"); 
	}
}
