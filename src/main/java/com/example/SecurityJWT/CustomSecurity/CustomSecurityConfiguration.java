package com.example.SecurityJWT.CustomSecurity;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.SecurityJWT.JWTFilter;

/*
 	1. We need to extend the Web Security Configure Adapter to customize
 	
 	2. HTTP Security Configure is used to customize the Authorization.
 	
 	3. Commence method is overridden to remove the alert dialogue login form
 	
 */
@EnableWebSecurity
public class CustomSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired 
	CustomUserDetails customUserDetails;
	
	@Autowired
	JWTFilter jwtFilter;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// Overriding User Details Service
		
		// default spring has this, but we override here.
		
		auth.userDetailsService(customUserDetails)
		.passwordEncoder(NoOpPasswordEncoder.getInstance());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		
		http.httpBasic()
		.authenticationEntryPoint(new AuthenticationEntryPoint() {
			
			// here we have one header to get alert box, we will remove that header
			
			@Override
			public void commence(HttpServletRequest request, HttpServletResponse response,
					AuthenticationException authException) throws IOException, ServletException {
				
				response.sendError(HttpStatus.UNAUTHORIZED.value(),HttpStatus.UNAUTHORIZED.getReasonPhrase());
			}
		});
		
		http
		.authorizeRequests().antMatchers("/items/{id}").authenticated()
		.and()
		.authorizeRequests().antMatchers("/additem").authenticated()
		.and()
		.authorizeRequests().anyRequest().permitAll();
		
		
		// Removing storing of Session token in cookie. For each request we need to pass token
		
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		// Adding new filter
		
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
	    return super.authenticationManagerBean();
	}

}
