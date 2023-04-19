package ru.plm.economy;

public class EconomyTransactionOperation {
    public enum Currency {
        BUCKS,
        PLUMCOINS
    }

    public enum UpdateType {
        DEPOSIT,
        WITHDRAW
    }

    private final String playerName;
    private final Currency currency;
    private final long amount;

    public EconomyTransactionOperation(String playerName, Currency currency, UpdateType updateType, long amount) {
        this.playerName = playerName;
        this.currency = currency;
        if (updateType == UpdateType.WITHDRAW) {
            this.amount = amount * -1;
        } else {
            this.amount = amount;
        }
    }

    public String getPlayerName() {
        return playerName;
    }

    public Currency getCurrency() {
        return currency;
    }

    public long getAmount() {
        return amount;
    }
}
