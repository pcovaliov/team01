package com.endava.aminternship.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.endava.aminternship.dao.interfaces.UserDAO;
import com.endava.aminternship.entity.User;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void addUser(User user) {
		sessionFactory.getCurrentSession().save(user);
	}

	@SuppressWarnings("unchecked")
	public List<User> listUser(int limit, int offset) {
		
		Query q = sessionFactory.getCurrentSession().createQuery("from User");
		q.setFirstResult(offset);
		q.setMaxResults(limit);
		return q.list();
		
	}

	public void removeUser(Integer id) {
		User user = (User) sessionFactory.getCurrentSession().load(
				User.class, id);
		if (null != user) {
			sessionFactory.getCurrentSession().delete(user);
		}

	}

	@Override
	public User findUserById(Integer id) {
		User user = (User) sessionFactory.getCurrentSession().get(
				User.class, id);
		return user;
	}

	@Override
	public boolean updateUser(User user) {
		User userFound = findUserByEmail(user.getEmail());
		System.out.println(userFound);
		if( userFound != null && userFound.getId().equals(user.getId())){
			sessionFactory.getCurrentSession().merge(user);
			return true;
		} else {
			return false;
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public User findUserByEmail(String email) {
		List<User> result = sessionFactory.getCurrentSession().createQuery("from User as u where u.email=?").setString(0,email).list();
		if(result.size() != 0){
			return (User) result.get(0); //returning first element
		} else {
			return null;
		}
	}
}