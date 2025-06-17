package org.scrumEscape.classes.Jokers;
import org.scrumEscape.base.Kamer;

public abstract class Joker {
    protected boolean used = false;

    public boolean isUsed() {
        return used;
    }

    public abstract void useIn(Kamer kamer);

    public abstract void offerUse(Kamer kamer, java.util.Scanner scanner);
}