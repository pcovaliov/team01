package com.endava.aminternship.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;

@Entity
@Table(name = "user_table")
public class User extends org.springframework.security.core.userdetails.User  implements Serializable {
	private static final long serialVersionUID = -4095447686156731089L;
	
	@Id
	@Column(name = "ID")
	@GeneratedValue
	private Integer id;
	
	@Column(name = "first_name")
	private String firstname;
	
	@Column(name = "last_name")
	private String lastname;
	
	@Column(name = "email", unique = true)
	@Pattern(regexp = "[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})", message= " Email is not valid")
	private String email;
	
	@Column(name = "role" , nullable = false, columnDefinition="varchar(15) default 'ROLE_USER'")
	private String role = "ROLE_USER";
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.REMOVE)
	@JsonBackReference
	private Collection<Tweet> tweets = new ArrayList<Tweet>();
	
	public User(){
		super("Anonimous", "", true, true, true, true, new ArrayList<GrantedAuthority>());
	};
		
	public User(
			String username, 
			String password, 
			boolean enabled,
			boolean accountNonExpired, 
			boolean credentialsNonExpired,
			boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		
		super(username, password, enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, authorities);
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	public Collection<Tweet> getTweets() {
		return tweets;
	}
	public void setTweets(Collection<Tweet> tweets) {
		this.tweets = tweets;
	}

	//security related 
	
	public Collection<GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>();
		authorities.add(new GrantedAuthorityImpl(this.role));
		return authorities;
	}
    public boolean isAccountNonExpired() { return true;}
    public boolean isAccountNonLocked() { return true; }
    public boolean isCredentialsNonExpired() {return true; }
    public boolean isEnabled() { return true;}
   
    //end security related
    
    @Override
    public String toString() {
    	return "User [id=" + id + ", firstname=" + firstname + ", lastname="
    			+ lastname + ", email=" + email + "]";
    }
}
