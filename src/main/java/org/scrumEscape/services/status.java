package org.scrumEscape.services;

import org.scrumEscape.classes.SpelerDAO;

import java.sql.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class status {

    public static void toonVoortgang(String spelerNaam, Connection conn, Scanner scanner) {
        try {
            SpelerDAO spelerDAO = new SpelerDAO(conn);
            int spelerId = spelerDAO.getSpelerId(spelerNaam);

            String sql = "SELECT event_value FROM player_progress WHERE player_id = ? AND event_type = 'kamer_voltooid'";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, spelerId);

                try (ResultSet rs = stmt.executeQuery()) {
                    Set<String> behaaldeKamers = new HashSet<>();

                    while (rs.next()) {
                        behaaldeKamers.add(rs.getString("event_value"));
                    }

                    System.out.println("==================================");
                    System.out.println("Kamer voortgang voor speler: " + spelerNaam);
                    if (behaaldeKamers.isEmpty()) {
                        System.out.println("Er zijn nog geen kamers behaald.");
                    } else {
                        System.out.println("Je hebt de volgende kamers behaald:");
                        for (String kamer : behaaldeKamers) {
                            System.out.println("- " + kamer);
                        }
                    }
                    System.out.println("==================================");
                }
            }
        } catch (SQLException e) {
            System.out.println("Fout bij het ophalen van voortgang: " + e.getMessage());
        }

        System.out.println("Druk op 'b' om terug te keren naar het hoofdmenu.");
        while (true) {
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("b")) {
                break;
            } else {
                System.out.println("Ongeldige invoer. Druk op 'b' om terug te keren.");
            }
        }
    }
}
