package com.ibk.pds;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.ibk.pds.common.config.ConstantCode;
import com.ibk.pds.test.mongodb.Account;
import com.ibk.pds.test.mongodb.AccountRepository;

@SpringBootApplication
public class PublicDataSupplyAppApplication extends SpringBootServletInitializer{
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(PublicDataSupplyAppApplication.class);
    }
//	@Autowired
//	AccountRepository accountRepository;
	
	//@Autowired 
	//MongoTemplate mongoTemplate;
	
	public static void main(String[] args) {
		//SpringApplication app = new SpringApplication(PublicDataSupplyAppApplication.class);
		//app.run(args);
		SpringApplication.run(PublicDataSupplyAppApplication.class, args);
	}
//	@Bean 
//	public ApplicationRunner applicationRunner() {
//		return args-> {
//			Account account = new Account();
//			String id = "1";
//			account.setId(id);
//			account.setEamil("luel2@naver.com");
//			account.setUsername("aa2a");
//		//	accountRepository.insert(account);
//			System.out.println("finished");
//			
//			Optional<Account> acc2 = accountRepository.findById(id);
//			System.out.println("findByid="+acc2.get().getEamil());
//		};
//	}

}
