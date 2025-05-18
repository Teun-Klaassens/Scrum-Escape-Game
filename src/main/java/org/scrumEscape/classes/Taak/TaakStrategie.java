package org.scrumEscape.classes.Taak;

public interface TaakStrategie {
    void toon();
    boolean valideer (String text);
    void ongeldigAntwoord();
    void geldigAntwoord();

}
