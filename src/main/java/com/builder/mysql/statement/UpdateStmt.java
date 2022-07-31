package com.builder.mysql.statement;

import com.builder.mysql.clause.LimitClause;
import com.builder.mysql.clause.OrderClause;
import com.builder.mysql.clause.WithClause;
import com.builder.mysql.common.Condition;
import com.builder.mysql.clause.WhereClause;
import com.builder.mysql.common.Table;
import com.builder.mysql.common.TableReference;
import com.builder.mysql.exception.EmptyColumnException;
import com.builder.mysql.exception.MissingClauseException;
import com.builder.mysql.exception.MissingTableException;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public final class UpdateStmt implements QueryStmt {
    private static final String clauseName = "Update";

    private Table table;
    private TableReference tableReference;
    private final List<String> columns;

    private WhereClause whereClause;
    private OrderClause orderClause;
    private LimitClause limitClause;
    private WithClause withClause;

    private StringBuilder query;

    public UpdateStmt() {
        this.columns = new ArrayList<>();
    }

    public UpdateStmt(WithClause withClause) {
        columns = new ArrayList<>();
        this.withClause = withClause;
    }

    public UpdateStmt table(String tableName) {
        this.table = new Table(tableName);
        return this;
    }

    public UpdateStmt table(Function<TableReference, TableReference> clause) {
        this.tableReference = clause.apply(new TableReference());
        return this;
    }

    public UpdateStmt set(String... columns) {
        this.columns.addAll(List.of(columns));
        return this;
    }

    public UpdateStmt where(Function<Condition, Condition> clause) {
        whereClause = new WhereClause(clause);
        return this;
    }

    public UpdateStmt order(String... orderList) {
        orderClause = new OrderClause(orderList);
        return this;
    }

    public UpdateStmt limit(int lmt, int offset) {
        limitClause = new LimitClause(lmt, offset);
        return this;
    }

    public UpdateStmt limit(int lmt) {
        limitClause = new LimitClause(lmt);
        return this;
    }

    private void build() throws MissingTableException, MissingClauseException, EmptyColumnException {
        query = new StringBuilder();

        if (withClause != null)
            query.append(withClause.getClause()).append(" ");

        if (table != null) {
            query.append(clauseName).append(" ").append(table).append(" Set ");
        } else if (tableReference != null) {
            query.append(clauseName).append(" ").append(tableReference.getQuery()).append(" Set ");
        } else {
            throw new MissingTableException();
        }

        for (int c = 0; c < columns.size(); ++c) {
            query.append(columns.get(c)).append(" = ?");
            if (c != columns.size() - 1)
                query.append(", ");
        }

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
    public String getQuery() throws MissingTableException, MissingClauseException, EmptyColumnException {
        build();
        return query.toString();
    }
}
