package edu.emich.power_cosmic.fog.commands;

import java.util.Scanner;

import com.db4o.ObjectContainer;

import edu.emich.power_cosmic.fog.menus.MenuNavigator;

public class Back extends Command {

	public Back() {
		super("back", "Exit current menu");
	}

	@Override
	public MenuNavigator doCommand(Scanner keyboard, ObjectContainer db) {
		return MenuNavigator.BACK;
	}

}
