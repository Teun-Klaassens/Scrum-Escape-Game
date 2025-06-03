package org.scrumEscape.interfaces;

import org.scrumEscape.base.Kamer;

import java.util.Scanner;

public interface GameObserver {
	void onPlayerUpdate();
	void onKamerBehaald(Kamer kamer);
	void nextKamer();
	Scanner getScanner();
}
