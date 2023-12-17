package com.arjuninamdar.congressionalchallenge.security.jwt;

import com.arjuninamdar.congressionalchallenge.models.sql.UserInfo;
import com.arjuninamdar.congressionalchallenge.repositories.UserInfoRepository;
import com.arjuninamdar.congressionalchallenge.security.CustomUserDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*; 

@Service
public class JwtUserDetailsService implements UserDetailsService {
	@Autowired
	UserInfoRepository userInfoRepository; 

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserInfo user = userInfoRepository.findByUsername(username); 
		
		
		if(user == null)
			throw new UsernameNotFoundException("USER NOT FOUND: " + username);
		

		List<SimpleGrantedAuthority> authorities = new ArrayList<>(); 
		authorities.add(new SimpleGrantedAuthority(user.getRoles())); 

		return new CustomUserDetails(user.getUsername(), user.getPassword(), user.getIsEnabled(), authorities, user.getUserId()); 

		



	}
}