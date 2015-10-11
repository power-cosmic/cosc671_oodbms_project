package edu.emich.power_cosmic.fog.test;

import org.junit.Test;

import edu.emich.power_cosmic.fog.schema.Game;
import edu.emich.power_cosmic.fog.schema.Player;
import junit.framework.TestCase;

public class PlayerTest
	extends TestCase {

	@Test
	public void testAddGameCreatesPlayHistory() {
		final Player player = new Player();
		final Game game = new Game();
		game.setTitle("hi");

		player.addGame(game);
		assertNotNull(player.getPlayHistory().get(game));
	}

}
