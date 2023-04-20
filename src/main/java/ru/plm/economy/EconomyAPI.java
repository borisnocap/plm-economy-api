package ru.plm.economy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface EconomyAPI {

    long createTransaction(String... transactionParticipants);

    boolean cancelTransaction(long transactionId);

    void closeTransaction(long transactionId) throws EconomyException;

    String formatBucks(long amount);

    boolean hasBucksAccount(String playerName);

    long getBucksBalance(String playerName);

    boolean hasBucks(String playerName, long amount);

    void setBucksBalance(long transactionId, String playerName, long balance) throws EconomyException;

    void depositBucks(Connection conn, PreparedStatement ps, long transactionId, String playerName, long amount) throws EconomyException, SQLException;

    void withdrawBucks(Connection conn, PreparedStatement ps, long transactionId, String playerName, long amount) throws EconomyException, SQLException;

    String formatPlumcoins(long amount);

    boolean hasPlumcoinsAccount(String playerName);

    long getPlumcoinsBalance(String playerName);

    boolean hasPlumcoins(String playerName, long amount);

    void setPlumcoinsBalance(long transactionId, String playerName, long balance) throws EconomyException;

    void depositPlumcoins(Connection conn, PreparedStatement ps, long transactionId, String playerName, long amount) throws EconomyException, SQLException;

    void withdrawPlumcoins(Connection conn, PreparedStatement ps, long transactionId, String playerName, long amount) throws EconomyException, SQLException;
}
