package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.User;
import com.example.model.UserRoles;

public interface RolesRepo extends JpaRepository<UserRoles, Integer>{

	public UserRoles findByRoleId(int id);
}
