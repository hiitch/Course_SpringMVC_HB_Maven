package com.webcustomer.dao;

import com.webcustomer.entity.User;

public interface UserDAO {
	
	User findByUserName(String userName);
	
	void saveUser(User user);
}
