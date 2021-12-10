package com.project.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.demo.dto.TheatreDto;
import com.project.demo.entities.Movies;
import com.project.demo.entities.Theatres;
import com.project.demo.entities.User;
import com.project.demo.repositories.MovieRepository;
import com.project.demo.repositories.TheatreRepository;
import com.project.demo.repositories.UserRepository;

@Service
public class TheatreServiceImpl implements TheatreService {
	@Autowired
	private TheatreRepository theatreRepo;
	
	@Autowired
	private MovieRepository movieRepo;
	
	   @Autowired
	   private UserRepository repository;
	
	  
	
	@Override
	@Transactional
	public Theatres create(TheatreDto dto) throws Exception {

		Theatres th=new Theatres();
			th.setTheatreName(dto.getTheatreName());
			th.setPlace(dto.getPlace());
			
			Movies m=movieRepo.findByTheatreName(dto.getTheatreName());
        	th.setMovies(m);
			
			User user=this.repository.findByUsername(dto.getUsername());
			th.setUser(user);
			
		
  
		return theatreRepo.save(th);
	}
	
	@Override
	@Transactional
	public Theatres update(TheatreDto dto) {
		{
			
	        Optional<Theatres> collegedb=this.theatreRepo.findById(dto.getId());
		

			
			if(collegedb.isPresent()) {
				Theatres c=collegedb.get();
				c.setName(dto.getCollege_name());
				c.setCode(dto.getCollege_code());
				
		
				 repository.save(c);
		          return c;
			}
			else {
				throw new CustomExceptions("Record not found with id" + orgnaizationdto.getId());
			}
	}}

	@Override
	@Transactional
	public void deleteById(Long id) {
		Optional<College> college= this.collegeRepository.findById(id);
        if(college.isPresent()) {
			
        	this.collegeRepository.deleteById(id);
		}
		else {
			throw new CustomExceptions("Record not found with id  :" +id);
		}
		
	}

	@Override
	public College findCollegeById(Long id) {

		Optional<College> collegedb=this.collegeRepository.findById(id);
		if(collegedb.isPresent()) {
			return collegedb.get();
		}
		
		else {
			throw  new CustomExceptions("Record not found with id  :" +id);
		}
	}
	

	@Override
		@Transactional
		public List<College> findColleges() {
			
			return this.collegeRepository.findAll();
		}

		}
	 