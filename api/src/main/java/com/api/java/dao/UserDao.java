package com.api.java.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.java.model.entity.User;



public interface UserDao extends JpaRepository<User, Integer> {
	
	@Query(value = "select * from users u where u.email like ?",  nativeQuery = true)
	User findByIdAndEmail(String email);

}
