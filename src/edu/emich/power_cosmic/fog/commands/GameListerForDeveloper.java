package edu.emich.power_cosmic.fog.commands;

import java.util.List;
import java.util.Scanner;

import com.db4o.ObjectContainer;
import com.db4o.query.Predicate;

import edu.emich.power_cosmic.fog.menus.MenuNavigator;
import edu.emich.power_cosmic.fog.schema.Developer;
import edu.emich.power_cosmic.fog.schema.Game;

public class GameListerForDeveloper
	extends Command {
	private final Developer developer;

	public GameListerForDeveloper(Developer developer) {
		super("mygames", "List all games you've created");
		this.developer = developer;
	}

	@Override
	public MenuNavigator doCommand(Scanner keyboard, ObjectContainer db,
			String[] args) {

		List<Game> games = db.query(new Predicate<Game>() {
			private static final long serialVersionUID = 1481927967081044818L;

			@Override
			public boolean match(Game g) {
				return g.getDeveloper().equals(
					GameListerForDeveloper.this.developer);
			}

		});

		for (Game game : games) {
			System.out.println(game.getTitle() + ": " + game.getDescription());
		}

		return MenuNavigator.CONTINUE;
	}

}
