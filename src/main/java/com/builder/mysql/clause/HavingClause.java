package com.builder.mysql.clause;

import com.builder.mysql.common.Condition;

import java.util.function.Function;

public class HavingClause implements Clause {
    private final Condition condition;
    private final Function<Condition, Condition> clause;
    private static final String clauseName = "Having";

    public HavingClause(Function<Condition, Condition> clause) {
        this.condition = new Condition();
        this.clause = clause;
    }

    @Override
    public String getClause() {
        return clauseName + " " + clause.apply(condition).getQuery();
    }
}
