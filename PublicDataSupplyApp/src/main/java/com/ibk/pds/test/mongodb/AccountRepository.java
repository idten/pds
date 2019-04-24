package com.ibk.pds.test.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<Account,String>{

}
