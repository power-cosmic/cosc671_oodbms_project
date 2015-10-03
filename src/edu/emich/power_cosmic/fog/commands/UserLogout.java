package edu.emich.power_cosmic.fog.commands;

import java.util.Scanner;

import com.db4o.ObjectContainer;

import edu.emich.power_cosmic.fog.menus.MenuNavigator;

public class UserLogout extends Command {

	public UserLogout() {
		super("logout", "Log out current user");
	}

	@Override
	public MenuNavigator doCommand(Scanner keyboard, ObjectContainer db) {
		return MenuNavigator.LOGOUT;
	}

}
