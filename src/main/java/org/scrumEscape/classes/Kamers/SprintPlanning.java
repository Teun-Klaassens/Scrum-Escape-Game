package org.scrumEscape.classes.Kamers;

import org.scrumEscape.base.Kamer;
import org.scrumEscape.classes.Monster;

public class SprintPlanning extends Kamer {
    Monster ScopeCreep = new Monster();
//    ArrayList<Taken> taken = new ArrayList<>();


    //Standaard constructor
    public SprintPlanning() {
    }

    @Override
    public String getBeschrijving() {
        return "Je moet inschatten welke taken passen binnen een sprint. Een verkeerde inschatting triggert het monster “Scope Creep”.";
    }
}
