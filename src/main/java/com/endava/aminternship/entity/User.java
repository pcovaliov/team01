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
import org.hibernate.annotations.Cascade;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;

import com.endava.aminternship.annotation.UniqueEmail;

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
	@UniqueEmail(message = "Such email already exists!")
	@Pattern(regexp = "[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})", message = " Email is not valid")
	private String email;

	@Column(name = "role", nullable = false, columnDefinition = "varchar(15) default 'ROLE_USER'")
	private String role = "ROLE_USER";

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade= {CascadeType.PERSIST, CascadeType.REMOVE})
	@JsonBackReference
	private Collection<Tweet> tweets = new ArrayList<Tweet>();

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "userFollower", cascade=CascadeType.REMOVE, orphanRemoval=true)
	private Set<FollowRelationship> followers = new HashSet<FollowRelationship>();
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "userFollowing", cascade=CascadeType.REMOVE, orphanRemoval=true)
	private Set<FollowRelationship> following = new HashSet<FollowRelationship>();
	
	@Column(name = "image_url")
	private String imageUrl;

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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

	public Set<FollowRelationship> getFollowers() {
		return this.followers;
	}

	public void setFollowers(Set<FollowRelationship> Followers) {
		this.followers = Followers;
	}
	

	public void addFollower(FollowRelationship follower) {
		this.followers.add(follower);
	}

	public void removeFollower(FollowRelationship follower) {
		this.followers.remove(follower);
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
	public Set<FollowRelationship> getFollowing() {
		return this.following;
	}

	public void setFollowing(Set<FollowRelationship> following) {
		this.following = following;
	}


	public void addFollowing(FollowRelationship following) {
		this.following.add(following);
	}
	public void removeFollowing(FollowRelationship following) {
		this.following.remove(following);
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", firstname=" + firstname + ", lastname="
				+ lastname + ", email=" + email + "]";
	}
}
