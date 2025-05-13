package org.scrumEscape.classes;

public class TaakSprintPlanning {
    private String beschrijving;
    private int storyPoints;
    private boolean isEssentieel;

    public TaakSprintPlanning(String beschrijving, int storyPoints, boolean isEssentieel) {
        this.beschrijving = beschrijving;
        this.storyPoints = storyPoints;
        this.isEssentieel = isEssentieel;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public int getStoryPoints() {
        return storyPoints;
    }

    public void setStoryPoints(int storyPoints) {
        this.storyPoints = storyPoints;
    }

    public boolean isEssentieel() {
        return isEssentieel;
    }

    public void setEssentieel(boolean essentieel) {
        isEssentieel = essentieel;
    }

    @Override
    public String toString() {
        return beschrijving + " (" + storyPoints + " story points)";
    }
}