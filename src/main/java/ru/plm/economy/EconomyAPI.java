package ru.plm.economy;

import org.bukkit.plugin.Plugin;

import java.sql.Connection;
import java.sql.SQLException;

@SuppressWarnings("unused")
public interface EconomyAPI {

    String formatCurrency(Currency currency, long amount);

    long getBalance(Currency currency, String playerName);

    boolean hasBalance(Currency currency, String playerName, long amount);

    EconomyTransaction createTransaction(Plugin initiator, String event, Currency currency, String... participants);

    boolean cancelTransaction(EconomyTransaction transaction);

    void writeTransactionToDatabase(Connection connection, EconomyTransaction transaction) throws SQLException, EconomyException;

    void validateTransaction(EconomyTransaction transaction);

    void closeTransaction(EconomyTransaction transaction);
}
