package com.endava.aminternship.dao.interfaces;

import java.util.List;

import com.endava.aminternship.entity.User;

public interface UserDAO {

	public void addUser(User user);
	public boolean updateUser(User user);

	public List<User> listUser(int limit, int offset);

	public void removeUser(Integer id);
	public User findUserById(Integer id);
	public User findUserByEmail(String email);
}
