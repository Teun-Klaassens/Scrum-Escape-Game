package org.scrumEscape.classes.Kamers;

import org.scrumEscape.base.Kamer;
import org.scrumEscape.classes.Player;
import java.util.ArrayList;

public class DailyScrum extends Kamer {
    ArrayList<Player> teamleden = new ArrayList<>();

    void roepen(){
        System.out.println("Vertraging");
    }

    @Override
    public String getBeschrijving() {
        return "Je krijgt een lijst teamleden en moet aangeven wie welke status-update zou geven. Een vergeten update roept het monster “Vertraging” op.";
    }
}
