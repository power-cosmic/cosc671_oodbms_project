package edu.emich.power_cosmic.fog.application;

import java.util.Scanner;

import com.db4o.ObjectContainer;

import edu.emich.power_cosmic.fog.schema.Game;

public class GameAdder implements FogQuery {

	@Override
	public void runQuery(Scanner keyboard, ObjectContainer db) {
		
		System.out.print("Enter title: ");
		String title = keyboard.nextLine();
		System.out.print("Enter description: ");
		String description = keyboard.nextLine();
		
		Game game = new Game();
		game.setTitle(title);
		game.setDescription(description);
		
		db.store(game);
	}

	@Override
	public String getName() {
		return "Add games";
	}

}
