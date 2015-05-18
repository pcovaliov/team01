package com.endava.aminternship.dao.interfaces;

import java.util.Collection;
import java.util.Set;

import com.endava.aminternship.entity.Tweet;
import com.endava.aminternship.entity.User;

public interface TwitterDAO {

	public Collection<Tweet> getTweetsForUser(User user, int limit, int offset);
	public Collection<Tweet> getNewsFeedForUser(User user);
	public Tweet insertTweet(Tweet tweet);
}
