package ru.plm.economy;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;

public interface EconomyAPI {

    UUID createBucksTransaction(String... transactionParticipants);

    UUID createPlumcoinsTransaction(String... transactionParticipants);

    boolean cancelTransaction(UUID transactionUUID);

    void closeTransaction(Connection connection, UUID transactionUUID) throws EconomyException, SQLException;

    String formatBucks(long amount);

    boolean hasBucksAccount(String playerName);

    long getBucksBalance(String playerName);

    boolean hasBucks(String playerName, long amount);

    void initializeBucksBalance(UUID transactionUUID, String playerName, long balance) throws EconomyException;

    void depositBucks(UUID transactionUUID, String playerName, long amount) throws EconomyException;

    void withdrawBucks(UUID transactionUUID, String playerName, long amount) throws EconomyException;

    String formatPlumcoins(long amount);

    boolean hasPlumcoinsAccount(String playerName);

    long getPlumcoinsBalance(String playerName);

    boolean hasPlumcoins(String playerName, long amount);

    void initializePlumcoinsBalance(UUID transactionUUID, String playerName, long balance) throws EconomyException;

    void depositPlumcoins(UUID transactionUUID, String playerName, long amount) throws EconomyException;

    void withdrawPlumcoins(UUID transactionUUID, String playerName, long amount) throws EconomyException;
}
