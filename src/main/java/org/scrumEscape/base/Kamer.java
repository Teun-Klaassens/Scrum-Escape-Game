package org.scrumEscape.base;

import org.scrumEscape.classes.Monster;
import org.scrumEscape.classes.Taak.MultiChoice;
import org.scrumEscape.classes.Taak.Puzzel;
import org.scrumEscape.interfaces.GameObserver;
import org.scrumEscape.interfaces.TaakStrategie;

import java.util.ArrayList;

public abstract class Kamer {
	// Kamer objecten
	private String kamerNaam;
	private GameObserver gameObserver;
	private ArrayList<TaakStrategie> taken = new ArrayList<>();
	private int huidigeTaak;
	private Monster monster;
	private boolean behaald;

	public Kamer(String kamerNaam, Monster monster, GameObserver gameObserver){
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
		toonTaak(taken.get(this.huidigeTaak), this.huidigeTaak);
	}
	public final void toonVoortgang(){
		System.out.println("=================================================");
		System.out.println("Je hebt momenteel " + (this.huidigeTaak+1) + " van de " + this.taken.size() + " vragen correct beantwoord.");
		System.out.println("=================================================");
	}
	public final void valideerAntwoord(String text) {
		TaakStrategie taak = taken.get(this.huidigeTaak);
		boolean correct = taak.valideer(text);

		// Check if the task is a puzzle and if it is completed
		if ((taak instanceof Puzzel)) {
 			if (correct) {
				 Puzzel puzzel = (Puzzel) taak;
 				 if(puzzel.isBehaald())
					this.huidigeTaak++;
			}
		}
 		else{
			if (correct) {
 				this.huidigeTaak++;
			}
		}

		 // After validating the answer
		if(!correct) {
			toonMisluktBericht();
			toonMonster();
		}else{
			if (monster.isActive()) {
				System.out.println("Je hebt de monster verslagen.");
				monster.oplossen();
			}
			updateSpeler();
			if (this.huidigeTaak >= taken.size()) {
				toonSuccesBericht();
				System.out.println("Je hebt de kamer behaald.");
				behaald = true;
				gameObserver.onKamerBehaald();
				gameObserver.nextKamer();
			} else {
				toonTaak(taken.get(this.huidigeTaak), this.huidigeTaak);
			}
		}
	}

	protected final void toonTaak(TaakStrategie taak,int positie){
		System.out.println("=================================================");
		System.out.println("Opdracht " + (positie+1) + ":");
		taak.toon();

		// Loop until the task is completed
  		while(!this.behaald) {
			if(taak instanceof MultiChoice) {
				// Handle multi-choice question
				int choice = gameObserver.getScanner().nextInt();
				gameObserver.getScanner().nextLine(); // Clear buffer
				valideerAntwoord(String.valueOf(choice));
			}
			else if (taak instanceof Puzzel) {
				// Handle puzzle question
				String choice = gameObserver.getScanner().nextLine();
				valideerAntwoord(choice);
			}
			else {
				// Handle multi-choice question
				String choice = gameObserver.getScanner().nextLine();
				valideerAntwoord(String.valueOf(choice));
			}
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

	protected void toonMisluktBericht(){
		System.out.println("Je hebt de vraag niet correct beantwoord.");
	}
	protected void toonSuccesBericht(){
		System.out.println("Je hebt de vraag correct beantwoord.");
	}

	private void toonMonster() {
		monster.toonImpediment();
	}
	private void updateSpeler() {
		gameObserver.onPlayerUpdate();
	}

}
