package org.scrumEscape.classes.taak;

public class Vraag {
    final String vraag;
    final String antwoord;
    boolean behaald;


    public Vraag(String vraag, String antwoord, boolean behaald) {
        this.vraag = vraag;
        this.antwoord = antwoord;
        this.behaald = behaald;
    }

    void toon(){
        System.out.println(vraag);
    }

    boolean valideer(String antwoord){
        return this.antwoord.equals(antwoord);
    }
}

