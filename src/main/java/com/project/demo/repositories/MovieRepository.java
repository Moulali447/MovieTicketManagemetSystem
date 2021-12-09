package com.project.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.demo.entities.Movies;

@Repository
public interface MovieRepository extends JpaRepository<Movies, Long> {

	public Movies findByTheatreName(String theatreName);

}
