package edu.emich.power_cosmic.fog.queries;

import java.util.List;
import java.util.Scanner;

import com.db4o.ObjectContainer;
import com.db4o.query.Predicate;

import edu.emich.power_cosmic.fog.application.OutputConstants;
import edu.emich.power_cosmic.fog.schema.Administrator;
import edu.emich.power_cosmic.fog.schema.Developer;
import edu.emich.power_cosmic.fog.schema.FogUser;
import edu.emich.power_cosmic.fog.schema.Player;

public class UserSignup implements FogQuery {

	private static final String ADMIN = "admin";
	private static final String DEVELOPER = "developer";
	private static final String PLAYER = "player";
	
	@Override
	public void runQuery(Scanner keyboard, ObjectContainer db) {
		System.out.printf("Select user type (%s, %s, %s):\n",
				ADMIN, DEVELOPER, PLAYER);
		
		System.out.print(OutputConstants.PROMPT);
		String userType = keyboard.nextLine().toLowerCase();
		FogUser user = null;
		
		switch(userType) {
		case ADMIN:
			user = createAdministrator(keyboard, db);
			break;
		case DEVELOPER:
			user = createDeveloper(keyboard, db);
			break;
		case PLAYER:
			user = createPlayer(keyboard, db);
			break;
		default:
			System.out.println("Invalid entry");
			break;
		}
		
		if (user != null) {
			db.store(user);
		}
	}
	
	private void addFogUserFields(FogUser user, Scanner keyboard, ObjectContainer db) {
		boolean uniqueUsername = false;
		
		// prompt user until they enter a unique username
		while (!uniqueUsername) {
			System.out.print("username: ");
			String username = keyboard.nextLine();
			
			if (isUniqueUsername(username, db)) {
				uniqueUsername = true;
				user.setUsername(username);
			}
		}
		
		// get other info
		
		System.out.print("password: ");
		user.setPassword(keyboard.nextLine());
		
		System.out.print("email: ");
		String email = keyboard.nextLine();
		// maybe do validation here
		user.setEmail(email);
		
		System.out.print("display name: ");
		user.setDisplayName(keyboard.nextLine());
	}
	
	private Administrator createAdministrator(Scanner keyboard, ObjectContainer db) {
		Administrator user = new Administrator();
		addFogUserFields(user, keyboard, db);
		
		System.out.print("first name: ");
		user.setFirstName(keyboard.nextLine());
		
		System.out.print("last name: ");
		user.setLastName(keyboard.nextLine());
		
		System.out.print("employee id: ");
		user.setEmployeeId(keyboard.nextLong());
		
		return user;
	}
	
	private Developer createDeveloper(Scanner keyboard, ObjectContainer db) {
		Developer user = new Developer();
		addFogUserFields(user, keyboard, db);
		
		System.out.print("name: ");
		user.setName(keyboard.nextLine());
		
		return user;
	}
	
	private Player createPlayer(Scanner keyboard, ObjectContainer db) {
		Player user = new Player();
		addFogUserFields(user, keyboard, db);
		
		System.out.print("first name: ");
		user.setFirstName(keyboard.nextLine());
		
		System.out.print("last name: ");
		user.setLastName(keyboard.nextLine());
		
		
		return user;
	}
	
	/**
	 * Check if username is unique
	 * @param username
	 * @param db
	 * @return
	 */
	private boolean isUniqueUsername(final String username, ObjectContainer db) {
		List<FogUser> users = db.query(new Predicate<FogUser>() {
			private static final long serialVersionUID = 8063325484980060534L;

			public boolean match(FogUser user) {
				return username.equals(user.getUsername());
			}
		});
		
		return users.size() == 0;
	}
	
}
