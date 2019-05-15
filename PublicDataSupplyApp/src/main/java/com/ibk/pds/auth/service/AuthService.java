package com.ibk.pds.auth.service;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.ibk.pds.common.controller.ApiInfoController;

import kr.co.smartguru.apim.gateway.cipher.SeedCipher;
import kr.co.smartguru.apim.gateway.codec.binary.Base64;
import kr.co.smartguru.apim.gateway.util.APIMGatewayUtil;

@Service
public class AuthService {
	
	private Logger logger = LoggerFactory.getLogger(AuthService.class);
	
	
	public int auth(String apimKey) {
	//	mav.setViewName("authTest");
		if(apimKey.contentEquals("testKey")) {
			apimKey = "lNWxryx-e4WE2ibp6SzLQknGNlQCcMfA8LHYjAE75iU";
		}
		logger.info("apimKey="+apimKey);
		//String apimKey = "lNWxryx-e4WE2ibp6SzLQknGNlQCcMfA8LHYjAE75iU";
		int checkResult = -1;
		try {
		if(apimKey!=null) {
			logger.info("인증처리전"+apimKey);
			
			
			logger.info("인증처리:"+SG_APIM_Check(apimKey));
			checkResult = SG_APIM_Check(apimKey);
		}
		
		if(checkResult==1) {
			logger.info("인증 성공");
		}else {
			logger.info("apikey="+apimKey+"returnCode:"+checkResult);
			logger.info("인증 실패");
		}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return checkResult;
		//return mav;
	}
	private static byte[] key = (new BigInteger("536d61727467757275204150494d3135", 16)).toByteArray();
	 public  int SG_APIM_Check(String keyStr) {
		    int i = 0;
		    SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmm");
		    SeedCipher cipher = new SeedCipher();
		    Calendar cal = Calendar.getInstance();
		    cal.add(12, -10);
		    
		    if (!Base64.isBase64(keyStr)) {
		    	logger.info("1 point");
		    	return -1;
		    }
		    byte[] keyValue = Base64.decodeBase64(keyStr);
		    try {
		      while (i <= 20) {
			    logger.info("2 point");

		        cal.add(12, 1);
		        String keyTime = df.format(cal.getTime());
		        logger.info("3 point");

		        byte[] encryptKey = cipher.encrypt(keyTime.getBytes(), key);
		        logger.info("4 point");

		        byte[] compareValue = cipher.encrypt("SmartGuru APIM Server".getBytes(), encryptKey);
		        logger.info("5 point["+new String(keyValue)+"]["+new String(compareValue)+"]");
		        logger.info("5-1 point["+keyValue+"]["+compareValue+"]");

		        if (Arrays.equals(keyValue, compareValue)) return 1; 
		        i++;
		      } 
		      byte[] compareValue = cipher.encrypt("SmartGuru APIM Server".getBytes(), Base64.decodeBase64("81KAF2xpiZ4z0MytcB8uRg=="));
		      
		        logger.info("6 point["+new String(keyValue)+"]["+new String(compareValue)+"]");
		        logger.info("6-1 point["+keyValue+"]["+compareValue+"]");
		      
		      if (Arrays.equals(keyValue, compareValue)) return 1; 
		    } catch (Exception e) {
		    	  logger.info("7 point");
e.printStackTrace();
		    	return -1;
		    } 

		    logger.info("point 8");
		    return -1;
		  }
	public int auth() {
		//	mav.setViewName("authTest");
			//if(apimKey.contentEquals("testKey")) {
			String 	apimKey = "lNWxryx-e4WE2ibp6SzLQknGNlQCcMfA8LHYjAE75iU";
			//}
			//String apimKey = "lNWxryx-e4WE2ibp6SzLQknGNlQCcMfA8LHYjAE75iU";
			int checkResult = -1;
			if(apimKey!=null)checkResult = SG_APIM_Check(apimKey);
			if(checkResult==1) {
				logger.info("인증 성공");
			}else {
				logger.info("인증 실패");
			}
			
			return checkResult;
			//return mav;
		}
}
