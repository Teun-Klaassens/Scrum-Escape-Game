package org.scrumEscape.classes.hints;

import java.util.HashMap;
import java.util.Map;

public class JokerHints {
    private static final Map<String, String> JokerHintsMap = new HashMap<>();

    static {
        JokerHintsMap.put("Daily Scrum", "Tijdens de Daily Scrum is het belangrijk om goed te communiceren.");
        JokerHintsMap.put("Retrospective", "Tijdens de Retrospective is het belangrijk om verbeteringen te zoeken.");
        JokerHintsMap.put("Scrum Bord", "Het Scrum Bord helpt bij het visualiseren van de voortgang van taken.");
        JokerHintsMap.put("Sprint Planning", "Tijdens de Sprint Planning is het belangrijk om duidelijke doelen te stellen.");
        JokerHintsMap.put("Sprint Review", "Tijdens de Sprint Review is het belangrijk om feedback te verzamelen van stakeholders.");
        JokerHintsMap.put("TIA", "De T in TIA staat voor 'Transparency'.");
    }

    public static String getHint(String kamerNaam) {
        return JokerHintsMap.getOrDefault(kamerNaam, "Geen hint beschikbaar.");
    }
}
