package com.example.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import com.example.model.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
@Table(name="subject")
public class Subject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="subject_id")
	private int subjectId;
	@Column(name="subject")
	private String subject;

	@JsonIgnore
	@OneToMany(mappedBy="subject")
	private List<Assignment> aType = new ArrayList<Assignment>();
	
	


	public Subject(int subjectId, String subject) {
		super();
		this.subjectId = subjectId;
		this.subject = subject;
	}
	public Subject(String subject) {
		this.subject = subject;
	}
	public Subject(String subject, List<Assignment> aType) {
		super();
		this.subject = subject;
		this.aType = aType;
	}

	
	
}
