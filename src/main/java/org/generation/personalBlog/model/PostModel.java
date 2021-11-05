package org.generation.personalBlog.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@Entity
@Table(name = "tb_posts")
public class PostModel {
	
	private @Id @GeneratedValue (strategy = GenerationType.IDENTITY) Long id;
	private @NotBlank @Size (min = 1, max = 100) String title;
	private @NotBlank @Size (min = 1, max = 500) String text;
	private @Temporal (TemporalType.TIMESTAMP) Date date = new java.sql.Date(System.currentTimeMillis());
	private @ManyToOne @JsonIgnoreProperties ("post") ThemeModel theme;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public ThemeModel getTheme() {
		return theme;
	}
	public void setTheme(ThemeModel theme) {
		this.theme = theme;
	}
	

}