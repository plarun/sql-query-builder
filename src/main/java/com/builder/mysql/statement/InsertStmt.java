package com.builder.mysql.statement;

import com.builder.mysql.common.Table;
import com.builder.mysql.exception.MissingTableException;

import java.util.ArrayList;
import java.util.List;

public final class InsertStmt implements QueryStmt {
    private static final String clauseName = "Insert";

    private Table table;
    private List<String> columns;
    private int rowCount;
    private int columnCount;
    private boolean isColumnRequired;

    private StringBuilder query;

    public InsertStmt() {
        this.rowCount = 0;
        this.columnCount = 0;
        this.columns = new ArrayList<>();
        this.isColumnRequired = false;
    }

    public InsertStmt into(String tableName) {
        this.table = new Table(tableName);
        return this;
    }

    public InsertStmt columns(String... columns) {
        this.isColumnRequired = true;
        if (this.columns == null)
            this.columns = new ArrayList<>();
        this.columns.addAll(List.of(columns));
        this.columnCount = this.columns.size();
        return this;
    }

    public InsertStmt columns(int columnCount) {
        this.isColumnRequired = false;
        this.columnCount = columnCount;
        return this;
    }

    public InsertStmt rows(int rowCount) {
        this.rowCount = rowCount;
        return this;
    }

    private void build() throws MissingTableException {
        query = new StringBuilder();

        if (table == null)
            throw new MissingTableException();
        query.append(clauseName).append(" Into ").append(table);

        if (isColumnRequired) {
            query.append(" (");
            String csv = String.join(", ", columns);
            query.append(csv);
            query.append(")");
        }

        query.append(" Values ");

        for (int r = 0; r < rowCount; ++r) {
            query.append("(");
            for (int c = 0; c < columnCount; ++c) {
                query.append("?");
                if (c != columnCount - 1)
                    query.append(", ");
            }
            query.append(")");
            if (r != rowCount - 1)
                query.append(", ");
        }
    }

    @Override
    public String getQuery() throws MissingTableException {
        build();
        return query.toString();
    }
}
