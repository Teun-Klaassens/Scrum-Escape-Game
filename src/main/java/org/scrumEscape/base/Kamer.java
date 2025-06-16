package org.scrumEscape.base;

import org.scrumEscape.classes.Jokers.HintJoker;
import org.scrumEscape.classes.Jokers.KeyJoker;
import org.scrumEscape.classes.Monster.Monster;
import org.scrumEscape.classes.Speler;
import org.scrumEscape.classes.SpelerDAO;
import org.scrumEscape.classes.hints.HintFactory;
import org.scrumEscape.classes.taak.MultiChoice;
import org.scrumEscape.classes.taak.Puzzel;
import org.scrumEscape.interfaces.GameObserver;
import org.scrumEscape.interfaces.TaakStrategie;
import org.scrumEscape.classes.Voorwerpen.KamerInfo;
import org.scrumEscape.classes.Voorwerpen.KamerInfoStrings;
import org.scrumEscape.classes.Voorwerpen.Zwaard;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Kamer {
	// Kamer objecten
	private int kamerId;
	private boolean isAfgerond;

	// Spelers objecten
	private int huidigeTaakIndex;
	private int lastBehaaldTaakIndex;

	// Kamer objecten
	protected String kamerNaam;
	private GameObserver gameObserver;
	private ArrayList<TaakStrategie> taken = new ArrayList<>();
	private int huidigeTaak;
	private Monster monster;
	private boolean behaald;
	private Zwaard zwaard = new Zwaard();
	private boolean kickout = false;
	protected Assistant assistant;

	protected Speler speler;
	protected Connection conn;
	protected SpelerDAO spelerDAO;

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

		kickout = false;
		toonIntro();
		toonKamerInfo();
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

		// Check if the task is a puzzle and if it is completed
		if ((taak instanceof Puzzel)) {
			if (correct) {
				Puzzel puzzel = (Puzzel) taak;
				if (puzzel.isBehaald()) this.huidigeTaak++;
			}
		} else {
			if (correct) this.huidigeTaak++;
		}

		// After validating the answer
		if (!correct) {
			if(monster.isActief()){
				vraagJokerGebruik(gameObserver.getHintJoker(), gameObserver.getKeyJoker(), gameObserver.getScanner());
				Scanner scanner = gameObserver.getScanner();
				System.out.println("Het monster heeft aangevallen! Wil je het zwaard gebruiken om het monster te doden? (ja/nee)");
				String antwoord = scanner.nextLine().trim().toLowerCase();
				if (antwoord.equals("j") || antwoord.equals("ja")) {
					zwaard.attack(monster);
					updateSpeler();
						toonSuccesBericht();
						System.out.println("Je hebt de kamer behaald.");
						behaald = true;
						gameObserver.nextKamer();

				}
				else{
					System.out.println("Je hebt het zwaard niet gebruikt! Het monster heeft je aangevallen! ");
					System.out.println("Je bent de kamer uit gestuurd!");
					kickout = true;
					gameObserver.kickToLobby();
				}

			}
			else {
				toonMisluktBericht();
				vraagJokerGebruik(gameObserver.getHintJoker(), gameObserver.getKeyJoker(), gameObserver.getScanner());
				monster.toonImpediment();
				if ((taak instanceof Puzzel)) ((Puzzel) taak).toonHuidigeStuk();
			}
		} else {
			if (monster.isActief()) {
				monster.oplossen();
			}
			updateSpeler();
			if (this.huidigeTaak >= taken.size()) {
				toonSuccesBericht();
				System.out.println("Je hebt de kamer behaald.");
				behaald = true;
				p_register();
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
		while (!this.behaald && !kickout) {
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
		}
	}

	protected final int totalAantalTaken() {
		return this.taken.size();
	}

	protected final GameObserver getGameObserver() {
		return gameObserver;
	}

	protected void toonKamerInfo() {
		System.out.println("Wil je het KamerInfo voorwerp gebruiken? Het geeft je informatie over de kamer! (ja/nee)");
		System.out.println(" ");
		String antwoord = gameObserver.getScanner().nextLine().trim().toLowerCase();
		if (antwoord.equals("ja") || antwoord.equals("j")) {
			KamerInfo kamerInfo = new KamerInfo();
			kamerInfo.showMessage(KamerInfoStrings.getInfo(this.kamerNaam));
			System.out.println(" ");

		}
		else {
			System.out.println("Geen KamerInfo gebruikt.");
			System.out.println(" ");
		}
	}

	public abstract void toonIntro();

	public abstract void toonBeschrijving();

	protected abstract ArrayList<TaakStrategie> initialiseren();

	protected void toonMisluktBericht() {
		 System.out.println("Je hebt de vraag niet correct beantwoord.");
	}

	protected void toonSuccesBericht() {
		// System.out.println("Je hebt de vraag correct beantwoord.");
	}

	protected void p_register() {
		try {
			if (spelerDAO == null && conn != null) {
				spelerDAO = new SpelerDAO(conn);
			}
			if (spelerDAO != null && speler != null) {
				spelerDAO.voegProgressToe(speler.getNaam(), "kamer_voltooid", kamerNaam);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void toonMonster() {
		System.out.println("Monster is actief");
		monster.toonImpediment();
	}

	private void updateSpeler() {
		gameObserver.onPlayerUpdate();
	}

	// In Kamer.java
	public void vraagJokerGebruik(HintJoker hintJoker, KeyJoker keyJoker, Scanner scanner) {
		boolean hintJokerAvailable = hintJoker != null && !hintJoker.isUsed();
		boolean keyJokerAvailable = keyJoker != null && !keyJoker.isUsed();

		if (!hintJokerAvailable && !keyJokerAvailable) {
			System.out.println("Je hebt geen jokers meer over.");
		}
		else {
			System.out.println("Wil je een joker gebruiken? (ja/nee)");
			String antwoord = scanner.nextLine().trim().toLowerCase();
			if (!(antwoord.equals("ja") || antwoord.equals("j"))) {
				System.out.println("Geen jokers gebruikt.");
			} else {

				if (!hintJoker.isUsed()) {
					hintJoker.offerUse(this, scanner);
				}
				if (!keyJoker.isUsed()) {
					keyJoker.offerUse(this, scanner);
					if (keyJoker.isUsed()) {
						behaald = true;
					}
				}
			}
		}
	}

	public void setSpeler(Speler speler) {
		this.speler = speler;
	}

	public void setConnection(Connection conn) {
		this.conn = conn;
		this.spelerDAO = new SpelerDAO(conn);
	}
}
