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
		printList("Administrators", db.queryByExample(Administrator.class));
		printList("Developers", db.queryByExample(Developer.class));
		printList("Users", db.queryByExample(Player.class));
		
		return MenuNavigator.CONTINUE;
	}
	
	private void printList(String title, List<FogUser> users) {
		OutputConstants.printTitle(title);
		for (FogUser user: users) {
			System.out.println("  " + user.getUsername());
		}
		System.out.println();
	}
	
}
