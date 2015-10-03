package edu.emich.power_cosmic.fog.commands;

import java.util.Scanner;

import com.db4o.ObjectContainer;

import edu.emich.power_cosmic.fog.menus.MenuNavigator;

public abstract class Command {
	private String name, description;
	
	public Command(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public abstract MenuNavigator doCommand(Scanner keyboard, 
			ObjectContainer db,
			String[] args);
	
	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
	
}
