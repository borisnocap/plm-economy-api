package ru.plm.economy;

import java.util.ArrayList;
import java.util.UUID;

public class EconomyTransaction {
    private final UUID uuid;
    private final ArrayList<EconomyTransactionOperation> balanceUpdates = new ArrayList<>();
    private boolean closed;

    public EconomyTransaction() {
        uuid = UUID.randomUUID();
    }

    public UUID getUUID() {
        return uuid;
    }

    public ArrayList<EconomyTransactionOperation> getOperations() {
        return balanceUpdates;
    }

    public boolean isClosed() {
        return closed;
    }
    public void close() {
        this.closed = true;
    }
}
