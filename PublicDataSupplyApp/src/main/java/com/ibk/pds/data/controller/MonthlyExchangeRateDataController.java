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
import com.ibk.pds.data.model.JobWorldData;
import com.ibk.pds.data.model.MonthlyExchangeRateData;
import com.ibk.pds.data.service.ATMInfoDataService;
import com.ibk.pds.data.service.JobWorldDataService;
import com.ibk.pds.data.service.MonthlyExchangeRateDataService;
@Controller
public class MonthlyExchangeRateDataController {
	@Autowired
	MonthlyExchangeRateDataService monthlyExchangeRateDataService;
	private Logger logger = LoggerFactory.getLogger(MonthlyExchangeRateDataController.class);

	@RequestMapping(value = "/monthlyExchangeRateDataView.do", method = RequestMethod.GET)
	public ModelAndView monthlyExchangeRateDataView(ModelAndView mav) {
		logger.info("atmInfoList Test");
		List<MonthlyExchangeRateData> list = monthlyExchangeRateDataService.findAll();
		mav.addObject("datalist",list);
		mav.setViewName("monthlyExchangeRateDataView");

		return mav;
	}

}
