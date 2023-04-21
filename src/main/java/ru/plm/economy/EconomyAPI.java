package ru.plm.economy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public interface EconomyAPI {

    UUID createTransaction(String... transactionParticipants);

    boolean cancelTransaction(UUID transactionUUID);

    void closeTransaction(UUID transactionUUID) throws EconomyException;

    String formatBucks(long amount);

    boolean hasBucksAccount(String playerName);

    long getBucksBalance(String playerName);

    boolean hasBucks(String playerName, long amount);

    void initializeBucksBalance(UUID transactionUUID, String playerName, long balance) throws EconomyException;

    void depositBucks(Connection conn, PreparedStatement ps, UUID transactionUUID, String playerName, long amount) throws EconomyException, SQLException;

    void withdrawBucks(Connection conn, PreparedStatement ps, UUID transactionUUID, String playerName, long amount) throws EconomyException, SQLException;

    String formatPlumcoins(long amount);

    boolean hasPlumcoinsAccount(String playerName);

    long getPlumcoinsBalance(String playerName);

    boolean hasPlumcoins(String playerName, long amount);

    void initializePlumcoinsBalance(UUID transactionUUID, String playerName, long balance) throws EconomyException;

    void depositPlumcoins(Connection conn, PreparedStatement ps, UUID transactionUUID, String playerName, long amount) throws EconomyException, SQLException;

    void withdrawPlumcoins(Connection conn, PreparedStatement ps, UUID transactionUUID, String playerName, long amount) throws EconomyException, SQLException;
}
