package com.endava.aminternship.dao.interfaces;

import java.util.List;

import com.endava.aminternship.entity.FollowRelationship;
import com.endava.aminternship.entity.User;

public interface UserDAO {
	public void addUser(User user);
	public boolean updateUser(User user);
	public void removeUser(Integer id);
	
	public User findUserById(Integer id);
	public User findUserByEmail(String email);
	public FollowRelationship isFollowing(User main, User follower);
	
	public List<User> listUser(int limit, int offset);
	public List<User> listUser();

}
