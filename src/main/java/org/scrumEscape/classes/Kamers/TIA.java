package org.scrumEscape.classes.Kamers;

import org.scrumEscape.base.Kamer;
import org.scrumEscape.classes.Monster;
import org.scrumEscape.interfaces.GameObserver;
import org.scrumEscape.interfaces.TaakStrategie;

import java.util.ArrayList;

import org.scrumEscape.classes.Monster;

import java.util.Scanner;

public class TIA extends Kamer {
    private Scanner scanner;
    private final Monster empiricismMonster;
    private int score = 0;
    private boolean gameCompleted = false;
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
	    System.out.println("\nVraag 1: Waar staat 'TIA' voor in de context van Scrum?");
	    System.out.println("1) Technical Implementation Analysis");
	    System.out.println("2) Transparency, Inspection, Adaptation");

	    int antwoord = getNumberInput(2);
	    if (antwoord == 2) {
		    System.out.println("Correct!");
		    score++;
	    } else {
		    System.out.println("Dat is niet correct.");
	    }

	    System.out.println("\nVraag 2: Wat betekent 'Transparency' in Scrum?");
	    System.out.println("1) Alle teamleden hebben dezelfde fysieke werkplek");
	    System.out.println("2) Het presenteren van feiten zoals ze zijn, waarbij iedereen dezelfde definitie van 'klaar' gebruikt");
	    System.out.println("3) Alle code moet open source zijn");

	    antwoord = getNumberInput(3);
	    if (antwoord == 2) {
		    System.out.println("Correct!");
		    score++;
	    } else {
		    System.out.println("Dat is niet correct.");
	    }


	    System.out.println("\nVraag 3: Wie is verantwoordelijk voor 'Inspection' in Scrum?");
	    System.out.println("1) Alleen de Scrum Master");
	    System.out.println("2) Externe auditors");
	    System.out.println("3) Iedereen in het Scrum Team");

	    antwoord = getNumberInput(3);
	    if (antwoord == 3) {
		    System.out.println("Correct!");
		    score++;
	    } else {
		    System.out.println("Dat is niet correct.");
	    }

	    System.out.println("\nVraag 4: Wat is het doel van 'Adaptation' in Scrum?");
	    System.out.println("1) Het aanpassen van het proces om beter te worden dan gisteren");
	    System.out.println("2) Het aanpassen van de planning zodat alles altijd af komt");
	    System.out.println("3) Het aanpassen van de requirements aan de wensen van het team");

	    antwoord = getNumberInput(4);
	    if (antwoord == 1) {
		    System.out.println("Correct!");
		    score++;
	    } else {
		    System.out.println("Dat is niet correct.");
	    }


	    System.out.println("\nVraag 5: Wat betekent 'empiricism' in de context van Scrum?");
	    System.out.println("1) Dat alle beslissingen alleen door de managers worden genomen");
	    System.out.println("2) Werken op basis van feiten, ervaring en bewijs in plaats van fictieve plannen");
	    System.out.println("3) Dat alle werknemers even belangrijk zijn");
	    System.out.println("4) Dat teams alleen experimenten doen");

	    antwoord = getNumberInput(4);
	    if (antwoord == 2) {
		    System.out.println("Correct!");
		    score++;
	    } else {
		    System.out.println("Dat is niet correct.");
	    }

	    return null;
    }

    @Override
    protected void toonMisluktBericht() {
       // super.toonMisluktBericht();
	    System.out.println("\n==== HELAAS ====");
	    System.out.println("Je hebt " + score + " van de " + TOTAL_QUESTIONS + " vragen correct beantwoord, maar je had er minstens " + PASSING_SCORE + " nodig.");
	    System.out.println("Het begrip van de drie pijlers van empiricism is essentieel voor Scrum:");
	    System.out.println("- Transparency");
	    System.out.println("- Inspection");
	    System.out.println("- Adaptation ");
	    System.out.println("\nHet empiricism-monster verschijnt! Je team verliest zich in chaos zonder deze fundamentele principes!");
	    empiricismMonster.attack("TIA");
	    System.out.println("\nProbeer het nog eens, en onthoud TIA: Transparency, Inspection, Adaptation!");
    }

    @Override
    protected void toonSuccesBericht() {
       // super.toonSuccesBericht();
	    System.out.println("\n==== GEFELICITEERD! ====");
	    System.out.println("Je hebt " + score + " van de " + TOTAL_QUESTIONS + " vragen correct beantwoord!");
	    System.out.println("Je begrijpt de drie pijlers van empiricism in Scrum");
	    System.out.println("============================");
    }
}
