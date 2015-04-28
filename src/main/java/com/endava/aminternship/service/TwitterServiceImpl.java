package com.endava.aminternship.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.endava.aminternship.dao.interfaces.TwitterDAO;
import com.endava.aminternship.dao.interfaces.UserDAO;
import com.endava.aminternship.entity.Tweet;
import com.endava.aminternship.entity.User;
import com.endava.aminternship.service.interfaces.TwitterService;
import com.endava.aminternship.service.interfaces.UserService;

@Service
@Transactional
public class TwitterServiceImpl implements TwitterService {

	@Autowired
	private TwitterDAO twitterDAO;

	@Override
	public Collection<Tweet> getTweetsForUser(User user) {
		if(user == null)
			return null;
		return twitterDAO.getTweetsForUser(user);
	}

	@Override
	public Tweet addTweet(Tweet tweet) {
		return twitterDAO.insertTweet(tweet);
	}


}
