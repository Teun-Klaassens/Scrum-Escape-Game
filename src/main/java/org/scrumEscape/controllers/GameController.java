package org.scrumEscape.controllers;

import java.util.Scanner;

public class GameController {
    private boolean isPlaying;
    private int currentRoom;
    private int huidigeSpelerId;

    public GameController() {
        this.isPlaying = true;
    }

    public void start() {
        Scanner s = new Scanner(System.in);
        System.out.println("Start!");

        while (isPlaying) {
            System.out.print("> ");
            String nextCommand = s.nextLine().toLowerCase();


            switch (nextCommand) {
                case "stop":
                    isPlaying = false;
                    System.out.println("Stopped!");
                    break;
                case "switch":
                    currentRoom = Integer.parseInt(s.nextLine());
                    switchRooms(currentRoom);
                    break;
                default:
                    System.out.println("Invalid command!");
            }
        }
    }

    private void initializeSpeler() {

    }

    private void intializeKamers(){}

    private void switchRooms(int newRoom) {
        currentRoom = newRoom;
    }
}
