package org.scrumEscape.controllers;

import org.scrumEscape.base.Kamer;
import org.scrumEscape.classes.Kamers.*;
import org.scrumEscape.classes.Speler;
import org.scrumEscape.classes.SpelerDAO;
import org.scrumEscape.interfaces.GameObserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class GameController {
    private Scanner scanner;
    private boolean isRunning;
    private boolean isPlaying;
    private int currentRoomIndex = 0;

    private GameObserver gameObserver;
    private Speler huidigeSpeler;
    private SpelerDAO spelerDAO;
    private Connection dbConnection;
    private ArrayList<Kamer> kamers = new ArrayList<>();

    public GameController(Scanner scanner) {
        this.scanner = scanner;
        this.isRunning = true;
        this.isPlaying = false;

        try {
            dbConnection = DriverManager.getConnection(
                    "jdbc:mysql://193.108.200.20:3306/scrumgame?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true",
                    "root", "hhs_admin");
            spelerDAO = new SpelerDAO(dbConnection);
        } catch (Exception e) {
            e.printStackTrace();
        }

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
        };
    }

    public void start() {
        while (huidigeSpeler == null) {
            System.out.println("Wil jij (1) een nieuw account aanmaken of (2) een bestaand account gebruiken? Typ 1 of 2 in:");
            String keuze = scanner.nextLine().trim();

            if (keuze.equals("2")) {
                System.out.println("Typ jouw naam in:");
                String naam = scanner.nextLine().trim();
                loadSpeler(naam);
                if (huidigeSpeler != null) {
                    System.out.println("Welkom terug, " + naam + "!");
                    initializeKamers();
                } else {
                    System.out.println("Naam niet gevonden. Maak een account aan.");
                }
            } else if (keuze.equals("1")) {
                System.out.println("Typ jouw naam in:");
                String naam = scanner.nextLine().trim();
                try {
                    if (spelerDAO.loadSpeler(naam) != null) {
                        System.out.println("Deze naam is al in gebruik. Kies een andere naam.");
                    } else {
                        initializeSpeler(naam);
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println("Dit is geen optie!");
            }
        }

        MenuController.gameStarting();
        isPlaying = true;

        while (isPlaying || isRunning) {
            String nextCommand = scanner.nextLine().toLowerCase().trim();

            switch (nextCommand) {
                case "x":
                    saveCurrentSpeler();
                    isPlaying = false;
                    isRunning = false;
                    break;
                case "sc":
                    MenuController.printMenu();
                    break;
                case "stop":
                    isPlaying = false;
                    break;
                case "s":
                    MenuController.printAvailableRooms(kamers);
                    System.out.println("Enter new room nr (max: " + (kamers.size() - 1) + ") or type 'b' om terug te gaan naar het hoofdmenu");
                    while (true) {
                        String input = scanner.nextLine().trim().toLowerCase();
                        if (input.equals("b")) {
                            System.out.println("Terug naar hoofdmenu.");
                            MenuController.printMenu();
                            break;
                        }
                        try {
                            int roomNumber = Integer.parseInt(input);
                            if (roomNumber >= 0 && roomNumber < kamers.size()) {
                                switchRooms(roomNumber);
                                break;
                            } else {
                                System.out.println("Ongeldig kamernummer. Probeer het opnieuw.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Ongeldige invoer. Voer een nummer in of 'b' om terug te gaan.");
                        }
                    }
                    break;
                default:
                    System.out.println("Dit is geen optie!");
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
        huidigeSpeler = new Speler(naam);
        System.out.println("Player " + naam + " has been created.");
        kamers = new ArrayList<>();
        initializeKamers();
    }

    private void initializeKamers() {
        kamers.add(new DailyScrum(gameObserver));
        kamers.add(new Retrospective(gameObserver));
        kamers.add(new ScrumBord(gameObserver));
        kamers.add(new SprintPlanning(gameObserver));
        kamers.add(new SprintReview(gameObserver));
        kamers.add(new TIA(gameObserver));
    }

    private void switchRooms(int newRoom) {
        if (newRoom >= 0 && newRoom < kamers.size()) {
            currentRoomIndex = newRoom;
            System.out.println("You are in room " + currentRoomIndex);
            kamers.get(currentRoomIndex).start();
            MenuController.printMenu();
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
