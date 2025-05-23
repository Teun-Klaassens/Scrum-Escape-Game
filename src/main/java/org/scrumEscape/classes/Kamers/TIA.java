package org.scrumEscape.classes.Kamers;

import org.scrumEscape.base.Kamer;
import org.scrumEscape.classes.Monster;
import org.scrumEscape.interfaces.GameObserver;
import org.scrumEscape.interfaces.TaakStrategie;

import java.util.ArrayList;

public class TIA extends Kamer {

    public TIA(GameObserver gameObserver) {
        super("TIA", new Monster(), gameObserver);
    }

    @Override
    public void toonIntro() {

    }

    @Override
    public void toonBeschrijving() {

    }

    @Override
    protected ArrayList<TaakStrategie> initialiseren() {
        return null;
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
