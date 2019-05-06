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
import com.ibk.pds.data.model.BranchInfoData;
import com.ibk.pds.data.model.EmploymentInfoData;
import com.ibk.pds.data.model.JobWorldData;
import com.ibk.pds.data.service.BranchInfoDataService;
import com.ibk.pds.data.service.EmploymentInfoDataService;
import com.ibk.pds.data.service.JobWorldDataService;
@Controller
public class EmploymentInfoDataController {

	private Logger logger = LoggerFactory.getLogger(EmploymentInfoDataController.class);

	@Autowired
	EmploymentInfoDataService employmentInfoDataService;

	@RequestMapping(value = "/employmentInfoView.do", method = RequestMethod.GET)
	public ModelAndView employmentInfo(ModelAndView mav) {
		logger.info("employmentInfoView Test");
		List<EmploymentInfoData> list = employmentInfoDataService.findAll();
		mav.addObject("datalist",list);
		mav.setViewName("employmentInfoView");
		return mav;
	}

}
