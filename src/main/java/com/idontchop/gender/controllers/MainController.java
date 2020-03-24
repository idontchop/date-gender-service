package com.idontchop.gender.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.idontchop.gender.dtos.ReduceRequest;

/**
 * Endpoints here are designed to return the name and reduce lists from the 
 * main api.
 * 
 * /api
 *  
 * @author micro
 *
 */
@RestController
public class MainController {
	
	/**
	 * Most of the request to this microservice go here.
	 * 
	 * This takes a username and checks it against potential matches based on their
	 * gender preferences. It will return a new list.
	 * 
	 * No names will ever be returned that weren't already in the potentials
	 * 
	 * @param reduceRequest
	 * @return
	 */
	@GetMapping ("/api/reduce")
	public List<String> reduce( @RequestBody @Valid ReduceRequest reduceRequest) {
		
		return reduceRequest.getPotentials();
		
	}

}
