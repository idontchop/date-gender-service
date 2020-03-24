package com.idontchop.gender.repositories;

import org.springframework.data.repository.CrudRepository;

import com.idontchop.gender.entities.Gender;

public interface GenderRepository extends CrudRepository <Gender, Long> {

}
