package edu.emich.power_cosmic.fog.application;

import java.util.List;
import java.util.Scanner;

import com.db4o.ObjectContainer;
import com.db4o.query.Predicate;

import edu.emich.power_cosmic.fog.schema.Game;

public class GameLister implements FogQuery {

	@Override
	public String getName() {
		return "List games";
	}

	@Override
	public void runQuery(Scanner keyboard, ObjectContainer db) {
		List<Game> games = db.query(new Predicate<Game>() {
			private static final long serialVersionUID = -6797553863285149881L;

			@Override
			public boolean match(Game g) {
				return true;
			}
			
		});
		
		for (Game game: games) {
			System.out.println(game.getTitle());
		}
		
	}

}
