package org.scrumEscape.classes.taak;

import org.scrumEscape.controllers.MenuController;
import org.scrumEscape.interfaces.TaakStrategie;

import java.util.*;

public class Puzzel implements TaakStrategie {
	final String taak;
	private Map<String, String> mapStukken = new HashMap<>();
	private int huidigeStuk = 0;
	private int overigeStukken;
	private boolean behaald;
	private boolean started = false;

	public Puzzel(String taak, Map<String, String> stukken) {
		this.taak = taak;
		this.overigeStukken = stukken.size();
		this.behaald = false;
		//this.mapStukken.putAll(stukken);
		// Shuffle the map entries
		List<Map.Entry<String, String>> entries = new ArrayList<>(stukken.entrySet());
		Collections.shuffle(entries);

		// Re-insert into a LinkedHashMap to preserve shuffled order
		this.mapStukken = new LinkedHashMap<>();
		for (Map.Entry<String, String> entry : entries) {
			this.mapStukken.put(entry.getKey(), entry.getValue());
		}

	}

	@Override
	public void toon() {
		if (!started) {
			// Hier print je de puzzelstukjes values
			System.out.println(MenuController.BOLD + MenuController.BG_WHITE + MenuController.BLACK + "\uD83E\uDDE9 Verbind de volgende situaties bij de juiste ding: \uD83E\uDDE9");
			System.out.print(MenuController.RESET);

			ArrayList<String> values = new ArrayList<>(mapStukken.values());
			java.util.Collections.shuffle(values);

			System.out.println(MenuController.YELLOW);
			for (String value : values) {
				System.out.println(value);
			}
			System.out.println(MenuController.RESET);

			// Hier print je de puzzelstukjes keys
			System.out.println(MenuController.BOLD + MenuController.BG_YELLOW + MenuController.BLACK + "Overige situatie:");
			ArrayList<String> keys = new ArrayList<>(mapStukken.keySet());
			java.util.Collections.shuffle(keys);
			for (String key : keys) {
				System.out.println(key);
			}
			System.out.print(MenuController.RESET);

			started = true;
		}

		// Print de huidige situatie
		toonHuidigeStuk();
	}

	public void toonHuidigeStuk() {
		String text = mapStukken.values().stream().toList().get(huidigeStuk).trim();
		System.out.println(MenuController.BOLD + MenuController.GREEN);
		System.out.printf("\n\uD83E\uDDE9 Situatie %d: %s %s\nVul je antwoord in:", huidigeStuk + 1, text, MenuController.RESET);
		System.out.print(MenuController.RESET);
	}

	@Override
	public boolean valideer(String text) {
		System.out.println("Je hebt het volgende antwoord gegeven: " + text);
		String key = mapStukken.keySet().stream().toList().get(huidigeStuk).trim();
		if (text.equalsIgnoreCase(key.trim())) {
			geldigAntwoord();
			huidigeStuk++;
			overigeStukken--;
			if (overigeStukken == 0) {
				behaald = true;
			}
			return true;
		} else {
			ongeldigAntwoord();
			return false;
		}
	}

	@Override
	public void ongeldigAntwoord() {
		System.out.print(MenuController.BOLD + MenuController.WHITE + MenuController.BG_RED);
		System.out.println("Antwoord is fout.");
		System.out.print(MenuController.RESET);
	}

	@Override
	public void geldigAntwoord() {
		System.out.print(MenuController.BOLD + MenuController.GREEN);
		System.out.println("Antwoord is correct.");
		System.out.println(MenuController.RESET);

		System.out.println(MenuController.BOLD + MenuController.BG_GREEN + MenuController.BLACK);
		System.out.println("==================================================");
		System.out.println("Je hebt de situatie " + (huidigeStuk + 1) + " van de " + mapStukken.size() + " behaald.");
		if (overigeStukken > 0)
			System.out.println("Je hebt nog " + overigeStukken + " situatie(s) te gaan.");
		else
			System.out.println("Je hebt de puzzel behaald.");
		System.out.println("==================================================");
		System.out.print(MenuController.RESET);

	}

	@Override
	public boolean isBehaald() {
		return behaald;
	}
}
