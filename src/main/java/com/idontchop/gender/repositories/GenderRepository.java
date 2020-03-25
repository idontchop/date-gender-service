package com.idontchop.gender.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.idontchop.gender.entities.Gender;

public interface GenderRepository extends CrudRepository <Gender, Long> {

	Optional<Gender> findByName(String name);
}
