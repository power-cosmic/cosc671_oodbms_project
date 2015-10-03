package edu.emich.power_cosmic.fog.menus;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.db4o.ObjectContainer;

import edu.emich.power_cosmic.fog.commands.Command;

public abstract class Menu {

	private List<Command> commands;
	
	public Menu() {
		commands = new ArrayList<>();
		
		addCommand(new Help());
		addCommand(new Exit());
	}
	
	public boolean addCommand(Command command) {
		return commands.add(command);
	}
	
	public MenuNavigator doCommand(String commandName, Scanner keyboard, ObjectContainer db) {
		for (Command command: commands) {
			if (command.getName().equalsIgnoreCase(commandName)) {
				return command.doCommand(keyboard, db);
			}
		}
		
		// if we got here the user didn't enter a real command
		System.out.println("Invalid command; type 'help' for help");
		return MenuNavigator.CONTINUE;
	}
	
	private class Exit extends Command {
		
		private Exit() {
			super("exit", "Exit the program");
		}
		
		@Override
		public MenuNavigator doCommand(Scanner keyboard, ObjectContainer db) {
			return MenuNavigator.EXIT;
		}	
	}
	
	private class Help extends Command {
		
		private Help() {
			super("help", "Display a list of commands");
		}

		@Override
		public MenuNavigator doCommand(Scanner keyboard, ObjectContainer db) {
			
			for (Command command: commands) {
				System.out.println(command.getName() + ": " 
						+ command.getDescription());
			}
			return MenuNavigator.CONTINUE;
		}
	}
	
}
