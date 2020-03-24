package com.idontchop.gender.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.idontchop.gender.entities.Gender;
import com.idontchop.gender.entities.User;

public interface UserRepository extends CrudRepository<User,Long> {
	
	Optional<User> findByName(String name);
	
	@Query ( "SELECT name FROM User u WHERE "
			+ "u.interestedIn = :interestedIn AND u.gender = :gender AND "
			+ "u.name IN (:potentials)")
	List<String> findNameByGenderAndInterestedInAndNameIn
		(Gender gender, Gender interestedIn, List<String> potentials);

	@Query ( "SELECT name FROM User u WHERE "
			+ "u.interestedIn = :interestedIn AND u.gender = :gender")
	List<String> findNameByGenderAndInterestedIn
		(Gender gender, Gender interestedIn);

}
