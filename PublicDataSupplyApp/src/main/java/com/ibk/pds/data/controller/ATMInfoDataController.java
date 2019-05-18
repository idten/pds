package com.ibk.pds.data.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ibk.pds.common.controller.ApiInfoController;
import com.ibk.pds.common.model.ApiInfo;
import com.ibk.pds.common.service.ApiInfoService;
import com.ibk.pds.data.model.ATMInfoData;
import com.ibk.pds.data.service.ATMInfoDataService;
@Controller
public class ATMInfoDataController {
	//
//	public class ApiInfoController {
		@Autowired
		ATMInfoDataService atmInfoDataService;
		private Logger logger = LoggerFactory.getLogger(ATMInfoDataController.class);
		
		@RequestMapping(value = "/atmInfoView.do", method = RequestMethod.GET)
		public ModelAndView atmInfo(ModelAndView mav) {
			logger.info("atmInfoList Test");
			List<ATMInfoData> list = atmInfoDataService.findAll();
			ATMInfoData data=null;
	        mav.addObject("datalist",list);
			mav.setViewName("atmInfoView");
			
			return mav;
		}
		
}
