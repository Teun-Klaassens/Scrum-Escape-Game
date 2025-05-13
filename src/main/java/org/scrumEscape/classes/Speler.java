package org.scrumEscape.classes;

import org.scrumEscape.base.Kamer;
import org.scrumEscape.interfaces.ISpelerControls;

public class Speler implements ISpelerControls {
 	private final String naam;
	private int huidgeKamerInt = 0;

	public Speler(String naam) {
 		this.naam = naam;
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
