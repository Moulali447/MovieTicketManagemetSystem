package com.project.demo.entities;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Theatres {
	
	    @Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		private String username;

		private String theatreName;
		
		private int place;
		
		@ManyToOne
		@JsonProperty(access = Access.WRITE_ONLY)
		private Movies movies;
		
		@ManyToMany
	    @JsonIgnore
	    private List<User> user;

}
