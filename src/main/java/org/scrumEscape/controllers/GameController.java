package org.scrumEscape.controllers;

import org.scrumEscape.base.Kamer;
import org.scrumEscape.classes.Kamers.*;
import org.scrumEscape.classes.Speler;
import org.scrumEscape.interfaces.GameObserver;


import java.util.ArrayList;
import java.util.Scanner;

public class GameController {
    private Scanner scanner;
    private boolean isRunning;
    private boolean isPlaying;
    private int currentRoomIndex =0 ;

   // Game propertie
    private GameObserver gameObserver;
    private Speler huidigeSpeler;
    private ArrayList<Kamer> kamers = new ArrayList<>();

    public GameController(Scanner scanner) {
        this.scanner = scanner;
        this.isRunning = true;
     }

    public void start() {
         if(huidigeSpeler == null) {
            System.out.println("Enter your unique player name: ");
             while(huidigeSpeler == null) {
                String naam = scanner.nextLine().trim();
                if(naam.isEmpty())return;
                initializeSpeler(naam);
            }
             MenuController.gameStarting();
             isPlaying  = true;
         }

        while (isPlaying || isRunning) {
            String nextCommand = scanner.nextLine().toLowerCase().trim(); // Added trim to avoid issue

            switch (nextCommand) {
                case "x":
                    isPlaying = false;
                    isRunning = false;
                    break;
                    ///////
                case "sc" :
                   MenuController.printMenu();
                case "stop":
                    isPlaying = false;
                     break;
                case "s":
                    MenuController.printAvailableRooms(kamers);
                    System.out.println("Enter new room nr (max: " + (kamers.size()-1) + "): ");
                    switchRooms(scanner.nextInt());
                    scanner.nextLine();
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
        initializeKamers();
    }

    private void initializeKamers(){

    }

    private void kamersToevoegen() {
        kamers.add(new DailyScrum(gameObserver));
        kamers.add(new Retrospective(gameObserver));
        kamers.add(new ScrumBord(gameObserver));
        kamers.add(new SprintPlanning(gameObserver));
        kamers.add(new SprintReview(gameObserver));
        kamers.add(new TIA(gameObserver));
    }

    private void switchRooms(int newRoom) {
        if (newRoom > 0 && newRoom <= kamers.size()) {
            currentRoomIndex = newRoom;
            System.out.println("You are in room " + currentRoomIndex);
            kamers.get(currentRoomIndex -1).start();
            MenuController.printMenu();
        } else {
            System.out.println("Invalid room number. Please try again.");
        }
    }

}