package org.scrumEscape.controllers;

import org.scrumEscape.base.Kamer;
import org.scrumEscape.classes.Kamers.*;
import org.scrumEscape.classes.Speler;
import org.scrumEscape.classes.SpelerDAO;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class GameController {
    private Scanner s;
    private boolean isRunning;
    private boolean isPlaying;
    private int currentRoomIndex =0 ;
    private SpelerDAO spelerDAO;
    private Connection dbConnection;

   // Game properties
    private Speler huidigeSpeler;
    private ArrayList<Kamer> kamers = new ArrayList<>();

    public GameController(Scanner scanner) {
        this.s = scanner;
        this.isRunning = true;
        try {
            dbConnection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/scrumgame", "root", "Olick160977!");
            spelerDAO = new SpelerDAO(dbConnection);
        } catch (Exception e) {
            e.printStackTrace();
        }
     }

    public void start() {
         if(huidigeSpeler == null) {
             System.out.println("Wil jij (1) een nieuw account aanmaken of (2) een bestaand account gebruiken? Typ 1 of 2 in:");
             String keuze = s.nextLine().trim();
             if (keuze.equals("2")) {
                 System.out.println("Typ jouw naam in:");
                 String naam = s.nextLine().trim();
                 loadSpeler(naam);
                 if (huidigeSpeler != null) {
                     System.out.println("Welkom terug, " + naam + "!");
                 } else {
                     System.out.println("Naam niet gevonden. Maak een account aan.");
                     return;
                 }
             } else if (keuze.equals("1")) {
                 System.out.println("Typ jouw naam in:");
                 String naam = s.nextLine().trim();
                 try {
                     if (spelerDAO.loadSpeler(naam) != null) {
                         System.out.println("Deze naam is al in gebruik. Kies een andere naam.");
                         return;
                     }
                 } catch (SQLException e) {
                     throw new RuntimeException(e);
                 }
                 initializeSpeler(naam);
             } else {
                 System.out.println("Dit is geen optie!");
                 return;
             }

             MenuController.gameStarting();
             isPlaying  = true;
         }

        while (isPlaying || isRunning) {
            String nextCommand = s.nextLine().toLowerCase().trim(); // Added trim to avoid issue

            switch (nextCommand) {
                case "x":
                    saveCurrentSpeler();
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

    public void saveCurrentSpeler() {
        if (huidigeSpeler != null && spelerDAO != null) {
            try {
                spelerDAO.saveSpeler(huidigeSpeler);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void loadSpeler(String naam) {
        if (spelerDAO != null) {
            try {
                huidigeSpeler = spelerDAO.loadSpeler(naam);
            } catch (Exception e) {
                e.printStackTrace();
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
}