package com.ibk.pds.common.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Document(collection = "PDS_USER_INFO")
public class DocumentLog {
	
	@Id
	private String logId;

	//문서 관련 처리한 사람 
	private String userId;
	private String summary;
	private String contents;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private Date regDate = new Date();	//등록일자 
}
