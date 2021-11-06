package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.model.User;
import com.example.demo.repo.UserRepo;

@Controller
public class MainController {

	@Autowired
	UserRepo userRepo;
	
	/* Register */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<String> register(@RequestBody User user) {
		
		String resp = "";
		HttpStatus http ;
		User users = new User();
		try {
			users.setUserid(user.getUserid());
			users.setEmail(user.getEmail());
			users.setPassword(user.getPassword());
			users.setFirstname(user.getFirstname());
			users.setLastname(user.getLastname());
			users.setUsername(user.getUsername());
			users.setDob(user.getDob());
			users.setJoiningdate(user.getJoiningdate());
			users.setPincode(user.getPincode());
			users.setIsflagged(false);
			
			userRepo.save(users);
			resp = "User created";
			http = HttpStatus.OK;
		}
		catch(Exception e) {
			System.out.println("Exception : "+e);
			resp = "Exception occured";
			http = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<String>(resp, http);
	}

	/* Search */
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ResponseEntity<List<User>> search(@RequestParam(value = "name") String name, 
			@RequestParam(value = "surname") String surname, @RequestParam(value = "pincode") int pincode) {

		List<User> user = new ArrayList<User>();
		
		try {
			if (!name.equals("")) {
				user = userRepo.findByFirstnameOrderByDobDesc(name);
			} else if(!surname.equals("")) {
				user = userRepo.findByLastnameOrderByDobDesc(name);
			} else {
				user = userRepo.findByPincodeOrderByDobDesc(pincode);
			}
			
		}
		catch(Exception e) {
			System.out.println("Exception : "+e);
		}

		return new ResponseEntity<List<User>>(user, HttpStatus.OK);
	}

	/* Login */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ResponseEntity<Boolean> login(@RequestParam(value = "email") String email, @RequestParam(value = "pass") String pass) {

		Boolean resp = null ;
		try {
			User user = userRepo.findByEmail(email);
			if(user.getEmail().equalsIgnoreCase(email) && user.getPassword().equals(pass)) {
				resp = true;
			}else {
				resp = false;
			}
		}
		catch(Exception e) {
			System.out.println("Exception : "+e);
			resp = false;
		}

		return new ResponseEntity<Boolean>(resp, HttpStatus.OK);
	}

	
	/* Get All */
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public @ResponseBody List<User> getAll() {
		List<User> all = (List<User>) userRepo.findAll();
		for(User u : all) {
			System.out.println(u.getEmail());
		}
		return all;
	}
	
	
	/* Update By Userid */
	@RequestMapping(value = "/update/{userid}", method = RequestMethod.POST)
	public ResponseEntity<String> update(@RequestBody User user, @PathVariable(value = "userid") String id) {
		
		String resp = "";
		HttpStatus http ;
		try {
			User users = userRepo.findByUserid(id);
			users.setEmail(user.getEmail());
			users.setPassword(user.getPassword());
			users.setFirstname(user.getFirstname());
			users.setLastname(user.getLastname());
			users.setUsername(user.getUsername());
			users.setDob(user.getDob());
			users.setJoiningdate(user.getJoiningdate());
			users.setPincode(user.getPincode());
			users.setIsflagged(false);
			
			userRepo.save(users);
			resp = "User details are updated";
			http = HttpStatus.OK;
		}
		catch(Exception e) {
			System.out.println("Exception : "+e);
			resp = "Exception occured";
			http = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<String>(resp, http);
	}

	/* Hard Delete */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public ResponseEntity<String> delete(@PathVariable(value = "id") String id) {
		
		String resp = "";
		HttpStatus http ;
		try {
			User user = userRepo.findByUserid(id);
			
			if (user.isIsflagged()) {
				userRepo.deleteById(id);
				resp = "User is deleted";
				http = HttpStatus.OK;
			}else {
				resp = "User is not yet flagged to delete";
				http = HttpStatus.BAD_REQUEST;
			}
		}
		catch(Exception e) {
			System.out.println("Exception : "+e);
			resp = "Exception occured";
			http = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<String>(resp, http);
	}

	/* Soft Delete */
	@RequestMapping(value = "/softDelete/{email}", method = RequestMethod.GET)
	public ResponseEntity<String> softDelete(@PathVariable(value = "email") String email) {
		
		String resp = "";
		HttpStatus http ;
		try {
			User user = userRepo.findByEmail(email);
			user.setIsflagged(true);
			
			resp = "1";
			http = HttpStatus.OK;
		}
		catch(Exception e) {
			System.out.println("Exception : "+e);
			resp = "Exception occured";
			http = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<String>(resp, http);
	}
	
	
}
