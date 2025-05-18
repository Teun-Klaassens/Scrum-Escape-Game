package org.scrumEscape.classes.Kamers;

import org.scrumEscape.base.Kamer;

public class ScrumBord extends Kamer {

    //    ArrayList<Taak> taken = new ArrayList<Kamer>();
    //    ArrayList<UserStory> userStories  = new ArrayList<Kamer>();
    //    ArrayList<Epic> epics = new ArrayList<Kamer>();
    @Override
    public String getBeschrijving() {
        return "Je krijgt een opdracht om een bord correct in te richten met taken, user stories en epics.";
    }

    public void start() {

    }
}
// STAPPENPLAN: Kamer "Het Scrum Board"

// 1. Maak de klasse ScrumBord in het pakket org.scrumEscape.classes.Kamers
//    -> Laat de klasse extends Kamer
//    -> Voeg een private boolean toe: isAfgerond (begin op false)

// 2. Maak binnen de klasse een nested class genaamd WerkItem
//    -> Geef WerkItem de volgende velden:
//       - int id
//       - String naam
//       - String type (bijv. "Epic", "User Story", "Taak")
//       - Integer parentId (null voor epics, anders ID van bovenliggend item)
//       - String correcteKolom (zoals "To Do", "In Progress", "Done")
//    -> Maak een constructor die al deze velden ontvangt en instelt

// 3. Maak in de start()-methode een lijst met werkitems
//    -> Voeg 1 epic toe, 1 user story die hoort bij die epic, en 2-3 taken die bij de user story horen
//    -> Vul bij elk item ook de correcte kolom in

// 4. Toon voor de speler het doel van de kamer:
//    -> Leg uit dat ze moeten aangeven:
//       - bij welk item-ID elk item hoort (voor user stories en taken)
//       - in welke kolom het item staat

// 5. Vraag per werkitem invoer van de speler:
//    -> Als het geen epic is: vraag om het bijbehorende parent-ID
//    -> Vraag voor elk item in welke kolom het hoort (To Do / In Progress / Done)

// 6. Sla de invoer van de speler op in twee aparte mappen:
//    -> Een map met gekozen parentIds per item
//    -> Een map met gekozen kolommen per item

// 7. Controleer de antwoorden:
//    -> Kijk of het opgegeven parent-ID klopt met het verwachte parentId
//    -> Kijk of het opgegeven kolomlabel klopt met de correcteKolom

// 8. Als alle koppelingen en kolommen correct zijn:
//    -> Zet isAfgerond op true
//    -> Toon een succesbericht

// 9. Als er fouten zijn:
//    -> Toon per item wat er misging (foute parent, foute kolom)
//    -> Laat isAfgerond op false staan en geef terugkoppeling

// EINDE STAPPENPLAN
