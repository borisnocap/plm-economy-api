package ru.plm.economy;

import java.sql.Connection;
import java.sql.SQLException;

public interface EconomyAPI {

    String formatCurrency(Currency currency, long amount);

    long getBalance(Currency currency, String playerName);

    boolean hasBalance(Currency currency, String playerName, long amount);

    EconomyTransaction createTransaction(Currency currency, String... participants);

    boolean cancelTransaction(EconomyTransaction transaction);

    void writeTransactionToDatabase(Connection connection, EconomyTransaction transaction) throws SQLException;

    void closeTransaction(EconomyTransaction transaction);
}
