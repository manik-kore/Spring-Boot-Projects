package com.yash.jwtdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.yash.jwtdemo.model.User;
import com.yash.jwtdemo.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public List<User> findAllUsers() {
		return userRepository.findAll();
	}

	public User save(User user) {
//		System.out.println("Encoding password for user: " + user.getUsername());
//		user.setPassword(passwordEncoder.encode(user.getPassword()));
//		System.out.println("Encoded password: " + user.getPassword());
		return userRepository.save(user);
	}

	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public boolean checkPassword(String rawPassword, String encodedPassword) {
		boolean matches = passwordEncoder.matches(rawPassword, encodedPassword);
		System.out.println("Password matches: " + matches);
		return matches;
	}
}
