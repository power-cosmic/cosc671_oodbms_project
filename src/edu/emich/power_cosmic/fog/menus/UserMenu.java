package edu.emich.power_cosmic.fog.menus;

import edu.emich.power_cosmic.fog.commands.GameLister;
import edu.emich.power_cosmic.fog.commands.UserLogout;
import edu.emich.power_cosmic.fog.schema.FogUser;

public class UserMenu extends Menu {
	
	public UserMenu(FogUser user) {
		super(user);
		
		addCommand(new GameLister());
		addCommand(new UserLogout());
	}
}
