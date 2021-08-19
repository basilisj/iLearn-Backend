package com.example.model;

import java.util.HashSet;
import java.util.Set;

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
import javax.persistence.Table;

import com.example.model.User;
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
@Table(name="discussion")
public class Discussion {

	
	@Id
	@Column(name="diss_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int dissId;
	
	
	@Column(name="content")
	private String dissContent;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	@JsonIgnore
	private User user;
	
	@JsonIgnore
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(
			name="diss_likes_junction",
			joinColumns= {@JoinColumn(name="diss_id")},
			inverseJoinColumns = {@JoinColumn(name="user_id")}
			)
		Set<User> likes = new HashSet<User>();

	public Discussion(int dissId, String dissContent, User user) {
		super();
		this.dissId = dissId;
		this.dissContent = dissContent;
		this.user = user;
	}

	public Discussion(String dissContent, User user) {
		super();
		this.dissContent = dissContent;
		this.user = user;
	}
}
