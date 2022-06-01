package com.webcustomer.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.webcustomer.entity.User;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public User findByUserName(String userName) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<User> q = currentSession.createQuery("from User where userName=:uName", User.class);
		q.setParameter("uName", userName);
		User u = null;
		
		try {
			return q.getSingleResult();
		} catch(Exception e) {
			return u;
		}
	}

	@Override
	public void saveUser(User user) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(user);
	}

}
