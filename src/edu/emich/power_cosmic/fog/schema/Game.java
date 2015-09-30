package edu.emich.power_cosmic.fog.schema;

import java.util.Set;
import java.util.TreeSet;

public class Game {
	
	private Developer developer;
	private String title;
	private String description;
	private Set<String> genres;
	
	public Game() {
		genres = new TreeSet<>();
	}

	public Developer getDeveloper() {
		return developer;
	}

	public void setDeveloper(Developer developer) {
		this.developer = developer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<String> getGenres() {
		return genres;
	}

	public void setGenres(Set<String> genres) {
		this.genres = genres;
	}
	
}
