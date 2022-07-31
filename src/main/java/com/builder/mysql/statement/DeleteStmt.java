package com.builder.mysql.statement;

import com.builder.mysql.clause.*;
import com.builder.mysql.common.Condition;
import com.builder.mysql.common.TableReference;
import com.builder.mysql.exception.MissingClauseException;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class DeleteStmt implements QueryStmt {
    private static final String clauseName = "Delete";
    private final List<String> columns;

    private FromClause fromClause;
    private WhereClause whereClause;
    private OrderClause orderClause;
    private LimitClause limitClause;

    StringBuilder query = new StringBuilder();

    public DeleteStmt() {
        columns = new ArrayList<>();
    }

    public DeleteStmt ref(String... columnList) {
        this.columns.addAll(List.of(columnList));
        return this;
    }

    public DeleteStmt from(Function<TableReference, TableReference> clause) {
        fromClause = new FromClause(clause);
        return this;
    }

    public DeleteStmt where(Function<Condition, Condition> clause) {
        whereClause = new WhereClause(clause);
        return this;
    }

    public DeleteStmt order(String... orderList) {
        orderClause = new OrderClause(orderList);
        return this;
    }

    public DeleteStmt limit(int lmt, int offset) {
        limitClause = new LimitClause(lmt, offset);
        return this;
    }

    public DeleteStmt limit(int lmt) {
        limitClause = new LimitClause(lmt);
        return this;
    }

    private void build() throws MissingClauseException {
        query.append(clauseName);

        if (columns.size() > 0) {
            query.append(" ");
            for (int c = 0; c < columns.size(); ++c) {
                query.append(columns.get(c));
                if (c != columns.size() - 1)
                    query.append(", ");
            }
        }

        if (fromClause == null)
            throw new MissingClauseException("from clause is missing");
        query.append(" ").append(fromClause.getClause());

        if (whereClause != null) {
            query.append(" ").append(whereClause.getClause());
        }

        if (orderClause != null) {
            query.append(" ").append(orderClause.getClause());
        }

        if (limitClause != null) {
            query.append(" ").append(limitClause.getClause());
        }
    }

    @Override
    public String getQuery() throws MissingClauseException {
        build();
        return query.toString();
    }
}
