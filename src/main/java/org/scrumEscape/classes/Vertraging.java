package org.scrumEscape.classes;

public class Vertraging extends Monster{
    private String naam;
    private boolean monsterAlive = false;

    public Vertraging() {
        this.naam = "Vertraging";
    }

    public boolean isMonsterAlive() {
        return monsterAlive;
    }

    public void toonInpediment() {
        monsterAlive = true;
        System.out.println("Het " + naam + " monster is verschenen!");
    }

    public void killMonster() {
        monsterAlive = false;
        System.out.println("Het monster is verslagen!");
    }
}
