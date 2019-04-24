package com.ibk.pds.code.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "PDS_DOCCYCLE")
public class DocCycle {
	@Id
	private String docCycleCode;
	private String docCycleName;
	
	
	public DocCycle(String docCycleCode, String docCycleName) {
		this.docCycleCode = docCycleCode;
		this.docCycleName = docCycleName;
	}


	public String getDocCycleCode() {
		return docCycleCode;
	}


	public void setDocCycleCode(String docCycleCode) {
		this.docCycleCode = docCycleCode;
	}


	public String getDocCycleName() {
		return docCycleName;
	}


	public void setDocCycleName(String docCycleName) {
		this.docCycleName = docCycleName;
	}

}
