package edu.emich.power_cosmic.fog.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

public class ThingThatDoesTheQueries {
	
	public static final String FILENAME = "fog.db";
	
	private static final String SEPARATOR = "*------------------*";
	
	private List<FogQuery> queries;
	
	public static void main(String[] args) {
		new ThingThatDoesTheQueries(FILENAME);
	}
	
	public ThingThatDoesTheQueries(String filename) {
		ObjectContainer db = Db4oEmbedded.openFile(filename);
		Scanner keyboard = new Scanner(System.in);
		
		queries = new ArrayList<FogQuery>();
		queries.add(new GameAdder());
		queries.add(new GameLister());
		
		boolean running = true;
		do {
			FogQuery query = getUserChoice(keyboard);
			if (query != null) {
				query.runQuery(keyboard, db);
			} else {
				running = false;
			}
		} while (running);
		
		System.out.println("Okay, bye.");
		
		keyboard.close();
		db.close();
	}
	
	public FogQuery getUserChoice(Scanner keyboard) {

		System.out.println(SEPARATOR);
		System.out.println("0: Exit");
		for (int i = 0; i < queries.size(); i++) {
			System.out.println((i + 1) + ": " + queries.get(i).getName());
		}
		
		System.out.print("Enter choice: ");
		int choice = keyboard.nextInt();
		keyboard.nextLine();
		System.out.println(SEPARATOR);
		if (choice > 0) {
			return queries.get(choice - 1);
		}
		return null;
	}
	
}
