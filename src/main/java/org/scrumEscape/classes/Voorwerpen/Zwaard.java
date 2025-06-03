package org.scrumEscape.classes.Voorwerpen;

import org.scrumEscape.classes.Monster;
import org.scrumEscape.classes.Voorwerpen.Wapen;

public class Zwaard implements Wapen {
    private final String hint;

    public Zwaard(String hint) {
        this.hint = hint;
    }

    @Override
    public void attack(Monster monster) {
        System.out.println("Je gebruikt het zwaard! " + hint);
        monster.oplossen();
    }
}