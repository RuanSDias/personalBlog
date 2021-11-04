package org.generation.personalBlog.controller;

import java.util.List;
import java.util.Optional;

import org.generation.personalBlog.model.UserLogin;
import org.generation.personalBlog.model.UserModel;
import org.generation.personalBlog.repository.UserRepository;
import org.generation.personalBlog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/user")
@CrossOrigin (origins = "*", allowedHeaders = "*")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/login")
	public ResponseEntity<UserLogin> Autentication (@RequestBody Optional<UserLogin> user) {
		return userService.Login(user).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
	
	@PostMapping("/register")
	public ResponseEntity<UserModel> Post(@RequestBody UserModel user) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(userService.RegisterUser(user));
	}
	
	@GetMapping
	public ResponseEntity<List<UserModel>> GetAllUsers () {
		return ResponseEntity.ok(userRepository.findAll());
	}
	
	

}