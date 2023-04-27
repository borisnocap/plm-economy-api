package ru.plm.economy;

public interface EconomyTransaction {

    void addWithdrawOperation(String playerName, long amount) throws EconomyException;

    void addDepositOperation(String playerName, long amount) throws EconomyException;
}
