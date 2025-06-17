package org.scrumEscape.classes.Monster;

import org.scrumEscape.base.Kamer;

public class ScopeCreep extends Monster {
    private boolean isOpgelost = false;
    private boolean isActief;


    @Override
    public void toonImpediment() {
        System.out.println("SCOPE CREEP is verschenen!");
        System.out.println("Het monster is niet met jouw gekozen taken!");
        System.out.println("Het monster blokkeert de deur naar de volgende kamer!");
        System.out.println("Geef het goede antwoord voordat het monster aanvalt!");
        isActief = true;
    }

    public void oplossen() {
        System.out.println("Je hebt de scope creep verslagen!");
        isActief = false;
        isOpgelost = true;
    }

    public void attack(Kamer kamer) {
        kamer.kickOutPlayer();
    }

    public boolean isActief() {
        return isActief;
    }

}