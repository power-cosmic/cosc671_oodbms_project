package edu.emich.power_cosmic.fog.queries;

import java.util.Scanner;

import com.db4o.ObjectContainer;

public interface FogQuery {
	
	public void runQuery(Scanner keyboard, ObjectContainer db);
	
}
