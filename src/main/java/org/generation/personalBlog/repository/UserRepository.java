package org.generation.personalBlog.repository;

import java.util.Optional;

import org.generation.personalBlog.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
	public Optional<UserModel> findByEmail(String email);

}
