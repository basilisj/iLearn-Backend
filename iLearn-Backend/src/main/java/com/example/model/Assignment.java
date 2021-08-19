package com.example.model;

import java.util.Date;
import java.util.List;

import com.example.model.*;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	
	@Column(name="grade")
	private int grade;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created", nullable=false)
	private Date createDate;
	
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="submitted")
	private Date updateDate;
	
	@Column(name="description")
	private String des;
	
	

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
}
