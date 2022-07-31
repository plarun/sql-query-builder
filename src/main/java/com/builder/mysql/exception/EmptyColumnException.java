package com.builder.mysql.exception;

public class EmptyColumnException extends Exception {
    public EmptyColumnException() {
        super("empty column");
    }

    public EmptyColumnException(String msg) {
        super(msg);
    }
}
