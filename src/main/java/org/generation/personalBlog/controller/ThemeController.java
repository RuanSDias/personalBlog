package org.generation.personalBlog.controller;

import java.util.List;

import org.generation.personalBlog.model.ThemeModel;
import org.generation.personalBlog.repository.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/theme")
public class ThemeController {
	
	@Autowired
	private ThemeRepository repository;
	
	@GetMapping
	public ResponseEntity<List<ThemeModel>> getAll() {
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ThemeModel> getById(@PathVariable long id) {
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/name/{name}")
	public ResponseEntity<List<ThemeModel>> getByName(@PathVariable String name) {
		return ResponseEntity.ok(repository.findAllByDescriptionContainingIgnoreCase(name));
	}
	
	@PostMapping
	public ResponseEntity<ThemeModel> post(@RequestBody ThemeModel theme) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(theme));
	}
	
	@PutMapping
	public ResponseEntity<ThemeModel> put(@RequestBody ThemeModel theme) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(theme));
	}
	
	@DeleteMapping ("/{id}")
	public void delete (@PathVariable long id) {
		repository.deleteById(id);
	}

}