package com.project.demo.entities;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;

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
		
		 @ManyToMany(fetch = FetchType.LAZY)
		    @JoinTable(name = "movie_theatre", 
			joinColumns = @JoinColumn(name = "theatre_id", referencedColumnName = "id"), 
			inverseJoinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"))
		    @OrderBy
		    @JsonIgnore
		    private List<Movies> movie;
		    
		    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
			@JoinColumn
			@JsonIgnore
			private User user;

}
