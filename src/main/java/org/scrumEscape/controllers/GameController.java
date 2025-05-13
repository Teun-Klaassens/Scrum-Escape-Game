package org.scrumEscape.controllers;

import org.scrumEscape.base.Kamer;
import org.scrumEscape.classes.Kamers.*;
import org.scrumEscape.classes.Speler;


import java.util.ArrayList;
import java.util.Scanner;

public class GameController {
    private Scanner s;
    private boolean isRunning;
    private boolean isPlaying;
    private int currentRoomIndex =0 ;

   // Game properties
    private Speler huidigeSpeler;
    ArrayList<Kamer> kamers;

    public GameController(Scanner scanner) {
        this.s = scanner;
        this.isRunning = true;
         System.out.println("Welcome to Scrum Escape!");
    }

    public void start() {
         if(huidigeSpeler == null) {
            System.out.println("Enter your unique player name: ");

            // Add a while loop to avoid empty names
            while(huidigeSpeler == null) {
                String naam = s.nextLine().trim();
                if(naam.isEmpty())return;
                initializeSpeler(naam);
            }
            System.out.println("Scrum Escape game is starting...");
            isPlaying  = true;
            printMenu();
        }

        while (isPlaying) {
            String nextCommand = s.nextLine().toLowerCase().trim(); // Added trim to avoid issue

            switch (nextCommand) {
                case "sc" :
                    printMenu();
                case "stop":
                    isPlaying = false;
                    System.out.println("Stopped!");
                    break;
                case "switch":
                case "s":
                    printRoomNumbers();
                    System.out.println("Enter new room nr (max: " + (kamers.size()-1) + "): ");
                    switchRooms(s.nextInt());
                    s.nextLine();
                    break;

                default:
                    System.out.println("Invalid command!");
            }
        }
    }

    private void initializeSpeler(String naam) {
        huidigeSpeler = new Speler( naam);
        System.out.println("Player " + naam + " has been created.");
        kamers = new ArrayList<>();
        kamersToevoegen();
        intializeKamers();
    }

    private void intializeKamers(){}

    private void kamersToevoegen() {
        kamers.add(new DailyScrum());
        kamers.add(new Retrospective());
        kamers.add(new ScrumBord());
        kamers.add(new SprintPlanning());
        kamers.add(new SprintReview());
        kamers.add(new TIA());
    }

    private void switchRooms(int newRoom) {
        if (newRoom > 0 && newRoom <= kamers.size()) {
            currentRoomIndex = newRoom;
            System.out.println("You are in room " + currentRoomIndex);
            kamers.get(currentRoomIndex -1).start();
        } else {
            System.out.println("Invalid room number. Please try again.");
        }
    }

    private void printRoomNumbers() {
        System.out.println("Available rooms:");
        for (int i = 0; i < kamers.size(); i++) {
            System.out.println("Room " + (i+1) + ": " + kamers.get(i).getClass().getSimpleName());
        }
    }

    private void printMenu() {
        System.out.println("\n=== Available Commands ===");
        System.out.println("switch (s) - Switch to another room");
        System.out.println("stop ( )   - Exit the game");
        System.out.println("help (h)   - Show this menu");
        System.out.println("info (i)   - Show current room information");
        System.out.println("rooms (r)  - Show all available rooms");
        System.out.println("close (x) - Close the application");
        System.out.println("commands (sc) - how all available commands");
        System.out.println("=====================");
    }
}