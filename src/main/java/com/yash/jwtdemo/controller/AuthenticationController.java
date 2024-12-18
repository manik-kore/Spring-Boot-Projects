package com.yash.jwtdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yash.jwtdemo.controller.dto.UserLoginRequest;
import com.yash.jwtdemo.model.User;
import com.yash.jwtdemo.service.UserService;
import com.yash.jwtdemo.utils.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	private final AuthenticationManager authenticationManager;
	private final JwtUtil jwtUtil;
	private final UserService userService;
	private final PasswordEncoder passwordEncoder;

	public AuthenticationController(AuthenticationManager authenticationManager, JwtUtil jwtUtil,
			UserService userService, PasswordEncoder passwordEncoder) {
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
	}

	@PostMapping("/login")
	public String login(@RequestBody UserLoginRequest userLoginRequest) {
		try {
			System.out.println("Attempting login for user: " + userLoginRequest.getUsername());

			User user = userService.findByUsername(userLoginRequest.getUsername());
			if (user == null) {
				return "User not found";
			}

			boolean passwordMatches = userService.checkPassword(userLoginRequest.getPassword(), user.getPassword());
			System.out.println("Password matches: " + passwordMatches);
			if (!passwordMatches) {
				return "Invalid username or password";
			}

			// Create the authentication token
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
					userLoginRequest.getUsername(), userLoginRequest.getPassword());

			System.out.println("Auth token created: " + authToken);

			// Authenticate the token
			Authentication authentication = authenticationManager.authenticate(authToken);

			System.out.println("Authentication successful for user: " + userLoginRequest.getUsername());

			// Set the authentication in the security context
			SecurityContextHolder.getContext().setAuthentication(authentication);

			// Generate the JWT token
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			String token = jwtUtil.generateToken(userDetails.getUsername());

			System.out.println("Generated token: " + token);

			return "Bearer " + token;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Authentication failed for user: " + userLoginRequest.getUsername());
			return "Invalid username or password";
		}
	}

	@PostMapping("/register")
	public String register(@RequestBody User user) {
	    System.out.println("Registering user: " + user.getUsername());
	    String encodedPassword = passwordEncoder.encode(user.getPassword());
	    System.out.println("Encoded password: " + encodedPassword);
	    user.setPassword(encodedPassword);
	    userService.save(user);
	    return "User registered successfully!";
	}

}
