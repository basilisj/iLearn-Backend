package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.User;
import com.example.model.Discussion;
import com.example.repository.DiscussionRepo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
@AllArgsConstructor(onConstructor=@__(@Autowired))
public class DiscussionService {

	private DiscussionRepo dDao;
	
	public void createDiscussion(Discussion p) {
		dDao.save(p);
	}
	
	public List<Discussion> getAllDiscussions(){
		return dDao.findAll();
	}
	
	public void likeDiscussion(int postId, User u) {
		Discussion p = dDao.findById(postId).get();
		p.getLikes().add(u);
		dDao.save(p);
	}
	
	public Discussion getDiscussionById(int postId) {
		return dDao.findById(postId).get();
	}
	
}
