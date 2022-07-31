package com.builder.mysql.common;

public class Condition {
    private final StringBuilder origQuery;
    private StringBuilder query;

    public Condition() {
        this.origQuery = new StringBuilder();
        this.query = new StringBuilder();
    }

    public String getQuery() {
        if (origQuery.length() != 0)
            wrap();
        else if (query.length() != 0)
            flush();
        return origQuery.toString();
    }

    private void flush() {
        origQuery.append(query.toString());
        query = new StringBuilder();
    }

    private void wrap() {
        origQuery.append("(");
        flush();
        origQuery.append(")");
    }

    public Condition andWrap() {
        wrap();
        origQuery.append(" And ");
        return this;
    }

    public Condition orWrap() {
        wrap();
        origQuery.append(" Or ");
        return this;
    }

    public Condition and() {
        query.append(" And ");
        return this;
    }

    public Condition or() {
        query.append(" Or ");
        return this;
    }

    private void add(String symbol, String left) {
        query.append(left)
                .append(" ")
                .append(symbol)
                .append(" ?");
    }

    private void add(String symbol, String left, String right) {
        query.append(left)
                .append(" ")
                .append(symbol)
                .append(" ")
                .append(right);
    }

    public Condition isTrue(String column) {
        query.append(column);
        query.append(" Is TRUE");
        return this;
    }

    public Condition isFalse(String column) {
        query.append(column);
        query.append(" Is FALSE");
        return this;
    }

    public Condition isNull(String column) {
        query.append(column);
        query.append(" Is NULL");
        return this;
    }

    public Condition isNotNull(String column) {
        query.append(column);
        query.append(" Is Not NULL");
        return this;
    }

    public Condition in(String column, int valuesCount) {
        query.append(column)
                .append(" In (");
        for (int v = 0; v < valuesCount; ++v) {
            query.append("?");
            if (v != valuesCount - 1)
                query.append(",");
        }
        query.append(")");
        return this;
    }

    public Condition notIn(String column, int valuesCount) {
        query.append(column)
                .append(" Not In (");
        for (int v = 0; v < valuesCount; ++v) {
            query.append("?");
            if (v != valuesCount - 1)
                query.append(",");
        }
        query.append(")");
        return this;
    }

    public Condition like(String column) {
        add("Like", column);
        return this;
    }

    public Condition eq(String column) {
        add("=", column);
        return this;
    }

    public Condition eq(String left, String right) {
        add("=", left, right);
        return this;
    }

    public Condition notEq(String column) {
        add("<>", column);
        return this;
    }

    public Condition notEq(String left, String right) {
        add("<>", left, right);
        return this;
    }

    public Condition lt(String column) {
        add("<", column);
        return this;
    }

    public Condition ltEq(String column) {
        add("<=", column);
        return this;
    }

    public Condition gt(String column) {
        add(">", column);
        return this;
    }

    public Condition gtEq(String column) {
        add(">=", column);
        return this;
    }

    public Condition between(String column) {
        query.append(column).append(" Between ? And ?");
        return this;
    }
}
