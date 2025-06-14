package org.scrumEscape.controllers;

import org.scrumEscape.base.Kamer;
import org.scrumEscape.classes.Jokers.HintJoker;
import org.scrumEscape.classes.Jokers.KeyJoker;
import org.scrumEscape.classes.Kamers.*;
import org.scrumEscape.classes.Speler;
import org.scrumEscape.interfaces.GameObserver;


import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GameController {
    private Scanner scanner;
    private boolean isRunning;
    private boolean isPlaying;
    private int currentRoomIndex =0 ;
    private String currentCommand = null;
    private final HintJoker hintJoker;
    private final KeyJoker keyJoker;

   // Game propertie
    private GameObserver gameObserver;
    private Speler huidigeSpeler;
    private ArrayList<Kamer> kamers = new ArrayList<>();

    public GameController(Scanner scanner) {
        this.scanner = scanner;
        this.isRunning = true;
        this.isPlaying = false;
        this.hintJoker = new HintJoker();
        this.keyJoker = new KeyJoker();

        this.gameObserver = new GameObserver() {
            @Override
            public void onPlayerUpdate() {
                System.out.println("Player score updated: " + huidigeSpeler);
            }

            @Override
            public void onKamerBehaald(Kamer kamer) {
                MenuController.kamerBehaald(kamer);
            }

            @Override
            public void nextKamer() {
                currentRoomIndex++;
                MenuController.MovingToRoom(kamers.get(currentRoomIndex));
            }

            @Override
            public Scanner getScanner() {
                return scanner;
            }

            @Override
            public void kickToLobby() {
                MenuController.printLobbyRoom(kamers);
                currentCommand = "s";
            }

            @Override
            public Speler getSpeler() {
                return huidigeSpeler;
            }
            @Override
            public HintJoker getHintJoker() {
                return hintJoker;
            }
            @Override
            public KeyJoker getKeyJoker() {
                return keyJoker;
            }
        };
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
            String nextCommand;
            if (currentCommand != null) {
                nextCommand = currentCommand;
                // Clear current command to avoid infinite loop
                currentCommand = null;
            }else {
                nextCommand = scanner.nextLine().toLowerCase().trim();
            }

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
                    System.out.println("Enter new room nr (max: " + (kamers.size()) + "): ");
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
         initializeKamers();
    }

    private void initializeKamers(){
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

    private void printRoomNumbers() {
        System.out.println("Available rooms:");
        for (int i = 0; i < kamers.size(); i++) {
            System.out.println("Room " + (i+1) + ": " + kamers.get(i).getClass().getSimpleName());
        }
    }

}