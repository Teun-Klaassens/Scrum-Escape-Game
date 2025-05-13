package org.scrumEscape.controllers;

public class MenuController {

	public static void printWelcome() {
		System.out.println("==================================");
		System.out.println("Welcome to Scrum Escape!");
		System.out.println("==================================");
	}

	public static void gameStarting() {
		System.out.println("==================================");
		System.out.println("Scrum Escape game is starting...");
		System.out.println("==================================");
		printMenu();
	}

	public static void header() {
		System.out.println("==================================");
		System.out.println("Scrum Escape");
		System.out.println("==================================");
	}

	public  static void applicationClosing() {
		System.out.println("==================================");
		System.out.println("Scrum Escape is closing...");
		System.out.println("==================================");
	}

	public static void printMenu() {
		System.out.println("==================================");
		System.out.println("Commands:");
		System.out.println("switch (s) - Switch to another room");
		System.out.println("stop ( )   - Exit the game");
		System.out.println("help (h)   - Show this menu");
		System.out.println("info (i)   - Show current room information");
		System.out.println("rooms (r)  - Show all available rooms");
		System.out.println("close (x) - Close the application");
		System.out.println("commands (sc) - how all available commands");
		System.out.println("==================================");
	}
}
