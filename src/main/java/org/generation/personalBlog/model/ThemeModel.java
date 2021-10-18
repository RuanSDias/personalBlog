package org.generation.personalBlog.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_theme")
public class ThemeModel {
	
	private @Id @GeneratedValue (strategy = GenerationType.IDENTITY) long id;
	private @NotBlank String description;
	private @OneToMany (mappedBy = "theme", cascade = CascadeType.ALL) @JsonIgnoreProperties ("theme") List<PostModel> post;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<PostModel> getPost() {
		return post;
	}
	public void setPost(List<PostModel> post) {
		this.post = post;
	}

}
