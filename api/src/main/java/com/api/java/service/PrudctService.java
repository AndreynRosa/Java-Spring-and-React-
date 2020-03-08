package com.api.java.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.java.dao.ProductDao;
import com.api.java.model.entity.Product;
import com.api.java.model.entity.User;

@Service
public class PrudctService {

	@Autowired
	private ProductDao dao;

	@Autowired
	private UserService userService;

	public Product save(Product productRequest, Integer userId) {
		if (assertsBeforeSave(productRequest, userId)) {
			productRequest.setUser(userService.findById(userId));
			if (isUpdateRecord(productRequest)) {
				Product product = findById(productRequest.getId());
				productRequest.setId(product.getId());
				Product resp = dao.save(productRequest);
				return userService.handlingPrductUser(resp);
			} else {
				Product resp = dao.save(productRequest);
				return userService.handlingPrductUser(resp);
			}
		}
		return null;
	}

	private boolean isUpdateRecord(Product productRequest) {
		return null != productRequest.getId();
	}

	private boolean assertsBeforeSave(Product productRequest, Integer userId) {
		return userService.exisits(userId) && productValuesAsserts(productRequest);
	}
	
	private boolean productValuesAsserts(Product productRequest) {

		return (productRequest.getPrice().compareTo(productRequest.getCoast()) > 0);
	}

	@Transactional
	private Product handlingPrductUser(Product product) {
		product.getUser().setProducts(null);
		return product;
	}

	private Product findById(Integer userId) {
		Optional<Product> opt = dao.findById(userId);
		return opt.orElse(null);
	}



	public Product remove(Integer id) {
		Product product = findById(id);
		if (null != product) {
			dao.delete(product);
			return product;
		}
		return null;
	}

	public List<Product> list(Integer userId) {
		User user = userService.findById(userId);
		if (null != user) {
			List<Product> products = dao.findAllByUserId(userId);

			return handlingUserData(products);
		}
		return null;
	}

	private List<Product> handlingUserData(List<Product> products) {
		List<Product> producstReps = new ArrayList<Product>();
		for (Product product : products) {

			producstReps.add(userService.handlingPrductUser(product));
		}
		return producstReps;
	}

}
