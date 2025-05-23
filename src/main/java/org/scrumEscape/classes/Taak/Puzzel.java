package org.scrumEscape.classes.Taak;
import org.scrumEscape.interfaces.TaakStrategie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Puzzel implements TaakStrategie{
  final String taak;
   final Map<String, String> mapStukken = new HashMap<>();
   private int huidigeStuk= 0;
   private int overigeStukken;
   private boolean behaald;

    public Puzzel(String taak,Map<String, String> stukken) {
        this.taak = taak;
        this.mapStukken.putAll(stukken);
        this.overigeStukken = stukken.size() * 2;
        this.behaald = false;
    }

    @Override
    public void toon() {
        // Hier print je de puzzelstukjes values
        System.out.println("\nVerbind de volgende situaties bij de juiste ding:");
        ArrayList<String> values = new ArrayList<>(mapStukken.values());
        java.util.Collections.shuffle(values);
        for (String value : values) {
            System.out.println(value);
        }

        // Hier print je de puzzelstukjes keys
        System.out.println("\nOverige situatie:");
        ArrayList<String> keys = new ArrayList<>(mapStukken.keySet());
        java.util.Collections.shuffle(keys);
        for (String key : keys) {
            System.out.println(key);
        }

        // Print de eerste stuk
        toonHuidigeStuk();
    }

    public void toonHuidigeStuk() {
        String text = mapStukken.values().stream().toList().get(huidigeStuk).trim();
        System.out.printf("\nSituatie %d: %s \nVul je antwoord in:", huidigeStuk + 1, text);
    }

    @Override
    public boolean valideer(String text) {
        String key = mapStukken.keySet().stream().toList().get(huidigeStuk).trim();
         if (text.equalsIgnoreCase(key.trim())) {
            geldigAntwoord();
            huidigeStuk++;
            overigeStukken--;
            if (overigeStukken == 0) {
                behaald = true;
            }
            return true;
        } else {
             ongeldigAntwoord();
            return false;
        }
    }

    @Override
    public void ongeldigAntwoord() {
        System.out.println("Antwoord is fout.");
    }

    @Override
    public void geldigAntwoord() {
        System.out.println("Antwoord is correct.");
        System.out.println("Je hebt de situatie " + (huidigeStuk + 1) + " van de " + mapStukken.size() + " behaald.");

        if(overigeStukken > 0) {
            System.out.println("Je hebt nog " + overigeStukken + " situatie(s) te gaan.");
            System.out.println(" ");
            toonHuidigeStuk();
        }
        else
            System.out.println("Je hebt de puzzel behaald.");

    }
    
    @Override
    public boolean isBehaald() {
        return behaald;
    }
}
