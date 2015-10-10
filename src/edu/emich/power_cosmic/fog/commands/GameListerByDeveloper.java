package edu.emich.power_cosmic.fog.commands;

import java.util.List;
import java.util.Scanner;

import com.db4o.ObjectContainer;
import com.db4o.query.Predicate;

import edu.emich.power_cosmic.fog.menus.MenuNavigator;
import edu.emich.power_cosmic.fog.schema.Game;

public class GameListerByDeveloper
	extends Command {

	public GameListerByDeveloper() {
		super("findby", "lists all games created by a given developer");
	}

	@Override
	public MenuNavigator doCommand(Scanner keyboard, ObjectContainer db,
			String[] args) {
		System.out.println("Developer:");
		String developerName = keyboard.nextLine();
		List<Game> games = db.query(new Predicate<Game>() {
			private static final long serialVersionUID = -3219661720872675445L;

			@Override
			public boolean match(Game g) {
				return g.getDeveloper().getName().equals(developerName);
			}

		});

		for (Game game : games) {
			System.out.println(game.getTitle() + ": " + game.getDescription());
		}

		return MenuNavigator.CONTINUE;
	}

}
