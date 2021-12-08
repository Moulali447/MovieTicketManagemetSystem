package com.project.demo.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TheatreDto {
	
	private Long id;
	
	private String username;
	
	private String theatreName;
	
	private int place;
	
	private List<String> role;

}
