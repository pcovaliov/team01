package com.endava.aminternship.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.endava.aminternship.UserController;
import com.endava.aminternship.dao.interfaces.UserDAO;
import com.endava.aminternship.entity.User;

@Repository
public class UserDAOImpl implements UserDAO {

	final static Logger logger = Logger.getLogger(UserDAOImpl.class);
	@Autowired
	private SessionFactory sessionFactory;

	public void addUser(User user) {
		try{
			sessionFactory.getCurrentSession().save(user);
		}  catch (Exception e) {
			logger.error(" exception at saving user ");
		}
		
	}

	@SuppressWarnings("unchecked")
	public List<User> listUser(int limit, int offset) {
		Query q = null;
		List<User> userList = null;

		try {
			logger.info(" getting list of users ");
			q = sessionFactory.getCurrentSession().createQuery("from User");
			q.setFirstResult(offset);
			q.setMaxResults(limit);
			userList = q.list();
		} catch (Exception e) {
			logger.error(" exception at getting list of user ");
		}

		return userList;

	}

	public void removeUser(Integer id) {
		
		User user = (User) sessionFactory.getCurrentSession().load(User.class,
				id);
		if (null != user) {
			logger.info(" user " + user + " was deleted ");
			sessionFactory.getCurrentSession().delete(user);
			
		}

	}

	@Override
	public User findUserById(Integer id) {

		User user = (User) sessionFactory.getCurrentSession().get(User.class,
				id);
		return user;
	}

	@Override
	public boolean updateUser(User user) {
		try {
			if (user != null) {
				sessionFactory.getCurrentSession().merge(user);
				logger.info("user was update");
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			logger.error(" exception at updating user "+ user);
			return false;
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public User findUserByEmail(String email) {
		List<User> result = sessionFactory.getCurrentSession()
				.createQuery("from User as u where u.email=?")
				.setString(0, email).list();
		if (result.size() != 0) {
			return (User) result.get(0); // returning first element
		} else {
			return null;
		}
	}
}