package org.scrumEscape.classes;

import org.scrumEscape.base.Kamer;
import org.scrumEscape.interfaces.ISpelerControls;

public class Speler implements ISpelerControls {
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

	@Override
	public void getStatus() {

	}

	@Override
	public void switchKamer(int kamerInt) {

	}

	@Override
	public void stopGame() {

	}
}
