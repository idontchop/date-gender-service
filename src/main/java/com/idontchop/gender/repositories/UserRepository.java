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
			+ "u.interestedIn.id = :interestedIn AND u.gender.id = :gender AND "
			+ "u.name IN (:potentials)")
	List<String> findNameByGenderAndInterestedInAndNameIn
		(long gender, long interestedIn, List<String> potentials);

	@Query ( "SELECT name FROM User u WHERE "
			+ "u.interestedIn.id = :interestedIn AND u.gender.id = :gender")
	List<String> findNameByGenderAndInterestedIn
		(long gender, long interestedIn);

}
