package org.scrumEscape.classes.taak;

import org.scrumEscape.controllers.MenuController;
import org.scrumEscape.interfaces.TaakStrategie;

public class Vraag implements TaakStrategie {
	final String vraag;
	final String antwoord;
	boolean behaald;

	public Vraag(String vraag, String antwoord, boolean behaald) {
		this.vraag = vraag;
		this.antwoord = antwoord;
		this.behaald = behaald;
	}

	@Override
	public void toon() {
		System.out.println(MenuController.BOLD + MenuController.YELLOW);
		System.out.println(vraag);
		System.out.println("Geef je antwoord:");
		System.out.println(MenuController.RESET);
	}

	@Override
	public boolean valideer(String antwoord) {
		return this.antwoord.equals(antwoord);
	}

	@Override
	public void ongeldigAntwoord() {
		System.out.print(MenuController.BOLD + MenuController.WHITE + MenuController.BG_RED);
		System.out.println("Dat is niet het juiste antwoord. Het correcte antwoord is: " + this.antwoord);
		System.out.print(MenuController.RESET);

		toon();
	}

	@Override
	public void geldigAntwoord() {
		System.out.print(MenuController.BOLD + MenuController.GREEN);
		System.out.println("Dat is het juiste antwoord!");
		System.out.print(MenuController.RESET);
	}

	@Override
	public boolean isBehaald() {
		return behaald;
	}
}