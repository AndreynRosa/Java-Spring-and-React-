package com.api.java.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.java.model.entity.Product;

public interface ProductDao extends JpaRepository<Product, Integer> {

	@Query(value = "SELECT * From products p where p.user_id = ?",  nativeQuery = true)
	List<Product> findAllByUserId(Integer userId);

}
