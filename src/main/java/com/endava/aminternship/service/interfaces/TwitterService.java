package com.endava.aminternship.service.interfaces;

import java.util.Collection;
import java.util.List;

import com.endava.aminternship.entity.Tweet;
import com.endava.aminternship.entity.User;

public interface TwitterService {

	public Collection<Tweet> getTweetsForUser(User user, int limit, int offset);
	public Tweet addTweet(Tweet tweet);
}
