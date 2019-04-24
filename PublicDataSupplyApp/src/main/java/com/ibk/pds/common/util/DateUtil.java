package com.ibk.pds.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
	public static String getDateYYYYMMDDHHMMSS() {
		SimpleDateFormat formatter = new SimpleDateFormat ( "yyyyMMddHHmmss", Locale.KOREA );
		Date currentTime = new Date ( );
		return formatter.format ( currentTime );
		//return dTime
	}
	public static String getDateYYYYMMDD() {
		SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy-MM-dd", Locale.KOREA );
		Date currentTime = new Date ( );
		return formatter.format ( currentTime );
		//return dTime
	}
	public static String getDateYYYYMMDDHHMMSSMISSS() {
		SimpleDateFormat formatter = new SimpleDateFormat ( "yyyyMMddHHmmssSSS", Locale.KOREA );
		Date currentTime = new Date ( );
		return formatter.format ( currentTime );
		//return dTime
	}
	
	
}
