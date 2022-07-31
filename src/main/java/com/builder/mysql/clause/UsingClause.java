package com.builder.mysql.clause;

public class UsingClause implements Clause {
    private final StringBuilder query;
    private static final String clauseName = "Using";

    public UsingClause() {
        this.query = new StringBuilder();
    }

    public UsingClause columns(String... cols) {
        String csv = String.join(", ", cols);
        query.append(csv);
        return this;
    }

    @Override
    public String getClause() {
        return clauseName + " (" + query + ")";
    }
}
