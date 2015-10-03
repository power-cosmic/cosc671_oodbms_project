package edu.emich.power_cosmic.fog.commands;

import java.util.List;
import java.util.Scanner;

import com.db4o.ObjectContainer;
import com.db4o.query.Predicate;

import edu.emich.power_cosmic.fog.menus.MenuNavigator;
import edu.emich.power_cosmic.fog.schema.FogUser;
import edu.emich.power_cosmic.fog.schema.Post;

public class MyPostLister extends Command {

	private FogUser user;
	
	public MyPostLister(FogUser user) {
		super("myposts", "List everything you've ever posted");
		this.user = user;
	}

	@Override
	public MenuNavigator doCommand(Scanner keyboard, ObjectContainer db, String[] args) {
		
		List<Post> posts = db.query(new Predicate<Post>() {
			private static final long serialVersionUID = 325504917869649894L;

			@Override
			public boolean match(Post post) {
				return post.getPoster() == user;
			}
		});
		
		for (Post post: posts) {
			System.out.printf("%s\n\t%s\n", 
					post.getForumThread().getTitle(),
					post.getContent());
		}
		System.out.println();
		
		return MenuNavigator.CONTINUE;
	}

}
