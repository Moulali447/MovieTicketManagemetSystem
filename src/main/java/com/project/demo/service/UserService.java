package com.project.demo.service;
import java.util.List;

import com.project.demo.entities.User;
import com.project.demo.entities.UserDto;

public interface UserService {

	public List<User> getAll();
	
	   public User saveUser(UserDto userdto)throws Exception;
		
		public User update(User user);
		
		 public void deleteById(int id);
	
}