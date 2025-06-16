package org.scrumEscape.classes.Kamers;

import org.scrumEscape.base.Kamer;
import org.scrumEscape.classes.Monster;
import org.scrumEscape.classes.taak.MultiChoice;
import org.scrumEscape.interfaces.GameObserver;
import org.scrumEscape.interfaces.TaakStrategie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class SprintPlanning extends Kamer {

	public SprintPlanning(GameObserver gameObserver) {
		super("Sprint Planning", new Monster(), gameObserver);
	}

	@Override
	protected ArrayList<TaakStrategie> initialiseren() {
		ArrayList<TaakStrategie> opdrachten = new ArrayList<>();

		// Keuzen voor de vragen 1
		ArrayList<String> keuzes1 = new ArrayList<>();
		keuzes1.add("Taken toewijzen aan individuele teamleden");
		keuzes1.add("De Sprint Goal vaststellen");
		keuzes1.add("De snelheid van het team evalueren");
		keuzes1.add("De Sprint Retrospective organiseren");
		opdrachten.add(new MultiChoice(
				"Wat is het primaire doel van een Sprint Planning?",
				keuzes1, 2
		));

		// Keuze voor de vragen 2
		ArrayList<String> keuzes2 = new ArrayList<>(Arrays.asList(
				"De Product Owner",
				"De Scrum Master",
				"Het Development Team",
				"Alle bovenstaande"
		));
		opdrachten.add(new MultiChoice(
				"Wie moet aanwezig zijn bij een Sprint Planning?",
				keuzes2, 4
		));

		// Keuze voor de vragen 3
		ArrayList<String> keuzes3 = new ArrayList<>(Arrays.asList(
				"Story Points",
				"Uren",
				"Dagen",
				"Euro's"
		));
		opdrachten.add(new MultiChoice(
				"Welke schattingsmethode wordt vaak gebruikt in Scrum?",
				keuzes3,
				1
		));

		// Keuze voor de vragen 4
		ArrayList<String> keuzes4 = new ArrayList<>(Arrays.asList(
				"De hoeveelheid werk die een team kan voltooien in een sprint",
				"De snelheid waarmee een team werkt gedurende een dag",
				"Het totaal aantal uren dat beschikbaar is voor een sprint",
				"De maximale grootte van het team"
		));
		opdrachten.add(new MultiChoice(
				"Wat is de definitie van 'Velocity' in Scrum?",
				keuzes4, 1
		));

		// Keuze voor de vragen 5
		ArrayList<String> keuzes5 = new ArrayList<>(Arrays.asList(
				"Zorgen dat het team niet overwerkt",
				"Het team beschermen tegen veranderende eisen tijdens de sprint",
				"De taken tijdens de sprint verdelen",
				"De Scrum ceremoniën plannen"
		));
		opdrachten.add(new MultiChoice(
				"Wat is het doel van het vastleggen van de Sprint Backlog?",
				keuzes5, 2
		));

		return opdrachten;
	}

	@Override
	public void toonIntro() {
		System.out.println("\n=== Welkom bij de Sprint Planning ===");
		System.out.println("Je krijgt 5 meerkeuze vragen over Sprint Planning.");
		System.out.println("Je moet minstens " + this.totalAantalTaken() + " vragen correct beantwoorden om de Scope Creep Monster te vermijden!");
		System.out.println("Druk op ENTER om te beginnen...");
		Scanner scanner = this.getGameObserver().getScanner();
		// Wacht op de gebruiker om te beginnen, bijvoorbeeld door op ENTER te drukken
		boolean entered = false;
		while (!entered) {
			String input = scanner.nextLine();
			if (input.isEmpty()) {
				entered = true;
			}
		}
	}

	@Override
	public void toonBeschrijving() {
		System.out.println("Test je kennis van Sprint Planning met meerkeuze vragen. Onvoldoende kennis triggert het monster 'Scope Creep'.");
	}

	@Override
	protected void toonSuccesBericht() {
		System.out.println("\nGefeliciteerd! Je hebt voldoende kennis van Sprint Planning aangetoond.");
		System.out.println("Je team kan nu een succesvolle Sprint Planning uitvoeren en het Scope Creep Monster blijft op afstand!");
	}

	@Override
	protected void toonMisluktBericht() {
		System.out.println("\nSCOPE CREEP MONSTER VERSCHIJNT!");
		System.out.println("Je kennis van Sprint Planning is onvoldoende! Het Scope Creep Monster valt je aan.");
		System.out.println("Het team heeft moeite om de sprint goed te plannen en wordt overweldigd door veranderende eisen!");
	}
}