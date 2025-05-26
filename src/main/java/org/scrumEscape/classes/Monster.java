package org.scrumEscape.classes;

public class Monster {

    public void attack(String kamerNaam) {
        switch (kamerNaam.toLowerCase()) {
            case "sprintplanning":
                System.out.println("SCOPE CREEP!");
                System.out.println("Er zijn essentiële taken vergeten. De planning loopt uit de hand.");
                System.out.println("Het team heeft moeite om de sprint goed te plannen en wordt overweldigd door veranderende eisen!");
                break;
            case "dailyscrum":
                System.out.println("VERTRAGING!");
                System.out.println("Een teamlid heeft geen update gegeven.");
                break;
            case "tia":
                System.out.println("EMPIRISCH FALEN!");
                System.out.println("Zonder begrip van Transparency, Inspection en Adaptation, valt het team terug in oude gewoonten.");
                System.out.println("Het team raakt verstrikt in lange planningssessies, onduidelijke communicatie en weerstand tegen verandering!");
                break;
            default:
                System.out.println("ONBEKEND MONSTER!");
                System.out.println("Er is iets misgegaan... het monster weet niet wat te doen.");
        }
    }
}
