package com.project.demo.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.demo.customExceptions.CustomException;
import com.project.demo.customExceptions.ResourceNotFoundException;
import com.project.demo.dto.UserDto;
import com.project.demo.entities.User;
import com.project.demo.service.UserDetailsServiceImpl;

 
@RestController
@RequestMapping("/api")
public class TheatreController {

	@Autowired
	 private UserDetailsServiceImpl detailsServiceImpl; 
		
		public TheatreController(UserDetailsServiceImpl detailsServiceImpl) {
			this.detailsServiceImpl = detailsServiceImpl;
		} 
		
	@GetMapping(value="/get")
    public List<User> getAll() {
        return detailsServiceImpl.getAll();
    }
	
	@GetMapping(value="get/{id}")
	public ResponseEntity<User> getUserById(@PathVariable int id) throws CustomException {
		return ResponseEntity.ok().body(detailsServiceImpl.getById(id));
	}
	
	@PostMapping("/add")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<User> create(@Valid @RequestBody UserDto user) throws Exception {
		
		User newUser=detailsServiceImpl.saveUser(user);
		return new ResponseEntity<>( newUser,HttpStatus.CREATED);
        
    }
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteById(@PathVariable int id) throws ResourceNotFoundException{
		return this.detailsServiceImpl.deleteById(id);
		 
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<User> update(@RequestBody UserDto users,@PathVariable int id) throws CustomException {
		users.setId(id);
		
		return ResponseEntity.ok().body(detailsServiceImpl.update(users));
		}
}
