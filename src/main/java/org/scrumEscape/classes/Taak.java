package org.scrumEscape.classes;

public class Taak {
    private String beschrijving;
    private int storyPoints;
    private boolean isEssentieel;

    public Taak(String beschrijving, int storyPoints, boolean isEssentieel) {
        this.beschrijving = beschrijving;
        this.storyPoints = storyPoints;
        this.isEssentieel = isEssentieel;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public int getStoryPoints() {
        return storyPoints;
    }

    public boolean isEssentieel() {
        return isEssentieel;
    }

    @Override
    public String toString() {
        return beschrijving + " (" + storyPoints + " story points)";
    }
}