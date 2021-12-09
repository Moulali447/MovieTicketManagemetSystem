package com.project.demo.service;

import java.util.List;
import com.project.demo.dto.TheatreDto;
import com.project.demo.entities.Theatres;

public interface TheatreService {
	
	   public Theatres create(TheatreDto dto)  throws Exception;
		
		public Theatres update(TheatreDto dto);
		
			public void deleteById(Long id);
			
			public List<Theatres> findTheatres();
			
			public Theatres findTheatresById(Long id);
			
		
}