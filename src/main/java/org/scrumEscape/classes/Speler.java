package org.scrumEscape.classes;

public class Speler   {
 	private final String naam;
	private int voortgang;

	public Speler(String naam, int voortgang) {
 		this.naam = naam;
		 this.voortgang = voortgang;
	}

	public Speler(String naam) {
		this(naam, 0);
	}

	public String getNaam() {
		return naam;
	}

	public void increaseVoortgang() {
		this.voortgang++;
	}

	public int getVoortgang() {
		return voortgang;
	}

}
