package org.scrumEscape.classes.hints;

import org.scrumEscape.interfaces.Hint;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Biedt komische opmerkingen voor verschillende contexten in het spel
 */
public class FunnyHint implements Hint {
    private final Map<String, String[]> hintMap;
    private final Random random;
    
    public FunnyHint() {
        this.random = new Random();
        this.hintMap = new HashMap<>();
        initializeHints();
    }
    
    private void initializeHints() {
        // DailyScrum komische opmerkingen
        hintMap.put("DailyScrum", new String[] {
            "Als de Daily Scrum langer duurt dan 15 minuten, probeer dan eens te staan op één been. Dat maakt het tenminste interessant!",
            "Definitie van optimisme: denken dat je Daily Scrum korter dan 10 minuten zal duren.",
            "Daily Scrum: het enige moment van de dag waarop sommige ontwikkelaars daadwerkelijk praten."
        });
        
        // SprintPlanning komische opmerkingen
        hintMap.put("SprintPlanning", new String[] {
            "Sprint Planning is zoals het plannen van een vakantie... die je vervolgens werkend doorbrengt.",
            "Sprint Planning: waar optimisme en realiteit elkaar ontmoeten voor een kort maar intens gevecht.",
            "Als jouw team Story Points schat met Fibonacci-getallen, komt dat omdat niemand weet wat 7 betekent."
        });
        
        // Retrospective komische opmerkingen
        hintMap.put("Retrospective", new String[] {
            "In een Retrospective is 'dat doen we de volgende sprint beter' de meest herhaalde zin in de geschiedenis van Scrum.",
            "Retrospective: de vergadering waar je team officieel toegeeft wat iedereen al wist dat fout ging.",
            "Een goede Retrospective is als therapie, maar goedkoper. En met post-its."
        });
        
        // SprintReview komische opmerkingen
        hintMap.put("SprintReview", new String[] {
            "Sprint Review: het moment waarop ontwikkelaars leren acteren. 'Nee, deze bug was er altijd al...'",
            "In een Sprint Review is 'het werkt op mijn machine' geen verdediging, het is een bekentenis.",
            "Stakeholders in een Sprint Review zijn als filmcritici, maar dan zonder het plezier van popcorn."
        });
        
        // Algemene Scrum komische opmerkingen
        hintMap.put("Default", new String[] {
            "Scrum: omdat 'chaotisch rondrennen en hopen op het beste' geen professionele projectmethodiek is.",
            "Een Scrum team is als een familie: niemand weet precies wat de anderen doen, maar iedereen heeft een mening over hoe het beter kan.",
            "Programmeren is als schrijven: de eerste draft is altijd vreselijk, maar in Scrum heb je maar twee weken om het te verbeteren."
        });
    }
    
    @Override
    public String getHint(String context) {
        String[] hints = hintMap.getOrDefault(context, hintMap.get("Default"));
        return hints[random.nextInt(hints.length)];
    }
    
    @Override
    public String getType() {
        return "Funny Hint";
    }
}
