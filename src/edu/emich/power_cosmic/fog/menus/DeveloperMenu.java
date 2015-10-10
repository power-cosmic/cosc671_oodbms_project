package edu.emich.power_cosmic.fog.menus;

import edu.emich.power_cosmic.fog.commands.GameAdder;
import edu.emich.power_cosmic.fog.commands.GameListerForDeveloper;
import edu.emich.power_cosmic.fog.schema.Developer;

public class DeveloperMenu extends FogUserMenu {

	public DeveloperMenu(Developer user) {
		super(user);
		
		addCommand(new GameAdder(user));
		addCommand(new GameListerForDeveloper(user));
	}

}
