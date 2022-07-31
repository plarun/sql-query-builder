package com.builder.mysql.exception;

public class QueryBuildException extends Exception {
    public QueryBuildException() {
        super("unable to build query");
    }

    public QueryBuildException(String msg) {
        super(msg);
    }
}
