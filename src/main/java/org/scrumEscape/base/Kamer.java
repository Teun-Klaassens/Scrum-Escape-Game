package org.scrumEscape.base;

import org.scrumEscape.classes.Monster;

import java.util.ArrayList;

public abstract class Kamer {
	private int kamerId;
	private boolean isAfgerond;
	private ArrayList<Monster> monsters;


	public abstract String getBeschrijving();
	public abstract void start();
}
