package edu.emich.power_cosmic.fog.schema;

import java.util.ArrayList;
import java.util.List;

public class PlayHistory {

	private List<TrophyCard> cardsEarned;
	private Game game;
	private double hoursPlayed;
	private Player player;

	public PlayHistory() {
		this.hoursPlayed = 0;
		this.cardsEarned = new ArrayList<>();
	}

	public void addCardsEarned(TrophyCard cardEarned) {
		this.cardsEarned.add(cardEarned);
	}

	public void addTime(double moreHoursPlayed) {
		this.hoursPlayed += moreHoursPlayed;
	}

	public List<TrophyCard> getCardsEarned() {
		return this.cardsEarned;
	}

	public Game getGame() {
		return this.game;
	}

	public double getHoursLogged() {
		return this.hoursPlayed;
	}

	public Player getPlayer() {
		return this.player;
	}

	public void setCardsEarned(List<TrophyCard> cardsEarned) {
		this.cardsEarned = cardsEarned;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

}
