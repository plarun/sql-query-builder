package com.builder.mysql.clause;

public class OnClause implements Clause {
    private final StringBuilder query;
    private static final String clauseName = "On";

    public OnClause() {
        this.query = new StringBuilder();
    }

    public OnClause and() {
        query.append(" And ");
        return this;
    }

    public OnClause or() {
        query.append(" Or ");
        return this;
    }

    public OnClause eq(String column1, String column2) {
        add("=", column1, column2);
        return this;
    }

    public OnClause notEq(String column1, String column2) {
        add("<>", column1, column2);
        return this;
    }

    private void add(String symbol, String column1, String column2) {
        query.append(column1)
                .append(" ")
                .append(symbol)
                .append(" ")
                .append(column2);
    }

    @Override
    public String getClause() {
        return clauseName + " (" + query + ")";
    }
}
