package org.scrumEscape.classes.Taak;
import org.scrumEscape.interfaces.TaakStrategie;

import java.util.ArrayList;
import java.util.Scanner;

public class MultiChoice implements TaakStrategie {
    final String vraag;
    final ArrayList<String> keuzes;
    final int antwoord;
    private boolean behaald;

    public MultiChoice(String vraag, ArrayList<String> keuzes, int antwoord) {
        this.vraag = vraag;
        if(antwoord < 1) {
            this.antwoord = 1;
        }
        else this.antwoord = Math.min(antwoord, keuzes.size());

        this.keuzes = keuzes;
        this.behaald = false;
    }

    @Override
    public void toon() {
        System.out.println(vraag);
        System.out.println("\nKies het juiste antwoord:");
        for (int i = 0; i < keuzes.size(); i++) {
            System.out.println("(" + (i + 1) + ")" + " : " + keuzes.get(i));
        }
    }

    @Override
    public boolean valideer(String antwoord) {
         if (String.valueOf(this.antwoord).equalsIgnoreCase(antwoord)) {
            this.behaald = true;
            return true;
        }
        return false;
    }

    @Override
    public void ongeldigAntwoord() {
        System.out.println("Helaas, dat is niet juist. Het juiste antwoord was: " + antwoord);
    }

    @Override
    public void geldigAntwoord() {
        System.out.println("Correct! Goed gedaan.");
    }

    @Override
    public boolean isBehaald() {
        return behaald;
    }
}
