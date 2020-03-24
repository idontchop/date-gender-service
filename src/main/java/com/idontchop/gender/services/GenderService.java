package com.idontchop.gender.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idontchop.gender.entities.User;
import com.idontchop.gender.repositories.GenderRepository;
import com.idontchop.gender.repositories.UserRepository;

@Service
public class GenderService {
	
	@Autowired
	GenderRepository genderRepository;
	
	@Autowired
	UserRepository userRepository;
	
	public List<String> reduceByInterested ( String userName, List<String> potentials) throws NoSuchElementException {
		
		User user = userRepository.findByName(userName).orElseThrow();
		
		
		return userRepository.findNameByGenderAndInterestedInAndNameIn
				(user.getInterestedIn(), user.getGender(), potentials);
		
	}

}
