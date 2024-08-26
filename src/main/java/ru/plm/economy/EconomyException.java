package ru.plm.economy;

@SuppressWarnings("unused")
public class EconomyException extends Exception {

    public static final String DESCRIPTION = "Во время обработки экономической транзакции произошла непредвиденная ошибка";

    public EconomyException() {
        super();
    }

    public EconomyException(String message) {
        super(message);
    }
}
