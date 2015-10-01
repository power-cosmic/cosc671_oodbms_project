package edu.emich.power_cosmic.fog.application;

import java.util.Scanner;

import com.db4o.ObjectContainer;

public interface FogQuery {
	
	public void runQuery(Scanner keyboard, ObjectContainer db);
	
	public String getName();
	
}
