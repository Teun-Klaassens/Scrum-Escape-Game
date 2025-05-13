package org.scrumEscape.classes.Kamers;

import org.scrumEscape.base.Kamer;
import org.scrumEscape.classes.Monster;
import org.scrumEscape.classes.TaakSprintPlanning;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SprintPlanning extends Kamer {
    final private Monster scopeCreep;
    private ArrayList<TaakSprintPlanning> beschikbareTaken;
    private final int MAX_STORY_POINTS = 20;
    private boolean isAfgerond;

    public SprintPlanning() {
        this.scopeCreep = new Monster();
        this.beschikbareTaken = new ArrayList<>();
        initializeTaken();
    }

    private void initializeTaken() {
        beschikbareTaken.add(new TaakSprintPlanning("Login functionaliteit implementeren", 5, true));
        beschikbareTaken.add(new TaakSprintPlanning("Database optimalisatie", 4, true));
        beschikbareTaken.add(new TaakSprintPlanning("Nieuwe UI design", 13, false));
        beschikbareTaken.add(new TaakSprintPlanning("Bug fixes", 3, true));
        beschikbareTaken.add(new TaakSprintPlanning("Documentatie bijwerken", 2, false));
        beschikbareTaken.add(new TaakSprintPlanning("Performance testing", 5, false));
        beschikbareTaken.add(new TaakSprintPlanning("Security updates", 2, true));
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<TaakSprintPlanning> gekozenTaken = new ArrayList<>();
        int totalPoints = 0;
        boolean planningCompleet = false;

        System.out.println("\nWelkom bij de Sprint Planning!");
        System.out.println("Je hebt maximaal " + MAX_STORY_POINTS + " story points beschikbaar voor deze sprint.");
        System.out.println("Kies zorgvuldig welke taken je wilt includeren!\n");

        while (!planningCompleet) {
            // Toon beschikbare taken
            System.out.println("\nBeschikbare taken:");
            for (int i = 0; i < beschikbareTaken.size(); i++) {
                TaakSprintPlanning taak = beschikbareTaken.get(i);
                System.out.println(i + ": " + taak + (taak.isEssentieel() ? " (Essentieel)" : ""));
            }

            System.out.println("\nHuidige totaal: " + totalPoints + "/" + MAX_STORY_POINTS + " story points");
            System.out.println("\nKies een taak nummer om toe te voegen (of -1 om te stoppen): ");

            try {
                int keuze = scanner.nextInt();
                if (keuze == -1) {
                    planningCompleet = true;
                } else if (keuze >= 0 && keuze < beschikbareTaken.size()) {
                    TaakSprintPlanning gekozenTaak = beschikbareTaken.get(keuze);
                    if (totalPoints + gekozenTaak.getStoryPoints() <= MAX_STORY_POINTS) {
                        gekozenTaken.add(gekozenTaak);
                        totalPoints += gekozenTaak.getStoryPoints();
                        beschikbareTaken.remove(keuze);
                    } else {
                        System.out.println("Deze taak past niet meer in de sprint! (Te veel story points)");
                    }
                } else {
                    System.out.println("Ongeldige keuze!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Ongeldige invoer. Voer een nummer in.");
                scanner.next(); // discard the invalid input
            }
        }

        boolean missedEssential = beschikbareTaken.stream().anyMatch(TaakSprintPlanning::isEssentieel);

        if (missedEssential) {
            System.out.println("\nSCOPE CREEP MONSTER VERSCHIJNT!");
            System.out.println("Je hebt niet alle essentiële taken ingepland! Dit zal problemen veroorzaken.");
//            scopeCreep.attack();
        } else {
            System.out.println("\nGoede planning! Alle essentiële taken zijn ingepland.");
            System.out.println("Totaal aantal story points: " + totalPoints);
        }
    }

    @Override
    public String getBeschrijving() {
        return "Je moet inschatten welke taken passen binnen een sprint. Een verkeerde inschatting triggert het monster 'Scope Creep'.";
    }
}