package org.scrumEscape.classes.Kamers;

import org.scrumEscape.base.Kamer;
import org.scrumEscape.classes.Monster;
import org.scrumEscape.classes.Taak.MultiChoice;
import org.scrumEscape.interfaces.GameObserver;
import org.scrumEscape.interfaces.TaakStrategie;

import java.util.ArrayList;

public class Retrospective extends Kamer{

    public Retrospective( GameObserver gameObserver) {
        super("Retrospective", new Monster(), gameObserver);
    }

    @Override
    protected ArrayList<TaakStrategie> initialiseren() {
        ArrayList<TaakStrategie> opdrachten = new ArrayList<>();


        // Vraag 1
        ArrayList<String> keuzes1 = new ArrayList<>();
        keuzes1.add("Wat is de beste les die het team hieruit kan trekken?");
        keuzes1.add("Voortaan meer overuren maken om de verloren tijd in te halen");
        keuzes1.add("Aan het begin van de sprint beter kijken naar taak-afhankelijkheden en de planning hierop aanpassen");
        keuzes1.add("Minder afhankelijke taken oppakken in de volgende sprint");
        opdrachten.add(new MultiChoice(
                "Tijdens de laatste sprint heeft het team gemerkt dat er veel tijd verloren ging " +
                        "doordat teamleden op elkaar moesten wachten met afhankelijke taken.",
                keuzes1, 3
        ));


        // Vraag 2
        ArrayList<String> keuzes2 = new ArrayList<>();
        keuzes2.add("Nieuwe requirements pas in de volgende sprint meenemen en de Sprint Planning strikt volgen");
        keuzes2.add("Harder werken om zowel de geplande stories als nieuwe requirements af te krijgen");
        keuzes2.add("De sprint verlengen tot alle nieuwe requirements zijn geïmplementeerd");
        opdrachten.add(new MultiChoice(
                "Het team heeft de laatste sprint niet alle user stories kunnen afronden omdat er halverwege nieuwe requirements werden toegevoegd door de stakeholders.\n" +
                        "Wat is de beste aanpak voor de volgende sprint?",
                keuzes2,
                1
        ));

        // Vraag 3
        ArrayList<String> keuzes3 = new ArrayList<>();
        keuzes3.add("De Daily Standup afschaffen en alleen communiceren via chat");
        keuzes3.add("De Daily Standup inkorten tot 5 minuten waarin iedereen alleen zegt of ze geblokkeerd zijn");
        keuzes3.add("Technische discussies parkeren voor een apart overleg na de Daily Standup");
        opdrachten.add(new MultiChoice(
                "Tijdens de Daily Standups merkt het team dat deze vaak uitlopen omdat teamleden in detail technische problemen gaan bespreken.\n" +
                        "Wat is de beste oplossing voor dit probleem?",
                keuzes3,
                3
        ));

        return opdrachten;
    }

    @Override
    public void toonIntro() {
        System.out.println("=== Welkom bij de Sprint Retrospective! ===");
        System.out.println("Analyseer de volgende situaties en kies het beste antwoord.\n");
    }

    @Override
    public void toonBeschrijving() {
        System.out.println("Je krijgt situaties die zich in een team voordoen en moet aangeven wat het team hiervan kan leren.");
    }

    @Override
    protected void toonMisluktBericht() {
        super.toonMisluktBericht();
    }

    @Override
    protected void toonSuccesBericht() {
        super.toonSuccesBericht();
    }

}