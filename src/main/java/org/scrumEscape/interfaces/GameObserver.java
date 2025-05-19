package org.scrumEscape.interfaces;

import java.util.Scanner;

public interface GameObserver {
	void onPlayerUpdate();
	void onKamerBehaald();
	void nextKamer();
	Scanner getScanner();
}
