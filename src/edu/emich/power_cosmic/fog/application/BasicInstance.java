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

			final Post initialPost = new Post(administrator, "content" + i);
			final ForumThread forumThread =
					new ForumThread(initialPost, "ForumTopic: " + i);
			initialPost.setForumThread(forumThread);
			for (int j = 0; j < numObjs; j++) {
				final Post post = new Post(player, "content " + j);
				post.setForumThread(forumThread);

				forumThread.addPost(post);

				final Game game = new Game();
				game.setDeveloper(developer);
				game.setTitle("game " + j);
				game.setDescription(
					"here is the description " + (i * j) + (i + j));

				final PlayHistory playHistory = new PlayHistory();
				playHistory.setGame(game);
				playHistory.setPlayer(player);

				final TrophyCard trophyCard = new TrophyCard();
				trophyCard.setGameFrom(game);
				trophyCard.setTitle("Card " + i + "" + j);

				playHistory.addCardsEarned(trophyCard);

				player.addPlayHistory(game, playHistory);

				db.store(trophyCard);
				db.store(playHistory);
				db.store(game);
				db.store(post);
			}

			db.store(forumThread);
			db.store(initialPost);
			db.store(billingInformation);
			db.store(player);
			db.store(developer);
			db.store(administrator);
		}
	}

}
