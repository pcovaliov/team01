package com.endava.aminternship.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.endava.aminternship.dao.interfaces.FollowRelationshipDAO;
import com.endava.aminternship.dao.interfaces.UserDAO;
import com.endava.aminternship.entity.FollowRelationship;
import com.endava.aminternship.entity.User;
import com.endava.aminternship.service.interfaces.FollowRelationshipService;

@Service
public class FollowRelationshipServiceImpl implements FollowRelationshipService {
	@Autowired
	private FollowRelationshipDAO frDAO;
	
	@Override
	public boolean addFollowRelationship(FollowRelationship fr) {
		frDAO.addFollowRelationship(fr);
		return false;
	}

	@Override
	public boolean removeFollowRelationship(User userFollowed,
			User userFollowing) {
		return frDAO.removeFollowRelationship(userFollowed, userFollowing);
	}

}
