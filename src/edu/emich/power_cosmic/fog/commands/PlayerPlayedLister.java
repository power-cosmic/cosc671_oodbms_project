package edu.emich.power_cosmic.fog.commands;

import java.util.List;
import java.util.Scanner;

import com.db4o.ObjectContainer;
import com.db4o.query.Predicate;

import edu.emich.power_cosmic.fog.menus.MenuNavigator;
import edu.emich.power_cosmic.fog.schema.PlayHistory;

public class PlayerPlayedLister
	extends Command {

	public PlayerPlayedLister() {
		super("whoplayed", "Lists all users who have played a given game");
	}

	@Override
	public MenuNavigator doCommand(Scanner keyboard, ObjectContainer db,
			String[] args) {

		System.out.println("Game Name:");
		String gameTitle = keyboard.nextLine();

		List<PlayHistory> histories = db.query(new Predicate<PlayHistory>() {
			private static final long serialVersionUID = -5167026258957645104L;

			@Override
			public boolean match(PlayHistory ph) {
				return ph.getGame().getTitle().equals(gameTitle);
			}

		});

		for (PlayHistory playHistory : histories) {
			System.out.println(playHistory.getPlayer().getUsername());
		}

		return MenuNavigator.CONTINUE;
	}

}
