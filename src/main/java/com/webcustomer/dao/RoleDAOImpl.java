package com.webcustomer.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.webcustomer.entity.Role;


@Repository
public class RoleDAOImpl implements RoleDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Role findRoleByName(String role) {

		Session currentSession = sessionFactory.getCurrentSession();

		Query<Role> q = currentSession.createQuery("from Role where name=:roleName", Role.class);
		q.setParameter("roleName", role);
		
		Role r = null;
		
		try {
			return q.getSingleResult();
		} catch (Exception e) {
			return r;
		}
	}
}
