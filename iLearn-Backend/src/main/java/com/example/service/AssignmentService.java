package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Assignment;
import com.example.model.Subject;
import com.example.model.User;
import com.example.repository.AssignmentRepo;
import com.example.repository.SubjectRepo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
@AllArgsConstructor(onConstructor=@__(@Autowired))
public class AssignmentService {

	private AssignmentRepo aDao;
	private SubjectRepo sDao;
	public void createAssignment(Assignment a) {
		aDao.save(a);
	}
	
	public List<Assignment> getAllAssignments(){
		return aDao.findAll();
	}
	public Assignment getAssignmentById(int assignId) {
		return aDao.findById(assignId).get();
	}
	
	public Subject getSubjectById(int id) {
		return sDao.findBySubjectId(id);
	}
	public Assignment gradeAssignment(int assignId, String grade, User u) {
		Assignment a = aDao.findById(assignId).get();
		a.setGrade(grade);
		aDao.save(a);
		if(a == null) {
			return null;
			
		}else {
			return a;
			
		}
		
	}
}
