package com.ibk.pds.code.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "PDS_ALARMSTD")
public class AlarmStd {
	@Id
	private String alarmStdCode;
	private String alarmStdName;
	
	
	public AlarmStd(String alarmStdCode, String alarmStdName) {
		this.alarmStdCode = alarmStdCode;
		this.alarmStdName = alarmStdName;
	}
	public String getAlarmStdCode() {
		return alarmStdCode;
	}
	public void setAlarmStdCode(String alarmStdCode) {
		this.alarmStdCode = alarmStdCode;
	}
	public String getAlarmStdName() {
		return alarmStdName;
	}
	public void setAlarmStdName(String alarmStdName) {
		this.alarmStdName = alarmStdName;
	}
	


}
