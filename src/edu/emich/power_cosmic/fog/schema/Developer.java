package edu.emich.power_cosmic.fog.schema;

public class Developer extends FogUser {
	
	private String name;
	
	public Developer(String username, String password) {
		super(username, password);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
