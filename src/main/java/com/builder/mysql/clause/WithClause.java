package com.builder.mysql.clause;

import com.builder.mysql.exception.EmptyColumnException;
import com.builder.mysql.exception.MissingClauseException;
import com.builder.mysql.statement.SelectStmt;
import com.builder.mysql.statement.UpdateStmt;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class WithClause implements Clause {
    private static final String clauseName = "With";

    private final List<WithTable> list;
    private boolean isRecursive;

    public WithClause() {
        list = new ArrayList<>();
        isRecursive = false;
    }

    private static class WithTable {
        SelectStmt subQuery;
        String name;
        String[] columns;

        WithTable(String name, SelectStmt subQuery, String[] columns) {
            this.subQuery = subQuery;
            this.name = name;
            this.columns = columns;
        }
    }

    public WithClause as(String name, Function<SelectStmt, SelectStmt> subQuery, String... columns) {
        SelectStmt selectStmt = subQuery.apply(new SelectStmt());
        list.add(new WithTable(name, selectStmt, columns));
        return this;
    }

    public WithClause recursive() {
        isRecursive = true;
        return this;
    }

    public SelectStmt select() {
        return new SelectStmt(this);
    }

    public UpdateStmt update() {
        return new UpdateStmt(this);
    }

    @Override
    public String getClause() throws MissingClauseException, EmptyColumnException {
        StringBuilder query = new StringBuilder();

        query.append(clauseName).append(" ");

        if (isRecursive)
            query.append("Recursive ");

        for (int l = 0; l < list.size(); ++l) {
            WithTable withTable = list.get(l);
            query.append(withTable.name).append(" ");
            if (withTable.columns.length > 0) {
                String csv = String.join(", ", withTable.columns);
                query.append("(").append(csv).append(") ");
            }
            query.append("As (")
                    .append(withTable.subQuery.getQuery())
                    .append(")");
            if (l != list.size() - 1)
                query.append(", ");
        }

        return query.toString();
    }
}
