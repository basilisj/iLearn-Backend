package com.example.controller;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Assignment;
import com.example.model.Discussion;
import com.example.model.User;
import com.example.model.UserRoles;
import com.example.service.EmailService;
import com.example.service.UserService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@RestController

@RequestMapping(value="/users")
@CrossOrigin(origins = "*")
//@RequestMapping({"/api"})
@NoArgsConstructor
@AllArgsConstructor(onConstructor=@__(@Autowired))
public class UserController {

	private UserService uServ;
	private EmailService eServ;
	
	@PostMapping("/register")
	public ResponseEntity<String> createUser(@RequestBody LinkedHashMap<String, String> user){
		UserRoles r1 = uServ.getRoleIdById(Integer.parseInt(user.get("roleId")));
		System.out.println(r1);
		User u = new User(user.get("firstName"), user.get("lastName"), user.get("email"), user.get("password"), r1);
		if(uServ.registerUser(u)) {
			
			try {
		    	//This is where I added the email component
		    	  eServ.sendUserLogin(u);
				} catch (Exception e) {
					e.printStackTrace();
				}
			return new ResponseEntity<String>("User was registered", HttpStatus.CREATED);
		}else {
			return new ResponseEntity<String>("User already exists", HttpStatus.CONFLICT);
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<User> loginUser(@RequestBody LinkedHashMap<String, String> user){
		User u = uServ.loginUser(user.get("username"), user.get("password"));
		if(u == null) {
			return new ResponseEntity<User>(u, HttpStatus.FORBIDDEN);
		}else { 
			return new ResponseEntity<User>(u, HttpStatus.OK);
		}
	}

	@PostMapping("/update")
	public ResponseEntity<User> updateUser (@RequestBody LinkedHashMap<String, String> user){
		User u =uServ.getUserById(Integer.parseInt(user.get("userId")));
		uServ.updateUser(Integer.parseInt(user.get("userId")),user.get("password"));
		return new ResponseEntity<>(uServ.getUserById(Integer.parseInt(user.get("userId"))), HttpStatus.OK);
	}
	@GetMapping("/discussion/{id}")
	 public ResponseEntity<List<Discussion>> getUserPosts(@PathVariable("id") int userId){
	        System.out.println(userId);
	        User u = uServ.getUserById(userId);
	        return new ResponseEntity<>(u.getDiss(), HttpStatus.OK);
	        }
	@GetMapping("/assignment/{id}")
	 public ResponseEntity<List<Assignment>> getUserAssignment(@PathVariable("id") int userId){
	        System.out.println(userId);
	        User u = uServ.getUserById(userId);
	        return new ResponseEntity<>(u.getAssign(), HttpStatus.OK);
	        }
}
