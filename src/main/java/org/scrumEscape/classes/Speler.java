package org.scrumEscape.classes;

import org.scrumEscape.base.Kamer;
import org.scrumEscape.interfaces.ISpelerControls;

import java.util.ArrayList;

public class Speler implements ISpelerControls {
 	private final String naam;
	private int huidgeKamerInt = 0;
	private ArrayList<Kamer> behaaldeKamers = new ArrayList<>();

	public Speler(String naam) {
 		this.naam = naam;
	}

	public void voegBehaaldeKamerToe(Kamer kamer) {
		if (!behaaldeKamers.contains(kamer)) {
			behaaldeKamers.add(kamer);
		}
	}

	@Override
	public String getStatus() {
		String status = "Je bevindt je momenteel in kamer nummer: " + huidgeKamerInt + "\n";

		status += "Je hebt " + behaaldeKamers.size() + " kamers behaald:\n";

		for (Kamer kamer : behaaldeKamers) {
			status += "- " + kamer.getKamerNaam() + "\n";
		}

		return status;
	}

	@Override
	public void switchKamer(int kamerInt) {

	}

	@Override
	public void stopGame() {

	}
}
