package com.endava.aminternship.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.endava.aminternship.dao.interfaces.FollowRelationshipDAO;
import com.endava.aminternship.dao.interfaces.TwitterDAO;
import com.endava.aminternship.entity.FollowRelationship;
import com.endava.aminternship.entity.Tweet;
import com.endava.aminternship.entity.User;

@Repository
@Transactional
public class FollowRelationshipDAOImpl implements FollowRelationshipDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean addFollowRelationship(FollowRelationship fr) {
		boolean operationResult = false;
		try {
			sessionFactory.getCurrentSession().save(fr);
			operationResult = true;
		} catch (Exception e) {
			
		}
		return operationResult;
	}

	@Override
	public boolean removeFollowRelationship(User userFollowed,
			User userFollowing) {
		
			if (userFollowed == null || userFollowing == null)
				return false;
			
			Query q = null;
			q = sessionFactory.getCurrentSession().createQuery(
					"delete from FollowRelationship fr where fr.userFollowing=? and fr.userFollower = ?");
			q.setInteger(0, userFollowed.getId());
			q.setInteger(1, userFollowing.getId());
			q.executeUpdate();
			
			return true;
	}
	
	
	
}
