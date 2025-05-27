package org.scrumEscape.classes.Kamers;

import org.scrumEscape.base.Kamer;
import org.scrumEscape.classes.Monster;

import java.util.ArrayList;
import java.util.Scanner;

public class DailyScrum extends Kamer {
    private final Monster mVertraging;
    private ArrayList<String> teamleden;
    private boolean isAfgerond;
    private Scanner scanner;

    public DailyScrum()  {
        this.mVertraging = new Monster();
        this.teamleden = new ArrayList<>();
    }

    @Override
    public String getBeschrijving() {
        return "Je krijgt een lijst teamleden en moet aangeven wie welke status-update zou geven. Een vergeten update roept het monster 'Vertraging' op.";
    }

    @Override
    public void start() {
        System.out.println("De Daily Scrum");
        System.out.println("Je krijgt een lijst teamleden en moet aangeven wie welke status-update zou geven.");
        System.out.println("Een vergeten update roept het monster 'Vertraging' op.\n");

        // Teamleden
        teamleden.clear(); // Reset de lijst om dubbele toevoegingen te voorkomen
        teamleden.add("teun");
        teamleden.add("roody");
        teamleden.add("kyran");
        teamleden.add("andre");

        String[] updates = {
                "Ik heb gisteren een taak afgerond.",
                "Ik heb een andere taak afgerond.",
                "Ik heb een probleem met het opstarten.",
                "Ik heb het afsluiten gemaakt"
        };

        ArrayList<String> antwoorden = new ArrayList<>();
        scanner = new Scanner(System.in);

        for (String update : updates) {
            System.out.println("Wie zegt dit?");
            System.out.println("\"" + update + "\"");
            String naam = scanner.nextLine().toLowerCase();
            
            // Controleer direct of het antwoord correct is
            if (!teamleden.contains(naam)) {
                System.out.println("Die naam staat niet in de lijst teamleden.");
                // Bied een hint aan als het antwoord niet correct is
                ongeldigAntwoordGegeven(scanner);
                // Vraag opnieuw
                System.out.println("Wie zegt dit?");
                System.out.println("\"" + update + "\"");
                naam = scanner.nextLine().toLowerCase();
            }
            
            antwoorden.add(naam);
        }

        boolean vertraging = false;

        for (String teamlid : teamleden) {
            if (!antwoorden.contains(teamlid)) {
                System.out.println(teamlid  + " heeft geen update gegeven!");
                vertraging = true;
            }
        }

        if (vertraging) {
            System.out.println("Niet alle teamleden hebben een update gegeven tijdens de Daily Scrum!");
            ongeldigAntwoordGegeven(scanner);
            mVertraging.attack("dailyscrum");
        } else {
            isAfgerond = true;
            System.out.println("Goed gedaan! Alle teamleden zijn aan het woord geweest.");
        }
    }
    
    public boolean isAfgerond() {
        return isAfgerond;
    }
}
