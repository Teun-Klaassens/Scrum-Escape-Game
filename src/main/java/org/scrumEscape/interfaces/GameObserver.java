package org.scrumEscape.interfaces;

import org.scrumEscape.base.Kamer;
import org.scrumEscape.classes.Jokers.HintJoker;
import org.scrumEscape.classes.Jokers.KeyJoker;
import org.scrumEscape.classes.Speler;

import java.util.Scanner;

public interface GameObserver {
	void onPlayerUpdate();
	void onKamerBehaald(Kamer kamer);
	void nextKamer();
	void kickToLobby();
	Scanner getScanner();
	Speler getSpeler();
	HintJoker getHintJoker();
	KeyJoker getKeyJoker();

}
