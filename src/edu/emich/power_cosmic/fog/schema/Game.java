package edu.emich.power_cosmic.fog.schema;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class Game implements Comparable<Game> {
	
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

	public Iterator<String> getGenres() {
		return genres.iterator();
	}

	public void setGenres(Set<String> genres) {
		this.genres = genres;
	}

	@Override
	public int compareTo(Game o) {
		if (o == null) {
			return 1;
		}
		return this.title.compareTo(o.title);
	}
	
}
