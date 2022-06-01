package com.webcustomer.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.webcustomer.dao.UserDAO;
import com.webcustomer.entity.Role;
import com.webcustomer.entity.User;
import com.webcustomer.user.CrmUser;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	@Transactional
	public void saveUser(CrmUser crmUser) {
		User user = new User();
		user.setUserName(crmUser.getUserName());
		user.setPassword(passwordEncoder.encode(crmUser.getPassword()));
		user.setFirstName(crmUser.getFirstName());
		user.setLastName(crmUser.getLastName());
		user.setEmail(crmUser.getEmail());
		
		if (!crmUser.getFormRole().equals("ROLE_EMPLOYEE")) {
			user.setRoles(Arrays.asList(new Role("ROLE_EMPLOYEE"), new Role(crmUser.getFormRole())));
		} else {
			user.setRoles(Arrays.asList(new Role("ROLE_EMPLOYEE")));
		}
		
		userDAO.saveUser(user);
	}

	@Override
	@Transactional
	public User findByUserName(String userName) {
		return userDAO.findByUserName(userName);
	}
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userDAO.findByUserName(userName);
		
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		
		return new org.springframework.security.core.userdetails.User(user.getUserName(),
				user.getPassword(),
				mapRolesToAuthorities(user.getRoles())
		);
	}
		
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new
		SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
}
