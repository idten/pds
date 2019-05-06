package com.ibk.pds.common.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ibk.pds.common.model.UserInfo;

@Service
public class AccountService implements UserDetailsService{

	
	@Autowired
	private UserInfoService userInfoService;
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("=========================");
		//accounts.
		UserInfo userInfo = userInfoService.findByUserId(username);
//		Account account = accounts.findByEmail(username);
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		//일단 무조건 password
		String password = passwordEncoder.encode("password");
				
		return new User(userInfo.getUserId(),password,authorities);
//		
//		UserDetails userDetails = new UserDetails() {
//
//			@Override
//			public Collection<? extends GrantedAuthority> getAuthorities() {
//				// TODO Auto-generated method stub
//				
//				List<GrantedAuthority> authorities = new ArrayList<>();
//				authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
//				
//				return authorities;
//			}
//
//			@Override
//			public String getPassword() {
//				// TODO Auto-generated method stub
//				return account.getPassword();
//				//return null;
//			}
//
//			@Override
//			public String getUsername() {
//				// TODO Auto-generated method stub
//				return account.getEmail();
//				//return null;
//			}
//
//			@Override
//			public boolean isAccountNonExpired() {
//				// TODO Auto-generated method stub
//				return true;
//			}
//
//			@Override
//			public boolean isAccountNonLocked() {
//				// TODO Auto-generated method stub
//				return true;
//			}
//
//			@Override
//			public boolean isCredentialsNonExpired() {
//				// TODO Auto-generated method stub
//				return true;
//			}
//
//			@Override
//			public boolean isEnabled() {
//				// TODO Auto-generated method stub
//				return true;
//			}
//			
//		};
//		
		
		
		//return userDetails;
	}
	
//	public Account save(Account account) {
//		account.setPassword(passwordEncoder.encode(account.getPassword()));
//		return accounts.save(account);
//	}

}
