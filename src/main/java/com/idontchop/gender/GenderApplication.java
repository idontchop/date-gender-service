package com.idontchop.gender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Gender Microservice for storage of gender preferences.
 * 
 * See MainController for endpoints
 * 
 * Spring Data Rest endpoints: /data
 * 
 * @author micro
 *
 */
@SpringBootApplication
public class GenderApplication {

	public static void main(String[] args) {
		SpringApplication.run(GenderApplication.class, args);
	}

}
