package com.endava.aminternship.dao;

import java.util.Collection;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.endava.aminternship.dao.interfaces.TwitterDAO;
import com.endava.aminternship.entity.Tweet;
import com.endava.aminternship.entity.User;

@Repository
@Transactional
public class TwitterDAOImpl implements TwitterDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Collection<Tweet> getTweetsForUser(User user, int limit, int offset) {
		 
		Query q = sessionFactory.getCurrentSession().createQuery("from Tweet t where t.user = :user_id order by t.date DESC");
		q.setInteger("user_id", user.getId());
		q.setFirstResult(offset);
		q.setMaxResults(limit);
		return q.list();
	}

	@Override
	public Tweet insertTweet(Tweet tweet) {
		sessionFactory.getCurrentSession().save(tweet);
		return tweet;
	}
}
