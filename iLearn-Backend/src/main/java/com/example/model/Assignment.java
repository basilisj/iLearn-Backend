package com.example.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.model.*;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="assignment")
public class Assignment {

	
	@Id
	@Column(name="assign_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int assignId;
	
	@Column(name="name")
	private String name;
	
	
	@Column(name="grade")
	private String grade;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created", nullable=false)
	private Date createDate;
	
	
	@Column(name="description")
	private String des;
	
	@JsonIgnore
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(
			name="assn_grade_junction",
			
			
			joinColumns= {@JoinColumn(name="assign_id")},
			
			inverseJoinColumns = {
					@JoinColumn(name="user_id")
					}
			
			)
		List<User> grades = new ArrayList<User>();

	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name="teacher", referencedColumnName = "user_id")
	private User teacher;
	
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name="student", referencedColumnName = "user_id")
	private User student;
	
	
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name="subject")
	private Subject subject;



	public Assignment(String name, String des, User teacher, Subject subject) {
		super();
		this.name = name;
		this.des = des;
		this.teacher=teacher;
		this.subject = subject;
	}


	public Assignment(String name, String content,  Subject subject, User u, User teachId) {
		this.des=content;
		this.student=u;
		this.subject=subject;
		this.name=name;
		this.teacher=teachId;
		
		
	}
	
	

	public Assignment(int assignId, String name, String des, User teacher, Subject subject) {
		super();
		this.assignId = assignId;
		this.name = name;
		this.des = des;
		this.teacher=teacher;
		this.subject = subject;
	}

/*
	public Assignment(int assignId, String grade, User student) {
		super();
		this.assignId = assignId;
		this.grade = grade;
		this.student = student;
	}
*/

	public Assignment(int assignId, String name, String content, User student, Subject subject, User teachId) {
		super();
		this.assignId = assignId;
		this.name = name;
		this.des = content;
		this.student = student;
		this.subject = subject;
		this.teacher=teachId;
	}

/*
	public Assignment(int assignId, String name, String des, User teacher, Subject subject) {
		super();
		this.assignId = assignId;
		this.name = name;
		this.des = des;
		this.teacher = teacher;
		this.subject = subject;
	}

	*/
	
}
