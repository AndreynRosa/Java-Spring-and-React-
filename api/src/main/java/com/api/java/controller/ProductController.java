package com.api.java.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.api.java.model.entity.Product;
import com.api.java.service.PrudctService;
	
@RestController
@RequestMapping(value = "/v1/product")
public class ProductController {
	
	@Autowired
	private PrudctService service;
	
	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
	public ModelAndView save(@RequestBody Product productRequest, @RequestParam Integer userId) {
		ModelAndView modelAndView = new ModelAndView(new MappingJackson2JsonView());

		try {

			Product product = service.save(productRequest, userId);
			if (null != product) {
				modelAndView.addObject("data", product);
				modelAndView.addObject("error", null);
				modelAndView.setStatus(HttpStatus.CREATED);
			} else {
				modelAndView.addObject("data", null);
				modelAndView.addObject("error", "Bad Request");
				modelAndView.setStatus(HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			modelAndView.addObject("data", null);
			modelAndView.addObject("error", e.getMessage());
			modelAndView.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return modelAndView;
	}
	
	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.DELETE, produces = "application/json")
	public ModelAndView delete(@RequestParam Integer id, @RequestParam Integer userId) {
		ModelAndView modelAndView = new ModelAndView(new MappingJackson2JsonView());
		try {
			Product product = service.remove(id);
			if (null != product) {
				modelAndView.addObject("data: ",""+ product.getName()+" deleted");
				modelAndView.addObject("error", null);
				modelAndView.setStatus(HttpStatus.CREATED);
			} else {
				modelAndView.addObject("data", null);
				modelAndView.addObject("error", "User not exist");
				modelAndView.setStatus(HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			modelAndView.addObject("data", null);
			modelAndView.addObject("error", e.getMessage());
			modelAndView.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return modelAndView;

	}
	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
	public ModelAndView list( @RequestParam Integer userId) {
		ModelAndView modelAndView = new ModelAndView(new MappingJackson2JsonView());
		try {
			List<Product> products = service.list(userId);
			if (products.size() >= 0) {
				modelAndView.addObject("data: ",products);
				modelAndView.addObject("error", null);
				modelAndView.setStatus(HttpStatus.CREATED);
			} else {
				modelAndView.addObject("data", null);
				modelAndView.addObject("error", "User not exist");
				modelAndView.setStatus(HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			modelAndView.addObject("data", null);
			modelAndView.addObject("error", e.getMessage());
			modelAndView.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return modelAndView;

	}


}


