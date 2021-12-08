package com.project.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.demo.entities.Theatres;

@Repository
public interface TheatreRepository extends JpaRepository<Theatres, Long>{

}
