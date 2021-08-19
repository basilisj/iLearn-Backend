package com.example.controller;

import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Discussion;
import com.example.model.User;
import com.example.service.DiscussionService;
import com.example.service.UserService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping(value="/discussion")
@AllArgsConstructor(onConstructor=@__(@Autowired))
@NoArgsConstructor
public class DiscussionController {

	private UserService uServ;
	private DiscussionService pServ;
	
	@PostMapping("/create")
	public ResponseEntity<String> createDiscussion(@RequestBody LinkedHashMap<String, String> discussion){
		User u = uServ.getUserById(Integer.parseInt(discussion.get("userId")));
		Discussion d = new Discussion(discussion.get("content"), u);
		pServ.createDiscussion(d);
		return new ResponseEntity<String>("Discussion created successfully", HttpStatus.CREATED);
	}
	
	@PostMapping("/like")
	public ResponseEntity<Discussion> likeDiscussion(@RequestBody LinkedHashMap<String, String> post){
		User u = uServ.getUserById(Integer.parseInt(post.get("userId")));
		pServ.likeDiscussion(Integer.parseInt(post.get("dissId")), u);
		return new ResponseEntity<>(pServ.getDiscussionById(Integer.parseInt(post.get("postId"))), HttpStatus.OK);
	}
	
}
