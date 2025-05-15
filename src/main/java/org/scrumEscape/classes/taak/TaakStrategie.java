package org.scrumEscape.classes.taak;

public interface TaakStrategie {
    void toon();
    boolean valideer (String text);
    void ongeldigAntwoord();
    void geldigAntwoord();

}
