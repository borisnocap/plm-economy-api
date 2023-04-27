package ru.plm.economy;

public interface Transaction {

    void addWithdrawOperation(String playerName, long amount) throws EconomyException;

    void addDepositOperation(String playerName, long amount) throws EconomyException;
}
