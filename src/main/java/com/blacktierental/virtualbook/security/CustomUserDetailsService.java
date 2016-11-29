package com.blacktierental.virtualbook.security;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blacktierental.virtualbook.model.User;
import com.blacktierental.virtualbook.model.UserProfile;
import com.blacktierental.virtualbook.service.UserService;
 
@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);
	
	@Autowired
	private UserService userService;
	
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username)throws UsernameNotFoundException{
		User user = userService.findByUsername(username);
		logger.info("User: {}",user);
		if(user==null){
			logger.info("USer not found");
			throw new UsernameNotFoundException("Username not found");
		}
		return new org.springframework.security.core.userdetails.User
				(user.getUsername(),user.getPassword(), user.getState().equals("Active"),true,true ,true, getGrantedAuthorities(user));
	}
	
	private List<GrantedAuthority> getGrantedAuthorities(User user){
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for(UserProfile userProfile : user.getUserProfiles()){
			System.out.println("UserProfile:"+userProfile);
			authorities.add(new SimpleGrantedAuthority("ROLE_"+userProfile.getType()));
		}
		logger.info("authorities: {}",authorities);
		return authorities;
	}
}
