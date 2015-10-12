package edu.emich.power_cosmic.fog.commands;

import java.util.List;
import java.util.Scanner;

import com.db4o.ObjectContainer;
import com.db4o.query.Predicate;

import edu.emich.power_cosmic.fog.menus.MenuNavigator;
import edu.emich.power_cosmic.fog.schema.ForumThread;

public class PlayerRespondedTo
	extends Command {

	public PlayerRespondedTo() {
		super("whorespond",
				"Lists the players who have responded to a given thread");
	}

	@Override
	public MenuNavigator doCommand(Scanner keyboard, ObjectContainer db,
			String[] args) {

		System.out.println("Thread Title:");
		String title = keyboard.nextLine();

		List<ForumThread> thread = db.query(new Predicate<ForumThread>() {
			private static final long serialVersionUID = -3401153496069213998L;

			@Override
			public boolean match(ForumThread ft) {
				return ft.getTitle().equals(title);
			}

		});

		thread.get(0).getPosts().forEachRemaining(
			(x) -> System.out.println(x.getPoster().getUsername()));

		return MenuNavigator.CONTINUE;
	}

}
