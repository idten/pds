package com.ibk.pds;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TestDateString {
	public static void main(String[] args) {
		SimpleDateFormat formatter = new SimpleDateFormat ( "yyyyMMddHHmmss", Locale.KOREA );
		Date currentTime = new Date ( );
		String dTime = formatter.format ( currentTime );
		System.out.println ( dTime );


//		출처: https://jang8584.tistory.com/232 [개발자의 길]

//		출처: https://jang8584.tistory.com/232 [개발자의 길]
	}

}
