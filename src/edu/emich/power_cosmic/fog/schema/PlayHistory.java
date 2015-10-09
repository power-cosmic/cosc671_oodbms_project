package edu.emich.power_cosmic.fog.schema;

import java.util.ArrayList;
import java.util.List;

public class PlayHistory {

	private Game game;
	private double hoursPlayed;
	private List<TrophyCard> cardsEarned;
	
	public PlayHistory() {
		this.hoursPlayed = 0;
		this.cardsEarned = new ArrayList<>();
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public double getHoursLogged() {
		return hoursPlayed;
	}

	public void addTime(double moreHoursPlayed) {
		hoursPlayed += moreHoursPlayed;
	}

	public List<TrophyCard> getCardsEarned() {
		return cardsEarned;
	}

	public void setCardsEarned(List<TrophyCard> cardsEarned) {
		this.cardsEarned = cardsEarned;
	}
	
	public void addCardsEarned(TrophyCard cardEarned) {
		this.cardsEarned.add(cardEarned);
	}

}
