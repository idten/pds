package com.ibk.pds.common.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ibk.pds.common.model.UserInfo;

public interface UserInfoRepository extends MongoRepository<UserInfo,String>{
	void deleteByUserId(String userId);
	public UserInfo findByUserId(String userId);
}
