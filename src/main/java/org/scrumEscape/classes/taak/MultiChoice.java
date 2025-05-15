package org.scrumEscape.classes.taak;

import java.util.ArrayList;
import java.util.Scanner;

public class MultiChoice {
    final String vraag;
    final ArrayList<String> keuzes;
    final String antwoord;
    private boolean behaald;

    public MultiChoice(String vraag, ArrayList<String> keuzes, String antwoord) {
        this.vraag = vraag;
        this.keuzes = keuzes;
        this.antwoord = antwoord;
        this.behaald = false;
    }

    public void toon() {
        System.out.println("\n" + vraag);
        for (int i = 0; i < keuzes.size(); i++) {
            System.out.println((i + 1) + ": " + keuzes.get(i));
        }
    }

    public boolean valideer(String antwoord) {
        if (this.antwoord.equalsIgnoreCase(antwoord)) {
            this.behaald = true;
            return true;
        }
        return false;
    }

    public boolean valideerKeuze(int keuzeNummer) {
        if (keuzeNummer >= 1 && keuzeNummer <= keuzes.size()) {
            String gekozenAntwoord = keuzes.get(keuzeNummer - 1);
            return valideer(gekozenAntwoord);
        }
        return false;
    }

    public String getVraag() {
        return vraag;
    }

    public ArrayList<String> getKeuzes() {
        return keuzes;
    }

    public String getAntwoord() {
        return antwoord;
    }

    public boolean isBehaald() {
        return behaald;
    }

    public void setBehaald(boolean behaald) {
        this.behaald = behaald;
    }

    public boolean behandel() {
        Scanner scanner = new Scanner(System.in);
        toon();
        System.out.print("\nJouw antwoord (geef het nummer): ");
        try {
            int keuze = scanner.nextInt();
            boolean correct = valideerKeuze(keuze);
            
            if (correct) {
                System.out.println("Correct! Goed gedaan.");
            } else {
                System.out.println("Helaas, dat is niet juist. Het juiste antwoord was: " + antwoord);
            }
            
            return correct;
        } catch (Exception e) {
            System.out.println("Ongeldige invoer. Voer een nummer in.");
            scanner.next(); // discard invalid input
            return false;
        }
    }
}
