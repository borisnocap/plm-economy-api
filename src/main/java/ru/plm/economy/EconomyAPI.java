package ru.plm.economy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

public interface EconomyAPI {

    HashMap<UUID, EconomyTransaction> economyTransactions = new HashMap<>();
    HashMap<String, Long> playersBucksBalances = new HashMap<>();
    HashMap<String, Long> playersPlumcoinsBalances = new HashMap<>();

    default UUID createEconomyTransaction() {
        EconomyTransaction economyTransaction = new EconomyTransaction();
        economyTransactions.put(economyTransaction.getUUID(), economyTransaction);
        return economyTransaction.getUUID();
    }

    default void closeEconomyTransaction(UUID uuid) throws EconomyException {
        EconomyTransaction economyTransaction = economyTransactions.remove(uuid);
        if (economyTransaction ==  null) throw new EconomyException("Отсутствует экономическая транзакция.");
        if (economyTransaction.isClosed()) throw new EconomyException("Экономическая транзакция уже закрыта.");
        economyTransaction.getBalanceUpdates().forEach(balanceUpdate -> {
            String player = balanceUpdate.getPlayerName();
            switch (balanceUpdate.getCurrency()) {
                case BUCKS -> {
                    long oldBalance = playersBucksBalances.get(player);
                    long newBalance = oldBalance + balanceUpdate.getAmount();
                    playersBucksBalances.put(player, newBalance);
                }
                case PLUMCOINS -> {
                    long oldBalance = playersPlumcoinsBalances.get(player);
                    long newBalance = oldBalance + balanceUpdate.getAmount();
                    playersPlumcoinsBalances.put(player, newBalance);
                }
            }
        });
        economyTransaction.close();
    }

    String formatBucks(long amount);

    boolean hasBucksAccount(String playerName);

    Long getBucksBalance(String playerName);

    boolean hasBucks(String playerName, long amount);

    default void setBucksBalance(UUID transactionUUID, String playerName, long newBalance) throws EconomyException {
        if (!economyTransactions.containsKey(transactionUUID)) throw new EconomyException("Отсутствует экономическая транзакция.");
        playersBucksBalances.put(playerName, newBalance);
    }

    void withdrawBucks(Connection conn, PreparedStatement ps, String playerName, long amount) throws SQLException;

    void depositBucks(Connection conn, PreparedStatement ps, String playerName, long amount) throws SQLException;

    String formatPlumcoins(long amount);

    boolean hasPlumcoinsAccount(String playerName);

    Long getPlumcoinsBalance(String playerName);

    boolean hasPlumcoins(String playerName, long amount);

    default void setPlumcoinsBalance(UUID transactionUUID, String playerName, long newBalance) throws EconomyException {
        if (!economyTransactions.containsKey(transactionUUID)) throw new EconomyException("Отсутствует экономическая транзакция.");
        playersPlumcoinsBalances.put(playerName, newBalance);
    }

    void withdrawPlumcoins(Connection conn, PreparedStatement ps, String playerName, long amount) throws SQLException;

    void depositPlumcoins(Connection conn, PreparedStatement ps, String playerName, long amount) throws SQLException;
}
