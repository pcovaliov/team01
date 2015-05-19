package com.endava.aminternship.service.interfaces;

import java.util.Collection;
import java.util.Set;

import com.endava.aminternship.entity.Tweet;
import com.endava.aminternship.entity.User;

public interface TwitterService {
	public Tweet addTweet(Tweet tweet);
	public Collection<Tweet> getTweetsForUser(User user, int limit, int offset);
	public Collection<Tweet> getAllTweetsForUser(User user);
	public Collection<Tweet> getNewsFeedForUser(User user);
}
