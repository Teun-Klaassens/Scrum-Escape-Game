package org.scrumEscape.base;

import org.scrumEscape.classes.Monster;
import org.scrumEscape.classes.hints.HintFactory;
import org.scrumEscape.interfaces.IKamerState;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class Kamer {
	// Kamer objecten
	private int kamerId;
	private int kamerNaam;
	private boolean isAfgerond;

	// Spelers objecten
	private int huidigeTaakIndex;
	private int lastBehaaldTaakIndex;

	// Kamer objecten
	private Monster monster;
	private ArrayList<Monster> monsters;

	// Kamer functie's
	public void enterKamer() {

	}
	public void verlaatKamer(){}

	public void spawnMonster() {
		// Monster spawnen
	}
	public void killMonster() {
		// Monster doden
	}

	// Taken functie's
	public boolean geefAntwoord() {
		// Antwoord geven
		return true;
	}
	
	/**
	 * Vraagt of de speler een hint wil en toont deze indien gewenst
	 * @param scanner De scanner voor gebruikersinvoer
	 */
	public void biedHintAan(Scanner scanner) {
		System.out.println("Wil je een hint? (j/n)");
		String antwoord = scanner.nextLine().trim().toLowerCase();
		
		if (antwoord.equals("j") || antwoord.equals("ja")) {
			String hint = HintFactory.getHintText(this.getClass().getSimpleName());
			System.out.println("\n" + hint + "\n");
		}
	}
	
	/**
	 * Wordt aangeroepen wanneer een speler een fout antwoord geeft
	 * @param scanner De scanner voor gebruikersinvoer
	 */
	public void ongeldigAntwoordGegeven(Scanner scanner) {
		System.out.println("Dat is helaas niet het juiste antwoord.");
		biedHintAan(scanner);
	}

	public IKamerState getKamerState() {
		return null;
	}

	public abstract String getBeschrijving();
	public abstract void start();
}
