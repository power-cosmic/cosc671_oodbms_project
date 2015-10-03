package edu.emich.power_cosmic.fog.menus;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.db4o.ObjectContainer;

import edu.emich.power_cosmic.fog.commands.Command;
import edu.emich.power_cosmic.fog.schema.FogUser;

public abstract class Menu {

	private List<Command> commands;
	private FogUser user;
	
	public Menu(FogUser user) {
		this.user = user;
		
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
				System.out.printf("%-12s : %s\n",
						command.getName(),
						command.getDescription());
			}
			return MenuNavigator.CONTINUE;
		}
	}

	public FogUser getUser() {
		return user;
	}

	public void setUser(FogUser user) {
		this.user = user;
	}
	
}
