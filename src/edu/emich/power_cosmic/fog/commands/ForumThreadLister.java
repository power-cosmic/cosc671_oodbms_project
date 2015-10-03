package edu.emich.power_cosmic.fog.commands;

import java.util.List;
import java.util.Scanner;

import com.db4o.ObjectContainer;
import com.db4o.query.Predicate;

import edu.emich.power_cosmic.fog.menus.MenuNavigator;
import edu.emich.power_cosmic.fog.schema.ForumThread;

public class ForumThreadLister extends Command {

	public ForumThreadLister() {
		super("forum threads", "Show forum threads");
	}

	@Override
	public MenuNavigator doCommand(Scanner keyboard, 
			ObjectContainer db,
			String[] args) {
		
		List<ForumThread> threads = db.query(new Predicate<ForumThread>() {
			private static final long serialVersionUID = -6694387122072021104L;

			@Override
			public boolean match(ForumThread thread) {
				return true;
			}
		});
		
		for (ForumThread thread: threads) {
			System.out.println(thread.getTitle());
		}
		
		return MenuNavigator.CONTINUE;
	}

}
