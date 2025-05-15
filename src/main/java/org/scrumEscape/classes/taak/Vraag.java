package org.scrumEscape.classes.taak;

public class Vraag implements TaakStrategie{
    final String vraag;
    final String antwoord;
    boolean behaald;


    public Vraag(String vraag, String antwoord, boolean behaald) {
        this.vraag = vraag;
        this.antwoord = antwoord;
        this.behaald = behaald;
    }

    public void toon(){
        System.out.println(vraag);
    }

    public boolean valideer(String antwoord){
        return this.antwoord.equals(antwoord);
    }

    @Override
    public void ongeldigAntwoord() {
        System.out.println("Dat is niet het juiste antwoord. Het correcte antwoord is: " + this.antwoord);
    }

    @Override
    public void geldigAntwoord() {

    }
}