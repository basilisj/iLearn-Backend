package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.User;
import com.example.model.UserRoles;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>{

	public List<User> findAll();
	
	public User findByUsername(String username);

	public User findByUserId(int userId);
	


}
