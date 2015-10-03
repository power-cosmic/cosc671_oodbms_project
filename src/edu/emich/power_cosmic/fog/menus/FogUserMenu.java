package edu.emich.power_cosmic.fog.menus;

import edu.emich.power_cosmic.fog.commands.ForumThreadLister;
import edu.emich.power_cosmic.fog.commands.ForumThreadMaker;
import edu.emich.power_cosmic.fog.commands.ForumThreadReader;
import edu.emich.power_cosmic.fog.commands.GameLister;
import edu.emich.power_cosmic.fog.commands.PostPoster;
import edu.emich.power_cosmic.fog.commands.UserLogout;
import edu.emich.power_cosmic.fog.schema.FogUser;

public class FogUserMenu extends Menu {

	public FogUserMenu(FogUser user) {
		super(user);
		
		addCommand(new GameLister());
		addCommand(new ForumThreadLister());
		addCommand(new ForumThreadReader());
		addCommand(new ForumThreadMaker(user));
		addCommand(new PostPoster(user));
		addCommand(new UserLogout());
	}

}
