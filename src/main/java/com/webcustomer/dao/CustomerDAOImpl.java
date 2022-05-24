package com.webcustomer.dao;

import com.webcustomer.utils.SortField;

import java.util.List;
import java.util.Objects;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.webcustomer.entity.Customer;


@Repository
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomers(int sortField) {
		Session session = sessionFactory.getCurrentSession();

		Query<Customer> q = session.createQuery("from Customer order by " + SortField.labelOfSortFieldByIndex(sortField) , Customer.class);
		
		return q.getResultList();
	}

	@Override
	public void saveCustomer(Customer customer) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(customer);
	}

	@Override
	public Customer getCustomerByID(int id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Customer.class, id);
	}

	@Override
	public void deleteCustomer(int customerID) {
		Session session = sessionFactory.getCurrentSession();
		Query<Customer> q = session.createQuery("delete from Customer where id=:customerID", Customer.class);
		q.setParameter("customerID", customerID);
		q.executeUpdate();
	}

	@Override
	public List<Customer> searchCustomers(String customerName) {
		Session session = sessionFactory.getCurrentSession();
		Query<Customer> q = null;
		
		if (Objects.isNull(customerName) || customerName.trim().length() <= 0) {
			return getCustomers(SortField.LAST_NAME.sortIndex);
		}
		
		q = session.createQuery("from Customer where lower(firstName) like :customerName or lower(lastName) like :customerName", Customer.class);
		q.setParameter("customerName", "%" + customerName.toLowerCase() + "%");
		
		return q.getResultList();
	}
}
