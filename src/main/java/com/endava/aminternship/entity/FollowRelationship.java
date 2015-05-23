package com.endava.aminternship.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "follow_relationship")
public class FollowRelationship {
	@Id
	@Column(name = "ID")
	@GeneratedValue
	private Integer id;
	@ManyToOne
	private User userFollowing;
	@ManyToOne
	private User userFollower;

	public User getUserFollowing() {
		return userFollowing;
	}

	public void setUserFollowing(User userFollowing) {
		this.userFollowing = userFollowing;
	}

	public User getUserFollower() {
		return userFollower;
	}

	@Override
	public String toString() {
		return "Follow [id=" + id + ", userFollowing=" + userFollowing.getId()
				+ ", userFollower=" + userFollower.getId() + "]";
	}

	public void setUserFollower(User userFollower) {
		this.userFollower = userFollower;
	}

	public FollowRelationship(User userFollowing, User userFollower) {
		this.userFollowing = userFollowing;
		this.userFollower = userFollower;
	}

	public FollowRelationship() {

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
		FollowRelationship other = (FollowRelationship) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
