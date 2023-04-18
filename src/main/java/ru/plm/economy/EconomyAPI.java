package ru.plm.economy;

import org.bukkit.OfflinePlayer;

import java.sql.Connection;
import java.sql.SQLException;

public interface EconomyAPI {

    String formatBucks(long amount);

    boolean hasBucksAccount(String playerName);

    boolean hasBucksAccount(OfflinePlayer player);

    Long getBucksBalance(String playerName);

    Long getBucksBalance(OfflinePlayer player);

    boolean hasBucks(String playerName, long amount);

    boolean hasBucks(OfflinePlayer player, long amount);

    void withdrawBucks(Connection conn, String playerName, long amount) throws SQLException;

    void withdrawBucks(Connection conn, OfflinePlayer player, long amount) throws SQLException;

    void depositBucks(Connection conn, String playerName, long amount) throws SQLException;

    void depositBucks(Connection conn, OfflinePlayer player, long amount) throws SQLException;
}
