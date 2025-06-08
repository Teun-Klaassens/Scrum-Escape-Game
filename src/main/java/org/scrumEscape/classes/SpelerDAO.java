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
}
