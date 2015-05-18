package com.endava.aminternship.service;

import java.util.Collection;


import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.endava.aminternship.dao.interfaces.TwitterDAO;
import com.endava.aminternship.entity.Tweet;
import com.endava.aminternship.entity.User;
import com.endava.aminternship.service.interfaces.TwitterService;


@Service
public class TwitterServiceImpl implements TwitterService {

	@Autowired
	private TwitterDAO twitterDAO;

	@Override
	@Transactional
	public Collection<Tweet> getTweetsForUser(User user,int limit, int offset) {
		if(user == null)
			return null;
		return twitterDAO.getTweetsForUser(user,limit,offset);
	}

	@Override
	@Transactional
	public Tweet addTweet(Tweet tweet) {
		return twitterDAO.insertTweet(tweet);
	}

	@Override
	public Collection<Tweet> getTweetsForUsers(Set<User> usersSet) {
		return twitterDAO.getTweetsForUsers(usersSet);
	}
}
