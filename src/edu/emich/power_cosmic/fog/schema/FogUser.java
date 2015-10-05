package edu.emich.power_cosmic.fog.schema;

import java.util.Date;

public abstract class FogUser {

	private String username;
	private String password;
	private String displayName;
	private String email;
	private Date dateJoined;

	public FogUser(String username, String password) {
		this.username = username;
		this.password = password;
		dateJoined = new Date();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDateJoined() {
		return dateJoined;
	}

	public void setDateJoined(Date dateJoined) {
		this.dateJoined = dateJoined;
	}

}
