package org.scrumEscape.controllers;

import org.scrumEscape.base.Kamer;
import org.scrumEscape.base.Kamers.*;

import java.util.ArrayList;
import java.util.Scanner;

public class GameController {
    private boolean play;
    int currentRoom;
    ArrayList<Kamer> kamers;

    public GameController() {
        this.play = true;
        this.kamers = new ArrayList<>();
    }

    public void start() {
        Scanner s = new Scanner(System.in);
        System.out.println("Start!");
        kamersToevoegen();

        while (play) {
            System.out.print("> ");
            String nextCommand = s.nextLine().toLowerCase();


            switch (nextCommand) {
                case "stop":
                    play = false;
                    System.out.println("Stopped!");
                    break;
                case "switch":
                case "s":
                    System.out.println("Enter new room nr (max: " + (kamers.size()-1) + "): ");
                    switchRooms(s.nextInt());
                    s.nextLine();
                    break;
                default:
                    System.out.println("Invalid command!");
            }
        }
    }

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
        } else {
            System.out.println("Invalid room number. Please try again.");
        }
    }

    private void printRoomNumbers() {
        System.out.println("Available rooms:");
        for (int i = 0; i < kamers.size(); i++) {
            System.out.println("Room " + i + ": " + kamers.get(i).getClass().getSimpleName());
        }
    }
}