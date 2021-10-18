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
			if(encoder.matches(user.get().getPassword(), user.get().getPassword())) {
				
				String auth = user.get().getUser() + ":" + user.get().getPassword();
				byte [] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String (encodedAuth);
				
				user.get().setToken(authHeader);
				user.get().setName(user.get().getName());
				
				return user;
			}
		}
		
		return null;
	}

}
