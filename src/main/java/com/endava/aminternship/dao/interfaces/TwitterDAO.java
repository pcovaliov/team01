package com.endava.aminternship.dao.interfaces;

import java.util.Collection;

import com.endava.aminternship.entity.Tweet;
import com.endava.aminternship.entity.User;

public interface TwitterDAO {

	public Collection<Tweet> getTweetsForUser(User user);
	public Tweet insertTweet(Tweet tweet);
}
