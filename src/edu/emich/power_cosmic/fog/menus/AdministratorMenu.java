package edu.emich.power_cosmic.fog.menus;

import edu.emich.power_cosmic.fog.commands.GameLister;
import edu.emich.power_cosmic.fog.commands.UserLister;
import edu.emich.power_cosmic.fog.commands.UserLogout;
import edu.emich.power_cosmic.fog.schema.FogUser;

public class AdministratorMenu extends Menu {

	public AdministratorMenu(FogUser user) {
		super(user);
		
		addCommand(new GameLister());
		addCommand(new UserLister());
		addCommand(new UserLogout());
	}

}
