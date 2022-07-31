package com.builder.mysql.exception;

public class MissingTableException extends Exception {
    public MissingTableException() {
        super("table missing");
    }

    public MissingTableException(String msg) {
        super(msg);
    }
}
