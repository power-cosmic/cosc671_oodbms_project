package edu.emich.power_cosmic.fog.application;

import java.io.File;
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

public class DatabaseProject
	extends Menu {

	private class Login
		extends Command {

		private Login() {
			super("login", "Login to the system");
		}

		@Override
		public MenuNavigator doCommand(Scanner keyboard, ObjectContainer db,
				String[] args) {

			String username, password;

			if (args.length > 0) {
				username = args[0];
			} else {
				System.out.print("username: ");
				username = keyboard.nextLine();
			}

			if (args.length > 1) {
				password = args[1];
			} else {
				System.out.print("password: ");
				password = keyboard.nextLine();
			}

			List<FogUser> users = db.query(new Predicate<FogUser>() {
				private static final long serialVersionUID =
						7591544779191591305L;

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
				DatabaseProject.this.user = users.get(0);
				MenuNavigator navigator = null;
				if (DatabaseProject.this.user instanceof Player) {
					navigator = new MenuNavigator(
							new PlayerMenu((Player) DatabaseProject.this.user));
				} else if (DatabaseProject.this.user instanceof Developer) {
					navigator = new MenuNavigator(new DeveloperMenu(
							(Developer) DatabaseProject.this.user));
				} else if (DatabaseProject.this.user instanceof Administrator) {
					navigator = new MenuNavigator(new AdministratorMenu(
							(Administrator) DatabaseProject.this.user));
				}

				System.out.println("Login successful");
				return navigator;
			}
		}
	}

	public static final String FILENAME = "fog.db4";

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("begin?");
		String item = keyboard.nextLine();
		boolean fresh = false;

		ObjectContainer db;

		switch (item) {
			case "fresh":
				final File file = new File(FILENAME);
				file.delete();
				fresh = true;

		}
		EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
		config.common().objectClass(ForumThread.class).cascadeOnUpdate(true);
		config.common().objectClass(FogUser.class).cascadeOnUpdate(true);

		db = Db4oEmbedded.openFile(config, FILENAME);

		if (fresh) {
			BasicInstance.init(db);
		}

		DatabaseProject mainMenu = new DatabaseProject();
		LinkedList<Menu> menuStack = new LinkedList<Menu>();
		menuStack.push(mainMenu);

		System.out.println("Welcome to fog");
		OutputConstants.printTitle("Type 'help' for a list of commands");

		boolean running = true;
		MenuNavigator menuStatus = null;

		while (running) {
			System.out.print(OutputConstants.PROMPT);
			String command = interpretCommand(keyboard.next());

			String line = keyboard.nextLine().trim();
			String[] arguments =
					line.length() > 0 ? line.split("\\s+") : new String[0];
			menuStatus = menuStack.peek().doCommand(command, keyboard, db,
				arguments);

			switch (menuStatus.getStatus()) {
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

	private static String interpretCommand(String command) {
		String toReturn = command;
		switch (command) {
			case "?":
			case "h":
				toReturn = "help";
				break;
			case "in":
			case "l":
				toReturn = "login";
				break;
			case "out":
				toReturn = "logout";
				break;
			case "x":
				toReturn = "exit";
				break;
			case "b":
				toReturn = "back";
				break;
		}
		return toReturn;
	}

	private FogUser user;

	public DatabaseProject() {
		super(null);

		this.addCommand(new Login());
		this.addCommand(new UserSignup());
	}

}
