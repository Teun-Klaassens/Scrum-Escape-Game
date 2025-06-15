package org.scrumEscape.controllers;

import org.scrumEscape.base.Kamer;

import java.util.ArrayList;

public class MenuController {

	public static void printWelcome() {
		System.out.println("==================================");
		System.out.println("Welkom bij Scrum Escape!");
		System.out.println("==================================");
	}

	public static void gameStarting() {
		System.out.println("==================================");
		System.out.println("Scrum Escape-spel wordt gestart...");
		System.out.println("==================================");
		printMenu();
	}

	public static void header() {
		System.out.println("==================================");
		System.out.println("Scrum Escape");
		System.out.println("==================================");
	}

	public static void applicationClosing() {
		System.out.println("==================================");
		System.out.println("Scrum Escape wordt afgesloten...");
		System.out.println("==================================");
	}

	public static void printMenu() {
		System.out.println("==================================");
		System.out.println("Commando's:");
		System.out.println("switch (s) - Wissel van kamer");
		System.out.println("stop (x) - Sluit de applicatie");
		System.out.println("commands (sc) - Toon alle beschikbare commando's");
		System.out.println("==================================");
	}

	public static void printAvailableRooms(ArrayList<Kamer> kamers) {
		System.out.println("==================================");
		System.out.println("Beschikbare kamers:");
		for (int i = 0; i < kamers.size(); i++) {
			System.out.println("Kamer " + (i+1) + ": " + kamers.get(i).getClass().getSimpleName());
		}
		System.out.println("==================================");
	}

	public static void kamerBehaald(Kamer kamer) {
		System.out.println("==================================");
		System.out.println("Gefeliciteerd! Je hebt de kamer " + kamer.getClass().getSimpleName() + " voltooid.");
		System.out.println("==================================");
	}

	public static void MovingToRoom(Kamer kamer) {
		System.out.println("==================================");
		System.out.println("Je gaat naar kamer: " + kamer.getClass().getSimpleName() + "...");
	}
}
