package edu.emich.power_cosmic.fog.application;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.query.Predicate;

import edu.emich.power_cosmic.fog.commands.Command;
import edu.emich.power_cosmic.fog.commands.UserSignup;
import edu.emich.power_cosmic.fog.menus.AdministratorMenu;
import edu.emich.power_cosmic.fog.menus.DeveloperMenu;
import edu.emich.power_cosmic.fog.menus.Menu;
import edu.emich.power_cosmic.fog.menus.MenuNavigator;
import edu.emich.power_cosmic.fog.menus.PlayerMenu;
import edu.emich.power_cosmic.fog.schema.Administrator;
import edu.emich.power_cosmic.fog.schema.Developer;
import edu.emich.power_cosmic.fog.schema.FogUser;
import edu.emich.power_cosmic.fog.schema.ForumThread;
import edu.emich.power_cosmic.fog.schema.Player;

public class DatabaseProject extends Menu {

	public static final String FILENAME = "fog.db4";
	
	private FogUser user;
	
	public static void main(String[] args) {
		EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
		config.common().objectClass(ForumThread.class).cascadeOnUpdate(true);
		ObjectContainer db = Db4oEmbedded.openFile(config, FILENAME);
		Scanner keyboard = new Scanner(System.in);
		
		DatabaseProject mainMenu = new DatabaseProject();
		LinkedList<Menu> menuStack = new LinkedList<Menu>();
		menuStack.push(mainMenu);
		
		System.out.println("Welcome to fog");
		System.out.println("Type 'help' for a list of commands");
		System.out.println(OutputConstants.SEPARATOR);
		
		boolean running = true;
		MenuNavigator menuStatus = null;
		
		while(running) {
			System.out.print(OutputConstants.PROMPT);
			String command = keyboard.nextLine();
			menuStatus = menuStack.peek().doCommand(command, keyboard, db);
			
			switch(menuStatus.getStatus()) {
			case CHANGE:
				menuStack.push(menuStatus.getMenu());
				break;
			case BACK:
				menuStack.pop();
				if (menuStack.isEmpty()) {
					running = false;
				} else if (menuStack.size() == 1) {
					mainMenu.user = null;
				}
				break;
			case LOGOUT:
				while (menuStack.size() > 1) {
					menuStack.pop();
					mainMenu.user = null;
				}
				System.out.println("Logged out successfully");
				break;
			case EXIT:
				running = false;
				break;
			default:
				break;
			}
		}
		db.commit();
		db.close();
	}
	
	public DatabaseProject() {
		super(null);
		
		addCommand(new Login());
		addCommand(new UserSignup());
	}
	
	private class Login extends Command {
		
		private Login() {
			super("login", "Login to the system");
		}

		@Override
		public MenuNavigator doCommand(Scanner keyboard, ObjectContainer db) {
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
			if (users.isEmpty()) {
				System.out.println("Invalid credentials");
				return new MenuNavigator(MenuNavigator.Status.CONTINUE);
			} else {
				user = users.get(0);
				MenuNavigator navigator = null;
				if (user instanceof Player) {
					navigator = new MenuNavigator(
							new PlayerMenu((Player)user));
				} else if (user instanceof Developer) {
					navigator = new MenuNavigator(
							new DeveloperMenu((Developer)user));
				} else if (user instanceof Administrator) {
					navigator = new MenuNavigator(
							new AdministratorMenu((Administrator)user));
				}
				
				System.out.println("Login successful");
				return navigator;
			}
		}
	}
	
	
}
