package ru.plm.economy;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;

public interface EconomyAPI {

    String formatCurrency(Currency currency, long amount);

    long getBalance(Currency currency, String playerName);

    boolean hasBalance(Currency currency, String playerName, long amount);

    void deposit(Currency currency, UUID transactionUUID, String playerName, long amount) throws EconomyException;

    void withdraw(Currency currency, UUID transactionUUID, String playerName, long amount) throws EconomyException;

    UUID createTransaction(Currency currency, String... participants);

    boolean cancelTransaction(UUID transactionUUID);

    void writeTransactionToDatabase(Connection connection, UUID transactionUUID) throws EconomyException, SQLException;

    void closeTransaction(UUID transactionUUID) throws EconomyException;
}
