package com.project.demo.service;

import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import com.project.demo.customExceptions.CustomException;
import com.project.demo.customExceptions.ResourceNotFoundException;
import com.project.demo.dto.TheatreDto;
import com.project.demo.entities.Theatres;

public interface TheatreService {
	public List<Theatres> getAll();
	
	   public Theatres saveUser(TheatreDto userdto)throws Exception;
		
		public Theatres update(TheatreDto user) throws CustomException;
		
		 public ResponseEntity<Map<String, Boolean>> deleteById(int id) throws ResourceNotFoundException;

		public Theatres getById(int id) throws CustomException;




}
