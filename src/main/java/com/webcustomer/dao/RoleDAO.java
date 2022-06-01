package com.webcustomer.dao;

import com.webcustomer.entity.Role;

public interface RoleDAO {

	Role findRoleByName(String role);
	
}
