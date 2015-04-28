package com.endava.aminternship.dao;

import java.util.Collection;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.endava.aminternship.dao.interfaces.TwitterDAO;
import com.endava.aminternship.dao.interfaces.UserDAO;
import com.endava.aminternship.entity.Tweet;
import com.endava.aminternship.entity.User;

@Repository
@Transactional
public class TwitterDAOImpl implements TwitterDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Collection<Tweet> getTweetsForUser(User user) {
		List result = sessionFactory.getCurrentSession().createQuery("from Tweet as t where t.user=?").setLong(0,user.getId()).list();
		return result;
	}

	@Override
	public Tweet insertTweet(Tweet tweet) {
		sessionFactory.getCurrentSession().save(tweet);
		return tweet;
	}
}
