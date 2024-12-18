package com.yash.jwtdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yash.jwtdemo.model.User;
import com.yash.jwtdemo.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	
	@PostMapping("/register")
	public String register(@RequestBody User user) {
		userService.save(user);
		return "User registered successfully!";
	}
	
	@GetMapping("/all")
	public List<User> getAllUsers() 
	{
		
		return userService.findAllUsers();
	}
	
	@GetMapping("/findByName/{username}")
	public User findByUsername(@PathVariable String username)
	{
		return userService.findByUsername(username);
	}

}
