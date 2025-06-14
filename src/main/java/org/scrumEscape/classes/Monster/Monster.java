package org.scrumEscape.classes.Monster;

public abstract class Monster {
	private boolean isActief;
	private boolean isOpgelost;

	public void toonImpediment(){

	}
	public void oplossen(){
	}

	public boolean isActief() {
		return isActief;
	}
	public boolean setActief() {
		isActief = true;
		return isActief;
	}

	public boolean setInactief() {
		isActief = false;
		return isActief;
	}
}