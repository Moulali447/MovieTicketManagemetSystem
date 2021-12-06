package com.project.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.project.demo.entities.Authority;
import com.project.demo.entities.MailData;
import com.project.demo.entities.RandomPassWordGenerator;
import com.project.demo.entities.User;
import com.project.demo.customExceptions.CustomException;
import com.project.demo.customExceptions.ResourceNotFoundException;
import com.project.demo.dto.UserDto;
import com.project.demo.repositories.AuthorityRepository;
import com.project.demo.repositories.UserRepository;

@Service
@ControllerAdvice
public class UserDetailsServiceImpl implements UserDetailsService,UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	 private  RandomPassWordGenerator passwordGenerator;
	
	@Autowired
	private MailService  mailservice;
	
	@Autowired
	private AuthorityRepository authorityRepository;
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);

		if (user != null) {
			return user;
		}

		throw new UsernameNotFoundException(username);
	}
	
	@Transactional(readOnly = true)
	 public List<User> getAll() {
		 return userRepository.findAll();
	 }
	
	@Override
	@Transactional
	public User saveUser(UserDto userdto) throws Exception {
		
		User user=new User();
		user.setFirstName(userdto.getFirstName());
	      user.setLastName(userdto.getLastName());
	      user.setEmail(userdto.getEmail());
	      user.setUsername(userdto.getUsername());
	      PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 
	      String password= passwordGenerator.generateRandomPassword(8);
	      String encodedPassword = passwordEncoder.encode(password);
	      System.out.println(password);
	      user.setPassword(encodedPassword);
	     
	      List<Authority> addAuthorities=authorityRepository.find(userdto.getRole());
          user.setAuthorities(addAuthorities); 
        
	MailData mail = new MailData();
	 mail.setSubject("Welcome to Movie Ticket Management System ");
	 mail.setFromEmail("kolnatimoulali@gmail.com");
    mail.setToEmail(user.getEmail());
  mail.setContent(" User name:"+user.getUsername() +"\n"+ "password :"+password);
  mailservice.sendEmail(mail);
 return userRepository.save(user);
 
	}
	
	@Override
	@Transactional
	public User getById(int id) throws CustomException {
		Optional<User> user=this.userRepository.findById(id);
		if(user.isPresent()) {
			return user.get();
		}
		
		else {
			throw  new CustomException("Record not found with id  :" +id);
		}
		
	}
	
	@Override
	@Transactional
	public User update(UserDto user) throws CustomException {
		
Optional<User> users=this.userRepository.findById(user.getId());
		
		if(users.isPresent()) {
			User u=users.get();
			u.setId(user.getId());
			u.setUsername(user.getUsername());
			u.setFirstName(user.getFirstName());
			u.setLastName(user.getLastName());
			u.setEmail(user.getEmail());
		    u.setPassword(new BCryptPasswordEncoder(8).encode(user.getPassword()));
		    userRepository.save(u);
		    return u;
		}
		else {
			throw new CustomException("Record not found with id" + user.getId());
		}
		
	}
	
	//deleteUserById
	@Override
	@Transactional
		public ResponseEntity<Map<String,Boolean>> deleteById(int id) throws ResourceNotFoundException{
			User u=userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User not exit with this id"+id));
			userRepository.delete(u);
			Map<String,Boolean> response=new HashMap<>();
			response.put("Deleted", Boolean.TRUE);
			return ResponseEntity.ok(response);
		}
	
	
}