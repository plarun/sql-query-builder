package com.builder.mysql.common;

import java.util.function.Function;

public class JoinCondition {
    private final StringBuilder query;

    public JoinCondition() {
        this.query = new StringBuilder();
    }

    public String getQuery() {
        return query.toString();
    }

    public JoinCondition and() {
        query.append(" And ");
        return this;
    }

    public JoinCondition or() {
        query.append(" Or ");
        return this;
    }

    private void add(String symbol, String column1, String column2) {
        query.append(column1)
                .append(" ")
                .append(symbol)
                .append(" ")
                .append(column2);
    }

    public JoinCondition eq(String column1, String column2) {
        add("=", column1, column2);
        return this;
    }

    public JoinCondition notEq(String column1, String column2) {
        add("<>", column1, column2);
        return this;
    }

    public JoinCondition on(Function<JoinCondition, JoinCondition> clause) {
        JoinCondition condition = clause.apply(new JoinCondition());
        query.append(" On (").append(condition.getQuery()).append(")");
        return this;
    }

    public JoinCondition using(String... columns) {
        query.append(" Using (");
        String csv = String.join(", ", columns);
        query.append(csv);
        query.append(")");
        return this;
    }
}
