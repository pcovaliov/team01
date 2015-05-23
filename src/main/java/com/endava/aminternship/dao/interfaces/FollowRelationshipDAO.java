package com.endava.aminternship.dao.interfaces;

import java.util.Collection;
import java.util.Set;

import com.endava.aminternship.entity.FollowRelationship;
import com.endava.aminternship.entity.Tweet;
import com.endava.aminternship.entity.User;

public interface FollowRelationshipDAO {

	public  boolean addFollowRelationship(FollowRelationship fr);
	public  boolean removeFollowRelationship(User userFollowed, User userFollowing);
}
