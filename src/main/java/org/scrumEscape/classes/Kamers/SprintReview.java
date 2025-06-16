package org.scrumEscape.classes.Kamers;

import org.scrumEscape.base.Kamer;
import org.scrumEscape.classes.Jokers.HintJoker;
import org.scrumEscape.classes.Jokers.KeyJoker;
import org.scrumEscape.classes.Monster.Monster;
import org.scrumEscape.classes.Monster.ReviewMonster;
import org.scrumEscape.classes.Monster.ScopeCreep;
import org.scrumEscape.classes.taak.MultiChoice;
import org.scrumEscape.interfaces.GameObserver;
import org.scrumEscape.interfaces.TaakStrategie;
import org.scrumEscape.classes.Jokers.HintJokerGebruiken;
import org.scrumEscape.classes.Jokers.KeyJokerGebruiken;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class SprintReview extends Kamer implements HintJokerGebruiken, KeyJokerGebruiken {

    public SprintReview(GameObserver gameObserver) {
        super("Sprint Review", new ReviewMonster(), gameObserver);
    }

    @Override
    protected ArrayList<TaakStrategie> initialiseren() {
        ArrayList<TaakStrategie> opdrachten = new ArrayList<>();

        opdrachten.add(new MultiChoice(
                "1. Een stakeholder zegt: 'De gebruikers vonden het inlogproces verwarrend.' Wat is de beste eerste reactie hierop?",
                new ArrayList<>(Arrays.asList(
                        "1. Direct een nieuwe user story aanmaken",
                        "2. De feedback negeren, want het werkte technisch goed",
                        "3. Vragen om verduidelijking en voorbeelden",
                        "4. Meteen de sprint aanpassen"
                )),
                3
        ));

        opdrachten.add(new MultiChoice(
                "2. Een stakeholder meldt: 'Het logo staat niet op de homepage.' Wat is de juiste beoordeling van deze feedback?",
                new ArrayList<>(Arrays.asList(
                        "1. Hoogste prioriteit: merkidentiteit is cruciaal",
                        "2. Lage prioriteit: het beïnvloedt de werking niet",
                        "3. Geen actie: stakeholders mogen geen ontwerpfeedback geven",
                        "4. Sla dit over tot de volgende release"
                )),
                2
        ));

        opdrachten.add(new MultiChoice(
                "3. De laadtijd van pagina's is traag bij mobiel gebruik. Wat zegt dit over de impact?",
                new ArrayList<>(Arrays.asList(
                        "1. Technisch detail, geen actie nodig",
                        "2. Grote impact op gebruikerservaring, verdient aandacht",
                        "3. Stakeholders overdrijven, alleen desktop is belangrijk",
                        "4. Laadtijd staat los van Sprint Review"
                )),
                2
        ));

        opdrachten.add(new MultiChoice(
                "4. Welke feedback is het meest geschikt om als nieuw backlog-item op te nemen?",
                new ArrayList<>(Arrays.asList(
                        "1. Ik hou niet van de kleuren van de knoppen",
                        "2. Gebruikers raken hun data kwijt als ze op ‘vorige’ klikken",
                        "3. De teamnaam op het dashboard voelt niet persoonlijk",
                        "4. We willen binnenkort een donkergroene achtergrond"
                )),
                2
        ));

        opdrachten.add(new MultiChoice(
                "5. Wat moet je doen met verzamelde feedback na de Sprint Review?",
                new ArrayList<>(Arrays.asList(
                        "1. Meteen in de sprint opnemen",
                        "2. Weggooien als het niet van de Product Owner komt",
                        "3. Bespreken met het team en de PO voor mogelijke opname in de backlog",
                        "4. Direct verwerken in de Definition of Done"
                )),
                3
        ));

        return opdrachten;
    }

    @Override
    public void toonIntro() {
        System.out.println("== Welkom bij de Sprint Review! ==");
        System.out.println("Je krijgt een opdracht om de resultaten van de sprint te presenteren aan de belanghebbenden.");
        System.out.println("Beantwoord de vragen correct om verder te gaan.");
        System.out.println("Een incorrect antwoord roept het 'Review' monster op.");

        System.out.println("Druk op ENTER om te beginnen...");
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
        System.out.println("Je moet feedback van stakeholders interpreteren en de impact ervan inschatten.");
    }

    @Override
    public void useHintJoker() {
        String hint = org.scrumEscape.classes.hints.JokerHints.getHint("Sprint Review");
        System.out.println("\nHint: " + hint + "\n");

    }

    @Override
    public void useKeyJoker() {
        getGameObserver().nextKamer();
    }
      
    @Override
    protected void toonSuccesBericht() {
        System.out.println("\nGoed gedaan! Je hebt de feedback correct geïnterpreteerd en ingeschat.");
    }

    @Override
    protected void toonMisluktBericht() {
        System.out.println("\nDat was niet de juiste aanpak. verwerk Stakeholderfeedback correct!");
    }
};
