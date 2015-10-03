package edu.emich.power_cosmic.fog.commands;

import java.util.List;
import java.util.Scanner;

import com.db4o.ObjectContainer;
import com.db4o.query.Predicate;

import edu.emich.power_cosmic.fog.menus.MenuNavigator;
import edu.emich.power_cosmic.fog.schema.FogUser;
import edu.emich.power_cosmic.fog.schema.ForumThread;
import edu.emich.power_cosmic.fog.schema.Post;

public class PostPoster extends Command {

	private FogUser user;
	
	public PostPoster(FogUser user) {
		super("post", "Make a post to a thread");
		this.user = user;
	}

	@Override
	public MenuNavigator doCommand(Scanner keyboard, ObjectContainer db) {
		
		String forumTitle, content;
		
		System.out.print("Forum title: ");
		forumTitle = keyboard.nextLine();
		
		System.out.print("Content: ");
		content = keyboard.nextLine();
		
		List<ForumThread> threads = db.query(new Predicate<ForumThread>() {
			private static final long serialVersionUID = 3874245580817416014L;

			@Override
			public boolean match(ForumThread thread) {
				return thread.getTitle().equals(forumTitle);
			}
		});
		
		if (threads.isEmpty()) {
			System.out.println("Forum thread not found");
		} else {
			Post post = new Post(user, content);
			threads.get(0).addPost(post);
			
			db.store(threads.get(0));
		}

		return MenuNavigator.CONTINUE;
	}

}
