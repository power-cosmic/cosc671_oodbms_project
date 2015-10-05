package edu.emich.power_cosmic.fog.application;

public class OutputConstants {

	public static final String SEPARATOR = "------------------------------";
	public static final String PROMPT = "> ";
	
	public static void printTitle(String title) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < title.length(); i++) {
			buffer.append('-');
		}

		System.out.println(title);
		System.out.println(buffer);
		
	}
}
