package com.project.demo.service;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.project.demo.entities.User;
import com.project.demo.customExceptions.CustomException;
import com.project.demo.customExceptions.ResourceNotFoundException;
import com.project.demo.dto.UserDto;

public interface UserService {

	public List<User> getAll();
	
	   public User saveUser(UserDto userdto)throws Exception;
		
		public User update(UserDto user) throws CustomException;
		
		 public ResponseEntity<Map<String, Boolean>> deleteById(int id) throws ResourceNotFoundException;

		public User getById(int id) throws CustomException;

	
}