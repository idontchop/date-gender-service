package com.idontchop.gender.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class User {
	
	public User () {}
	
	@Id
	@GeneratedValue ( strategy = GenerationType.IDENTITY)
	private long id;
	
	private String username;
	
	@NotNull
	@ManyToOne
	@JoinColumn ( name = "gender_id")
	private Gender gender;
	
	@NotNull
	@ManyToOne
	@JoinColumn ( name = "interestedin_id")
	private Gender interestedIn;
	
	// Any benefit to this?
	//private Gender List<Gender> interestedIn;
	

	public User (String username) {
		this.username = username;
	}
	
	public User (String username, Gender gender) {
		this.gender = gender;
		this.username = username;
	}
	
	public User (String username, Gender gender, Gender interestedIn) {
		this.gender = gender;
		this.username = username;
		this.interestedIn = interestedIn;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Gender getInterestedIn() {
		return interestedIn;
	}

	public void setInterestedIn(Gender interestedIn) {
		this.interestedIn = interestedIn;
	}
	
	
	
}
