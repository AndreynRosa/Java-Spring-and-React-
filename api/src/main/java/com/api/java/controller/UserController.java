package com.api.java.controller;

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

import com.api.java.model.dto.AuthenticatedUserDto;
import com.api.java.model.dto.UserDto;
import com.api.java.model.entity.User;
import com.api.java.service.UserService;

@RestController
@RequestMapping(value = "/v1/users")
public class UserController {

	@Autowired
	private UserService service;

	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
	public ModelAndView save(@RequestBody User userRequest) {
		ModelAndView modelAndView = new ModelAndView(new MappingJackson2JsonView());

		try {

			User user = service.save(userRequest);
			if (null != user) {
				modelAndView.addObject("data", user);
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
	public ModelAndView delete(@RequestParam Integer id) {
		ModelAndView modelAndView = new ModelAndView(new MappingJackson2JsonView());
		try {
			User user = service.remove(id);
			if (null != user) {
				modelAndView.addObject("data", user);
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
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
	public ModelAndView login(@RequestBody UserDto userRequest) {
		ModelAndView modelAndView = new ModelAndView(new MappingJackson2JsonView());

		try {
			 AuthenticatedUserDto user = service.login(userRequest);
			 if (null != user) {
					modelAndView.addObject("data", user);
					modelAndView.addObject("error", null);
					modelAndView.setStatus(HttpStatus.CREATED);
				} else {
					modelAndView.addObject("data", null);
					modelAndView.addObject("error", "User not exist");
					modelAndView.setStatus(HttpStatus.BAD_REQUEST);
				}
		}catch (Exception e) {
			modelAndView.addObject("data", null);
			modelAndView.addObject("error", e.getMessage());
			modelAndView.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return modelAndView;
	}

}
