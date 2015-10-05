package edu.emich.power_cosmic.fog.commands;

import java.util.List;
import java.util.Scanner;

import com.db4o.ObjectContainer;

import edu.emich.power_cosmic.fog.application.OutputConstants;
import edu.emich.power_cosmic.fog.menus.MenuNavigator;
import edu.emich.power_cosmic.fog.schema.Administrator;
import edu.emich.power_cosmic.fog.schema.Developer;
import edu.emich.power_cosmic.fog.schema.FogUser;
import edu.emich.power_cosmic.fog.schema.Player;

public class UserLister extends Command {

	public UserLister() {
		super("users", "List users");
	}
	
	public MenuNavigator doCommand(Scanner keyboard, 
			ObjectContainer db,
			String[] args) {

		System.out.println();
		printList("Administrators", db.queryByExample(new Administrator()));
		printList("Developers", db.queryByExample(new Developer()));
		printList("Users", db.queryByExample(new Player()));
		
		return MenuNavigator.CONTINUE;
	}
	
	private void printList(String title, List<FogUser> users) {
		System.out.println(title);
		System.out.println(OutputConstants.SEPARATOR);
		for (FogUser user: users) {
			System.out.println("  " + user.getUsername());
		}
		System.out.println();
	}
	
}
