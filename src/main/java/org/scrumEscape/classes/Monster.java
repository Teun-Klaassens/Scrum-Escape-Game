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
                System.out.println("Een teamlid heeft geen update gegeven. Het team struikelt over communicatieproblemen.");
                break;
            default:
                System.out.println("ONBEKEND MONSTER!");
                System.out.println("Er is iets misgegaan... het monster weet niet wat te doen.");
        }
    }
}
