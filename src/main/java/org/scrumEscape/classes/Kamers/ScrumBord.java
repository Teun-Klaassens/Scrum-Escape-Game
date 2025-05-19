package org.scrumEscape.classes.Kamers;

import org.scrumEscape.base.Kamer;
import org.scrumEscape.classes.Monster;
import org.scrumEscape.interfaces.GameObserver;
import org.scrumEscape.interfaces.TaakStrategie;

import java.util.ArrayList;

public class ScrumBord extends Kamer {

    public ScrumBord(GameObserver gameObserver) {
        super("Scrum Bord", new Monster(), gameObserver);
    }

    @Override
    protected ArrayList<TaakStrategie> initialiseren() {
        return null;
    }

    @Override
    public void toonIntro() {
        System.out.println("== Welkom bij het Scrum Bord! ==");
        System.out.println("Je krijgt een opdracht om een bord correct in te richten met taken, user stories en epics.");
    }

    @Override
    public void toonBeschrijving() {

    }

    @Override
    protected void toonMisluktBericht() {
        super.toonMisluktBericht();
    }

    @Override
    protected void toonSuccesBericht() {
        super.toonSuccesBericht();
    }

}
