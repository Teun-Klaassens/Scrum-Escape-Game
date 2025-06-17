package org.scrumEscape.classes.Monster;

import org.scrumEscape.base.Kamer;

public class TIAMonster extends Monster {
    private boolean isOpgelost = false;
    private boolean isActief;


    @Override
    public void toonImpediment() {
        System.out.println("TIA monster is verschenen!");
        System.out.println("Het monster is niet blij met jouw TIA!"); //fix
        System.out.println("Het monster blokkeert de deur naar de volgende kamer!");
        System.out.println("Geef het goede antwoord voordat het monster aanvalt!");
        isActief = true;
    }

    public void oplossen() {
        System.out.println("Je hebt het vertraging monster verslagen!");
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
