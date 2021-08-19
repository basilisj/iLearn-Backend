package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.User;
import com.example.model.Discussion;

public interface DiscussionRepo extends JpaRepository<Discussion, Integer> {

	public List<Discussion> findAll();
	public Discussion findByUser(User u);
}
