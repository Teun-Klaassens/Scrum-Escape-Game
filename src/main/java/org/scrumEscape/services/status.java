package org.scrumEscape.services;

import java.sql.*;
import java.util.*;

public class status {

    public static void toonVoortgang(String spelerNaam, Connection conn, Scanner scanner) {
        List<String> behaaldeKamers = new ArrayList<>();

        String sql = "SELECT event_value FROM player_progress " +
                "JOIN speler ON speler.id = player_progress.player_id " +
                "WHERE speler.naam = ? AND event_type = 'kamer_voltooid'";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, spelerNaam);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    behaaldeKamers.add(rs.getString("event_value"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("==================================");
        System.out.println("Voltooide kamers voor speler: " + spelerNaam);
        if (behaaldeKamers.isEmpty()) {
            System.out.println("Nog geen kamers voltooid.");
        } else {
            for (String kamer : behaaldeKamers) {
                System.out.println("- " + kamer);
            }
        }
        System.out.println("==================================");
        System.out.println("Druk op 'b' om terug te gaan naar het hoofdmenu.");

        while (true) {
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("b")) break;
            else System.out.println("Typ 'b' om terug te keren.");
        }
    }
    public static Set<String> getVoltooideKamers(String spelerNaam, Connection conn) {
        Set<String> behaald = new HashSet<>();

        String sql = "SELECT event_value FROM player_progress " +
                "JOIN speler ON speler.id = player_progress.player_id " +
                "WHERE speler.naam = ? AND event_type = 'kamer_voltooid'";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, spelerNaam);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    behaald.add(rs.getString("event_value"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return behaald;
    }
    
}
