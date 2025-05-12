package org.scrumEscape.classes;

public class Taak {
    private String beschrijving;
    private int storyPoints;
    private boolean isEssentieel;
	private final String taakId;
	private final String taakNaam;
	private final String Vraag;
	private String antwoord;
	private boolean behaald;

	public Taak(String taakId, String taakNaam, String vraag, String antwoord) {
        this.beschrijving = beschrijving;
        this.storyPoints = storyPoints;
        this.isEssentieel = isEssentieel;
		this.taakId = taakId;
		this.taakNaam = taakNaam;
		this.Vraag = vraag;
		this.antwoord = antwoord;
		this.behaald = false;
	}

	public String getTaakId() {
		return taakId;
	}

    public int getStoryPoints() {
        return storyPoints;
    }
	public String getTaakNaam() {
		return taakNaam;
	}

    public boolean isEssentieel() {
        return isEssentieel;
    }
	public String getVraag() {
		return Vraag;
	}

    @Override
    public String toString() {
        return beschrijving + " (" + storyPoints + " story points)";
    }
	public String getAntwoord() {
		return antwoord;
	}

	public boolean isBehaald() {
		return behaald;
	}

	public void sendAntwoord(String antwoord) {
 	}
}
