package edu.emich.power_cosmic.fog.commands;

import java.util.Scanner;

import com.db4o.ObjectContainer;

import edu.emich.power_cosmic.fog.menus.MenuNavigator;
import edu.emich.power_cosmic.fog.schema.Developer;
import edu.emich.power_cosmic.fog.schema.Game;

public class GameAdder extends Command {

	private Developer developer;
	
	public GameAdder(Developer developer) {
		super("add game", "Add a game");
		this.developer = developer;
	}
	
	@Override
	public MenuNavigator doCommand(Scanner keyboard, ObjectContainer db) {
		
		System.out.print("Enter title: ");
		String title = keyboard.nextLine();
		System.out.print("Enter description: ");
		String description = keyboard.nextLine();
		
		Game game = new Game();
		game.setTitle(title);
		game.setDescription(description);
		
		db.store(game);
		
		return MenuNavigator.CONTINUE;
	}
}
