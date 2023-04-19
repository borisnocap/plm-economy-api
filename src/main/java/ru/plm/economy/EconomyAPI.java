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

    // Создание новой экономической транзакции.
    // Взаимодействие с балансами игроков возможно только при наличии экономической транзакции, внутрь которой
    // записываются все выполняемые операции.
    default UUID createEconomyTransaction() {
        EconomyTransaction economyTransaction = new EconomyTransaction();
        economyTransactions.put(economyTransaction.getUUID(), economyTransaction);
        return economyTransaction.getUUID();
    }

    // Отмена экономической транзакции.
    // В случае вызова этого метода экономическая транзакция закрывается и удаляется без выполнения записанных
    // в нее операций (изменений балансов игроков не будут применены к кэшу).
    default void cancelEconomyTransaction(UUID uuid) throws EconomyException {
        EconomyTransaction economyTransaction = economyTransactions.remove(uuid);
        if (economyTransaction ==  null) throw new EconomyException("Экономическая транзакция не найдена.");
        if (!economyTransaction.isClosed()) economyTransaction.close();
    }

    // Закрытие существующей экономической транзакции.
    // После закрытия транзакции все записанные в нее операции с балансом применяются к кэшу плагина.
    default void closeEconomyTransaction(UUID uuid) throws EconomyException {
        EconomyTransaction economyTransaction = economyTransactions.remove(uuid);
        if (economyTransaction ==  null) throw new EconomyException("Отсутствует экономическая транзакция.");
        if (economyTransaction.isClosed()) throw new EconomyException("Экономическая транзакция уже закрыта.");
        economyTransaction.close();
        economyTransaction.getOperations().forEach(operation -> {
            String player = operation.getPlayerName();
            switch (operation.getCurrency()) {
                case BUCKS -> {
                    long oldBalance = playersBucksBalances.get(player);
                    long newBalance = oldBalance + operation.getAmount();
                    playersBucksBalances.put(player, newBalance);
                }
                case PLUMCOINS -> {
                    long oldBalance = playersPlumcoinsBalances.get(player);
                    long newBalance = oldBalance + operation.getAmount();
                    playersPlumcoinsBalances.put(player, newBalance);
                }
            }
        });
    }

    String formatBucks(long amount);

    boolean hasBucksAccount(String playerName);

    long getBucksBalance(String playerName);

    boolean hasBucks(String playerName, long amount);

    // Сохранение баланса баксов игрока в кэше плагина (используется только после старта плагина).
    // Метод выбросит ошибку, если вызвать его, когда баланс игрока уже находится в кэше.
    default void setBucksBalance(UUID txUUID, String playerName, long balance) throws EconomyException {
        if (!economyTransactions.containsKey(txUUID)) throw new EconomyException("Отсутствует экономическая транзакция.");
        if (playersBucksBalances.containsKey(playerName)) throw new EconomyException("Запрещено устанавливать баланс игрока, если баланс уже был инициализирован.");
        EconomyTransactionOperation operation = new EconomyTransactionOperation(playerName, EconomyTransactionOperation.Currency.BUCKS, EconomyTransactionOperation.UpdateType.DEPOSIT, balance);
        economyTransactions.get(txUUID).getOperations().add(operation);
    }

    // Начисление баксов игроку.
    // Начисление должно быть записано в БД в виде применения апдейта (executeUpdate();) к SQL-транзакции (опция setAutoCommit(false);).
    void depositBucks(Connection conn, PreparedStatement ps, UUID transactionUUID, String playerName, long amount) throws SQLException;

    // Списание баксов у игрока.
    // Списание должно быть записано в БД в виде применения апдейта (executeUpdate();) к SQL-транзакции (опция setAutoCommit(false);).
    void withdrawBucks(Connection conn, PreparedStatement ps, UUID transactionUUID, String playerName, long amount) throws SQLException;

    String formatPlumcoins(long amount);

    boolean hasPlumcoinsAccount(String playerName);

    long getPlumcoinsBalance(String playerName);

    boolean hasPlumcoins(String playerName, long amount);

    // Сохранение баланса плюмкоинов игрока в кэше плагина (используется только после старта плагина).
    // Метод выбросит ошибку, если вызвать его, когда баланс игрока уже находится в кэше.
    default void setPlumcoinsBalance(UUID txUUID, String playerName, long balance) throws EconomyException {
        if (!economyTransactions.containsKey(txUUID)) throw new EconomyException("Отсутствует экономическая транзакция.");
        if (playersPlumcoinsBalances.containsKey(playerName)) throw new EconomyException("Запрещено устанавливать баланс игрока, если баланс уже был инициализирован.");
        EconomyTransactionOperation operation = new EconomyTransactionOperation(playerName, EconomyTransactionOperation.Currency.PLUMCOINS, EconomyTransactionOperation.UpdateType.DEPOSIT, balance);
        economyTransactions.get(txUUID).getOperations().add(operation);
    }

    // Начисление плюмкоинов игроку.
    // Начисление должно быть записано в БД в виде применения апдейта (executeUpdate();) к SQL-транзакции (опция setAutoCommit(false);).
    void depositPlumcoins(Connection conn, PreparedStatement ps, UUID transactionUUID, String playerName, long amount) throws SQLException;


    // Списание плюмкоинов у игрока.
    // Списание должно быть записано в БД в виде применения апдейта (executeUpdate();) к SQL-транзакции (опция setAutoCommit(false);).
    void withdrawPlumcoins(Connection conn, PreparedStatement ps, UUID transactionUUID, String playerName, long amount) throws SQLException;
}
