package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Subject;

public interface SubjectRepo extends JpaRepository<Subject, Integer>{

	public List<Subject> findAll();
	public Subject findBySubjectId(int id);
}
