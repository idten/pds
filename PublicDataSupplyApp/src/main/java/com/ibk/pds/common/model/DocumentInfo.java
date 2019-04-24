package com.ibk.pds.common.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Document(collection = "PDS_DOC_INFO")
public class DocumentInfo {

	
	//문서 ID
	@Id
	private String docId;
	//문서 이름 
	private String docName;
	//문서 상세 정보 
	private String docDetailInfo;
	//문서 주인 
	private String docOwners;
	private String docOwnerName;
	private String depName;
	//자동 발신여부 
	private String autoSendYN;
	//알람 사용여부 
	private String alarmYN;
	//갱신주기
	private String docCycle;
	private String docCycleName;
	
	





	//@OneToMany(mappedBy = "docId")
      private List<ApiInfo> apis;
  //  		  private List<City> cities;
	//private String docType;//한글 엑셀 
	//private String depName;//담당부서 
	
    
      public List<ApiInfo> getApis() {
		return apis;
	}

	public void setApis(List<ApiInfo> apis) {
		this.apis = apis;
	}

//	public ApiInfo removeApiInfo(ApiInfo apiInfo) {
//		getApis().remove(apiInfo);
//          apis.setState(null);
//
//          return city;
//      }
//      
//	  public ApiInfo addApiInfo(ApiInfo apiInfo) {
//    	  getApis().add(apiInfo);
//    	  
//          apiInfo.setState(this);
//
//          return city;
//      }

	private String autoApprovalYN;
	//알람기준 주 - 워화수목금토일
	//월 - 날짜 
	
	private String alarmStd;
	private String alarmStdName;
	
	//@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private String regDate;	//등록일자 
	private String updateDate;
	
	
	public DocumentInfo(String docId, 
			String docName, 
			String docDetailInfo, 
			String docCycle, 
			String docCycleName, 
			
			String docOwners, 
			String docOwnerName,
			String depName,
			String alarmYN, 
			String autoSendYN, 
			String autoApprovalYN, 
			String alarmStd,
			String alarmStdName,
			
			String regDate,
			String updateDate
			) {
		this.docId = docId;
		this.docName = docName;
		this.docDetailInfo = docDetailInfo;
		this.docCycle = docCycle;
		this.docCycleName = docCycleName;
		
		this.docOwners = docOwners;
		this.docOwnerName = docOwnerName;
		this.depName = depName;
		this.alarmYN = alarmYN;
		this.autoSendYN = autoSendYN;
		this.autoApprovalYN = autoApprovalYN;
		this.alarmStd = alarmStd;
		this.alarmStdName = alarmStdName;
		this.regDate= regDate;
		this.updateDate = updateDate;
		}
	
	
	public String getDocOwnerName() {
		return docOwnerName;
	}
	public void setDocOwnerName(String docOwnerName) {
		this.docOwnerName = docOwnerName;
	}
	public String getDepName() {
		return depName;
	}
	public void setDepName(String depName) {
		this.depName = depName;
	}
	
	public String getDocCycleName() {
		return docCycleName;
	}
	public void setDocCycleName(String docCycleName) {
		this.docCycleName = docCycleName;
	}
	public String getAlarmStdName() {
		return alarmStdName;
	}
	public void setAlarmStdName(String alarmStdName) {
		this.alarmStdName = alarmStdName;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getRegDate() {
		return regDate;
	}
	public String getUpdateDate() {
		return updateDate;
	}

	
	
	public String toString() {

		return "[DocumentInfo"
				+ ""
				+ "]docId="+docId 
				+ ",docName="+ docName  
				+ ",docDetailInfo=" + docDetailInfo
				+ ",docCycle="  + docCycle
				+ ",docOwners=" + docOwners
				+ ",alarmYN=" + alarmYN
				+ ",autoSendYN=" + autoSendYN
				+ ",autoApprovalYN=" + autoApprovalYN
				+ ",alarmStd=" + alarmStd
				+ ",regDate=" + regDate
				+ ",updateDate=" + updateDate
				
				;

	}
	public String getDocId() {
		return docId;
	}
	public void setDocId(String docId) {
		this.docId = docId;
	}
	public String getDocName() {
		return docName;
	}





	public void setDocName(String docName) {
		this.docName = docName;
	}





	public String getDocDetailInfo() {
		return docDetailInfo;
	}





	public void setDocDetailInfo(String docDetailInfo) {
		this.docDetailInfo = docDetailInfo;
	}





	public String getDocCycle() {
		return docCycle;
	}





	public void setDocCycle(String docCycle) {
		this.docCycle = docCycle;
	}





	public String getDocOwners() {
		return docOwners;
	}





	public void setDocOwners(String docOwners) {
		this.docOwners = docOwners;
	}


	public String getAlarmYN() {
		return alarmYN;
	}





	public void setAlarmYN(String alarmYN) {
		this.alarmYN = alarmYN;
	}





	public String getAutoSendYN() {
		return autoSendYN;
	}





	public void setAutoSendYN(String autoSendYN) {
		this.autoSendYN = autoSendYN;
	}





	public String getAutoApprovalYN() {
		return autoApprovalYN;
	}





	public void setAutoApprovalYN(String autoApprovalYN) {
		this.autoApprovalYN = autoApprovalYN;
	}





	public String getAlarmStd() {
		return alarmStd;
	}





	public void setAlarmStd(String alarmStd) {
		this.alarmStd = alarmStd;
	}




	
}
