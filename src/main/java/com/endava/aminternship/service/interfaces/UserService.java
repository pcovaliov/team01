package com.endava.aminternship.service.interfaces;

import java.util.List;

import com.endava.aminternship.entity.User;

public interface UserService {
	public void addUser(User user);
	public boolean updateUser(User user);
	public void removeUser(Integer id);

	public List<User> listUser(int limit, int offset);
	
	public User findUserById(Integer id);
	public User findUserByEmail(String email);
}
