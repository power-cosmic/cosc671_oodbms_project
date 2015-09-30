package edu.emich.power_cosmic.fog.schema;

import java.util.List;

public class PlayHistory {

	private Game game;
	private double hoursPlayed;
	private List<TrophyCard> cardsEarned;

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

}
