package org.scrumEscape.classes.Kamers;

import org.scrumEscape.base.Kamer;
import org.scrumEscape.classes.Monster;
import org.scrumEscape.classes.Speler;
import org.scrumEscape.classes.Taak.Puzzel;
import org.scrumEscape.interfaces.GameObserver;
import org.scrumEscape.interfaces.TaakStrategie;

import java.util.ArrayList;
import java.util.Map;

public class DailyScrum extends Kamer {

	public DailyScrum( GameObserver gameObserver) {
		super("Daily Scrum", new Monster(), gameObserver);
	}

	@Override
	protected ArrayList<TaakStrategie> initialiseren() {
		ArrayList<TaakStrategie> opdrachten = new ArrayList<>();

		// Maak puzzel opdracht
		Puzzel puzzel1 = new Puzzel(
				"Verbind de teamleden bij de juiste taken",
				Map.ofEntries(
				Map.entry("Teun:Scrum Master", "Ik heb een probleem met het opstarten"),
				Map.entry("Roody:Developer", "Ik heb gisteren een taak afgerond"),
				Map.entry("Kyran:Developer", "Ik heb een andere taak afgerond"),
				Map.entry("Andre:Product Owner", "Ik heb het afsluiten gemaakt")
		));

		opdrachten.add(puzzel1);

		return opdrachten;
	}

	@Override
	public void toonIntro() {
		System.out.println("De Daily Scrum");
		System.out.println("Je krijgt een lijst teamleden en moet aangeven wie welke status-update zou geven.");
		System.out.println("Een vergeten update roept het monster 'Vertraging' op.");
		System.out.println(" ");
	}

	@Override
	public void toonBeschrijving() {

	}

	@Override
	protected void toonMisluktBericht() {
		super.toonMisluktBericht();
	}

	@Override
	protected void toonSuccesBericht() {
		super.toonSuccesBericht();
	}
}
