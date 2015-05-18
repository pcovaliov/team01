package com.endava.aminternship.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.persistence.JoinColumn;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;

@Entity
@Table(name = "user_table")
public class User implements Serializable {
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
	@Pattern(regexp = "[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})", message = " Email is not valid")
	private String email;

	@Column(name = "role", nullable = false, columnDefinition = "varchar(15) default 'ROLE_USER'")
	private String role = "ROLE_USER";

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.REMOVE)
	@JsonBackReference
	private Collection<Tweet> tweets = new ArrayList<Tweet>();

	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JoinTable(name = "following_users", joinColumns = { @JoinColumn(name = "main_user", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "following_user", nullable = false, updatable = false) })
	private Set<User> followers = new HashSet<User>();

	@ManyToMany(mappedBy = "followers", fetch = FetchType.EAGER)
	private Set<User> following = new HashSet<User>();

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

	public Set<User> getFollowers() {
		return followers;
	}

	public void setFollowers(Set<User> followers) {
		this.followers = followers;
	}

	public void addFollower(User follower) {
		this.followers.add(follower);
	}

	public void removeFollower(User follower) {
		this.followers.remove(follower);
	}

	public Set<User> getFollowing() {
		return following;
	}

	public void setFollowing(Set<User> following) {
		this.following = following;
	}

	public void addFollowing(User following) {
		this.following.add(following);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstname=" + firstname + ", lastname="
				+ lastname + ", email=" + email + "]";
	}
}
