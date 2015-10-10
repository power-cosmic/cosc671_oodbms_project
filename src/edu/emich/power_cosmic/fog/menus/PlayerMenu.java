package edu.emich.power_cosmic.fog.menus;

import edu.emich.power_cosmic.fog.commands.GameListerByDeveloper;
import edu.emich.power_cosmic.fog.schema.Player;

public class PlayerMenu extends FogUserMenu {
	
	public PlayerMenu(Player user) {
		super(user);
		
		addCommand(new GameListerByDeveloper());
	}
}
