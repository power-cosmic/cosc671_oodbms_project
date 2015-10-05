package edu.emich.power_cosmic.fog.menus;

public class MenuNavigator {
	
	public enum Status {
		EXIT, BACK, CONTINUE, CHANGE, LOGOUT;
	}
	
	public static final MenuNavigator CONTINUE 
			= new MenuNavigator(Status.CONTINUE);
	public static final MenuNavigator BACK 
			= new MenuNavigator(Status.BACK);
	public static final MenuNavigator EXIT 
			= new MenuNavigator(Status.EXIT);
	public static final MenuNavigator LOGOUT 
			= new MenuNavigator(Status.LOGOUT);
	
	private Status status;
	private Menu menu;
	
	public MenuNavigator(Status status) {
		this.status = status;
	}
	
	public MenuNavigator(Menu menu) {
		this.status = Status.CHANGE;
		this.menu = menu;
	}

	public Status getStatus() {
		return status;
	}

	public Menu getMenu() {
		return menu;
	}
	
}
