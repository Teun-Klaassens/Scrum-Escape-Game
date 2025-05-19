package org.scrumEscape.classes.Kamers;

import org.scrumEscape.base.Kamer;
import org.scrumEscape.classes.Speler;
import org.scrumEscape.interfaces.ISpelerOntvanger;

import java.util.Scanner;

public class Retrospective extends Kamer implements ISpelerOntvanger {
    private int score = 0;
    private final Scanner scanner = new Scanner(System.in);
    private Speler speler;

    public void setSpeler(Speler speler) {
        this.speler = speler;
    }

    @Override
    public String getBeschrijving() {
        return "Je krijgt situaties die zich in een team voordoen en moet aangeven wat het team hiervan kan leren.";
    }

    public void start() {
        score = 0;
        System.out.println("Welkom bij de Sprint Retrospective!");
        System.out.println("Analyseer de volgende situaties en kies het beste antwoord.\n");

        vraag1();
        vraag2();
        vraag3();

        System.out.println("\nJe hebt " + score + " van de 3 vragen correct beantwoord.");

        speler.voegBehaaldeKamerToe(this);//voegt de kamer toe bij afronden.
    }

    private void vraag1() {
        System.out.println("\nSituatie 1:");
        System.out.println("Tijdens de laatste sprint heeft het team gemerkt dat er veel tijd verloren ging " +
                "doordat teamleden op elkaar moesten wachten met afhankelijke taken.");
        System.out.println("\nWat is de beste les die het team hieruit kan trekken?");
        System.out.println("1) Voortaan meer overuren maken om de verloren tijd in te halen");
        System.out.println("2) Aan het begin van de sprint beter kijken naar taak-afhankelijkheden en de planning hierop aanpassen");
        System.out.println("3) Minder afhankelijke taken oppakken in de volgende sprint");

        int antwoord = getAntwoord(3);
        if (antwoord == 2) {
            System.out.println("Correct! Door vooraf beter te plannen en rekening te houden met afhankelijkheden " +
                    "kan het team efficiënter werken.");
            score++;
        } else {
            System.out.println("Dat is niet het beste antwoord. Het is belangrijk om vooraf goed te plannen en " +
                    "rekening te houden met afhankelijkheden tussen taken.");
        }
    }

    private void vraag2() {
        System.out.println("\nSituatie 2:");
        System.out.println("Het team heeft de laatste sprint niet alle user stories kunnen afronden omdat " +
                "er halverwege nieuwe requirements werden toegevoegd door de stakeholders.");
        System.out.println("\nWat is de beste aanpak voor de volgende sprint?");
        System.out.println("1) Nieuwe requirements pas in de volgende sprint meenemen en de Sprint Planning strikt volgen");
        System.out.println("2) Harder werken om zowel de geplande stories als nieuwe requirements af te krijgen");
        System.out.println("3) De sprint verlengen tot alle nieuwe requirements zijn geïmplementeerd");

        int antwoord = getAntwoord(3);
        if (antwoord == 1) {
            System.out.println("Correct! Een sprint heeft een vaste scope. Nieuwe requirements kunnen worden " +
                    "opgepakt in de volgende sprint.");
            score++;
        } else {
            System.out.println("Dat is niet het beste antwoord. In Scrum heeft een sprint een vaste scope om " +
                    "focus en voorspelbaarheid te behouden.");
        }
    }

    private void vraag3() {
        System.out.println("\nSituatie 3:");
        System.out.println("Tijdens de Daily Standups merkt het team dat deze vaak uitlopen omdat teamleden " +
                "in detail technische problemen gaan bespreken.");
        System.out.println("\nWat is de beste oplossing voor dit probleem?");
        System.out.println("1) De Daily Standup afschaffen en alleen communiceren via chat");
        System.out.println("2) De Daily Standup inkorten tot 5 minuten waarin iedereen alleen zegt of ze geblokkeerd zijn");
        System.out.println("3) Technische discussies parkeren voor een apart overleg na de Daily Standup");

        int antwoord = getAntwoord(3);
        if (antwoord == 3) {
            System.out.println("Correct! De Daily Standup moet kort en effectief blijven. Technische discussies " +
                    "kunnen beter in een apart overleg worden besproken.");
            score++;
        } else {
            System.out.println("Dat is niet het beste antwoord. De Daily Standup moet focussen op voortgang en " +
                    "blokkades. Technische details kunnen beter apart worden besproken.");
        }
    }

    private int getAntwoord(int maxOpties) {
        int antwoord = 0;
        boolean geldigAntwoord = false;

        while (!geldigAntwoord) {
            System.out.print("\nJouw antwoord (1-" + maxOpties + "): ");
            try {
                antwoord = Integer.parseInt(scanner.nextLine());
                if (antwoord >= 1 && antwoord <= maxOpties) {
                    geldigAntwoord = true;
                } else {
                    System.out.println("Voer een nummer in tussen 1 en " + maxOpties);
                }
            } catch (NumberFormatException e) {
                System.out.println("Voer een geldig nummer in");
            }
        }
        return antwoord;
    }

}