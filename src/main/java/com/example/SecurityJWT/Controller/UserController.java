package com.example.SecurityJWT.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.example.SecurityJWT.JWTUtility;
import com.example.SecurityJWT.CustomSecurity.CustomUserDetails;
import com.example.SecurityJWT.Model.JWTLogin;
import com.example.SecurityJWT.Model.User;
import com.example.SecurityJWT.Service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	CustomUserDetails customUserDetails;
	
	@Autowired
	JWTUtility jwtUtility;
	
	@PostMapping("/signup")
	public ResponseEntity<?> signUp(@RequestBody User user){
		return userService.createUser(user);
	}
	
	@PostMapping("/userlogin")
	public ResponseEntity<?> login(@RequestBody JWTLogin jwtlogin) throws Exception{
		
		// here we are authenticating using default User Name Password Token 
		// It uses UserDetails service Load By UserName method and validates our input with it
		
		try {
			authenticationManager.
			authenticate
			(new UsernamePasswordAuthenticationToken(jwtlogin.getUsername(), jwtlogin.getPassword()));
		}
		catch (Exception e) {
			String string = e.getMessage();
			return new ResponseEntity<String>(string,HttpStatus.BAD_REQUEST);
		}
		
		UserDetails userDetails = customUserDetails.loadUserByUsername(jwtlogin.getUsername());

		String token = jwtUtility.generateToken(userDetails);

		return new ResponseEntity<String>(token,HttpStatus.OK);
		
	}
	

}
