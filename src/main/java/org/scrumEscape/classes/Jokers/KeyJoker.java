package org.scrumEscape.classes.Jokers;
import org.scrumEscape.base.Kamer;
import java.util.Scanner;

public class KeyJoker extends Joker {
    @Override
    public void useIn(Kamer kamer) {
        if (!used) {
            used = true;
            System.out.println("De KeyJoker is gebruikt! Je gaat door naar de volgende kamer?.");
            ((KeyJokerGebruiken) kamer).useKeyJoker();
        }
    }

    @Override
    public void offerUse(Kamer kamer, Scanner scanner) {
        if (!used) {
            System.out.println("Wil je de KeyJoker gebruiken? Je krijgt een extra sleutel en kunt door naar de volgende kamer!" +
                    " Je kunt deze maar één keer gebruiken! (ja/nee)");
            String antwoord = scanner.nextLine().trim().toLowerCase();
            if (antwoord.equals("ja") || antwoord.equals("j")) {
                useIn(kamer);
            }
        }
    }
}