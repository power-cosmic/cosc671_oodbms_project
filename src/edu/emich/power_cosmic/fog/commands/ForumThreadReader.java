package edu.emich.power_cosmic.fog.commands;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.db4o.ObjectContainer;
import com.db4o.query.Predicate;

import edu.emich.power_cosmic.fog.application.OutputConstants;
import edu.emich.power_cosmic.fog.menus.MenuNavigator;
import edu.emich.power_cosmic.fog.schema.ForumThread;
import edu.emich.power_cosmic.fog.schema.Post;

public class ForumThreadReader extends Command {

	public ForumThreadReader() {
		super("read-forum", "Read the posts to a thread");
	}

	@Override
	public MenuNavigator doCommand(Scanner keyboard, ObjectContainer db) {
		
		String forumTitle;
		System.out.print("Forum title: ");
		forumTitle = keyboard.nextLine();
		
		List<ForumThread> threads = db.query(new Predicate<ForumThread>() {

			@Override
			public boolean match(ForumThread thread) {
				return thread.getTitle().equals(forumTitle);
			}
		});
		
		System.out.println();
		for (ForumThread thread: threads) {
			// show original post
			System.out.println(thread.getTitle());
			System.out.println("by: " + thread.getInitialPost().getPoster().getDisplayName());
			System.out.println(thread.getInitialPost().getContent());
			System.out.println(OutputConstants.SEPARATOR);
			
			// show responses
			Iterator<Post> posts = thread.getPosts();
			while(posts.hasNext()) {
				Post post = posts.next();
				System.out.printf("%-12s: %s\n",
						post.getPoster().getDisplayName(),
						post.getContent());
			}
			
			System.out.println();
		}
		
		return MenuNavigator.CONTINUE;
	}
	
}
