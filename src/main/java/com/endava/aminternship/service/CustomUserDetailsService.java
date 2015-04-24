package com.endava.aminternship.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.endava.aminternship.dao.interfaces.UserDAO;


 
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

		System.out.println(user);
		return buildUserForAuthentication(user);

	}
 
	// Converts com.mkyong.users.model.User user to
	// org.springframework.security.core.userdetails.User
	private User buildUserForAuthentication(com.endava.aminternship.entity.User user) {
		Collection<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>();
		authorities.add(new GrantedAuthorityImpl("ROLE_USER"));
		return new User(user.getEmail(), "", true, true, true, true, authorities);
	}
}



//	if(user!=null)
//	{
//		String password=user.getPassword();
//		boolean enabled=user.isEnabled();
//		boolean accountNonExpired=user.isEnabled();
//		boolean credentialsNonExpired=user.isEnabled();
//		boolean accountNonLocker=user.isEnabled();
//		//Populate the user role
//		Collection<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>();
//		authorities.add(new GrantedAuthorityImpl(user.getRole()));
//		//Creation of Spring security user object
//		org.springframework.security.core.userdetails.User securityUser=new 
//		org.springframework.security.core.userdetails.User(username,password,enabled,accountNonExpired,credentialsNonExpired,accountNonLocker,authorities);
//	
//	return securityUser;
//	}
//	else
//	{
//		throw new UsernameNotFoundException("User Not found!!!");
//	}
			