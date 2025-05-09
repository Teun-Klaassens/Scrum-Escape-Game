package org.scrumEscape.classes;

import org.scrumEscape.base.Kamer;

public class Player {
	private String naam;
	private Kamer huidigeKamer;

	

	public void setHuidigeKamer(Kamer kamer) {
		this.huidigeKamer = kamer;
	}

	public Kamer getHuidigeKamer() {
		return huidigeKamer;
	}

}
