package org.scrumEscape.classes.Kamers;

import org.scrumEscape.base.Kamer;
import org.scrumEscape.classes.Monster;
import org.scrumEscape.interfaces.GameObserver;
import org.scrumEscape.interfaces.TaakStrategie;

import java.util.ArrayList;

public class SprintReview extends Kamer {

    public SprintReview(GameObserver gameObserver) {
        super("Sprint Review", new Monster(), gameObserver);
    }

    @Override
    protected ArrayList<TaakStrategie> initialiseren() {
        ArrayList<TaakStrategie> strategies = new ArrayList<>();

        // Implements your methods here
        // ...............................
        // ...............................

        return  strategies;
    }
    
    @Override
    public void toonIntro() {
        System.out.println("== Welkom bij de Sprint Review! ==");
        System.out.println("Je krijgt een opdracht om de resultaten van de sprint te presenteren aan de belanghebbenden.");
        System.out.println("Beantwoord de vragen correct om verder te gaan.");
    }

    @Override
    public void toonBeschrijving() {

    }

}
