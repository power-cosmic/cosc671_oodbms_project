package edu.emich.power_cosmic.fog.commands;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import com.db4o.ObjectContainer;

import edu.emich.power_cosmic.fog.menus.MenuNavigator;
import edu.emich.power_cosmic.fog.schema.Developer;
import edu.emich.power_cosmic.fog.schema.Game;

public class GameAdder extends Command {

	private Developer developer;
	
	public GameAdder(Developer developer) {
		super("addgame", "Add a game");
		this.developer = developer;
	}
	
	@Override
	public MenuNavigator doCommand(Scanner keyboard, 
			ObjectContainer db,
			String[] args) {
		
		System.out.print("Enter title: ");
		String title = keyboard.nextLine();
		System.out.print("Enter description: ");
		String description = keyboard.nextLine();
		System.out.print("Enter genres (separated by commas): ");
		String genreLine = keyboard.nextLine().trim();
		String[] genres = new String[0];
		if (genreLine.length() > 0) {
			genres = genreLine.split("\\s+,\\s+");
		}
		Set<String> genreSet = new TreeSet<>();
		for (int i = 0; i < genres.length; i++) {
			genreSet.add(genres[i]);
		}
		
		
		Game game = new Game();
		game.setTitle(title);
		game.setDescription(description);
		game.setDeveloper(developer);
		game.setGenres(genreSet);
		
		db.store(game);
		
		return MenuNavigator.CONTINUE;
	}
}
