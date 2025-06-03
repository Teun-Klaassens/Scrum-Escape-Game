package org.scrumEscape.classes;

public class Vertraging extends Monster {
    private boolean isOpgelost = false;

    @Override
    public void toonImpediment() {
        System.out.println("VERTRAGING monster is verschenen!");
    }

    public void oplossen() {
        System.out.println("Je hebt het vertraging monster verslagen!");
        isOpgelost = true;
    }

    public boolean isOpgelost() {
        return isOpgelost;
    }

}
