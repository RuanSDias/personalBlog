package org.generation.personalBlog.repository;

import java.util.List;

import org.generation.personalBlog.model.ThemeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThemeRepository extends JpaRepository<ThemeModel, Long> {
	public List<ThemeModel> findAllByDescriptionContainingIgnoreCase(String description);

}