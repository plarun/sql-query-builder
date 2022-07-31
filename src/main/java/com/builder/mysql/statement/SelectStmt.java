package com.builder.mysql.statement;

import com.builder.mysql.clause.*;
import com.builder.mysql.common.Condition;
import com.builder.mysql.common.TableReference;
import com.builder.mysql.exception.EmptyColumnException;
import com.builder.mysql.exception.MissingClauseException;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public final class SelectStmt implements QueryStmt {
    private static final String clauseName = "Select";
    private final List<String> columnList;
    private boolean isDistinct;

    private FromClause fromClause;
    private WhereClause whereClause;
    private GroupClause groupClause;
    private HavingClause havingClause;
    private OrderClause orderClause;
    private LimitClause limitClause;
    private WithClause withClause;

    private StringBuilder query;

    public SelectStmt() {
        columnList = new ArrayList<>();
        isDistinct = false;
    }

    public SelectStmt columns(String... columnList) {
        this.columnList.addAll(List.of(columnList));
        return this;
    }

    public SelectStmt distinct() {
        isDistinct = true;
        return this;
    }

    public void setWithClause(WithClause withClause) {
        this.withClause = withClause;
    }

    public SelectStmt from(String tableName) {
        fromClause = new FromClause(ref -> ref.tbl(tableName));
        return this;
    }

    public SelectStmt from(String tableName, String alias) {
        fromClause = new FromClause(ref -> ref.tbl(tableName, alias));
        return this;
    }

    public SelectStmt from(Function<TableReference, TableReference> clause) {
        fromClause = new FromClause(clause);
        return this;
    }

    public SelectStmt where(Function<Condition, Condition> clause) {
        whereClause = new WhereClause(clause);
        return this;
    }

    public SelectStmt group(String... groupList) {
        groupClause = new GroupClause(groupList);
        return this;
    }

    public SelectStmt having(Function<Condition, Condition> clause) {
        havingClause = new HavingClause(clause);
        return this;
    }

    public SelectStmt order(String... orderList) {
        orderClause = new OrderClause(orderList);
        return this;
    }

    public SelectStmt limit(int lmt, int offset) {
        limitClause = new LimitClause(lmt, offset);
        return this;
    }

    public SelectStmt limit(int lmt) {
        limitClause = new LimitClause(lmt);
        return this;
    }

    private void build() throws MissingClauseException, EmptyColumnException {
        query = new StringBuilder();

        if (withClause != null)
            query.append(withClause.getClause()).append(" ");

        query.append(clauseName).append(" ");

        if (isDistinct)
            query.append("Distinct ");

        if (columnList.size() == 0)
            throw new EmptyColumnException();
        String csv = String.join(", ", columnList);
        query.append(csv);

        if (fromClause == null)
            throw new MissingClauseException("From clause missing");
        query.append(" ").append(fromClause.getClause());

        if (whereClause != null) {
            query.append(" ").append(whereClause.getClause());
        }

        if (groupClause != null) {
            query.append(" ").append(groupClause.getClause());
        }

        if (havingClause != null) {
            query.append(" ").append(havingClause.getClause());
        }

        if (orderClause != null) {
            query.append(" ").append(orderClause.getClause());
        }

        if (limitClause != null) {
            query.append(" ").append(limitClause.getClause());
        }
    }

    @Override
    public String getQuery() throws MissingClauseException, EmptyColumnException {
        build();
        return query.toString();
    }
}
