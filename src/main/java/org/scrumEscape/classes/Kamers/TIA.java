package org.scrumEscape.classes.Kamers;

import org.scrumEscape.base.Kamer;
import org.scrumEscape.base.Assistant;
import org.scrumEscape.classes.Monster;
import org.scrumEscape.classes.taak.MultiChoice;
import org.scrumEscape.interfaces.GameObserver;
import org.scrumEscape.interfaces.TaakStrategie;


import java.util.ArrayList;

public class TIA extends Kamer {
	private final int PASSING_SCORE = 4;
	private final int TOTAL_QUESTIONS = 5;

	public TIA(GameObserver gameObserver) {
		super("TIA", new Monster(), gameObserver);
		
		String[] hints = {
			"TIA staat voor Testing in Agile",
			"Denk aan de verschillende testvormen binnen Scrum",
			"In Scrum moet testen geïntegreerd worden in elke sprint"
		};
		
		String[] educationalTools = {
			"In Scrum is het belangrijk om 'Definition of Done' te hebben.",
			"Test-Driven Development (TDD) is een populaire aanpak in Agile",
			"Geautomatiseerde tests zijn essentieel."
		};
		
		String[] motivationalMessages = {
			"Je bent bij de laatste uitdaging!",
			"Jouw kennis van Scrum is indrukwekkend!",
			"Deze laatste uitdaging kun je zeker aan!"
		};
		
		this.assistant = new   Assistant("SCRUM MASTER ASSISTENT", hints, educationalTools, motivationalMessages);
	}

	@Override
	public void toonIntro() {
		System.out.println("\n==== WELKOM BIJ DE FINALE KAMER: TIA ====");
		System.out.println("Je moet minimaal " + PASSING_SCORE + " van de " + TOTAL_QUESTIONS + " vragen goed beantwoorden om deze kamer te voltooien.");
		System.out.println("\nTIP: Bij elke vraag kun je de SCRUM MASTER ASSISTENT activeren door de laatste optie te kiezen.");
		System.out.println("De assistent geeft je een hint, educatief hulpmiddel en motiverende boodschap in één keer!");
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
		MultiChoice vraag1 = new MultiChoice(
				"Waar staat 'TIA' voor in de context van Scrum?",
				keuzes1, 2
		);
		vraag1.addAssistantOption();
		opdrachten.add(vraag1);
	
		// Vraag 2
		ArrayList<String> keuzes2 = new ArrayList<>();
		keuzes2.add("Alle teamleden hebben dezelfde fysieke werkplek");
		keuzes2.add("Het presenteren van feiten zoals ze zijn, waarbij iedereen dezelfde definitie van 'klaar' gebruikt");
		keuzes2.add("Alle code moet open source zijn");
		MultiChoice vraag2 = new MultiChoice(
				"Wat betekent 'Transparency' in Scrum?",
				keuzes2, 2
		);
		vraag2.addAssistantOption();
		opdrachten.add(vraag2);
	
		// Vraag 3
		ArrayList<String> keuzes3 = new ArrayList<>();
		keuzes3.add("Alleen de Scrum Master");
		keuzes3.add("Externe auditors");
		keuzes3.add("Iedereen in het Scrum Team");
		MultiChoice vraag3 = new MultiChoice(
				"Wie is verantwoordelijk voor 'Inspection' in Scrum?",
				keuzes3, 3
		);
		vraag3.addAssistantOption();
		opdrachten.add(vraag3);
	
		// Vraag 4
		ArrayList<String> keuzes4 = new ArrayList<>();
		keuzes4.add("Het aanpassen van het proces om beter te worden dan gisteren");
		keuzes4.add("Het aanpassen van de planning zodat alles altijd af komt");
		keuzes4.add("Het aanpassen van de requirements aan de wensen van het team");
		MultiChoice vraag4 = new MultiChoice(
				"Wat is het doel van 'Adaptation' in Scrum?",
				keuzes4, 1
		);
		vraag4.addAssistantOption();
		opdrachten.add(vraag4);
	
		// Vraag 5
		ArrayList<String> keuzes5 = new ArrayList<>();
		keuzes5.add("Dat alle beslissingen alleen door de managers worden genomen");
		keuzes5.add("Werken op basis van feiten, ervaring en bewijs in plaats van fictieve plannen");
		keuzes5.add("Dat alle werknemers even belangrijk zijn");
		keuzes5.add("Dat teams alleen experimenten doen");
		MultiChoice vraag5 = new MultiChoice(
				"Wat betekent 'empiricism' in de context van Scrum?",
				keuzes5, 2
		);
		vraag5.addAssistantOption();
		opdrachten.add(vraag5);

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
		
		System.out.println("\n-------------------------------------------------");
		System.out.println("Vraag wordt opnieuw getoond:");
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
