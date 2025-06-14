// src/main/java/org/scrumEscape/classes/Voorwerpen/KamerInfoRepository.java
package org.scrumEscape.classes.Voorwerpen;

import java.util.HashMap;
import java.util.Map;

public class KamerInfoStrings {
    private static final Map<String, String> kamerInfoMap = new HashMap<>();

    static {
        kamerInfoMap.put("Daily Scrum", "In deze kamer wordt je kennis over daily standups getest!");
        kamerInfoMap.put("Retrospective", "In deze kamer moet je goed nadenken over de keuzes die zijn gemaakt!");
        kamerInfoMap.put("Scrum Bord", "In deze kamer moet je taken goed kunnen verdelen!");
        kamerInfoMap.put("Sprint Planning", "In deze kamer moet je zorgen voor een goede sprint planning!");
        kamerInfoMap.put("Sprint Review", "In deze kamer moet je kritisch zijn over de resultaten van de sprint!");
        kamerInfoMap.put("TIA", "In deze kamer moet je er achter komen waar TIA voor staat!");
    }

    public static String getInfo(String kamerNaam) {
        return kamerInfoMap.getOrDefault(kamerNaam, "Geen info beschikbaar voor deze kamer.");
    }
}