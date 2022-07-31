package com.builder.mysql.exception;

public class MissingClauseException extends Exception {
    public MissingClauseException() {
        super("clause missing");
    }

    public MissingClauseException(String msg) {
        super(msg);
    }
}
