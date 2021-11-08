package org.generation.personalBlog.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.generation.personalBlog.model.UserLogin;
import org.generation.personalBlog.model.UserModel;
import org.generation.personalBlog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public UserModel RegisterUser (UserModel user) {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String passwordEncoder = encoder.encode(user.getPassword());
			user.setPassword(passwordEncoder);
			
			return repository.save(user);
	}
	

	
	public Optional<UserLogin> Login(Optional<UserLogin> user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<UserModel> userL = repository.findByUser(user.get().getUser());
		if(userL.isPresent()) {
			if(encoder.matches(user.get().getPassword(), userL.get().getPassword())) {
				
				String auth = user.get().getUser() + ":" + user.get().getPassword();
				byte [] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String (encodedAuth);
				
				user.get().setToken(authHeader);
				user.get().setName(userL.get().getName());
				user.get().setPassword(userL.get().getPassword());
				user.get().setUser(userL.get().getEmail());
				user.get().setIdUser(userL.get().getId());
				user.get().setPicture(userL.get().getPicture());
				user.get().setType(userL.get().getType());
				
				return user;
			}
		}
		
		return Optional.empty();
	}

}