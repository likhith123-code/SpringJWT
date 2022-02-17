package com.example.SecurityJWT.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.SecurityJWT.Model.User;

@Repository
public interface UserRepository extends JpaRepository<User,String>{
	
	@Query(value = "select * from users where username = ?1",nativeQuery =  true)
	User findByUsername(String username);
}
