package org.scrumEscape.classes.Voorwerpen;

import org.scrumEscape.classes.Monster.Monster;
import org.scrumEscape.classes.Voorwerpen.Wapen;

public class Zwaard implements Wapen {


    public void attack(Monster monster) {
        System.out.println("Je gebruikt het zwaard! ");
        monster.oplossen();
    }
}