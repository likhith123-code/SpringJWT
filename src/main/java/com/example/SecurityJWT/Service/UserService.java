package com.example.SecurityJWT.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.SecurityJWT.Model.JWTLogin;
import com.example.SecurityJWT.Model.User;
import com.example.SecurityJWT.Repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public ResponseEntity<?> createUser(User user){
		User saved = userRepository.save(user);
		return new ResponseEntity<User>(saved,HttpStatus.OK);
	}
	
	public ResponseEntity<String> login(JWTLogin jwtLogin){
		
		return new ResponseEntity<String>("Welcome User",HttpStatus.OK);
	}
	
	

}
