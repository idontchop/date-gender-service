package com.idontchop.gender.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.idontchop.gender.dtos.ReduceRequest;
import com.idontchop.gender.dtos.RestMessage;
import com.idontchop.gender.entities.User;
import com.idontchop.gender.services.GenderService;

/**
 * Endpoints here are designed to return the name and reduce lists from the 
 * main api.
 * 
 * /api
 * 
 * /api/reduce: The main reduce api that search will call
 * 
 * /api/user: api used to manage users
 * 
 * PUT/POST: /api/user/{username}/{gender}/{interestedIn}
 * DELETE: /api/user/{username}
 * GET: /api/user/{username}
 * 
 * /api/gender: api used to retrieve genders (not updatable)
 * 
 * @author micro
 *
 */
@RestController
public class MainController {
	
	@Autowired
	GenderService genderService;
	
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
	@PostMapping ("/api/reduce")
	public List<String> reduce( @RequestBody @Valid ReduceRequest reduceRequest) {
		
		return genderService.reduceByInterested(reduceRequest.getName(), reduceRequest.getPotentials());
		
	}
	
	/**
	 * Development only, returns list of all usernames.
	 * @return
	 */
	@GetMapping ("/api/userList")
	public List<String> userList () {
		List<String> userList = new ArrayList<>();
		genderService.userList().forEach(e -> userList.add(e.getName()));
		return userList;
	}
	
	@RequestMapping ( value = "/api/user/{username}/{gender}/{interestedIn}",
			method = { RequestMethod.POST, RequestMethod.PUT } )
	public User addUser( @PathVariable ( name = "username", required = true ) String username,
			@PathVariable ( name = "gender", required = true) String gender,
			@PathVariable ( name = "interestedIn", required = true) String interestedIn) {
		
		return genderService.addUser(username, gender, interestedIn);
	}
	
	@DeleteMapping ( value = "/api/user/{username}")
	public RestMessage deleteUser ( @PathVariable ( name = "username", required=true) String username)  {
		try {
			genderService.deleteUser(username);
		} catch ( Exception e ) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return RestMessage.build("deleted");
	}
	
	@GetMapping ( value = "/api/user/{username}")
	public User getUser ( @PathVariable ( name = "username", required=true) String username ) {
		return genderService.getUser(username);
	}
	

}
