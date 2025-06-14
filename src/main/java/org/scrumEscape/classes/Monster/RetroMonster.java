package org.scrumEscape.classes.Monster;

public class RetroMonster extends Monster{
    private boolean isOpgelost = false;
    private boolean isActief;


    @Override
    public void toonImpediment() {
        System.out.println("RETRO monster is verschenen!");
        System.out.println("Het monster is niet blij met jouw retrospective!");
        System.out.println("Het monster blokkeert de deur naar de volgende kamer!");
        System.out.println("Geef het goede antwoord voordat het monster aanvalt!");
        isActief = true;
    }

    public void oplossen() {
        System.out.println("Je hebt het vertraging monster verslagen!");
        isOpgelost = true;
        isActief = false;
    }

    public boolean isActief() {
        return isActief;
    }
    public boolean setActief() {
        isActief = true;
        return isActief;
    }

    public boolean setInactief() {
        isActief = false;
        return isActief;
    }
}
