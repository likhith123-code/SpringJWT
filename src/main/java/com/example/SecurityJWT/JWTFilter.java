package com.example.SecurityJWT;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.SecurityJWT.CustomSecurity.CustomUserDetails;

// making this filter : removing default filter login for every request

// Link: https://www.kindsonthegenius.com/spring-boot/jwtjson-web-token-with-springboot-step-by-step-tutorial-part-2/

@Component
public class JWTFilter extends OncePerRequestFilter {
	
	@Autowired
	JWTUtility jwtUtility;
	
	@Autowired
	CustomUserDetails customUserDetails;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String authorizationHeader = request.getHeader("Authorization");

	    String username = null;
	    String jwtToken = null;

	    if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
	        jwtToken = authorizationHeader.substring(7);
	        username = jwtUtility.getUsernameFromToken(jwtToken);
	    }

	    if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
	        UserDetails userDetails = customUserDetails.loadUserByUsername(username);
	        if(jwtUtility.validateToken(jwtToken, userDetails)){
	            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
	                    userDetails, null, userDetails.getAuthorities()
	            );
	            usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
	        }
	    }
	    filterChain.doFilter(request, response);
		
	}

}
