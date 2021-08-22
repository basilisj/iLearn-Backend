package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Assignment;
import com.example.model.Discussion;
import com.example.model.User;
import com.example.model.UserRoles;
import com.example.repository.RolesRepo;
import com.example.repository.UserRepo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
@AllArgsConstructor(onConstructor=@__(@Autowired))

public class UserService {

	private UserRepo uDao;
	private RolesRepo rDao;
	
	public boolean registerUser(User user) {
		try {
			User saved = uDao.save(user);
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	
	
	public User loginUser(String username, String password) {
		User user = uDao.findByUsername(username);
		if(user == null) {
			return null;
		}else {
			if(!user.getPassword().equals(password)) {
				return null;
			}else {
				return user;
			}
		}
	}
	
	public User displayUser(String username) {
		User user = uDao.findByUsername(username);
		if(user == null) {
			return null;
		}else {
			return user;
		}
	}
	
	public User getUserById(int id) {
		return uDao.getById(id);
	}
	
	
	public List<Discussion> getUserDiss(int id) {
		User user = uDao.getById(id);
		return user.getDiss();
	}
	
	
	public UserRoles getRoleIdById(int id) {
		return rDao.getById(id);
	}
	public User updateUser(int userId, String password ) {
			User a = uDao.findById(userId).get();
			a.setPassword(password);
			uDao.save(a);
			if(a == null) {
				return null;
				
			}else {
				return a;
			}
	}
}
