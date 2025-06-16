package org.scrumEscape.controllers;

import org.scrumEscape.base.Kamer;
import org.scrumEscape.classes.Jokers.HintJoker;
import org.scrumEscape.classes.Jokers.KeyJoker;
import org.scrumEscape.classes.Kamers.*;
import org.scrumEscape.classes.Speler;
import org.scrumEscape.classes.SpelerDAO;
import org.scrumEscape.interfaces.GameObserver;
import org.scrumEscape.services.status;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.util.List;


public class GameController {
    private Scanner scanner;
    private boolean isRunning;
    private boolean isPlaying;
    private int currentRoomIndex =0 ;
    private String currentCommand = null;
    private final HintJoker hintJoker;
    private final KeyJoker keyJoker;

    private GameObserver gameObserver;
    private Speler huidigeSpeler;
    private SpelerDAO spelerDAO;
    private Connection dbConnection;
    private ArrayList<Kamer> kamers = new ArrayList<>();

    public GameController(Scanner scanner) {
        this.scanner = scanner;
        this.isRunning = true;
        this.isPlaying = false;
        this.hintJoker = new HintJoker();
        this.keyJoker = new KeyJoker();

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
                if (currentRoomIndex < kamers.size()) {
                    MenuController.MovingToRoom(kamers.get(currentRoomIndex));
                } else {
                    // All rooms completed
                    System.out.println("\n==================================================");
                    System.out.println("GEFELICITEERD! JE HEBT ALLE KAMERS VOLTOOID!");
                    System.out.println("Je hebt bewezen dat je een echte Scrum Master bent!");
                    System.out.println("==================================================\n");
                    // Don't print menu here as it will be printed by switchRooms after TIA completion
                }
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
                    return;
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
                initializeSpeler(naam);
            } else {
                System.out.println("Dit is geen optie!");
                return;
            }
        }

        MenuController.gameStarting();
        isPlaying = true;

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
                case "stats":
                    if (huidigeSpeler != null && dbConnection != null) {
                        status.toonVoortgang(huidigeSpeler.getNaam(), dbConnection, scanner);
                        MenuController.printMenu();
                    } else {
                        System.out.println("Geen speler geladen.");
                    }
                    break;
                case "s":
                    saveCurrentSpeler();
                    MenuController.printAvailableRooms(kamers);
                    System.out.println("Typ een kamernummer (1 t/m " + kamers.size() + ") of 'b' om terug te gaan naar het hoofdmenu:");
                    while (true) {
                        String input = scanner.nextLine().trim().toLowerCase();
                        if (input.equals("b")) {
                            System.out.println("Terug naar hoofdmenu.");
                            MenuController.printMenu();
                            break;
                        }
                        try {
                            int roomNumber = Integer.parseInt(input);
                            if (switchRooms(roomNumber)) {
                                break;
                            } else {
                                System.out.println("Probeer nogmaals een kamer te kiezen of typ 'b' om terug te gaan.");
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
        kamers = new ArrayList<>();

        for (Kamer kamer : new Kamer[]{
                new DailyScrum(gameObserver),
                new Retrospective(gameObserver),
                new ScrumBord(gameObserver),
                new SprintPlanning(gameObserver),
                new SprintReview(gameObserver),
                new TIA(gameObserver)
        }) {
            kamer.setSpeler(huidigeSpeler);
            kamer.setConnection(dbConnection);
            kamers.add(kamer);
        }
    }



    private boolean switchRooms(int newRoom) {
        int index = newRoom - 1;
        if (index < 0 || index >= kamers.size()) {
            System.out.println("Ongeldig kamernummer. Probeer het opnieuw.");
            return false;
        }

        if (index == 5) { // Kamer 6 vergrendeld check
            Set<String> voltooid = status.getVoltooideKamers(huidigeSpeler.getNaam(), dbConnection);
            List<String> vereisteKamers = List.of("Daily Scrum", "Retrospective", "Scrum Bord", "Sprint Planning", "Sprint Review");
            if (!voltooid.containsAll(vereisteKamers)) {
                System.out.println("Kamer 6 is vergrendeld. Voltooi eerst alle andere kames.");
                return false;
            }
        }

        currentRoomIndex = index;
        Kamer kamer = kamers.get(currentRoomIndex);
        kamer.setSpeler(huidigeSpeler);
        kamer.setConnection(dbConnection);
        kamer.start();
        MenuController.printMenu();
        return true;
    }






    private void printRoomNumbers() {
        System.out.println("Available rooms:");
        for (int i = 0; i < kamers.size(); i++) {
            System.out.println("Kamer " + (i + 1) + ": " + kamers.get(i).getClass().getSimpleName());
        }
    }
}
