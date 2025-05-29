package org.scrumEscape.classes.Kamers;

import org.scrumEscape.base.Kamer;
import org.scrumEscape.classes.Monster;
import org.scrumEscape.classes.taak.Puzzel;
import org.scrumEscape.interfaces.GameObserver;
import org.scrumEscape.interfaces.TaakStrategie;

import java.util.ArrayList;
import java.util.Map;

public class ScrumBord extends Kamer {

	public ScrumBord(GameObserver gameObserver) {
		super("Scrum Bord", new Monster(), gameObserver);
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
		System.out.println("== Welkom bij het Scrum Bord! ==");
		System.out.println("Je krijgt een opdracht om een bord correct in te richten met taken, user stories en epics.");
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
