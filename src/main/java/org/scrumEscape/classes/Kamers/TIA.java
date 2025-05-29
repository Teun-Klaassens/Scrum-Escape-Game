package org.scrumEscape.classes.Kamers;

import org.scrumEscape.base.Kamer;
import org.scrumEscape.classes.Monster;
import org.scrumEscape.classes.taak.MultiChoice;
import org.scrumEscape.interfaces.GameObserver;
import org.scrumEscape.interfaces.TaakStrategie;

import java.util.ArrayList;

public class TIA extends Kamer {
	private final int PASSING_SCORE = 3;
	private final int TOTAL_QUESTIONS = 5;

	public TIA(GameObserver gameObserver) {
		super("TIA", new Monster(), gameObserver);
	}

	@Override
	public void toonIntro() {
		System.out.println("\n==== WELKOM BIJ DE FINALE KAMER: TIA ====");
		System.out.println("Dit is de laatste uitdaging. Toon je kennis van de onderdelen van Scrum");
		System.out.println("Je moet minimaal " + PASSING_SCORE + " van de " + TOTAL_QUESTIONS + " vragen goed beantwoorden om deze kamer te voltooien.");
		System.out.println("\nDruk op ENTER om te beginnen...");
	}

	@Override
	public void toonBeschrijving() {
		System.out.println("In deze kamer het eindspel. Pas als je hier doorheen komt, heb je gewonnen! Zoek uit wat TIA is!");
	}

	@Override
	protected ArrayList<TaakStrategie> initialiseren() {
		ArrayList<TaakStrategie> opdrachten = new ArrayList<>();

		// Vraag 1
		ArrayList<String> keuzes1 = new ArrayList<>();
		keuzes1.add("Technical Implementation Analysis");
		keuzes1.add("Transparency, Inspection, Adaptation");
		opdrachten.add(new MultiChoice(
				"Waar staat 'TIA' voor in de context van Scrum?",
				keuzes1, 2
		));

		// Vraag 2
		ArrayList<String> keuzes2 = new ArrayList<>();
		keuzes2.add("Alle teamleden hebben dezelfde fysieke werkplek");
		keuzes2.add("Het presenteren van feiten zoals ze zijn, waarbij iedereen dezelfde definitie van 'klaar' gebruikt");
		keuzes2.add("Alle code moet open source zijn");
		opdrachten.add(new MultiChoice(
				"Wat betekent 'Transparency' in Scrum?",
				keuzes2, 2
		));

		// Vraag 3
		ArrayList<String> keuzes3 = new ArrayList<>();
		keuzes3.add("Alleen de Scrum Master");
		keuzes3.add("Externe auditors");
		keuzes3.add("Iedereen in het Scrum Team");
		opdrachten.add(new MultiChoice(
				"Wie is verantwoordelijk voor 'Inspection' in Scrum?",
				keuzes3, 3
		));

		// Vraag 4
		ArrayList<String> keuzes4 = new ArrayList<>();
		keuzes4.add("Het aanpassen van het proces om beter te worden dan gisteren");
		keuzes4.add("Het aanpassen van de planning zodat alles altijd af komt");
		keuzes4.add("Het aanpassen van de requirements aan de wensen van het team");
		opdrachten.add(new MultiChoice(
				"Wat is het doel van 'Adaptation' in Scrum?",
				keuzes4, 1
		));

		// Vraag 5
		ArrayList<String> keuzes5 = new ArrayList<>();
		keuzes5.add("Dat alle beslissingen alleen door de managers worden genomen");
		keuzes5.add("Werken op basis van feiten, ervaring en bewijs in plaats van fictieve plannen");
		keuzes5.add("Dat alle werknemers even belangrijk zijn");
		keuzes5.add("Dat teams alleen experimenten doen");
		opdrachten.add(new MultiChoice(
				"Wat betekent 'empiricism' in de context van Scrum?",
				keuzes5, 2
		));


		return opdrachten;
	}

	@Override
	protected void toonMisluktBericht() {
		// super.toonMisluktBericht();
		System.out.println("\n==== HELAAS ====");
		// System.out.println("Je hebt " + score + " van de " + TOTAL_QUESTIONS + " vragen correct beantwoord, maar je had er minstens " + PASSING_SCORE + " nodig.");
		System.out.println("Het begrip van de drie pijlers van empiricism is essentieel voor Scrum:");
		System.out.println("- Transparency");
		System.out.println("- Inspection");
		System.out.println("- Adaptation ");
		System.out.println("\nHet empiricism-monster verschijnt! Je team verliest zich in chaos zonder deze fundamentele principes!");
		System.out.println("\nProbeer het nog eens, en onthoud TIA: Transparency, Inspection, Adaptation!");
	}

	@Override
	protected void toonSuccesBericht() {
		// super.toonSuccesBericht();
		System.out.println("\n==== GEFELICITEERD! ====");
		// System.out.println("Je hebt " + score + " van de " + TOTAL_QUESTIONS + " vragen correct beantwoord!");
		System.out.println("Je begrijpt de drie pijlers van empiricism in Scrum");
		System.out.println("============================");
	}
}
