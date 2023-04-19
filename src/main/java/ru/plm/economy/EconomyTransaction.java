package ru.plm.economy;

import java.util.ArrayList;
import java.util.UUID;

public class EconomyTransaction {
    private final UUID uuid;
    private final ArrayList<BalanceUpdate> balanceUpdates = new ArrayList<>();
    private boolean closed;

    public EconomyTransaction() {
        uuid = UUID.randomUUID();
    }

    public UUID getUUID() {
        return uuid;
    }

    public ArrayList<BalanceUpdate> getBalanceUpdates() {
        return balanceUpdates;
    }

    public boolean isClosed() {
        return closed;
    }
    public void close() {
        this.closed = true;
    }
}
