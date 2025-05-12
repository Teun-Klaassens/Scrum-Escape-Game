package org.scrumEscape.controllers;

import java.util.Scanner;

public class GameController {
    private boolean isPlaying;
    private int currentRoom;
    private int huidigeSpelerId;
    int currentRoom;
    ArrayList<Kamer> kamers;

    public GameController() {
        this.isPlaying = true;
        this.kamers = new ArrayList<>();
    }

    public void start() {
        Scanner s = new Scanner(System.in);
        System.out.println("Start!");
        kamersToevoegen();

        while (isPlaying) {
            printMenu();
            System.out.print("> ");
            String nextCommand = s.nextLine().toLowerCase();


            switch (nextCommand) {
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

    private void initializeSpeler() {

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
            currentRoom = newRoom;
            System.out.println("You are in room " + currentRoom);
            kamers.get(currentRoom-1).start();
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
        System.out.println("stop      - Exit the game");
        System.out.println("help (h)  - Show this menu");
        System.out.println("info (i)  - Show current room information");
        System.out.println("rooms (r) - Show all available rooms");
        System.out.println("=====================");
    }
}