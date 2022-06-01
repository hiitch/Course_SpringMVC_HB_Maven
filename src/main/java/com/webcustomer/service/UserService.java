package com.webcustomer.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.webcustomer.entity.User;
import com.webcustomer.user.CrmUser;

public interface UserService extends UserDetailsService {
	
	User findByUserName(String userName);
	
	void saveUser(CrmUser crmUser);
	
}
