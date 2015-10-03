package edu.emich.power_cosmic.fog.menus;

import java.util.Scanner;

import com.db4o.ObjectContainer;

import edu.emich.power_cosmic.fog.commands.Command;
import edu.emich.power_cosmic.fog.commands.GameLister;
import edu.emich.power_cosmic.fog.commands.UserLogout;
import edu.emich.power_cosmic.fog.schema.FogUser;

public class FogUserMenu extends Menu {

	public FogUserMenu(FogUser user) {
		super(user);
		
		addCommand(new GameLister());
		addCommand(new ForumMenuGoer(user));
		addCommand(new UserLogout());
	}
	
	private class ForumMenuGoer extends Command {

		private FogUser user;
		
		public ForumMenuGoer(FogUser user) {
			super("forum", "Go to forum menu");
			this.user = user;
		}

		@Override
		public MenuNavigator doCommand(Scanner keyboard, 
				ObjectContainer db,
				String[] args) {
			
			return new MenuNavigator(new ForumMenu(user));
		}
		
	}

}
