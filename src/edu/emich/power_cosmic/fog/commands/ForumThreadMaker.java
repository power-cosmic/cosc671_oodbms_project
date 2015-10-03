package edu.emich.power_cosmic.fog.commands;

import java.util.Scanner;

import com.db4o.ObjectContainer;

import edu.emich.power_cosmic.fog.menus.MenuNavigator;
import edu.emich.power_cosmic.fog.schema.FogUser;
import edu.emich.power_cosmic.fog.schema.ForumThread;
import edu.emich.power_cosmic.fog.schema.Post;

public class ForumThreadMaker extends Command {

	private FogUser user;
	
	public ForumThreadMaker(FogUser user) {
		super("start thread", "Start a new thread");
		this.user = user;
	}

	@Override
	public MenuNavigator doCommand(Scanner keyboard, ObjectContainer db) {
		String title, content;
		
		System.out.print("Title: ");
		title = keyboard.nextLine();
		
		System.out.print("Content: ");
		content = keyboard.nextLine();
		
		Post post = new Post(user, content);
		ForumThread thread = new ForumThread(post, title);
		
		// store both the post and the thread
		db.store(post);
		db.store(thread);
		
		return MenuNavigator.CONTINUE;
	}
	
	
	
}
