package com.idontchop.gender;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.idontchop.gender.entities.Gender;
import com.idontchop.gender.entities.User;
import com.idontchop.gender.repositories.GenderRepository;
import com.idontchop.gender.repositories.UserRepository;

@SpringBootTest
class GenderApplicationTests {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	GenderRepository genderRepository;

	@Test
	void contextLoads() {
	}
	
	@Test
	void testWhatdoesitdo () {
		//userRepository.deleteById((long) 138);
		//userRepository.deleteByName("Nate Man");
	}
	
	void createRecords ( ) {
		
		Gender gender = genderRepository.findByName("Man").get();
		Gender interestedIn = genderRepository.findByName("Woman").get();
				
		for ( int c = 21; c < 40; c++ ) {
			User user = new User(Integer.toString(c));
			
			if ( c % 4 == 0) {
				user.setGender(gender);
				user.setInterestedIn(interestedIn);
			} else {
				user.setGender(interestedIn);
				user.setInterestedIn(gender);
			}
			
			userRepository.save(user);
		}
	}

	
	void testFind () {
		
		String userName = "1";
		List<String> potentials = List.of("11","2");
		User u = userRepository.findByName(userName).orElseThrow();
		
		System.out.println(u.getUsername());
		assertTrue (u.getUsername().equals( "1"));
		//genderRepository.save (u.getGender());
		//genderRepository.save(u.getInterestedIn());
		assertTrue(u.getGender().getName().equals("Man"));
		assertTrue (u.getInterestedIn().getName().equals("Woman"));
		List<String> reducedList = userRepository.findNameByGenderAndInterestedInAndNameIn(u.getGender().getId(), u.getInterestedIn().getId(), potentials);
		//List<String> reducedList = userRepository.findNameByGenderAndInterestedIn(u.getGender().getId(), u.getInterestedIn().getId());
		
		assertTrue(!reducedList.isEmpty());
		assertTrue(reducedList.get(0).equals(userName));
	}
}
