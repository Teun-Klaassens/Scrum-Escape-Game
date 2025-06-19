package org.scrumEscape.classes.taak;

import org.scrumEscape.controllers.MenuController;
import org.scrumEscape.interfaces.TaakStrategie;

import java.util.ArrayList;

public class MultiChoice implements TaakStrategie {
	final String vraag;
	final ArrayList<String> keuzes;
	final int antwoord;
	private boolean behaald;
	private boolean hasAssistantOption;
	private static final String ASSISTANT_OPTION = "Vraag de assistent om hulp";

	public MultiChoice(String vraag, ArrayList<String> keuzes, int antwoord) {
		this.vraag = vraag;
		if (antwoord < 1) {
			this.antwoord = 1;
		} else this.antwoord = Math.min(antwoord, keuzes.size());

		this.keuzes = keuzes;
		this.behaald = false;
		this.hasAssistantOption = false;
	}

	public void addAssistantOption() {
		this.hasAssistantOption = true;
	}

	public boolean hasAssistantOption() {
		return hasAssistantOption;
	}

	@Override
	public void toon() {
		System.out.println(MenuController.BOLD + MenuController.YELLOW);
		System.out.println(vraag);
		System.out.println(MenuController.RESET);


		System.out.println(MenuController.BOLD + MenuController.BG_YELLOW + MenuController.BLACK);
		System.out.println("╔═════════════════════════════════════════╗");
		System.out.println("\n✅ Kies het juiste antwoord:");
		for (int i = 0; i < keuzes.size(); i++) {
			System.out.println("(" + (i + 1) + ")" + " : " + keuzes.get(i));
		}
		System.out.println("╚═════════════════════════════════════════╝");
		System.out.println(MenuController.RESET);

		if (hasAssistantOption) {
			int assistantOptionNumber = keuzes.size() + 1;
			System.out.println("(" + assistantOptionNumber + ")" + " : " + ASSISTANT_OPTION);
		}
	}

	@Override
	public boolean valideer(String antwoord) {
		if (hasAssistantOption && String.valueOf(keuzes.size() + 1).equalsIgnoreCase(antwoord)) {
			return false;
		}

		if (String.valueOf(this.antwoord).equalsIgnoreCase(antwoord)) {
			this.behaald = true;
			return true;
		}
		return false;
	}


	public boolean isAssistantRequest(String answer) {
		return hasAssistantOption && String.valueOf(keuzes.size() + 1).equalsIgnoreCase(answer);
	}

	@Override
	public void ongeldigAntwoord() {
		System.out.print(MenuController.BOLD + MenuController.BG_RED);
		System.out.println("Helaas, dat is niet juist. Het juiste antwoord was: " + antwoord);
		toon();
		System.out.print(MenuController.RESET);
	}

	@Override
	public void geldigAntwoord() {
		System.out.print(MenuController.BOLD + MenuController.BG_GREEN);
		System.out.println("Correct! Goed gedaan.");
		System.out.print(MenuController.RESET);
	}

	@Override
	public boolean isBehaald() {
		return behaald;
	}
}
