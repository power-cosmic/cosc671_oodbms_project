package edu.emich.power_cosmic.fog.queries;

import java.util.List;
import java.util.Scanner;

import com.db4o.ObjectContainer;
import com.db4o.query.Predicate;

import edu.emich.power_cosmic.fog.schema.FogUser;

public class UserSignin implements FogQuery {

	private FogUser user;
	
	@Override
	public void runQuery(Scanner keyboard, ObjectContainer db) {
		String username, password;
		
		System.out.print("username: ");
		username = keyboard.nextLine();
		
		System.out.print("password: ");
		password = keyboard.nextLine();

		List<FogUser> users = db.query(new Predicate<FogUser>() {
			private static final long serialVersionUID = 7591544779191591305L;

			@Override
			public boolean match(FogUser user) {
				return user.getUsername().equals(username)
						&& user.getPassword().equals(password);
			}
		});
		
		if (!users.isEmpty()) {
			user = users.get(0);
		}
		
	}
	
	public FogUser getUser() {
		return user;
	}
	
}
