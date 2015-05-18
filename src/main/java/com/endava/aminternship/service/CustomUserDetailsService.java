package com.endava.aminternship.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.endava.aminternship.dao.interfaces.UserDAO;
import com.endava.aminternship.entity.SecurityUser;

@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
	//get user from the database, via Hibernate
	@Autowired
	private UserDAO userDao;
 
	@Transactional(readOnly=true)
	@Override
	public UserDetails loadUserByUsername(final String username) 
		throws UsernameNotFoundException {
		com.endava.aminternship.entity.User user = userDao.findUserByEmail(username);
		SecurityUser secUser = new SecurityUser(user);
		return secUser;
	}
	
	
 
	// Converts our "User" to
	// org.springframework.security.core.userdetails.User
//	private User buildUserForAuthentication(com.endava.aminternship.entity.User user) {
//		Collection<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>();
//		authorities.add(new GrantedAuthorityImpl(user.getRole()));
//		return new User(user.getEmail(), "", true, true, true, true, authorities);
//	}
}
