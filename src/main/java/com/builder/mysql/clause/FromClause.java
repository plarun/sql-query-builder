package com.builder.mysql.clause;

import com.builder.mysql.common.TableReference;

import java.util.function.Function;

public class FromClause implements Clause {
    private final TableReference tableReference;
    private final Function<TableReference, TableReference> clause;
    private static final String clauseName = "From";

    public FromClause(Function<TableReference, TableReference> clause) {
        this.tableReference = new TableReference();
        this.clause = clause;
    }

    @Override
    public String getClause() {
        return clauseName + " " + clause.apply(tableReference).getQuery();
    }
}
