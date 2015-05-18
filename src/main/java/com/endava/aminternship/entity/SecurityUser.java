package com.endava.aminternship.entity;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;

public class SecurityUser extends
		org.springframework.security.core.userdetails.User {

	private User userObject;

	public SecurityUser(String username, String password, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {

		super(username, password, enabled, accountNonExpired,
				credentialsNonExpired, accountNonLocked, authorities);
	}
	
	public SecurityUser(User normalUser){
		super(normalUser.getEmail(), "", true, true,
				true, true, new ArrayList<GrantedAuthority>());
		this.userObject = normalUser;
	};

	// security related

	public Collection<GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new GrantedAuthorityImpl(this.getUserObject().getRole()));
		return authorities;
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		return true;
	}

	// end security related
	
	public User getUserObject() {
		return userObject;
	}
	
	public void setUserObject(User userObject) {
		this.userObject = userObject;
	}
	
}
