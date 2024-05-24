package ru.plm.economy;

import org.bukkit.plugin.Plugin;

import java.sql.Connection;
import java.sql.SQLException;

@SuppressWarnings("unused")
public interface EconomyAPI {

    String formatCurrency(Currency currency, long amount);

    long getBalance(Currency currency, String playerName);

    boolean hasBalance(Currency currency, String playerName, long amount);

    EconomyTransaction createTransaction(Plugin initiator, String event, Currency currency, String player1, String player2);

    void cancelTransaction(EconomyTransaction transaction);

    void writeTransactionToDatabase(Connection connection, EconomyTransaction transaction) throws SQLException;

    void closeTransaction(EconomyTransaction transaction);
}
