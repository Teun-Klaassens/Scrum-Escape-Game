package org.scrumEscape.classes.hints;

import org.scrumEscape.interfaces.Hint;

import java.util.Random;

public class HintFactory {
    private static final Random random = new Random();

    public static Hint getRandomHint() {
        //50/50
        if (random.nextBoolean()) {
            return new HelpHint();
        } else {
            return new FunnyHint();
        }
    }

    public static String getHintText(String context) {
        Hint hint = getRandomHint();
        return hint.getType() + ": " + hint.getHint(context);
    }
}
