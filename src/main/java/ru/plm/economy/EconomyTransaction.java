package ru.plm.economy;

@SuppressWarnings("unused")
public interface EconomyTransaction {

    void addWithdrawOperation(String playerName, long amount) throws EconomyException;

    void addDepositOperation(String playerName, long amount) throws EconomyException;
}
