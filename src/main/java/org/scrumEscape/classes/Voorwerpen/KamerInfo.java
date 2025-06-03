package org.scrumEscape.classes.Voorwerpen;

public class KamerInfo implements Leesbaar {
    private final String hint;

    public KamerInfo(String hint) {
        this.hint = hint;
    }

    @Override
    public void showMessage() {
        System.out.println(hint);
    }
}
