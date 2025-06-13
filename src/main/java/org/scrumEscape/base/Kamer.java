package org.scrumEscape.base;

import org.scrumEscape.classes.Monster;
import org.scrumEscape.classes.hints.HintFactory;
import org.scrumEscape.classes.taak.MultiChoice;
import org.scrumEscape.classes.taak.Puzzel;
import org.scrumEscape.interfaces.GameObserver;
import org.scrumEscape.interfaces.TaakStrategie;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class Kamer {
	// Kamer objecten
	private String kamerNaam;
	private GameObserver gameObserver;
	private ArrayList<TaakStrategie> taken = new ArrayList<>();
	private int huidigeTaak;
	private Monster monster;
	private boolean behaald;
	protected Assistant assistant;

	public Kamer(String kamerNaam, Monster monster, GameObserver gameObserver) {
		this.kamerNaam = kamerNaam;
		this.monster = monster;
		this.gameObserver = gameObserver;
		this.taken = initialiseren();
	}

	public final void start() {
		if (behaald) {
			System.out.println("=================================================");
			System.out.println("Kamer: " + kamerNaam);
			System.out.println("Is al behaald.!!");
			System.out.println("=================================================");
			return;
		}
		toonIntro();
		toonBeschrijving();
		toonVoortgang();
		toonTaak(taken.get(this.huidigeTaak), this.huidigeTaak, true);
	}

	public final void toonVoortgang() {
		System.out.println("=================================================");
		System.out.println("Je hebt momenteel " + this.huidigeTaak + " van de " + this.taken.size() + " vragen correct beantwoord.");
		System.out.println("=================================================");
	}

	public final void valideerAntwoord(String text) {
		if (text == null || text.isBlank()) return;
		int previousTaak = this.huidigeTaak;
		TaakStrategie taak = taken.get(this.huidigeTaak);
		boolean correct = taak.valideer(text);

		if ((taak instanceof Puzzel)) {
			if (correct) {
				Puzzel puzzel = (Puzzel) taak;
				if (puzzel.isBehaald()) this.huidigeTaak++;
			}
		} else {
			if (correct) this.huidigeTaak++;
		}

		if (!correct) {
			toonMonster();
			toonMisluktBericht();
			if ((taak instanceof Puzzel)) ((Puzzel) taak).toonHuidigeStuk();
			else if (this.getClass().getSimpleName().equals("TIA")) {
				taak.toon();
			}
		} else {
			if (monster.isActive()) {
				System.out.println("Je hebt de monster verslagen.");
				monster.oplossen();
			}
			updateSpeler();
			if (this.huidigeTaak >= taken.size()) {
				toonSuccesBericht();
				System.out.println("Je hebt de kamer behaald.");
				behaald = true;
				gameObserver.onKamerBehaald(this);
				
				if (!this.getClass().getSimpleName().equals("TIA")) {
					gameObserver.nextKamer();
				} else {
					gameObserver.nextKamer();
					return;
				}
			} else {
				toonTaak(taken.get(this.huidigeTaak), this.huidigeTaak, previousTaak != this.huidigeTaak);
			}
		}
	}


	public void biedHintAan(Scanner scanner) {
		System.out.println("Wil je een hint? (j/n)");
		String antwoord = scanner.nextLine().trim().toLowerCase();

		if (antwoord.equals("j") || antwoord.equals("ja")) {
			String hint = HintFactory.getHintText(this.getClass().getSimpleName());
			System.out.println("\n" + hint + "\n");
		}
	}


	public boolean activateAssistant(Scanner scanner) {
		if (assistant == null) {
			System.out.println("Er is geen assistent beschikbaar in deze kamer.");
			return false;
		}
		
		System.out.println("\n=== " + assistant.getName() + " GEACTIVEERD ===");
		System.out.println("\n🔍 HINT: " + assistant.getHint());
		System.out.println("\n📚 EDUCATIEF HULPMIDDEL: " + assistant.getEducationalTool());
		System.out.println("\n💪 MOTIVERENDE BOODSCHAP: " + assistant.getMotivationalMessage());
		System.out.println("\nDruk op ENTER om door te gaan...");
		scanner.nextLine();
		return true;
	}

	/**
	 * Wordt aangeroepen wanneer een speler een fout antwoord geeft
	 *
	 * @param scanner De scanner voor gebruikersinvoer
	 */
	public void ongeldigAntwoordGegeven(Scanner scanner) {
		System.out.println("Dat is helaas niet het juiste antwoord.");
		biedHintAan(scanner);
	}

	protected final void toonTaak(TaakStrategie taak, int positie, boolean toonOpdrachtHeader) {
		if (toonOpdrachtHeader) {
			System.out.println("=================================================");
			System.out.println("Opdracht " + (positie + 1) + ":");
		}
		taak.toon();

		// Loop until the task is completed
		while (!this.behaald) {
			if (!gameObserver.getScanner().hasNext()) continue;

			if (taak instanceof MultiChoice) {
				// Handle multi-choice question
				int choice = gameObserver.getScanner().nextInt();
				gameObserver.getScanner().nextLine(); // Clear buffer
				
				// Check if the assistant option was selected
				MultiChoice multiChoice = (MultiChoice) taak;
				if (multiChoice.isAssistantRequest(String.valueOf(choice))) {
					// Activate assistant and show question again
					activateAssistant(gameObserver.getScanner());
					taak.toon();
					continue;
				}
				
				valideerAntwoord(String.valueOf(choice));
			} else if (taak instanceof Puzzel) {
				// Handle puzzle question
				String choice = gameObserver.getScanner().nextLine();
				valideerAntwoord(choice);
			} else {
				// Handle multi-choice question
				String choice = gameObserver.getScanner().nextLine();
				valideerAntwoord(String.valueOf(choice));
			}

			// Clear the scanner buffer
			//  gameObserver.getScanner().nextLine();
		}
	}

	protected final int totalAantalTaken() {
		return this.taken.size();
	}

	protected final GameObserver getGameObserver() {
		return gameObserver;
	}

	public abstract void toonIntro();

	public abstract void toonBeschrijving();

	protected abstract ArrayList<TaakStrategie> initialiseren();

	protected void toonMisluktBericht() {
		// System.out.println("Je hebt de vraag niet correct beantwoord.");
	}

	protected void toonSuccesBericht() {
		// System.out.println("Je hebt de vraag correct beantwoord.");
	}

	private void toonMonster() {
		System.out.println("Monster is actief");
		monster.toonImpediment();
	}

	private void updateSpeler() {
		gameObserver.onPlayerUpdate();
	}
}
