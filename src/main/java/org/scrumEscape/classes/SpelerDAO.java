package org.scrumEscape.classes;

import java.sql.*;

public class SpelerDAO {
    private final Connection conn;

    public SpelerDAO(Connection conn) {
        this.conn = conn;
        try {
            this.conn.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveSpeler(Speler speler) throws SQLException {
        String sql = "INSERT INTO speler (naam, voortgang) VALUES (?, ?) ON DUPLICATE KEY UPDATE voortgang = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, speler.getNaam());
            stmt.setInt(2, speler.getVoortgang());
            stmt.setInt(3, speler.getVoortgang());
            stmt.executeUpdate();
        }
    }

    public Speler loadSpeler(String naam) throws SQLException {
        String sql = "SELECT naam, voortgang FROM speler WHERE naam = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, naam);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Speler(rs.getString("naam"), rs.getInt("voortgang"));
                }
            }
        }
        return null;
    }

    public void voegProgressToe(String spelerNaam, String eventType, String eventValue) throws SQLException {
        String spelerIdQuery = "SELECT id FROM speler WHERE naam = ?";
        int spelerId = -1;

        try (PreparedStatement stmt = conn.prepareStatement(spelerIdQuery)) {
            stmt.setString(1, spelerNaam);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    spelerId = rs.getInt("id");
                } else {
                    throw new SQLException("Speler niet gevonden: " + spelerNaam);
                }
            }
        }

        String insertProgress = "INSERT INTO player_progress (player_id, event_type, event_value) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(insertProgress)) {
            stmt.setInt(1, spelerId);
            stmt.setString(2, eventType);
            stmt.setString(3, eventValue);
            stmt.executeUpdate();
        }
    }
}

