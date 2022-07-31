package com.builder.mysql.clause;

public class LimitClause implements Clause {
    private final int limit;
    private final int offset;
    private static final String clauseName = "Limit";

    public LimitClause(int limit) {
        this.limit = limit;
        this.offset = 0;
    }

    public LimitClause(int limit, int offset) {
        this.limit = limit;
        this.offset = offset;
    }

    @Override
    public String getClause() {
        StringBuilder sb = new StringBuilder();
        sb.append(clauseName).append(" ").append(limit);
        if (offset != 0)
            sb.append(", ").append(offset);
        return sb.toString();
    }
}