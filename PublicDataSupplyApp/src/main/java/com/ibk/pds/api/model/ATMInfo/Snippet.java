package com.ibk.pds.api.model.ATMInfo;

public class Snippet {
	public List<ATMInfoData> findATMInfoDataList(Pageable page){
			List<ATMInfoData> atmInfoDataList = atmInfoDataRepository.findAll(page).getContent();
			return atmInfoDataList;
		}
		
}

