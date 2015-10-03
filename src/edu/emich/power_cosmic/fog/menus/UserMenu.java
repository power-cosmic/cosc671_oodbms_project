package edu.emich.power_cosmic.fog.menus;

import edu.emich.power_cosmic.fog.commands.GameLister;

public class UserMenu extends Menu {
	
	public UserMenu() {
		super();
		
		addCommand(new GameLister());
	}
}
