package edu.emich.power_cosmic.fog.commands;

import java.util.Iterator;
import java.util.Scanner;

import com.db4o.ObjectContainer;

import edu.emich.power_cosmic.fog.menus.MenuNavigator;
import edu.emich.power_cosmic.fog.schema.Player;
import edu.emich.power_cosmic.fog.schema.TrophyCard;

public class CardLister
	extends Command {

	private final Player player;

	public CardLister(Player player) {
		super("listcards", "Lists all of the currently unlocked user cards");
		this.player = player;
	}

	@Override
	public MenuNavigator doCommand(Scanner keyboard, ObjectContainer db,
			String[] args) {
		// TODO Auto-generated method stub

		for (Iterator<TrophyCard> iterator =
				this.player.getTrophyCards(); iterator.hasNext();) {
			TrophyCard item = iterator.next();
			System.out.println(
				item.getTitle() + " : from: " + item.getGameFrom().getTitle());
		}

		return MenuNavigator.CONTINUE;
	}

}
