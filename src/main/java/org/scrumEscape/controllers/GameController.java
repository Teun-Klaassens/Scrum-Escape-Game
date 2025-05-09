package org.scrumEscape.controllers;

import java.util.Scanner;

public class GameController {
    private boolean play;
    int currentRoom;

    public GameController() {
        this.play = true;
    }

    public void start() {
        Scanner s = new Scanner(System.in);
        System.out.println("Start!");

        while (play) {
            System.out.print("> ");
            String nextCommand = s.nextLine().toLowerCase();


            switch (nextCommand) {
                case "stop":
                    play = false;
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

    private void switchRooms(int newRoom) {
        currentRoom = newRoom;
    }
}
