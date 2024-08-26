package ru.plm.economy;

@SuppressWarnings("unused")
public interface EconomyTransaction {

    boolean isNotInitialized();

    String getInitializationErrorMessage();

    /**
     * Добавить в экономическую транзакцию операцию по списанию баланса
     * @param playerName игрок, у которого нужно списать баланс (должен быть участником транзакции)
     * @param amount количество списываемой валюты.
     * @throws EconomyException
     * Если игрок не является участником транзакции.
     * Если amount меньше 0.
     * Если текущий калькулируемый (по результатам предыдущих операций в транзакции) баланс игрока меньше, чем amount.
     */
    void withdraw(String playerName, long amount) throws EconomyException;

    /**
     * Добавить в экономическую транзакцию операцию по зачислению баланса.
     * @param playerName игрок, которому нужно начислить баланс (должен быть участником транзакции).
     * @param amount количество зачисляемой валюты.
     * @throws EconomyException
     * Если игрок не является участником транзакции.
     * Если amount меньше 0.
     */
    void deposit(String playerName, long amount) throws EconomyException;
}
