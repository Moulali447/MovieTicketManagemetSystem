package com.project.demo.service;

import java.util.List;

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
	public College update(Orgnaizationdto orgnaizationdto) {
		{
			
	        Optional<College> collegedb=this.collegeRepository.findById(orgnaizationdto.getId());
		

			
			if(collegedb.isPresent()) {
				College c=collegedb.get();
				c.setName(orgnaizationdto.getCollege_name());
				c.setCode(orgnaizationdto.getCollege_code());
				
		
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
	 