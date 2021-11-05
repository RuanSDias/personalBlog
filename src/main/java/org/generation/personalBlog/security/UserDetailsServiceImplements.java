package org.generation.personalBlog.security;

import org.generation.personalBlog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImplements implements UserDetailsService {
	
	
	private @Autowired UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByEmail(username).map(resp -> {
			return new UserDetailsImplements(resp);
		}).orElseThrow(() -> {
			throw new UsernameNotFoundException(username + "not found.");
		});
	}
	

}
