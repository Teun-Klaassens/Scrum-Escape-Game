package org.scrumEscape.base;

import org.scrumEscape.classes.Monster;
import org.scrumEscape.interfaces.IKamerState;

import java.util.ArrayList;

public abstract class Kamer {
	// Kamer objecten
	private int kamerId;
	private int kamerNaam;
	private boolean isAfgerond;
	private boolean monsterSpawned;

	// Spelers objecten
	private int huidigeTaakIndex;
	private int lastBehaaldTaakIndex;

	// Kamer objecten
	private Monster monster;
	private ArrayList<Monster> monsters;

	// Kamer functie's
	public void enterKamer() {

	}
	public void verlaatKamer(){}

	public void spawnMonster() {
		// Monster spawnen
	}
	public void killMonster() {
		// Monster doden
	}

	// Taken functie's
	public boolean geefAntwoord() {
		// Antwoord geven
		return true;
	}

	public IKamerState getKamerState() {
		return null;
	}

	public abstract String getBeschrijving();
	public abstract void start();
}
