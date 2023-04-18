package ru.plm.economy;

import org.bukkit.OfflinePlayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface EconomyAPI {

    String formatBucks(long amount);

    boolean hasBucksAccount(String playerName);

    boolean hasBucksAccount(OfflinePlayer player);

    Long getBucksBalance(String playerName);

    Long getBucksBalance(OfflinePlayer player);

    boolean hasBucks(String playerName, long amount);

    boolean hasBucks(OfflinePlayer player, long amount);

    void withdrawBucks(Connection conn, PreparedStatement ps, String playerName, long amount) throws SQLException;

    void withdrawBucks(Connection conn, PreparedStatement ps, OfflinePlayer player, long amount) throws SQLException;

    void depositBucks(Connection conn, PreparedStatement ps, String playerName, long amount) throws SQLException;

    void depositBucks(Connection conn, PreparedStatement ps, OfflinePlayer player, long amount) throws SQLException;

    String formatPlumcoins(long amount);

    boolean hasPlumcoinsAccount(String playerName);

    boolean hasPlumcoinsAccount(OfflinePlayer player);

    Long getPlumcoinsBalance(String playerName);

    Long getPlumcoinsBalance(OfflinePlayer player);

    boolean hasPlumcoins(String playerName, long amount);

    boolean hasPlumcoins(OfflinePlayer player, long amount);

    void withdrawPlumcoins(Connection conn, PreparedStatement ps, String playerName, long amount) throws SQLException;

    void withdrawPlumcoins(Connection conn, PreparedStatement ps, OfflinePlayer player, long amount) throws SQLException;

    void depositPlumcoins(Connection conn, PreparedStatement ps, String playerName, long amount) throws SQLException;

    void depositPlumcoins(Connection conn, PreparedStatement ps, OfflinePlayer player, long amount) throws SQLException;
}
