package org.scrumEscape.controllers;

import org.scrumEscape.base.Kamer;

import java.util.ArrayList;

public class MenuController {

	// ANSI escape codes
	public static final String RESET = "\u001B[0m";
	public static final String BOLD = "\u001B[1m";

	// Foreground (Text) Colors
	public static final String BLACK = "\u001B[30m";
	public static final String RED = "\u001B[31m";
	public static final String GREEN = "\u001B[32m";
	public static final String YELLOW = "\u001B[33m";
	public static final String BLUE = "\u001B[34m";
	public static final String PURPLE = "\u001B[35m";
	public static final String CYAN = "\u001B[36m";
	public static final String WHITE = "\u001B[37m";

	// Background Colors
	public static final String BG_BLACK = "\u001B[40m";
	public static final String BG_RED = "\u001B[41m";
	public static final String BG_GREEN = "\u001B[42m";
	public static final String BG_YELLOW = "\u001B[43m";
	public static final String BG_BLUE = "\u001B[44m";
	public static final String BG_PURPLE = "\u001B[45m";
	public static final String BG_CYAN = "\u001B[46m";
	public static final String BG_WHITE = "\u001B[47m";

	public static void printWelcome() {
		System.out.println(RESET);
		System.out.print(BOLD + GREEN);
		System.out.print(
				"╔══════════════════════════════════════════════╗\n" +
						"║                                              ║\n" +
						"║   ██████   ██████ ██████  ██    ██ ███    ███║\n" +
						"║  ██       ██      ██   ██ ██    ██ ████  ████║\n" +
						"║  ██████   ██      ██████  ██    ██ ██ ████ ██║\n" +
						"║       ██  ██      ██   ██ ██    ██ ██  ██  ██║\n" +
						"║  ██████    ██████ ██    ██ ██████  ██      ██║\n" +
						"║                                              ║\n" +
						"║       >> WELKOM BIJ SCRUM ESCAPE! <<         ║\n" +
						"║                                              ║\n" +
						"╚══════════════════════════════════════════════╝\n"
		);
		System.out.println(RESET);
	}

	public static void printLobbyRoom(ArrayList<Kamer> kamers) {
		System.out.println("==================================");
		System.out.println("You are in the lobby room.");
		/*System.out.println("Available rooms:");
		for (int i = 0; i < kamers.size(); i++) {
			System.out.println("Room " + (i+1) + ": " + kamers.get(i).getClass().getSimpleName());
		}*/
	}

	public static void gameStarting() {
		System.out.println(BG_WHITE + BLACK + BOLD + "==================================");
		System.out.print("╔════════════════════════════════════╗\n" +
				"║                                    ║\n" +
				"║   ██████ ████████  █████  ██████████ ║\n" +
				"║  ██        ██    ██   ██    ██     ║\n" +
				"║  ██████    ██    ███████    ██     ║\n" +
				"║       ██   ██    ██   ██    ██     ║\n" +
				"║  ██████    ██    ██   ██    ██     ║\n" +
				"║                                    ║\n" +
				"║      >> SPEL WORDT GESTART. <<     ║\n" +
				"║                                    ║\n" +
				"╚════════════════════════════════════╝\n");
		System.out.println("==================================");
		System.out.println(RESET);
		printMenu();
	}

	public static void applicationClosing() {
		System.out.println(RED + BOLD);
		System.out.print(
				"╔══════════════════════════════════════════════╗\n" +
						"║                                              ║\n" +
						"║   ██████   ██████ ██████  ██    ██ ███    ███║\n" +
						"║  ██       ██      ██   ██ ██    ██ ████  ████║\n" +
						"║  ██████   ██      ██████  ██    ██ ██ ████ ██║\n" +
						"║       ██  ██      ██   ██ ██    ██ ██  ██  ██║\n" +
						"║  ██████    ██████ ██    ██ ██████  ██      ██║\n" +
						"║                                              ║\n" +
						"║           SCRUM ESCAPE AFSLUITEN             ║\n" +
						"║                                              ║\n" +
						"║              Tot ziens! 👋                   ║\n" +
						"║                                              ║\n" +
						"║    Bedankt voor het spelen van Scrum Escape  ║\n" +
						"║                                              ║\n" +
						"╚══════════════════════════════════════════════╝\n"
		);
		System.out.println(RESET);
	}

	public static void printMenu() {
		System.out.println(BG_YELLOW + BLACK + BOLD + "==================================");
		System.out.println(BG_YELLOW + BLACK + BOLD + "Commando's:");
		System.out.println(BG_YELLOW + BLACK + BOLD + "switch (s) - Wissel van kamer");
		System.out.println(BG_YELLOW + BLACK + BOLD + "status (stats) - Toon je voortgang");
		System.out.println(BG_YELLOW + BLACK + BOLD + "stop (x) - Sluit de applicatie");
		System.out.println(BG_YELLOW + BLACK + BOLD + "commands (sc) - Toon alle beschikbare commando's");
		System.out.println(BG_YELLOW + BLACK + BOLD + "==================================");
		System.out.println(RESET);
	}

	public static void printAvailableRooms(ArrayList<Kamer> kamers) {
		System.out.println();
		System.out.println(BG_WHITE + YELLOW + BOLD + "==================================");
		System.out.println(BG_WHITE + YELLOW + BOLD + "  Beschikbare kamers:");
		for (int i = 0; i < kamers.size(); i++) {
			System.out.println(BG_WHITE + YELLOW + BOLD + "  Kamer " + (i + 1) + ": " + kamers.get(i).getClass().getSimpleName());
		}
		System.out.println(BG_WHITE + YELLOW + BOLD + "==================================");
		System.out.println(RESET);
	}

	public static void kamerBehaald(Kamer kamer) {
		System.out.println(BG_YELLOW + PURPLE + BOLD + "==================================");
		System.out.println(BG_YELLOW + PURPLE + BOLD + "  Gefeliciteerd! Je hebt de kamer " + kamer.getClass().getSimpleName() + " voltooid.");
		System.out.println(BG_YELLOW + PURPLE + BOLD + "==================================" + RESET);
	}

	public static void MovingToRoom(Kamer kamer) {
		System.out.println(BG_GREEN + BLACK + "╔═════════════════════════════════════════╗");
		System.out.println("║ Je gaat naar kamer: " + kamer.getClass().getSimpleName() + "║");
		System.out.println("╚═════════════════════════════════════════╝" + RESET);
	}

	public static void clearScreen() {
		// Clear the screen and move cursor to top-left
		System.out.print("\u001B[2J");  // Clear screen
		System.out.print("\u001B[H");   // Move cursor home
		System.out.flush();
		for (int i = 0; i < 200; i++) {
			System.out.println();
		}
	}
}
