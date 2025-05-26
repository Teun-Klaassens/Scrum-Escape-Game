package org.scrumEscape.classes.hints;

import org.scrumEscape.interfaces.Hint;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class HelpHint implements Hint {
    private final Map<String, String[]> hintMap;
    private final Random random;
    
    public HelpHint() {
        this.random = new Random();
        this.hintMap = new HashMap<>();
        initializeHints();
    }
    
    private void initializeHints() {
        hintMap.put("DailyScrum", new String[] {
            "Bij een Daily Scrum beantwoordt elk teamlid drie vragen: wat heb ik gisteren gedaan, wat ga ik vandaag doen, en zijn er belemmeringen?",
            "Een Daily Scrum hoort kort te zijn, ongeveer 15 minuten.",
            "Elk teamlid moet tijdens de Daily Scrum aan het woord komen."
        });

        hintMap.put("SprintPlanning", new String[] {
            "De Sprint Goal is een belangrijk onderdeel van de Sprint Planning.",
            "In Sprint Planning bepaal je WAT er gedaan wordt en HOE het gedaan wordt.",
            "De Product Owner stelt prioriteiten, het Development Team bepaalt de haalbaarheid."
        });

        hintMap.put("Retrospective", new String[] {
            "In een Retrospective bespreek je wat goed ging, wat beter kan, en welke acties je neemt.",
            "De Retrospective is bedoeld om het proces te verbeteren, niet om mensen te bekritiseren.",
            "Een veilige omgeving is cruciaal voor een effectieve Retrospective."
        });

        hintMap.put("SprintReview", new String[] {
            "In de Sprint Review demonstreert het team het voltooide werk aan stakeholders.",
            "Feedback verzamelen is een belangrijk doel van de Sprint Review.",
            "De Product Backlog kan worden bijgewerkt op basis van de Sprint Review."
        });

        hintMap.put("Default", new String[] {
            "Scrum is gebaseerd op empirische procescontrole: transparantie, inspectie en aanpassing.",
            "Een Sprint heeft een vaste tijdsduur, meestal 2-4 weken.",
            "De Product Owner vertegenwoordigt de belangen van stakeholders."
        });
    }
    
    @Override
    public String getHint(String context) {
        String[] hints = hintMap.getOrDefault(context, hintMap.get("Default"));
        return hints[random.nextInt(hints.length)];
    }
    
    @Override
    public String getType() {
        return "Help Hint";
    }
}
