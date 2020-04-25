package com.idontchop.gender.dtos;

import com.idontchop.gender.entities.User;

public class UserDto {
	
	private String name;
	private String gender;
	private String interestedIn;
	
	public static UserDto from (User user) {
		UserDto dto = new UserDto();
		dto.name = user.getName();
		dto.gender = user.getGender().getName();
		dto.interestedIn = user.getInterestedIn().getName();
		return dto;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getInterestedIn() {
		return interestedIn;
	}
	public void setInterestedIn(String interestedIn) {
		this.interestedIn = interestedIn;
	}
	public String getUsername() {
		return name;
	}
	
	

}
