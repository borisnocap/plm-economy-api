package ru.plm.economy;

public interface EconomyTransaction {

    void addWithdrawOperation(String playerName, long amount);

    void addDepositOperation(String playerName, long amount);
}
