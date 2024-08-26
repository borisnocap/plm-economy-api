package ru.plm.economy;

import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.SQLException;

@SuppressWarnings("unused")
public interface EconomyAPI {

    /**
     *
     * @param currency форматируемая валюта (от нее зависит символ валюты).
     * @param amount количество валюты.
     * @return Возвращает строку вида "1,000,000$".
     */
    @NotNull
    String formatCurrency(Currency currency, long amount);

    long getBalance(Currency currency, String playerName);

    boolean hasBalance(Currency currency, String playerName, long amount);

    /**
     * Следует вызывать только из основного потока.
     * *
     * Если в результате вызова этого метода транзакция не была зарегистрирована в PLM-Economy, будет возвращен
     * пустой объект EconomyTransaction с полями "notInitialized = true" и "InitializationErrorMessage = ...".
     */
    @NotNull
    EconomyTransaction createTransaction(Plugin initiator, String event, Currency currency, String player1, String player2);

    /**
     * Следует вызывать только из асинхронного потока.
     * *
     * Снимает блокировку валюты с участников транзакции без применения результатов калькуляции.
     */
    void cancelTransaction(EconomyTransaction transaction);

    /**
     * Следует вызывать только из асинхронного потока.
     * *
     * Записывает результат калькуляции транзакции в базу данных (обновляет баланс участников).
     * При этом новый баланс не кэшируется. Для кэширования необходимо вызвать метод closeTransaction.
     */
    void writeTransactionToDatabase(Connection connection, EconomyTransaction transaction) throws SQLException;

    /**
     * Следует вызывать только из основного потока.
     * *
     * Применяет результат калькуляции транзакции к кэшу PLM-Economy (обновляет балансы игроков) и снимает блокировку
     * валюты с участников транзакции.
     */
    void closeTransaction(EconomyTransaction transaction);
}
