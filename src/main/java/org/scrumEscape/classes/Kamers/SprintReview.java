package org.scrumEscape.classes.Kamers;

import org.scrumEscape.base.Kamer;
import org.scrumEscape.classes.Jokers.HintJoker;
import org.scrumEscape.classes.Jokers.KeyJoker;
import org.scrumEscape.classes.Monster.Monster;
import org.scrumEscape.classes.Monster.ReviewMonster;
import org.scrumEscape.classes.Monster.ScopeCreep;
import org.scrumEscape.interfaces.GameObserver;
import org.scrumEscape.interfaces.TaakStrategie;
import org.scrumEscape.classes.Jokers.HintJokerGebruiken;
import org.scrumEscape.classes.Jokers.KeyJokerGebruiken;

import java.util.ArrayList;
import java.util.Scanner;

public class SprintReview extends Kamer implements HintJokerGebruiken, KeyJokerGebruiken {

    public SprintReview(GameObserver gameObserver) {
        super("Sprint Review", new ReviewMonster(), gameObserver);
    }

    @Override
    protected ArrayList<TaakStrategie> initialiseren() {
        ArrayList<TaakStrategie> opdrachten = new ArrayList<>();

        // Implements your methods here
        // ...............................
        // ...............................

        return  opdrachten;
    }
    
    @Override
    public void toonIntro() {
        System.out.println("== Welkom bij de Sprint Review! ==");
        System.out.println("Je krijgt een opdracht om de resultaten van de sprint te presenteren aan de belanghebbenden.");
        System.out.println("Beantwoord de vragen correct om verder te gaan.");
        System.out.println("Een incorrect antwoord roept het 'Review' monster op.");

    }

    @Override
    public void toonBeschrijving() {
        System.out.println("Je moet feedback van stakeholders interpreteren en de impact ervan inschatten.");
    }

    @Override
    public void useHintJoker() {
        String hint = org.scrumEscape.classes.hints.JokerHints.getHint("Sprint Review");
        System.out.println("\nHint: " + hint + "\n");

    }

    @Override
    public void useKeyJoker() {
        getGameObserver().nextKamer();

    }
}
