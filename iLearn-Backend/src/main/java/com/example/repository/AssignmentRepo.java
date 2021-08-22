package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.model.Assignment;

public interface AssignmentRepo extends JpaRepository<Assignment, Integer>{

	public List<Assignment> findAll();
	//public Assignment findById(int id);
	/*
	@Query("UPDATE Assignment set grade = :grade WHERE id = :assignId")
	void setGrade(@Param("assignId") int id, @Param("grade") String grade);
	*/
}
