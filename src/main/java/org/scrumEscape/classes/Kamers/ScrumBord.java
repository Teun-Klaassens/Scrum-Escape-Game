package org.scrumEscape.classes.Kamers;

import org.scrumEscape.base.Kamer;
import org.scrumEscape.classes.Jokers.HintJoker;
import org.scrumEscape.classes.Jokers.HintJokerGebruiken;
import org.scrumEscape.classes.Jokers.KeyJoker;
import org.scrumEscape.classes.Monster.BoardMonster;
import org.scrumEscape.classes.taak.MultiChoice;
import org.scrumEscape.interfaces.GameObserver;
import org.scrumEscape.interfaces.TaakStrategie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ScrumBord extends Kamer implements HintJokerGebruiken {

	public ScrumBord(GameObserver gameObserver) {
		super("Scrum Bord", new BoardMonster(), gameObserver);
	}

	@Override
	protected ArrayList<TaakStrategie> initialiseren() {
		ArrayList<TaakStrategie> taken = new ArrayList<>();

		taken.add(new MultiChoice(
				"1. Welke taak hoort bij de user story: 'Als speler wil ik hints krijgen via een willekeurige hintprovider'?",
				new ArrayList<>(Arrays.asList(
						"1. Laat kamers alleen met HintProvider werken",
						"2. Voeg een directe HintProvider toe aan elke kamer",
						"3. Implementeer FunnyHintProvider en HelpHintProvider",
						"4. Maak HintFactory die willekeurig tussen hintproviders kiest"
				)),
				4
		));

		taken.add(new MultiChoice(
				"2. Welke taak hoort bij de user story: 'Als speler wil ik verschillende soorten hints kunnen ontvangen'?",
				new ArrayList<>(Arrays.asList(
						"1. Gebruik if-statements in kamers voor hintselectie",
						"2. Maak HintFactory",
						"3. Implementeer FunnyHintProvider en HelpHintProvider",
						"4. Laat kamers direct met FunnyHintProvider werken"
				)),
				3
		));

		taken.add(new MultiChoice(
				"3. Welke taak hoort bij de user story: 'Als ontwikkelaar wil ik dat kamers losstaan van specifieke hintimplementaties'?",
				new ArrayList<>(Arrays.asList(
						"1. Laat kamers alleen met HintProvider werken",
						"2. Voeg FunnyHintProvider rechtstreeks toe aan kamer",
						"3. Gebruik een concrete klasse in kamer",
						"4. Maak hinttekst direct in kamer"
				)),
				1
		));

		taken.add(new MultiChoice(
				"4. Welk van deze matches is onjuist op het Scrum-bord?",
				new ArrayList<>(Arrays.asList(
						"1. Story: Voorwerpen gebruiken → Taak: Zwaard implementeert attack()",
						"2. Story: Willekeurige hintprovider → Taak: KeyJoker\"",
						"3. Story: Jokers gebruiken → Taak: KeyJoker alleen in Daily Scrum",
						"4. Story: Hints tonen → Taak: HelpHintProvider"
				)),
				2
		));

		taken.add(new MultiChoice(
				"5. Welke combinatie is correct gestructureerd op het Scrum-bord?",
				new ArrayList<>(Arrays.asList(
						"1. Epic: Hint Systeem → Story: Willekeurige hintprovider → Taak: HintFactory maken",
						"2. Epic: Joker Functionaliteit → Story: Willekeurige hintprovider → Taak: HintFactory maken",
						"3. Epic: Interactieve Objecten → Story: Fun hints → Taak: FunnyHintProvider",
						"4. Epic: Joker Functionaliteit → Story: Verschillende voorwerpen → Taak: HelpHintProvider"
				)),
				1
		));

		return taken;
	}

	@Override
	public void toonIntro() {

		System.out.println("\n=== Welkom bij de Scrum Bord Kamer ===");
		System.out.println("Je gaat een Scrum-bord inrichten met epics, user stories en taken rondom hintfunctionaliteit, objectinteractie en jokers.");
		System.out.println("Druk op ENTER om te beginnen...");
		System.out.println("Een incorrect anwoord roept het 'BOARD' monster op.");
		Scanner scanner = this.getGameObserver().getScanner();
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
		System.out.println("Test je kennis van het structureren van een Scrum-bord met behulp van meerkeuzevragen.");
	}


	@Override
	protected void toonSuccesBericht() {
		System.out.println("\nGefeliciteerd! Je hebt het Scrum-bord succesvol ingericht.");
	}

	@Override
	protected void toonMisluktBericht() {
		System.out.println("\nDat klopt niet helemaal. Bekijk goed welke items logisch onder elkaar vallen en probeer het opnieuw.");
	}


	@Override
	public void useHintJoker() {
		String hint = org.scrumEscape.classes.hints.JokerHints.getHint("ScrumBord");
		System.out.println("\nHint: " + hint + "\n");
	}

	@Override
	public KeyJoker getKeyJoker() {
		return null;
	}

	@Override
	public HintJoker getHintJoker() {
		return this.getGameObserver().getHintJoker();
	}


}
