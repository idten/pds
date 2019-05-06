package com.ibk.pds.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		 .headers().disable()
		.authorizeRequests()
			.antMatchers("/", "/api/**","/create").permitAll()		
		.anyRequest().authenticated()				//나머지 모든건 인증이 필요하다 
		.and()
		.formLogin()
		.loginPage("/login")
			.permitAll()
		.and()
		.logout()
		.logoutSuccessUrl("/hello")     //성공시 
		.permitAll();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		
//		return PasswordEncoderFactories.createDelegatingPasswordEncoder()
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
//	
//	
//	@Bean
//	@Override
//	public UserDetailsService userDetailsService() {
//		UserDetails user =
//				User.withDefaultPasswordEncoder()
//				.username("user")
//				.password("password")
//				.roles("USER")
//				.build();
//
//		return new InMemoryUserDetailsManager(user);
//	}
	

}
