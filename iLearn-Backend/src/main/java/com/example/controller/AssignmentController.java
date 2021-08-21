package com.example.controller;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Assignment;
import com.example.model.Subject;
import com.example.model.User;
import com.example.service.AssignmentService;
import com.example.service.UserService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping(value="/assignment")
@CrossOrigin(origins= "*")
@AllArgsConstructor(onConstructor=@__(@Autowired))
@NoArgsConstructor
public class AssignmentController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserService uServ;
	private AssignmentService aServ;
	
	@PostMapping("/create")
	public ResponseEntity<String> createAssignment(@RequestBody LinkedHashMap<String, String> assign){
		User u = uServ.getUserById(Integer.parseInt(assign.get("userId")));
		Subject s = aServ.getSubjectById(Integer.parseInt(assign.get("subjectId")));
		Assignment a = new Assignment(assign.get("name"), assign.get("des"), u, s);
		aServ.createAssignment(a);
		return new ResponseEntity<String>("Assignment created successfully", HttpStatus.CREATED);
	}
	@PostMapping("/grade")
	public ResponseEntity<Assignment> gradeAssignment(@RequestBody LinkedHashMap<String, String> assign){
		User u = uServ.getUserById(Integer.parseInt(assign.get("userId")));
		aServ.gradeAssignment(Integer.parseInt(assign.get("assignId")), assign.get("grade") , u);
		return new ResponseEntity<>(aServ.getAssignmentById(Integer.parseInt(assign.get("assignId"))), HttpStatus.OK);
	}
	
	@PostMapping("/submit")
	public ResponseEntity<String> submitAssignment(@RequestBody LinkedHashMap<String, String> submit){
		User u = uServ.getUserById(Integer.parseInt(submit.get("userId")));
		User t = uServ.getUserById(Integer.parseInt(submit.get("userId")));
		//Assignment b = aServ.getAssignmentById(Integer.parseInt(submit.get("assignId")));
		Subject s = aServ.getSubjectById(Integer.parseInt(submit.get("subjectId")));
		Assignment a = new Assignment(submit.get("name"), submit.get("content"), s, u, t);
		aServ.createAssignment(a);
		return new ResponseEntity<String>("Assignment submitted successfully", HttpStatus.ACCEPTED);
	}
	@GetMapping("/all")
	public ResponseEntity<List<Assignment>> getAll(){
		return new ResponseEntity<>(aServ.getAllAssignments(), HttpStatus.OK);
	}
	@PostMapping("/viewgrades")
	public ResponseEntity<Assignment> viewGrades(@RequestBody LinkedHashMap<String, String> grade){
		User u = uServ.getUserById(Integer.parseInt(grade.get("userId")));
		aServ.viewGrade(Integer.parseInt(grade.get("assignId")), u);
		return new ResponseEntity<>(aServ.getAssignmentById(Integer.parseInt(grade.get("assignId"))), HttpStatus.OK);
	}
}
