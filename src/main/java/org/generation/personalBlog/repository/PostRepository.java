package org.generation.personalBlog.repository;

import java.util.List;

import org.generation.personalBlog.model.PostModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository <PostModel, Long> {
	public List <PostModel> findAllByTitleContainingIgnoreCase (String title);

}