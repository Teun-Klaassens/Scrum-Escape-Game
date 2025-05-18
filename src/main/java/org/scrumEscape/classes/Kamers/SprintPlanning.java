package org.scrumEscape.classes.Kamers;

import org.scrumEscape.base.Kamer;
import org.scrumEscape.classes.Monster;
import org.scrumEscape.classes.Speler;
import org.scrumEscape.classes.taak.MultiChoice;
import org.scrumEscape.classes.taak.TaakStrategie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class SprintPlanning extends Kamer {
    final private Monster scopeCreep;
    private ArrayList<TaakStrategie> sprintPlanningVragen;
    private final int MINIMUM_CORRECTE_ANTWOORDEN = 3;
    private Scanner scanner;
    private Speler speler;

    public SprintPlanning() {
        this.scopeCreep = new Monster();
        this.sprintPlanningVragen = new ArrayList<>();
        initializeVragen();
    }

    private void initializeVragen() {
        ArrayList<String> keuzes1 = new ArrayList<>();
        keuzes1.add("Taken toewijzen aan individuele teamleden");
        keuzes1.add("De Sprint Goal vaststellen");
        keuzes1.add("De snelheid van het team evalueren");
        keuzes1.add("De Sprint Retrospective organiseren");

        sprintPlanningVragen.add(new MultiChoice(
            "Wat is het primaire doel van een Sprint Planning?",
            keuzes1, 
            "De Sprint Goal vaststellen"
        ));

        ArrayList<String> keuzes2 = new ArrayList<>(Arrays.asList(
            "De Product Owner",
            "De Scrum Master",
            "Het Development Team",
            "Alle bovenstaande"
        ));
        sprintPlanningVragen.add(new MultiChoice(
            "Wie moet aanwezig zijn bij een Sprint Planning?",
            keuzes2, 
            "Alle bovenstaande"
        ));

        // Vraag 3
        ArrayList<String> keuzes3 = new ArrayList<>(Arrays.asList(
            "Story Points",
            "Uren",
            "Dagen",
            "Euro's"
        ));
        sprintPlanningVragen.add(new MultiChoice(
            "Welke schattingsmethode wordt vaak gebruikt in Scrum?",
            keuzes3, 
            "Story Points"
        ));

        ArrayList<String> keuzes4 = new ArrayList<>(Arrays.asList(
            "De hoeveelheid werk die een team kan voltooien in een sprint",
            "De snelheid waarmee een team werkt gedurende een dag",
            "Het totaal aantal uren dat beschikbaar is voor een sprint",
            "De maximale grootte van het team"
        ));
        sprintPlanningVragen.add(new MultiChoice(
            "Wat is de definitie van 'Velocity' in Scrum?",
            keuzes4, 
            "De hoeveelheid werk die een team kan voltooien in een sprint"
        ));

        ArrayList<String> keuzes5 = new ArrayList<>(Arrays.asList(
            "Zorgen dat het team niet overwerkt",
            "Het team beschermen tegen veranderende eisen tijdens de sprint",
            "De taken tijdens de sprint verdelen",
            "De Scrum ceremoniën plannen"
        ));
        sprintPlanningVragen.add(new MultiChoice(
            "Wat is het doel van het vastleggen van de Sprint Backlog?",
            keuzes5, 
            "Het team beschermen tegen veranderende eisen tijdens de sprint"
        ));
    }

    public void start() {
        scanner = new Scanner(System.in);
        toonWelkomBericht();
        int correcteAntwoorden = behandelVragen();
        toonResultaten(correcteAntwoorden);
    }
    
    private void toonWelkomBericht() {
        System.out.println("\n=== Welkom bij de Sprint Planning ===");
        System.out.println("Je krijgt 5 meerkeuze vragen over Sprint Planning.");
        System.out.println("Je moet minstens " + MINIMUM_CORRECTE_ANTWOORDEN + " vragen correct beantwoorden om de Scope Creep Monster te vermijden!");
        System.out.println("Druk op ENTER om te beginnen...");
        scanner.nextLine();
    }
    
    private int behandelVragen() {
        int correcteAntwoorden = 0;
        
        for (TaakStrategie vraag : sprintPlanningVragen) {
            vraag.toon();
            System.out.print("\nJouw antwoord (geef het nummer): ");
            
            try {
                int keuze = scanner.nextInt();
                scanner.nextLine(); // Clear buffer
                
                // Voor MultiChoice, we moeten het nummer naar tekst converteren
                String antwoordText = "";
                if (vraag instanceof MultiChoice) {
                    MultiChoice mcVraag = (MultiChoice) vraag;
                    if (keuze >= 1 && keuze <= mcVraag.getKeuzes().size()) {
                        antwoordText = mcVraag.getKeuzes().get(keuze - 1);
                    }
                } else {
                    // Voor andere types zoals gewone Vraag
                    antwoordText = String.valueOf(keuze);
                }
                
                boolean correct = vraag.valideer(antwoordText);
                if (correct) {
                    vraag.geldigAntwoord();
                    correcteAntwoorden++;
                } else {
                    vraag.ongeldigAntwoord();
                }
            } catch (Exception e) {
                System.out.println("Ongeldige invoer. Voer een nummer in.");
                scanner.nextLine(); // discard invalid input
            }
            
            toonVoortgang(correcteAntwoorden);
            wachtOpGebruiker();
        }
        
        return correcteAntwoorden;
    }
    
    private void toonVoortgang(int correcteAntwoorden) {
        System.out.println("\nJe hebt momenteel " + correcteAntwoorden + " van de " + sprintPlanningVragen.size() + " vragen correct beantwoord.");
    }
    
    private void wachtOpGebruiker() {
        System.out.println("Druk op ENTER om door te gaan...");
        scanner.nextLine();
    }
    
    private void toonResultaten(int correcteAntwoorden) {
        System.out.println("\n=== Sprint Planning Resultaten ===");
        System.out.println("Je hebt " + correcteAntwoorden + " van de " + sprintPlanningVragen.size() + " vragen correct beantwoord.");
        
        if (isVoldoendeAntwoorden(correcteAntwoorden)) {
            toonSuccesBericht();
        } else {
            toonMisluktBericht();
        }
    }
    
    private boolean isVoldoendeAntwoorden(int correcteAntwoorden) {
        return correcteAntwoorden >= MINIMUM_CORRECTE_ANTWOORDEN;
    }
    
    private void toonSuccesBericht() {
        System.out.println("\nGefeliciteerd! Je hebt voldoende kennis van Sprint Planning aangetoond.");
        System.out.println("Je team kan nu een succesvolle Sprint Planning uitvoeren en het Scope Creep Monster blijft op afstand!");

        speler.voegBehaaldeKamerToe(this);//voegt de kamer toe bij afronden.
    }
    
    private void toonMisluktBericht() {
        System.out.println("\nSCOPE CREEP MONSTER VERSCHIJNT!");
        System.out.println("Je kennis van Sprint Planning is onvoldoende! Het Scope Creep Monster valt je aan.");
        System.out.println("Het team heeft moeite om de sprint goed te plannen en wordt overweldigd door veranderende eisen!");
        // scopeCreep.attack(); - Uncomment if Monster.attack() is implemented
    }

    @Override
    public String getBeschrijving() {
        return "Test je kennis van Sprint Planning met meerkeuze vragen. Onvoldoende kennis triggert het monster 'Scope Creep'.";
    }
}