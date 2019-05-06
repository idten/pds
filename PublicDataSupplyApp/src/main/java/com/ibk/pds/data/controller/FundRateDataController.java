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
import com.ibk.pds.data.model.FundRateData;
import com.ibk.pds.data.model.JobWorldData;
import com.ibk.pds.data.service.ATMInfoDataService;
import com.ibk.pds.data.service.FundRateDataService;
import com.ibk.pds.data.service.JobWorldDataService;
@Controller
public class FundRateDataController {
	//
//	public class ApiInfoController {
		@Autowired
		FundRateDataService fundRateService;
		private Logger logger = LoggerFactory.getLogger(FundRateDataController.class);
		
		@RequestMapping(value = "/fundRateDataView.do", method = RequestMethod.GET)
		public ModelAndView fundRateDataView(ModelAndView mav) {
			logger.info("fundRateDataView Test");
			List<FundRateData> list = fundRateService.findAll();
		    mav.addObject("datalist",list);
			mav.setViewName("fundRateDataView");
			
			return mav;
		}
		
}
