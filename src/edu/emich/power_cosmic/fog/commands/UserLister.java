package edu.emich.power_cosmic.fog.commands;

import java.util.List;
import java.util.Scanner;

import com.db4o.ObjectContainer;
import com.db4o.query.Predicate;

import edu.emich.power_cosmic.fog.menus.MenuNavigator;
import edu.emich.power_cosmic.fog.schema.FogUser;

public class UserLister extends Command {

	public UserLister() {
		super("users", "List users");
	}
	
	public MenuNavigator doCommand(Scanner keyboard, ObjectContainer db) {
		List<FogUser> users = db.query(new Predicate<FogUser>() {
			private static final long serialVersionUID = -8095306821698079482L;

			@Override
			public boolean match(FogUser arg0) {
				return true;
			}
		});
		
		for (FogUser user: users) {
			System.out.println(user.getUsername() + ": "
					+ user.getPassword());
		}
		return MenuNavigator.CONTINUE;
	}
	
}
