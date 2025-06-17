package org.scrumEscape.classes.Jokers;
import org.scrumEscape.base.Kamer;
import java.util.Scanner;

public class HintJoker extends Joker {
    @Override
    public void useIn(Kamer kamer) {
        if (!used) {
            used = true;
            System.out.println("De HintJoker is gebruikt! Hier is een hint voor je:");
            ((HintJokerGebruiken) kamer).useHintJoker();
        }
    }

    @Override
    public void offerUse(Kamer kamer, Scanner scanner) {
        if (!used) {
            System.out.println("Wil je de HintJoker gebruiken? Je krijgt met deze joker een hint! Je kunt deze maar één keer gebruiken. (ja/nee)");
            String antwoord = scanner.nextLine().trim().toLowerCase();
            if (antwoord.equals("ja") || antwoord.equals("j")) {
                useIn(kamer);
            }
        }
    }
}