package edu.emich.power_cosmic.fog.application;

import java.util.Scanner;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

import edu.emich.power_cosmic.fog.commands.Command;
import edu.emich.power_cosmic.fog.menus.Menu;
import edu.emich.power_cosmic.fog.menus.MenuNavigator;
import edu.emich.power_cosmic.fog.queries.UserLister;
import edu.emich.power_cosmic.fog.queries.UserSignin;
import edu.emich.power_cosmic.fog.queries.UserSignup;
import edu.emich.power_cosmic.fog.schema.FogUser;

public class DatabaseProject extends Menu {

	public static final String FILENAME = "fog.db4";
	
	private FogUser user;
	
	public static void main(String[] args) {
		ObjectContainer db = Db4oEmbedded.openFile(FILENAME);
		Scanner keyboard = new Scanner(System.in);
		
		DatabaseProject mainMenu = new DatabaseProject();
		Menu currentMenu = mainMenu;
		
		System.out.println("Welcome to fog");
		System.out.println("Type 'help' for a list of commands");
		System.out.println(OutputConstants.SEPARATOR);
		
		boolean running = true;
		MenuNavigator menuStatus = MenuNavigator.CONTINUE;
		while(running) {
			System.out.print(OutputConstants.PROMPT);
			String command = keyboard.nextLine();
			menuStatus = currentMenu.doCommand(command, keyboard, db);
			
			switch(menuStatus) {
			case LOGIN:
				System.out.println(mainMenu.user);
				break;
			case EXIT:
				running = false;
				break;
			}
		}
		db.close();
	}
	
	public DatabaseProject() {
		super();
		addCommand(new SignUp());
		addCommand(new Login());
		addCommand(new Command("ls", "List users") {

			@Override
			public MenuNavigator doCommand(Scanner keyboard, ObjectContainer db) {
				new UserLister().runQuery(keyboard, db);
				return MenuNavigator.CONTINUE;
			}
		});
	}

	private class SignUp extends Command {
		
		private SignUp() {
			super("signup", "sign up as a user");
		}

		@Override
		public MenuNavigator doCommand(Scanner keyboard, ObjectContainer db) {
			UserSignup query = new UserSignup();
			query.runQuery(keyboard, db);
			return MenuNavigator.CONTINUE;
		}
	}
	
	private class Login extends Command {
		
		private Login() {
			super("login", "Login to the system");
		}

		@Override
		public MenuNavigator doCommand(Scanner keyboard, ObjectContainer db) {
			UserSignin query = new UserSignin();
			query.runQuery(keyboard, db);
			
			if (query.getUser() == null) {
				System.out.println("Invalid credentials");
				return MenuNavigator.CONTINUE;
			} else {
				user = query.getUser();
				System.out.println("Login successful");
				return MenuNavigator.LOGIN;
			}
		}
	}
	
	
}
