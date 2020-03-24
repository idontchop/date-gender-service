package com.idontchop.gender;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.idontchop.gender.entities.User;
import com.idontchop.gender.repositories.UserRepository;

@SpringBootTest
class GenderApplicationTests {
	
	@Autowired
	UserRepository userRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void testFind () {
		
		String user = "1";
		List<String> potentials = List.of("1","2");
		User u = userRepository.findByName("1").orElseThrow();
		
		System.out.println(u.getUsername());
		assertTrue (u.getUsername().equals( "1"));
//		List<String> reducedList = userRepository.findNameByGenderAndInterestedInAndNameIn(u.getGender(), u.getInterestedIn(), potentials);
		List<String> reducedList = userRepository.findNameByGenderAndInterestedIn(u.getGender(), u.getInterestedIn());
		
		assertTrue(!reducedList.isEmpty());
	}
}
