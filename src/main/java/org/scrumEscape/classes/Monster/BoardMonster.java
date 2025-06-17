package org.scrumEscape.classes.Monster;

import org.scrumEscape.base.Kamer;

public class BoardMonster extends Monster {
    private boolean isOpgelost = false;
    private boolean isActief;


    @Override
    public void toonImpediment() {
        System.out.println("BOARD MONSTER is verschenen!");
        System.out.println("Het monster is niet blij met jouw scrum board!");
        System.out.println("Het monster blokkeert de deur naar de volgende kamer!");
        System.out.println("Geef het goede antwoord voordat het monster aanvalt!");
        isActief = true;
    }

    public void oplossen() {
        System.out.println("Je hebt de scope creep verslagen!");
        isOpgelost = true;
        isActief = false;
    }

    public boolean isActief() {
        return isActief;
    }

    public void attack(Kamer kamer) {
        kamer.kickOutPlayer();
    }
}
