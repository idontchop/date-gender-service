package com.idontchop.gender.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.idontchop.gender.entities.Gender;
import com.idontchop.gender.entities.User;
import com.idontchop.gender.repositories.GenderRepository;
import com.idontchop.gender.repositories.UserRepository;

@Service
public class GenderService {
	
	@Autowired
	GenderRepository genderRepository;
	
	@Autowired
	UserRepository userRepository;
	
	/**
	 * Receives a Username and the potentials that the search api is considering. This will reduce that list
	 * based on gender preferences.
	 * 
	 * Throws an error if user is not found. Can't make a search if we haven't been told the user's preferences.
	 * 
	 * @param userName
	 * @param potentials
	 * @return
	 * @throws NoSuchElementException
	 */
	public List<String> reduceByInterested ( String userName, List<String> potentials) throws NoSuchElementException {
		
		User user = userRepository.findByName(userName).orElseThrow();
		
		return userRepository.findNameByGenderAndInterestedInAndNameIn
				(user.getInterestedIn().getId(), user.getGender().getId(), potentials);
		
	}
	
	public Iterable<User> userList() {
		return userRepository.findAll();
	}
	
	/**
	 * Accepts a username and matches the gender and interestedIn strings to 
	 * set the gender preferences. Returns NoSuchElement if gender or interestedIn
	 * not found. Username is always add. If duplicate, user is overwritten 
	 * @param username
	 * @param gender
	 * @param interestedIn
	 * @return
	 */
	public User addUser ( String username, String genderArg, String interestedInArg ) {
		
		// check if already exists
		User user = userRepository.findByName(username).orElse(new User(username));
		
		// Find gender preferences
		Gender gender = genderRepository.findByName(genderArg).orElseThrow();
		Gender interestedIn = genderRepository.findByName(interestedInArg).orElseThrow();
		
		user.setGender(gender);
		user.setInterestedIn(interestedIn);
		
		userRepository.save(user);
		
		return user;
		
	}
	
	public void deleteUser ( String username ) {
		User user = userRepository.findByName(username).orElseThrow();
		userRepository.delete(user);
	}
	
	public User getUser ( String username ) {
		return userRepository.findByName(username).orElseThrow();
	}

}
