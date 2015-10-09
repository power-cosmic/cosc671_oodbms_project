package edu.emich.power_cosmic.fog.application;

import com.db4o.ObjectContainer;

import edu.emich.power_cosmic.fog.schema.Administrator;
import edu.emich.power_cosmic.fog.schema.BillingInformation;
import edu.emich.power_cosmic.fog.schema.Developer;
import edu.emich.power_cosmic.fog.schema.ForumThread;
import edu.emich.power_cosmic.fog.schema.Game;
import edu.emich.power_cosmic.fog.schema.PlayHistory;
import edu.emich.power_cosmic.fog.schema.Player;
import edu.emich.power_cosmic.fog.schema.Post;
import edu.emich.power_cosmic.fog.schema.TrophyCard;

public class BasicInstance {
	public static void init(ObjectContainer db) {
		int numObjs = 5;

		for (int i = 0; i < numObjs; i++) {
			final Administrator administrator =
					new Administrator("admin" + i, "admin" + i);
			final Developer developer = new Developer("dev" + i, "dev" + i);
			final Player player = new Player("player" + i, "player" + i);

			final BillingInformation billingInformation =
					new BillingInformation();
			billingInformation.setCcv(i);
			player.setBillingInformation(billingInformation);

			final ForumThread forumThread = new ForumThread(
					new Post(administrator, "content" + i), "ForumTopic: " + i);
			for (int j = 0; j < numObjs; j++) {
				forumThread.addPost(new Post(player, "content " + j));

				final Game game = new Game();
				game.setDeveloper(developer);
				game.setTitle("game: " + j);

				final PlayHistory playHistory = new PlayHistory();
				playHistory.setGame(game);

				final TrophyCard trophyCard = new TrophyCard();
				trophyCard.setGameFrom(game);
				trophyCard.setTitle("Card " + i + "" + j);

				playHistory.addCardsEarned(trophyCard);

				player.addPlayHistory(game, playHistory);

				db.store(trophyCard);
				db.store(playHistory);
				db.store(game);
			}
			db.store(forumThread);
			db.store(billingInformation);
			db.store(player);
			db.store(developer);
			db.store(administrator);

		}
	}

}
