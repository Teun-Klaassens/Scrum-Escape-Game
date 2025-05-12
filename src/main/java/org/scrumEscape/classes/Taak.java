package org.scrumEscape.classes;

public class Taak {
	private final String taakId;
	private final String taakNaam;
	private final String Vraag;
	private String antwoord;
	private boolean behaald;

	public Taak(String taakId, String taakNaam, String vraag, String antwoord) {
		this.taakId = taakId;
		this.taakNaam = taakNaam;
		this.Vraag = vraag;
		this.antwoord = antwoord;
		this.behaald = false;
	}

	public String getTaakId() {
		return taakId;
	}

	public String getTaakNaam() {
		return taakNaam;
	}

	public String getVraag() {
		return Vraag;
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
